package org.sonatype.sisu.charger.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.sonatype.sisu.charger.ExceptionHandler;

/**
 * Charge wrapper that wraps all the Callables being submitted into a Charge. This also holds reference to
 * ExceptionHandler if any, and delegates to it, meaning, every callable has it's own ExceptionHandler too.
 * 
 * @author cstamas
 * @param <E>
 */
public class ChargeWrapper<E>
    implements Callable<E>, ExceptionHandler
{
    private final Charge<E> charge;

    private final Callable<? extends E> callable;

    private final ExceptionHandler exceptionHandler;

    private Future<E> future;

    public ChargeWrapper( final Charge<E> charge, final Callable<? extends E> callable,
                          final ExceptionHandler exceptionHandler )
    {
        this.charge = Check.notNull( charge, "Charge is null!" );
        this.callable = Check.notNull( callable, "Callable is null!" );
        this.exceptionHandler = Check.notNull( exceptionHandler, "ExceptionHander is null!" );
    }

    protected void setFuture( Future<E> future )
    {
        this.future = Check.notNull( future, "Future is null!" );
    }

    public Future<E> getFuture()
    {
        return future;
    }

    @Override
    public E call()
        throws Exception
    {
        try
        {
            if ( !charge.isDone() )
            {
                return callable.call();
            }
            else
            {
                throw new InterruptedException( "Charge itself was done before call() was made!" );
            }
        }
        finally
        {
            charge.checkIsDone( this );
        }
    }

    @Override
    public boolean handle( Exception ex )
    {
        return exceptionHandler.handle( ex );
    }
}
