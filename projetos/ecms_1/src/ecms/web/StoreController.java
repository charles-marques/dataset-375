package ecms.web;


import java.util.ArrayList;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import ecms.domain.BaseUser;
import ecms.domain.Item;
import ecms.service.IStoreManagerSer;
import ecms.web.validation.StoreManagerValidator;

@Controller
@RequestMapping("/store")
public class StoreController {
	
	private IStoreManagerSer storeManager;
	
	@Autowired
	public StoreController(IStoreManagerSer storeManager) {
		this.storeManager = storeManager;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String boutiqueWelcomeHandler() {
		return "store/welcomeStore";
	}
	
	@RequestMapping(value = "/consult", method = RequestMethod.GET)
	public String showStoreManager(Model model) {
		ArrayList<Item>  listItem = (ArrayList<Item>) storeManager.listAllItem();
		model.addAttribute("ListItem", listItem);
		
		return "store/listItem";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String showUserForm(Model model) {
		BaseUser user = new BaseUser();
		model.addAttribute("USER", user);
		
		return "store/addUser";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String processStoreManagerForm(@ModelAttribute(value = "USER") BaseUser user, BindingResult result, Model model) {
		new StoreManagerValidator().validate(user, result);
		if(result.hasErrors()) {
			return "store/addUser";
		} else {
			if(storeManager.existCustomerByLogin(user.getLogin())) {
				model.addAttribute("error", "User login already used");
				return "store/addUser";
			} else {
				storeManager.addCustomer(user);
			 	return "redirect:/store";
			}
		}
	}
	
}