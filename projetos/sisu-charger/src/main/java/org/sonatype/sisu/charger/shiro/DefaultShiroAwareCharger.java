package org.sonatype.sisu.charger.shiro;

import org.apache.shiro.SecurityUtils;
import org.sonatype.sisu.charger.ChargeStrategy;
import org.sonatype.sisu.charger.internal.Charge;
import org.sonatype.sisu.charger.internal.DefaultCharger;

/**
 * A Charger implementation that is capable of running in Shiro-enabled environment, and will execute the Callables
 * under same realm as invoker thread is.
 * 
 * @author cstamas
 */
public class DefaultShiroAwareCharger
    extends DefaultCharger
{
    @Override
    protected <E> Charge<E> getChargeInstance( final ChargeStrategy<E> strategy )
    {
        return new ShiroAwareCharge<E>( strategy, SecurityUtils.getSubject() );
    }
}
