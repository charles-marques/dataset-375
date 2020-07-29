package ecms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
//import javax.validation.Valid;

import ecms.domain.BaseUser;

import ecms.service.IAdministrationManagerSer;

@Controller
@RequestMapping("/signup")
public class SignupController {
    private final IAdministrationManagerSer dao;

    /**
* Creates a new {@link SignupController}
* @param userService the {@link MailUserService} to use for signing someone up. Cannot be null.
* @throws IllegalArgumentException if userService is null
*/
    @Autowired
    public SignupController(IAdministrationManagerSer dao) {
        if (dao == null) {
            throw new IllegalArgumentException("userService cannot be null");
        }
        this.dao = dao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView signupForm() {
        return new ModelAndView("signup", "user", new BaseUser());
    }

    @RequestMapping(method = RequestMethod.POST)
    public String signup(/*@Valid*/ BaseUser mailUser, BindingResult result) {
        if (result.hasErrors()) {
            return "signup";
        }
        if (this.dao.findAdminByLogin(mailUser.getLogin()) != null) {
            result.rejectValue("email", "errors.signup.email.existingUser",
                    "A user with email " + mailUser.getLogin() + " already exists.");
            return "signup";
        }
        this.dao.addAdminManager(mailUser);
        
        Authentication authentication = new UsernamePasswordAuthenticationToken(mailUser, mailUser.getPassword(),
                AuthorityUtils.createAuthorityList("ROLE_USER"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/messages/inbox";
    }
}	 