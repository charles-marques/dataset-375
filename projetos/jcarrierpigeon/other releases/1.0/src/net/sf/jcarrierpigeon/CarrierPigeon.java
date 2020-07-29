/**
 * JCarrierPigeon -- A notification library
 * Copyright (c) 2010, Paulo Roberto Massa Cereda
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. Neither the name of the project's author nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS
 * OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 * TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.sf.jcarrierpigeon;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.JFrame;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;

/**
 * Provides the notification features to any {@code javax.swing.JFrame} object.
 * Currently supports multiple windows to be displayed, though it requires some
 * fine tuning on that. In order to achieve a greater effect, it's highly
 * recommended to remove the window border of the provided {@code javax.swing.JFrame}.
 * @author Paulo Roberto Massa Cereda
 * @email cereda DOT paulo AT gmail DOT com
 * @version 1.0
 */
public class CarrierPigeon implements TimingTarget {

    private static int activeWindowsBR = 0;
    private static int activeWindowsBL = 0;
    private static int activeWindowsTR = 0;
    private static int activeWindowsTL = 0;
    private static double payloadBR = 0;
    private static double payloadBL = 0;
    private static double payloadTR = 0;
    private static double payloadTL = 0;
    private double thisPayload = 0;
    private int thisWindowID;
    private WindowPosition windowPosition;
    private JFrame window;
    private double borderX, borderY;
    private double boundX, boundY;
    private double positionX, positionY;
    private int duration;
    private AnimationFrame animationFrame;
    private int frameCount;
    private Animator animatorHandler;

    /**
     * Constructor method. It basically builds the notification model according
     * to the provided parameters. Just keep in mind the provided {@code javax.swing.JFrame}
     * is <b>disposed</b> after the notification being displayed.
     * @param window The window to act as a notification. Please, remove the borders
     * in order to achieve a greater effect.
     * @param windowPosition The window position on screen. You may choose one amongst
     * four states, each one representing the screen corners.
     * @param borderX The distance in pixels the window must keep from the X axis border. If
     * the notification is right-aligned, this border will be from the right side, and so forth.
     * Usually 50 pixels or less is an acceptable value for this parameter.
     * @param borderY The distance in pixels the window must keep from the Y axis border. If
     * the notification is aligned from the top, this border will be from the top itself, and so forth.
     * Usually 50 pixels or less is an acceptable value for this parameter.
     * @param duration The animation duration in milliseconds. So if you want 2 seconds, you need
     * to multiply it by 1000; 2 seconds times 1000 = 2000 milliseconds.
     */
    public CarrierPigeon(JFrame window, WindowPosition windowPosition, int borderX, int borderY, int duration) {

        // setting some attributes
        this.windowPosition = windowPosition;
        this.window = window;
        this.borderX = borderX;
        this.borderY = borderY;

        // first calculation: determine the current payload
        this.thisPayload = this.window.getHeight() + this.borderY;

        // set the animation duration
        this.duration = duration;

        {
            // retrieve the screen resolution and set some attributes
            Rectangle rect = getScreenResolution();
            this.boundX = rect.getWidth();
            this.boundY = rect.getHeight();
        }

        // second calculation: based on the window position and the provided
        // values, calculate positions on screen and the global payload for
        // that specific region
        switch (this.windowPosition) {
            case BOTTOMRIGHT:
                this.positionX = this.boundX - (this.window.getWidth() + this.borderX);
                this.positionY = this.boundY - (this.window.getHeight() + this.borderY);
                CarrierPigeon.payloadBR = CarrierPigeon.payloadBR + this.thisPayload;
                break;
            case BOTTOMLEFT:
                this.positionX = this.borderX;
                this.positionY = this.boundY - (this.window.getHeight() + this.borderY);
                CarrierPigeon.payloadBL = CarrierPigeon.payloadBL + this.thisPayload;
                break;
            case TOPRIGHT:
                this.positionX = this.boundX - (this.window.getWidth() + this.borderX);
                this.positionY = this.borderY;
                CarrierPigeon.payloadTR = CarrierPigeon.payloadTR + this.thisPayload;
                break;
            case TOPLEFT:
                this.positionX = this.borderX;
                this.positionY = this.borderY;
                CarrierPigeon.payloadTL = CarrierPigeon.payloadTL + this.thisPayload;
                break;
        }
    }

    /**
     * Calculates the screen size.
     * @return A {@code java.awt.Rectangle} with the exact size of the screen.
     */
    private Rectangle getScreenResolution() {
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        return environment.getMaximumWindowBounds();
    }

