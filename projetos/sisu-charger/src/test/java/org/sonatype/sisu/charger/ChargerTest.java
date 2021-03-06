package org.sonatype.sisu.charger;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sonatype.sisu.charger.internal.AllArrivedChargeStrategy;
import org.sonatype.sisu.charger.internal.ChargeWrapper;
import org.sonatype.sisu.charger.internal.DefaultCharger;
import org.sonatype.sisu.charger.internal.FirstArrivedChargeStrategy;
import org.sonatype.sisu.charger.internal.FirstArrivedInOrderChargeStrategy;

import static org.hamcrest.MatcherAssert.assertThat;

public class ChargerTest
{
    private SimpleCallableExecutor executorServiceProvider;

    private Charger charger;

    @Before
    public void before()
    {
        this.executorServiceProvider = new SimpleCallableExecutor();

        this.charger = new DefaultCharger();
    }

    @After
    public void after()
    {
        this.executorServiceProvider.shutdown();
    }

    @Test
    public void testSimple()
        throws Exception
    {
        List<Callable<String>> callables = new ArrayList<Callable<String>>();

        callables.add( new HelloCallable( "Jason" ) );
        callables.add( new HelloCallableWithExceptionHandler( "Brian" ) );

        ChargeFuture<String> cf =
            charger.submit( callables, new AllArrivedChargeStrategy<String>(), executorServiceProvider );

        final List<String> result = cf.getResult();

        assertThat( "We should greet Jason and Brian", result.size(), Matchers.equalTo( 2 ) );
    }

    @Test
    public void testEmptyAllArrivedChargeStrategy()
        throws Exception
    {
        List<Callable<String>> callables = new ArrayList<Callable<String>>();

        ChargeFuture<String> cf =
            charger.submit( callables, new AllArrivedChargeStrategy<String>(), executorServiceProvider );

        final List<String> result = cf.getResult();

        assertThat( "We should greet no one", result.size() == 0 );
    }

    @Test
    public void testEmptyFirstArrivedChargeStrategy()
        throws Exception
    {
        List<Callable<String>> callables = new ArrayList<Callable<String>>();

        ChargeFuture<String> cf =
            charger.submit( callables, new FirstArrivedChargeStrategy<String>(), executorServiceProvider );

        final List<String> result = cf.getResult();

        assertThat( "We should greet no one", result.size() == 0 );
    }

    @Test
    public void testEmptyFirstArrivedInOrderChargeStrategy()
        throws Exception
    {
        List<Callable<String>> callables = new ArrayList<Callable<String>>();

        ChargeFuture<String> cf =
            charger.submit( callables, new FirstArrivedInOrderChargeStrategy<String>(), executorServiceProvider );

        final List<String> result = cf.getResult();

        assertThat( "We should greet no one", result.size() == 0 );
    }

    @Test
    public void testBailingOut()
        throws Exception
    {
        List<Callable<String>> callables = new ArrayList<Callable<String>>();

        callables.add( new BailingOutCallable<String>( true ) );
        callables.add( new HelloCallable( "Jason" ) );
        callables.add( new BailingOutCallable<String>( false ) );
        callables.add( new HelloCallableWithExceptionHandler( "Brian" ) );

        ChargeFuture<String> cf =
            charger.submit( callables, new AllArrivedChargeStrategy<String>(), executorServiceProvider );

        final List<String> result = cf.getResult();

        assertThat( "We should greet Jason and Brian", result.size() == 2 );
    }

    @Test
    public void testFailing()
        throws Exception
    {
        List<Callable<String>> callables = new ArrayList<Callable<String>>();

        callables.add( new FailingCallable<String>( new IOException( "I failed!" ) ) );
        callables.add( new HelloCallable( "Jason" ) );
        callables.add( new BailingOutCallable<String>( false ) );
        callables.add( new HelloCallableWithExceptionHandler( "Brian" ) );

        ChargeFuture<String> cf =
            charger.submit( callables, new AllArrivedChargeStrategy<String>(), executorServiceProvider );

        try
        {
            List<String> result = cf.getResult();

            assertThat( "We need to get an IOException!", false );
        }
        catch ( IOException e )
        {
            // good
        }
    }

