package org.sonatype.sisu.charger;

import org.sonatype.sisu.charger.internal.AllArrivedChargeStrategy;
import org.sonatype.sisu.charger.internal.FirstArrivedChargeStrategy;
import org.sonatype.sisu.charger.internal.FirstArrivedInOrderChargeStrategy;

public class Builder
{
    public static <E> ChargeStrategy<E> all()
    {
        return new AllArrivedChargeStrategy<E>();
    }

    public static <E> ChargeStrategy<E> first()
    {
        return new FirstArrivedChargeStrategy<E>();
    }

    public static <E> ChargeStrategy<E> firstInOrder()
    {
        return new FirstArrivedInOrderChargeStrategy<E>();
    }
    
    

}