    /**
     * Calculates the current window position based on the Y axis and the
     * fraction of elapsed time.
     * @param x Fraction of elapsed time. This value is on a continuum interval, 0 <= x <= 1.
     * @return An {@code int} value representing the current Y value according to the
     * elapsed time.
     */
    private int calculateCurrentPositionOnY(float x) {
        
        int result = 0;

        // checks if the animation is the beginning
        if (animationFrame == AnimationFrame.ONSHOW) {

            // calculates the position, using the following math function
            switch (windowPosition) {
                case BOTTOMRIGHT:
                case BOTTOMLEFT:
                    result = (int) (positionY + ((boundY - positionY) * (1 - x)));
                    break;
                case TOPRIGHT:
                case TOPLEFT:
                    result = (int) (positionY - ((window.getHeight() + borderY) * (1 - x)));
                    break;
            }

        } else {

            // animation is now closing
            if (animationFrame == AnimationFrame.ONCLOSE) {

                // calculates the position, now using the inverse math function
                switch (windowPosition) {
                    case BOTTOMRIGHT:
                    case BOTTOMLEFT:
                        result = (int) (positionY + ((boundY - positionY) * (x)));
                        break;
                    case TOPRIGHT:
                    case TOPLEFT:
                        result = (int) (positionY - ((window.getHeight() + borderY) * (x)));
                        break;
                }

            } else {
                
                // seems animation is now on display, then just return the very
                // same position
                result = (int) positionY;
            }

        }

        return result;
    }

    /**
     * Calculates the current window position based on the X axis and the
     * fraction of elapsed time.
     * @param x Fraction of elapsed time. This value is on a continuum interval, 0 <= x <= 1.
     * @return An {@code int} value representing the current X value according to the
     * elapsed time.
     */
    private int calculateCurrentPositionOnX(float x) {

        int result = 0;

        // checks if the animation is the beginning
        if (animationFrame == AnimationFrame.ONSHOW) {

            // calculates the position, using the following math function
            switch (windowPosition) {
                case BOTTOMRIGHT:
                case TOPRIGHT:
                    result = (int) (positionX + ((boundX - positionX) * (1 - x)));
                    break;
                case BOTTOMLEFT:
                case TOPLEFT:
                    result = (int) (positionX - ((window.getWidth() + borderX) * (1 - x)));
                    break;
            }

        } else {

            // checks if the animation is the beginning
            if (animationFrame == AnimationFrame.ONCLOSE) {

                // calculates the position, now using the inverse math function
                switch (windowPosition) {
                    case BOTTOMRIGHT:
                    case TOPRIGHT:
                        result = (int) (positionX + ((boundX - positionX) * (x)));
                        break;
                    case BOTTOMLEFT:
                    case TOPLEFT:
                        result = (int) (positionX - ((window.getWidth() + borderX) * (x)));
                        break;
                }

            } else {

                // seems animation is now on display, then just return the very
                // same position
                result = (int) positionX;
            }

        }

        return result;
    }

    /**
     * Implements the {@code timingEvent} method from {@code org.jdesktop.animation.timing.TimingTarget}.
     * Please don't call this function directly.
     * @param f The continnum interval referring to the animation.
     */
    public void timingEvent(float f) {

        // check if this is the only window in the current window position pool
        if (thisWindowID == 1) {

            // animation is on the Y axis
            window.setBounds((int) positionX, calculateCurrentPositionOnY(f), window.getWidth(), window.getHeight());
        } else {

            // there are other windows, so animation will be on X axis
            window.setBounds(calculateCurrentPositionOnX(f), (int) positionY, window.getWidth(), window.getHeight());
        }
    }


    /**
     * Implements the {@code begin} method from {@code org.jdesktop.animation.timing.TimingTarget}.
     * This method is called before animation begins. Please don't call this function directly.
     */
    public void begin() {

        // set the animation status
        animationFrame = AnimationFrame.ONSHOW;
        frameCount = 0;

        // define some window properties
        window.setAlwaysOnTop(true);
        window.setVisible(true);

    }

    /**
     * Implements the {@code end} method from {@code org.jdesktop.animation.timing.TimingTarget}.
     * This method is called after the animation finishes. Please don't call this function directly.
     */
    public void end() {

        // animation is done, so hide and dispose window
        window.setVisible(false);
        window.dispose();

        // now it's time to remove this notification from the pool
        int thisActiveWindow = 0;

        // check the window position
        switch (windowPosition) {
            case BOTTOMRIGHT:
                CarrierPigeon.activeWindowsBR--;
                thisActiveWindow = CarrierPigeon.activeWindowsBR;
                break;
            case BOTTOMLEFT:
                CarrierPigeon.activeWindowsBL--;
                thisActiveWindow = CarrierPigeon.activeWindowsBL;
                break;
            case TOPRIGHT:
                CarrierPigeon.activeWindowsTR--;
                thisActiveWindow = CarrierPigeon.activeWindowsTR;
                break;
            case TOPLEFT:
                CarrierPigeon.activeWindowsTL--;
                thisActiveWindow = CarrierPigeon.activeWindowsTL;
                break;
        }

        // if there are other windows
        if (thisActiveWindow > 1) {

            // remove a partial payload from the payload pool
            switch (windowPosition) {
                case BOTTOMRIGHT:
                    CarrierPigeon.payloadBR = CarrierPigeon.payloadBR - borderY + (thisPayload - window.getHeight());
                    break;
                case BOTTOMLEFT:
                    CarrierPigeon.payloadBL = CarrierPigeon.payloadBL - borderY + (thisPayload - window.getHeight());
                    break;
                case TOPRIGHT:
                    CarrierPigeon.payloadTR = CarrierPigeon.payloadTR - borderY + (thisPayload - window.getHeight());
                    break;
                case TOPLEFT:
                    CarrierPigeon.payloadTL = CarrierPigeon.payloadTL - borderY + (thisPayload - window.getHeight());
                    break;
            }

        } else {

            // this is the only window, so let's remove all the payload
            switch (windowPosition) {
                case BOTTOMRIGHT:
                    CarrierPigeon.payloadBR = CarrierPigeon.payloadBR - thisPayload;
                    break;
                case BOTTOMLEFT:
                    CarrierPigeon.payloadBL = CarrierPigeon.payloadBL - thisPayload;
                    break;
                case TOPRIGHT:
                    CarrierPigeon.payloadTR = CarrierPigeon.payloadTR - thisPayload;
                    break;
                case TOPLEFT:
                    CarrierPigeon.payloadTL = CarrierPigeon.payloadTL - thisPayload;
                    break;
            }

        }

    }

