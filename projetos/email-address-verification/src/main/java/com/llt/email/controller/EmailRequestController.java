package com.llt.email.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.llt.email.model.EmailRequest;
import com.llt.email.service.EmailService;

@Controller
public class EmailRequestController {
    private static final Logger logger = LoggerFactory.getLogger(EmailRequestController.class);

    private static final String VIEW_ALL_REQUESTS = "allrequests";
    private static final String VIEW_CREATE_REQUEST = "createrequest";
    // private static final String VIEW_REQUEST_DETAILS = "requestdetails";

    private String domainsBlocked;

    @Value("${domains.blocked}")
    public void setDomainsBlocked(String domainsBlocked) {
        this.domainsBlocked = domainsBlocked;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String createNewRequest(Model model) {
        logger.info("emailRequestPresent called ");

        model.addAttribute(new EmailRequest());

        return VIEW_CREATE_REQUEST;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createNewRequest(@Valid EmailRequest emailRequest, BindingResult bindingResult, Model model) {
        logger.info("emailRequestHandle Called");
        if (bindingResult.hasErrors()) {
            logger.info("----------------EmaiRequest is In-Valid ----------");
            return VIEW_CREATE_REQUEST;
        }else{
            if(domainsBlocked.contains(emailRequest.getDomain().toLowerCase())){
                bindingResult.rejectValue("domain", null, "Please provide a valid domain name. Emailbuddy doesn't support general public domains.");
            }    
        }
        logger.info("----------------EmaiRequest is Valid ----------");
        logger.info(emailRequest.toString());

        emailRequest.setStatus("NEW");

        emailService.createEmailRequest(emailRequest);

        model.addAttribute("success", "Your request is being processed. " + "It will take 5 minutes to verify <b>"
            + emailRequest.getFirstName() + "</b> " + "<b>" + emailRequest.getLastName()
            + "'s </b> email. The final results will be sent to you via email.");
        model.addAttribute(new EmailRequest());
        return VIEW_CREATE_REQUEST;
    }

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/allrequests", method = RequestMethod.GET)
    public String showAllRequests(Model model) {

        List<EmailRequest> emailRequests = emailService.getAllEmailRequests();
        model.addAttribute(emailRequests);

        return VIEW_ALL_REQUESTS;
    }

//     @RequestMapping(value = "/{emailRequestId}", method = RequestMethod.GET)
//     public String getEmailRequest(@PathVariable ("emailRequestId") int
//     emailRequestId, Model model) {
//     logger.info("---getEmailRequest ---  emailRequestId=" + emailRequestId );
//    
//     EmailRequest emailRequest = emailService.getEmailRequest(emailRequestId);
//     model.addAttribute(emailRequest);
//    
//     List<EmailResponse> emailResponseList =
//     emailService.getEmailResponses(emailRequestId);
//     model.addAttribute(emailResponseList);
//    
//     return VIEW_REQUEST_DETAILS;
//     }
//
//    @RequestMapping(value = "/cancel", method = RequestMethod.GET)
//    public String cancelEmailRequest(Model model) {
//        return "redirect:/";
//    }

}