package org.sonatype.sisu.charger.internal;

public class Check
{
    public static <T> T notNull( final T t, final Object message )
    {
        if ( null == t )
        {
            throw new NullPointerException( String.valueOf( message ) );
        }

        return t;
    }

    public static void argument( boolean condition, final Object message )
    {
        argument( condition, null, message );
    }

    public static <T> T argument( boolean condition, final T t, final Object message )
    {
        if ( !condition )
        {
            throw new IllegalArgumentException( String.valueOf( message ) );
        }

        return t;
    }
}