    /**
     * Implements the {@code repeat} method from {@code org.jdesktop.animation.timing.TimingTarget}.
     * This function is called on every animation repetition. Please don't call this function directly.
     */
    public void repeat() {

        // changes animation state
        frameCount++;
        switch (frameCount) {
            case 1:
                animationFrame = AnimationFrame.ONDISPLAY;
                break;
            case 2:
                animationFrame = AnimationFrame.ONCLOSE;
                break;
        }
    }

    /**
     * Performs the animation itself based on the parameters provided in the
     * constructor method. Keep in mind this method is synchronized.
     */
    public synchronized void animate() {

        // checks the window position and sets a new window to the pool
        switch (windowPosition) {
            case BOTTOMRIGHT:
                CarrierPigeon.activeWindowsBR++;
                thisWindowID = CarrierPigeon.activeWindowsBR;
                break;
            case BOTTOMLEFT:
                CarrierPigeon.activeWindowsBL++;
                thisWindowID = CarrierPigeon.activeWindowsBL;
                break;
            case TOPRIGHT:
                CarrierPigeon.activeWindowsTR++;
                thisWindowID = CarrierPigeon.activeWindowsTR;
                break;
            case TOPLEFT:
                CarrierPigeon.activeWindowsTL++;
                thisWindowID = CarrierPigeon.activeWindowsTL;
                break;
        }

        // defines the animator handler from Timing Framework
        animatorHandler = new Animator(duration, 3, Animator.RepeatBehavior.LOOP, this);

        // if there are other windows
        if (thisWindowID > 1) {

            // we need to add the payload
            switch (windowPosition) {
                case BOTTOMRIGHT:
                    borderY = CarrierPigeon.payloadBR - window.getHeight();
                    break;
                case BOTTOMLEFT:
                    borderY = CarrierPigeon.payloadBL - window.getHeight();
                    break;
                case TOPRIGHT:
                    borderY = CarrierPigeon.payloadTR - window.getHeight();
                    break;
                case TOPLEFT:
                    borderY = CarrierPigeon.payloadTL - window.getHeight();
                    break;
            }

            // the recalculate all positions
            recalculate();

            // define a new position based on the X axis
            window.setBounds(calculateCurrentPositionOnX(0), (int) positionY, window.getWidth(), window.getHeight());
        } else {

            // there's only one window, so just define its new
            // position based on the Y axis
            window.setBounds((int) positionX, calculateCurrentPositionOnY(0), window.getWidth(), window.getHeight());
        }

        // start animation
        animatorHandler.start();
    }

    /**
     * Checks if the notification process is still running. It basically calls the
     * {@code isRunning} method from {@code org.jdesktop.animation.timing.Animator}.
     * @return {@code true} if the notification is still running, or {@code false}
     * otherwise.
     */
    public boolean isRunning() {
        return animatorHandler.isRunning();
    }

    /**
     * Recalculates all the variables referring to the window position
     */
    private synchronized void recalculate() {

        // perform the calculation based on the window position
        switch (this.windowPosition) {
            case BOTTOMRIGHT:
                this.positionX = this.boundX - (this.window.getWidth() + this.borderX);
                this.positionY = this.boundY - (this.window.getHeight() + this.borderY);
                break;
            case BOTTOMLEFT:
                this.positionX = this.borderX;
                this.positionY = this.boundY - (this.window.getHeight() + this.borderY);
                break;
            case TOPRIGHT:
                this.positionX = this.boundX - (this.window.getWidth() + this.borderX);
                this.positionY = this.borderY;
                break;
            case TOPLEFT:
                this.positionX = this.borderX;
                this.positionY = this.borderY;
                break;
        }
    }
}