    @Test
    public void testFailingWithHanderHandlingIt()
        throws Exception
    {
        List<Callable<String>> callables = new ArrayList<Callable<String>>();

        final SimpleExceptionHandler simpleExceptionHandler = new SimpleExceptionHandler( IOException.class );
        callables.add( new ExceptionHandlingCallable<String>( new FailingCallable<String>( new IOException(
            "I failed but should be ignored!" ) ), simpleExceptionHandler ) );
        callables.add( new HelloCallable( "Jason" ) );
        callables.add( new BailingOutCallable<String>( false ) );
        callables.add( new HelloCallableWithExceptionHandler( "Brian" ) );

        ChargeFuture<String> cf =
            charger.submit( callables, new AllArrivedChargeStrategy<String>(), executorServiceProvider );

        try
        {
            List<String> result = cf.getResult();

            // good, IOException should go unnoticed
            assertThat( "We should greet Jason and Brian", result.size() == 2 );
        }
        catch ( IOException e )
        {
            assertThat( "We must not get an IOException!", false );
        }

        assertThat( "Hander should kick in!", simpleExceptionHandler.isKickedIn() );
    }

    @Test
    public void testFailingWithHanderHandlingItWithError()
        throws Exception
    {
        List<Callable<String>> callables = new ArrayList<Callable<String>>();

        final SimpleExceptionHandler simpleExceptionHandler = new SimpleExceptionHandler( IOException.class );
        callables.add( new ExceptionHandlingCallable<String>( new FailingCallable<String>( new IOException(
            "I am handled, hence ignored!" ) ), simpleExceptionHandler ) );
        callables.add( new HelloCallable( "Jason" ) );
        callables.add( new FailingCallable<String>( new IOException( "I am not handled!" ) ) );
        callables.add( new HelloCallableWithExceptionHandler( "Brian" ) );

        ChargeFuture<String> cf =
            charger.submit( callables, new AllArrivedChargeStrategy<String>(), executorServiceProvider );

        try
        {
            List<String> result = cf.getResult();

            assertThat( "We need to get an IOException!", false );
        }
        catch ( IOException e )
        {
            // good
        }

        assertThat( "Handler should kick in!", simpleExceptionHandler.isKickedIn() );
    }

    @Test
    public void testFirstArrivedInOrderStrategyAllFineAndDandy()
        throws Exception
    {
        List<Callable<String>> callables = new ArrayList<Callable<String>>();

        callables.add( new HelloCallable( "Tinkler" ) );
        callables.add( new HelloCallableWithExceptionHandler( "Taylor" ) );
        callables.add( new HelloCallableWithExceptionHandler( "Soldier" ) );
        callables.add( new HelloCallableWithExceptionHandler( "Sailor" ) );

        ChargeFuture<String> cf =
            charger.submit( callables, new FirstArrivedInOrderChargeStrategy<String>(), executorServiceProvider );

        final List<String> result = cf.getResult();

        assertThat( "We expect to greet Tinkler but it was " + result.toString(), result.size() == 1 );
    }

