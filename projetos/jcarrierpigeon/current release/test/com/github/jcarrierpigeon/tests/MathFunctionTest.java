package com.github.jcarrierpigeon.tests;

import junit.framework.*;

/**
 *
 * @author Paulo Roberto Massa Cereda
 * @email cereda DOT paulo AT gmail DOT com
 */
public class MathFunctionTest extends TestCase {

    public void testBottomBeginning() {
        assertEquals((int) (200 + ((600 - 200) * (1 - 0.0))), 600 );
    }

    public void testBottomEnd() {
        assertEquals((int) (200 + ((600 - 200) * (1 - 1.0))), 200 );
    }

    public void testUpBeginning() {
        assertEquals((int) (50 - ((350 + 50) * (1 - 0.0))), -350 );
    }

    public void testUpEnd() {
        assertEquals((int) (50 - ((350 + 50) * (1 - 1.0))), 50 );
    }

}
