/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2008, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors. 
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.picketlink.test.integration.saml2;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.SubmitButton;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

/**
 * <p>
 *   Unit test the SAML2 Global Log Out scenarios.
 * </p>
 * <p>
 *   <b>Note:</b> This test expects that a set of endpoints that are configured
 *   for the test are available. You may have to start web containers offline
 *   for the endpoints to be live.
 * </p>
 * 
 * @author Anil.Saldhana@redhat.com
 * @since Apr 8, 2010
 */
public class SAML2MixedBindingGlobalLogOutUnitTestCase
{
   String SERVICE_1_URL = System.getProperty( "SERVICE_1_URL", "http://localhost:8080/sales-post/" );
   String SERVICE_2_URL = System.getProperty( "SERVICE_2_URL", "http://localhost:8080/employee/" );
   String SERVICE_3_URL = System.getProperty( "SERVICE_3_URL", "http://localhost:8080/employee-redirect-valve/" );
   String SERVICE_4_URL = System.getProperty( "SERVICE_4_URL", "http://localhost:8080/sales-post-valve/" );

   String LOGOUT_URL = "?GLO=true";
   
   @Test
   public void testSAMLMixedBindingWithPostFirstGlobalLogOut() throws Exception
   {
      hitURLs(SERVICE_1_URL, SERVICE_2_URL, SERVICE_3_URL, SERVICE_4_URL);       
   }
   
   private void hitURLs( String url1, String url2, String url3, String url4) throws Exception
   {
      System.out.println("Trying "+ url1);
      //Sales post Application Login
      WebRequest serviceRequest1 = new GetMethodWebRequest( url1 );
      WebConversation webConversation = new WebConversation();
      
      WebResponse webResponse = webConversation.getResponse( serviceRequest1 ); 
      WebForm loginForm = webResponse.getForms()[0];
      loginForm.setParameter("j_username", "tomcat" );
      loginForm.setParameter("j_password", "tomcat" );
      SubmitButton submitButton = loginForm.getSubmitButtons()[0];
      submitButton.click(); 
      
      webResponse = webConversation.getCurrentPage();
      assertTrue( " Reached the sales index page ", webResponse.getText().contains( "SalesTool" ));
      
      //Employee post Application Login
      System.out.println("Trying "+ url2);
      webResponse = webConversation.getResponse( url2 );
      assertTrue( " Reached the employee index page ", webResponse.getText().contains( "EmployeeDashboard" ));

      //Employee Redirect Valve Application Login
      System.out.println("Trying "+ url3);
      webResponse = webConversation.getResponse( url3 );
      assertTrue( " Reached the employee index page ", webResponse.getText().contains( "EmployeeDashboard" ));
      
      //Sales Post Valve Application Login
      System.out.println("Trying "+ url4);
      webResponse = webConversation.getResponse( url4 );
      assertTrue( " Reached the employee index page ", webResponse.getText().contains( "SalesTool" ));

      //Logout from sales
      System.out.println("Trying "+ url1 + LOGOUT_URL);
      webResponse = webConversation.getResponse( url1 + LOGOUT_URL ); 
      assertTrue( "Reached logged out page", webResponse.getText().contains( "Logout" ) );
      
      //Hit the Sales Apps again
      System.out.println("Trying "+ url1);
      webResponse = webConversation.getResponse( url1 );
      assertTrue( " Reached the Login page ", webResponse.getText().contains( "Login" ));
 
      //Hit the Employee Apps again
      System.out.println("Trying "+ url2);
      webResponse = webConversation.getResponse( url2 );
      assertTrue( " Reached the Login page ", webResponse.getText().contains( "Login" ));

      //Hit the Sales Valve Apps again
      System.out.println("Trying "+ url4);
      webResponse = webConversation.getResponse( url4 );
      assertTrue( " Reached the Login page ", webResponse.getText().contains( "Login" ));

      webConversation.clearContents();
   }
}