    @Test
    public void testFirstArrivedInOrderStrategyAllFineAndDandyButTasksFinishInOppositeOrder()
        throws Exception
    {
        List<Callable<String>> callables = new ArrayList<Callable<String>>();

        callables.add( new SleepingWrapperCallable<String>( 2000, new HelloCallable( "Tinkler" ) ) );
        callables.add( new SleepingWrapperCallable<String>( 1500, new HelloCallableWithExceptionHandler( "Taylor" ) ) );
        callables.add( new SleepingWrapperCallable<String>( 1000, new HelloCallableWithExceptionHandler( "Soldier" ) ) );
        callables.add( new SleepingWrapperCallable<String>( 500, new HelloCallableWithExceptionHandler( "Sailor" ) ) );
        callables.add( new SleepingWrapperCallable<String>( 8000, new HelloCallable(
            "Sleepie that will be forgot about" ) ) );

        // Note: before, such a "processing" would take sum(t) which is 13sec
        // Now, that processing is max( strategy )... so
        // if strategy is "all" then max( allCallablesExecTime )
        // if strategy is "first" then max ( firstSucceedingOrFailingExecTime )

        final long submitted = System.currentTimeMillis();

        ChargeFuture<String> cf =
            charger.submit( callables, new FirstArrivedInOrderChargeStrategy<String>(), executorServiceProvider );

        final List<String> result = cf.getResult();

        final long runtime = System.currentTimeMillis() - submitted;

        assertThat( "We expect to greet Tinkler but it was " + result.toString(), result.size() == 1 );
        assertThat( "We have to finish in less than 3 seconds! We finished in " + ( runtime / 1000 ), runtime < 3000 );
    }

    @Test
    public void testAllArrivedStrategyAllFineAndDandyButTasksFinishInOppositeOrder()
        throws Exception
    {
        List<Callable<String>> callables = new ArrayList<Callable<String>>();

        callables.add( new SleepingWrapperCallable<String>( 2000, new HelloCallable( "Tinkler" ) ) );
        callables.add( new SleepingWrapperCallable<String>( 1500, new HelloCallableWithExceptionHandler( "Taylor" ) ) );
        callables.add( new SleepingWrapperCallable<String>( 1000, new HelloCallableWithExceptionHandler( "Soldier" ) ) );
        callables.add( new SleepingWrapperCallable<String>( 500, new HelloCallableWithExceptionHandler( "Sailor" ) ) );
        callables.add( new SleepingWrapperCallable<String>( 8000, new HelloCallable(
            "Sleepie that will be forgot about" ) ) );

        // Note: before, such a "processing" would take sum(t) which is 13sec
        // Now, that processing is max( strategy )... so
        // if strategy is "all" then max( allCallablesExecTime )
        // if strategy is "first" then max ( firstSucceedingOrFailingExecTime )

        final long submitted = System.currentTimeMillis();

        ChargeFuture<String> cf =
            charger.submit( callables, new AllArrivedChargeStrategy<String>(), executorServiceProvider );

        final List<String> result = cf.getResult();

        final long runtime = System.currentTimeMillis() - submitted;

        assertThat( "We expect to greet all but it was " + result.toString(), result.size() == 5 );
        assertThat( "We have to finish in less than 9 seconds! We finished in " + ( runtime / 1000 ), runtime < 9000 );
    }

