/*
 * $Id: SOAPConnection.java,v 1.13 2006/03/30 00:59:40 ofung Exp $
 * $Revision: 1.13 $
 * $Date: 2006/03/30 00:59:40 $
 */

/*
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License).  You may not use this file except in
 * compliance with the License.
 * 
 * You can obtain a copy of the license at
 * https://glassfish.dev.java.net/public/CDDLv1.0.html.
 * See the License for the specific language governing
 * permissions and limitations under the License.
 * 
 * When distributing Covered Code, include this CDDL
 * Header Notice in each file and include the License file
 * at https://glassfish.dev.java.net/public/CDDLv1.0.html.
 * If applicable, add the following below the CDDL Header,
 * with the fields enclosed by brackets [] replaced by
 * you own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * Copyright 2006 Sun Microsystems Inc. All Rights Reserved
 */
package javax.xml.soap;


/**
 * A point-to-point connection that a client can use for sending messages
 * directly to a remote party (represented by a URL, for instance).
 * <p>
 * The SOAPConnection class is optional. Some implementations may
 * not implement this interface in which case the call to
 * <code>SOAPConnectionFactory.newInstance()</code> (see below) will
 * throw an <code>UnsupportedOperationException</code>.
 * <p>
 * A client can obtain a <code>SOAPConnection</code> object using a
 * {@link SOAPConnectionFactory} object as in the following example:
 * <PRE>
 *      SOAPConnectionFactory factory = SOAPConnectionFactory.newInstance();
 *      SOAPConnection con = factory.createConnection();
 * </PRE>
 * A <code>SOAPConnection</code> object can be used to send messages
 * directly to a URL following the request/response paradigm.  That is,
 * messages are sent using the method <code>call</code>, which sends the
 * message and then waits until it gets a reply.
 */
public abstract class SOAPConnection {

    /**
     * Sends the given message to the specified endpoint and blocks until
     * it has returned the response.
     *
     * @param request the <code>SOAPMessage</code> object to be sent
     * @param to an <code>Object</code> that identifies
     *         where the message should be sent. It is required to
     *         support Objects of type
     *         <code>java.lang.String</code>,
     *         <code>java.net.URL</code>, and when JAXM is present
     *         <code>javax.xml.messaging.URLEndpoint</code>
     *
     * @return the <code>SOAPMessage</code> object that is the response to the
     *         message that was sent
     * @throws SOAPException if there is a SOAP error
     */
    public abstract SOAPMessage call(SOAPMessage request,
                                     Object to) throws SOAPException;

    /**
     * Gets a message from a specific endpoint and blocks until it receives,
     *
     * @param to an <code>Object</code> that identifies where
     *                  the request should be sent. Objects of type
     *                 <code>java.lang.String</code> and
     *                 <code>java.net.URL</code> must be supported.
     *
     * @return the <code>SOAPMessage</code> object that is the response to the
     *                  get message request
     * @throws SOAPException if there is a SOAP error
     * @since SAAJ 1.3
     */
    public SOAPMessage get(Object to)
                                throws SOAPException {
        throw new UnsupportedOperationException("All subclasses of SOAPConnection must override get()");
    }
    
    /**
     * Closes this <code>SOAPConnection</code> object.
     *
     * @throws SOAPException if there is a SOAP error
     */
    public abstract void close() 
        throws SOAPException; 
}
