/*
 * $Id: FactoryFinder.java,v 1.7 2006/03/30 00:59:38 ofung Exp $
 * $Revision: 1.7 $
 * $Date: 2006/03/30 00:59:38 $
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

import java.io.*;
import java.util.Properties;

class FactoryFinder {

    private static final String JBOSS_SAAJ_IMPL_MODULE = "org.jboss.ws.saaj-impl";
    /**
     * Creates an instance of the specified class using the specified 
     * <code>ClassLoader</code> object.
     *
     * @exception SOAPException if the given class could not be found
     *            or could not be instantiated
     */
    private static Object newInstance(String className,
                                      ClassLoader classLoader)
        throws SOAPException
    {
        try {
            Class spiClass;
            if (classLoader == null) {
                spiClass = Class.forName(className);
            } else {
                spiClass = classLoader.loadClass(className);
            }
            return spiClass.newInstance();
        } catch (ClassNotFoundException x) {
            throw new SOAPException(
                "Provider " + className + " not found", x);
        } catch (Exception x) {
            throw new SOAPException(
                "Provider " + className + " could not be instantiated: " + x,
                x);
        }
    }

    /**
     * Finds the implementation <code>Class</code> object for the given
     * factory name, or null if that fails.
     * <P>
     * This method is package private so that this code can be shared.
     *
     * @return the <code>Class</code> object of the specified message factory;
     *         or <code>null</code>
     *
     * @param factoryId             the name of the factory to find, which is
     *                              a system property
     * @exception SOAPException if there is a SOAP error
     */
    static Object find(String factoryId)
        throws SOAPException
    {
        ClassLoader classLoader;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        } catch (Exception x) {
            throw new SOAPException(x.toString(), x);
        }

        // Use the system property first
        try {
            String systemProp =
                System.getProperty( factoryId );
            if( systemProp!=null) {
                return newInstance(systemProp, classLoader);
            }
        } catch (SecurityException se) {
        }

        // try to read from $java.home/lib/jaxm.properties
        try {
            String javah=System.getProperty( "java.home" );
            String configFile = javah + File.separator +
                "lib" + File.separator + "jaxm.properties";
            File f=new File( configFile );
            if( f.exists()) {
                Properties props=new Properties();
                props.load( new FileInputStream(f));
                String factoryClassName = props.getProperty(factoryId);
                return newInstance(factoryClassName, classLoader);
            }
        } catch(Exception ex ) {
        }

        String serviceId = "META-INF/services/" + factoryId;
        // try to find services in CLASSPATH
        try {
            InputStream is=null;
            if (classLoader == null) {
                is=ClassLoader.getSystemResourceAsStream(serviceId);
            } else {
                is=classLoader.getResourceAsStream(serviceId);
            }
        
            if( is!=null ) {
                BufferedReader rd =
                    new BufferedReader(new InputStreamReader(is, "UTF-8"));
        
                String factoryClassName = rd.readLine();
                rd.close();

                if (factoryClassName != null &&
                    ! "".equals(factoryClassName)) {
                    return newInstance(factoryClassName, classLoader);
                }
            }
        } catch( Exception ex ) {
        }

        ClassLoader moduleClassLoader = null;
        try {
           Class<?> moduleClass = Class.forName("org.jboss.modules.Module");
           Class<?> moduleIdentifierClass = Class.forName("org.jboss.modules.ModuleIdentifier");
           Class<?> moduleLoaderClass = Class.forName("org.jboss.modules.ModuleLoader");
           Object moduleLoader = moduleClass.getMethod("getBootModuleLoader").invoke(null);
           Object moduleIdentifier = moduleIdentifierClass.getMethod("create", String.class).invoke(null, JBOSS_SAAJ_IMPL_MODULE);
           Object module = moduleLoaderClass.getMethod("loadModule", moduleIdentifierClass).invoke(moduleLoader, moduleIdentifier);
           moduleClassLoader = (ClassLoader)moduleClass.getMethod("getClassLoader").invoke(module);
        } catch (ClassNotFoundException e) {
           //ignore, JBoss Modules might not be available at all
        } catch (Exception e) {
           throw new SOAPException(e);
        }
        if (moduleClassLoader != null) {
           try {
              InputStream is = moduleClassLoader.getResourceAsStream(serviceId);
          
              if( is!=null ) {
                  BufferedReader rd =
                      new BufferedReader(new InputStreamReader(is, "UTF-8"));
          
                  String factoryClassName = rd.readLine();
                  rd.close();

                  if (factoryClassName != null &&
                      ! "".equals(factoryClassName)) {
                      return newInstance(factoryClassName, moduleClassLoader);
                  }
              }
          } catch( Exception ex ) {
          }
        }

        return null;
    }

    /**
     * Finds the implementation <code>Class</code> object for the given
     * factory name, or if that fails, finds the <code>Class</code> object
     * for the given fallback class name. The arguments supplied must be
     * used in order. If using the first argument is successful, the second
     * one will not be used.
     * <P>
     * This method is package private so that this code can be shared.
     *
     * @return the <code>Class</code> object of the specified message factory;
     *         may not be <code>null</code>
     *
     * @param factoryId             the name of the factory to find, which is
     *                              a system property
     * @param fallbackClassName     the implementation class name, which is
     *                              to be used only if nothing else
     *                              is found; <code>null</code> to indicate that
     *                              there is no fallback class name
     * @exception SOAPException if there is a SOAP error
     */
    static Object find(String factoryId, String fallbackClassName)
        throws SOAPException
    {

        Object obj = find(factoryId);
        if (obj != null)
            return obj;

        ClassLoader classLoader;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        } catch (Exception x) {
            throw new SOAPException(x.toString(), x);
        }

        if (fallbackClassName == null) {
            throw new SOAPException(
                "Provider for " + factoryId + " cannot be found", null);
        }

        return newInstance(fallbackClassName, classLoader);
    }
}
