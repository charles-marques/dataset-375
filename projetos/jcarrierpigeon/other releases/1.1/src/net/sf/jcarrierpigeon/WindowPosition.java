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

/**
 * Provides an enumeration for the window position on screen. It basically
 * contains four states:
 * <ul>
 * <li><b>BOTTOMRIGHT</b>: The window will be displayed on the bottom right
 * of the screen.</li>
 * <li><b>BOTTOMLEFT</b>: The window will be displayed on the bottom left
 * of the screen.</li>
 * <li><b>TOPRIGHT</b>: The window will be displayed on the top right of
 * the screen.</li>
 * <li><b>TOPLEFT</b>: The window will be displayed on the top left of the
 * screen.</li>
 * </ul>
 * @author Paulo Roberto Massa Cereda
 * @email cereda DOT paulo AT gmail DOT com
 * @version 1.1
 */
public enum WindowPosition {
    TOPLEFT, TOPRIGHT, BOTTOMLEFT, BOTTOMRIGHT
}