    @Test
    public void testFirstArrivedInOrderStrategyAllFineAndDandyButTasksFinishInOppositeOrderWithHandledErrors()
        throws Exception
    {
        List<Callable<String>> callables = new ArrayList<Callable<String>>();

        // this will decide to bail out after 1 seconds
        callables.add( new SleepingWrapperCallable<String>( 1000, new BailingOutCallable<String>( true ) ) );
        // this one will end with handled exception
        callables.add( new ExceptionHandlingCallable<String>( new FailingCallable<String>( new IOException(
            "I am  handled!" ) ), new SimpleExceptionHandler( IOException.class ) ) );
        // this will decide to bail out after 3 seconds
        callables.add( new SleepingWrapperCallable<String>( 3000, new BailingOutCallable<String>( true ) ) );
        // this will deliver Tinkler after 2 seconds
        callables.add( new SleepingWrapperCallable<String>( 2000, new HelloCallable( "Tinkler" ) ) );
        // since strategy is "first", all the rest will be left to just finish what they do in peace, but noone will
        // wait or ask them anything
        callables.add( new SleepingWrapperCallable<String>( 1500, new HelloCallableWithExceptionHandler( "Taylor" ) ) );
        callables.add( new SleepingWrapperCallable<String>( 1000, new HelloCallableWithExceptionHandler( "Soldier" ) ) );
        callables.add( new ExceptionHandlingCallable<String>( new FailingCallable<String>( new FileNotFoundException(
            "boo" ) ), new SimpleExceptionHandler( FileNotFoundException.class, EOFException.class ) ) );
        callables.add( new SleepingWrapperCallable<String>( 500, new HelloCallableWithExceptionHandler( "Sailor" ) ) );
        callables.add( new SleepingWrapperCallable<String>( 8000, new HelloCallable(
            "Sleepie that will be forgot about" ) ) );

        // Note: before, such a "processing" would take sum(t) which is 16sec
        // Now, estimated run time should be around 3.5 sec

        final long submitted = System.currentTimeMillis();

        ChargeFuture<String> cf =
            charger.submit( callables, new FirstArrivedInOrderChargeStrategy<String>(), executorServiceProvider );

        final List<String> result = cf.getResult();

        final long runtime = System.currentTimeMillis() - submitted;

        assertThat( "We expect to greet Tinkler but it was " + result.toString(), result.size() == 1 );
        assertThat( "We have to finish in less than 3.5 seconds! We finished in " + ( runtime / 1000 ), runtime < 3500 );
    }

    @Test
    public void testFirstArrivedInOrderStrategyAllFineAndDandyButTasksFinishInOppositeOrderWithNotHandledErrors()
        throws Exception
    {
        List<Callable<String>> callables = new ArrayList<Callable<String>>();

        // this will decide to bail out after 1 seconds
        callables.add( new SleepingWrapperCallable<String>( 1000, new BailingOutCallable<String>( true ) ) );
        // this one will end with handled exception
        callables.add( new ExceptionHandlingCallable<String>( new FailingCallable<String>( new IOException(
            "I am  handled!" ) ), new SimpleExceptionHandler( IOException.class ) ) );
        // this will decide to bail out after 3 seconds
        callables.add( new SleepingWrapperCallable<String>( 3000, new BailingOutCallable<String>( true ) ) );
        // this one will end with unhandled exception, failing the whole charge
        callables.add( new ExceptionHandlingCallable<String>( new FailingCallable<String>( new IOException(
            "I am not IllegalStateException, hence am not handled!" ) ), new SimpleExceptionHandler(
            IllegalStateException.class ) ) );
        // this will deliver Tinkler after 2 seconds, but the execution should stop here
        callables.add( new SleepingWrapperCallable<String>( 2000, new HelloCallable( "Tinkler" ) ) );
        // since strategy is "first", all the rest will be left to just finish what they do in peace, but noone will
        // wait or ask them anything
        callables.add( new SleepingWrapperCallable<String>( 1500, new HelloCallableWithExceptionHandler( "Taylor" ) ) );
        callables.add( new SleepingWrapperCallable<String>( 1000, new HelloCallableWithExceptionHandler( "Soldier" ) ) );
        callables.add( new ExceptionHandlingCallable<String>( new FailingCallable<String>( new FileNotFoundException(
            "boo" ) ), new SimpleExceptionHandler( FileNotFoundException.class, EOFException.class ) ) );
        callables.add( new SleepingWrapperCallable<String>( 500, new HelloCallableWithExceptionHandler( "Sailor" ) ) );
        callables.add( new SleepingWrapperCallable<String>( 8000, new HelloCallable(
            "Sleepie that will be forgot about" ) ) );

        // Note: before, such a "processing" would take sum(t) which is 17sec
        // Now, estimated run time should be around 4 sec, that ends with an unhandled IOException

        final long submitted = System.currentTimeMillis();

        ChargeFuture<String> cf =
            charger.submit( callables, new FirstArrivedInOrderChargeStrategy<String>(), executorServiceProvider );

        List<String> result;
        try
        {
            result = cf.getResult();

            assertThat( "We expect an unhandled IOException to be thrown", false );
        }
        catch ( IOException e )
        {
            // good
        }

        final long runtime = System.currentTimeMillis() - submitted;

        assertThat( "We have to finish in less than 4 seconds! We finished in " + ( runtime / 1000 ), runtime < 4000 );
    }

