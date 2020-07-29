/*
 * Created on Mar 27, 2012 by skusunam
 */
package com.llt.email.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.llt.email.model.EmailRequest;

@Component ("emailAddressBuilder")
public class EmailAddressBuilder {

    public List<String> buildEmailToAddresses(String firstName, String lastName, String domain) {
        List<String> toAddresses = new ArrayList<String>();
        
        toAddresses.add(firstName + "@" + domain);                          //john@example.com
        toAddresses.add(firstName + lastName.charAt(0) + "@" + domain);     //johnd@example.com
        toAddresses.add(firstName.charAt(0) + lastName + "@" + domain);     //jdoe@example.com
        toAddresses.add(firstName + lastName + "@" + domain);               //johndoe@example.com
        toAddresses.add(firstName + "." + lastName.charAt(0) + "@" + domain);//john.d@example.com
        toAddresses.add(firstName.charAt(0) + "." + lastName + "@" + domain);//j.doe@example.com
        toAddresses.add(firstName + "." + lastName + "@" + domain);          //john.doe@example.com
        toAddresses.add(firstName + "_" + lastName.charAt(0) + "@" + domain);//john_d@example.com
        toAddresses.add(firstName.charAt(0) + "_" + lastName + "@" + domain);//j_doe@example.com
        toAddresses.add(firstName + "_" + lastName + "@" + domain);          //john_doe@example.com
        
        return toAddresses;
    }
    
}
