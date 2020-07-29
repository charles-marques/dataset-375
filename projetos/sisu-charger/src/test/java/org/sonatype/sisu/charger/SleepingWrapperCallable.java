package org.sonatype.sisu.charger;

import java.util.concurrent.Callable;

public class SleepingWrapperCallable<E>
    implements Callable<E>
{
    private final long sleepTimeMillis;

    private final Callable<E> callable;

    public SleepingWrapperCallable( final long sleepTimeMillis, final Callable<E> callable )
    {
        this.sleepTimeMillis = sleepTimeMillis;
        this.callable = callable;
    }

    @Override
    public E call()
        throws Exception
    {
        try
        {
            Thread.sleep( sleepTimeMillis );

            return callable.call();
        }
        catch ( InterruptedException e )
        {
            System.out.println( e.toString() );

            throw e;
        }
    }
}