    @Test
    public void testFirstArrivedStrategyLastCallableSucceedsFirst()
        throws Exception
    {
        List<Callable<String>> callables = new ArrayList<Callable<String>>();
        callables.add( new SleepingWrapperCallable<String>( 8000, new HelloCallable( "Sleepy" ) ) );
        callables.add( new SleepingWrapperCallable<String>( 4000, new HelloCallable( "Grumpy" ) ) );
        callables.add( new SleepingWrapperCallable<String>( 1000, new HelloCallable( "Sneezy" ) ) );

        final long submitted = System.currentTimeMillis();

        ChargeFuture<String> cf =
            charger.submit( callables, new FirstArrivedChargeStrategy<String>(), executorServiceProvider );

        List<String> result = cf.getResult();

        final long runtime = System.currentTimeMillis() - submitted;
        assertThat( result, Matchers.hasSize( 1 ) );
        assertThat( result, Matchers.hasItem( "hello Sneezy" ) );
        assertThat( runtime, Matchers.lessThan( 1500L ) );
    }

    @Test
    public void testFirstArrivedStrategyCallableAlreadyDoneOnFirstCheck()
        throws Exception
    {
        List<Callable<String>> callables = new ArrayList<Callable<String>>();
        callables.add( new SleepingWrapperCallable<String>( 8000, new HelloCallable( "Sleepy" ) ) );
        callables.add( new SleepingWrapperCallable<String>( 4000, new HelloCallable( "Grumpy" ) ) );
        callables.add( new SleepingWrapperCallable<String>( 100, new HelloCallable( "Sneezy" ) ) );

        final long submitted = System.currentTimeMillis();

        ChargeFuture<String> cf =
            charger.submit( callables, new FirstArrivedChargeStrategy<String>(), executorServiceProvider );

        Thread.sleep( 500 );

        List<String> result = cf.getResult();

        final long runtime = System.currentTimeMillis() - submitted;

        assertThat( result, Matchers.hasSize( 1 ) );
        assertThat( result, Matchers.hasItem( "hello Sneezy" ) );
        assertThat( runtime, Matchers.lessThan( 1000L ) );
    }

    @Test
    public void testOverload()
    {
        List<Callable<String>> callables = new ArrayList<Callable<String>>();
        for ( int i = 0; i < 10; i++ )
        {
            callables.add( new SleepingWrapperCallable<String>( 8000, new HelloCallable( "Sleepy" ) ) );
        }

        final long submitted = System.currentTimeMillis();

        try
        {
            ChargeFuture<String> cf =
                charger.submit( callables, new FirstArrivedChargeStrategy<String>(), new CallableExecutor()
                {
                    final ExecutorService pool = new ThreadPoolExecutor( 0, 5, 60L, TimeUnit.SECONDS,
                        new ArrayBlockingQueue<Runnable>( 5 ) );

                    @Override
                    public <T> Future<T> submit( Callable<T> task )
                    {
                        return pool.submit( task );
                    }
                } );
        }
        catch ( RejectedExecutionException e )
        {
            // good, we expected this
        }

        // Note: as Ben pointed out, the Java's ExecutorService implementations -- depending on their configuration --
        // will eventually throw RejectedExecutionException when they are full.
        // IMO, this is fine, and to keep library generic, we should just clearly mark that on Charger interface, since
        // the interface CallableExecutor is anyway wide open to integrator (of this lib) to put whatever it wants
        // behind it.
        // Also, in case of Nexus, there is a clear "fallback" path given for full thread-pool: fallback to "old"
        // (sequential)
        // processing!
    }
}
