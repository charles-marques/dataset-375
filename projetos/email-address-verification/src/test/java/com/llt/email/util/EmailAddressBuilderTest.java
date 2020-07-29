/*
 * Created on Mar 27, 2012 by skusunam
 */
package com.llt.email.util;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.llt.email.model.EmailRequest;

public class EmailAddressBuilderTest {

    @Test
    public void shouldBuildEmailAddresses() {
        EmailAddressBuilder builder = new EmailAddressBuilder();
        
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setFirstName("john");
        emailRequest.setLastName("doe");
        emailRequest.setDomain("example.com");
        
        List<String> toEmailAddresses = builder.buildEmailToAddresses(emailRequest);
        
        Assert.assertEquals(10, toEmailAddresses.size());
    }
}
