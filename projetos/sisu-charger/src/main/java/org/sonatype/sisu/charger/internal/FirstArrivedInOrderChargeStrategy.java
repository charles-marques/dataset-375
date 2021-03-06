package org.sonatype.sisu.charger.internal;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

/**
 * ChargeStrategy for "first with payload or unhandled exception". This strategy will block as long as first Callable
 * delivers some payload or fails with unhandled exception -- making whole Charge to fail. In case of "bail out", the
 * next Callable is processed in same way, as long as there are Callables.
 * 
 * @author cstamas
 * @param <E>
 */
public class FirstArrivedInOrderChargeStrategy<E>
    extends AbstractChargeStrategy<E>
{
    @Override
    public boolean isDone( final Charge<E> charge, final ChargeWrapper<E> wrapper )
    {
        List<ChargeWrapper<E>> ammoFutures = charge.getAmmoFutures();

        for ( ChargeWrapper<E> a : ammoFutures )
        {
            final Future<E> f = a.getFuture();
            
            // did we start at all?
            if ( f != null )
            {
                if ( f.isDone() )
                {
                    try
                    {
                        if ( getFutureResult( a ) != null )
                        {
                            return true;
                        }
                    }
                    catch ( Exception e )
                    {
                        // nope, not done but failed badly
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public List<E> getResult( final Charge<E> charge )
        throws Exception
    {
        final List<ChargeWrapper<E>> futures = charge.getAmmoFutures();

        for ( ChargeWrapper<E> f : futures )
        {
            E e = getFutureResult( f );

            if ( e != null )
            {
                return Collections.singletonList( e );
            }
        }

        return Collections.emptyList();
    }
}
