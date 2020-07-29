package ecms.web;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;

import ecms.service.IAdministrationManagerSer;
import ecms.web.validation.StoreManagerValidator;
import ecms.domain.BaseUser;


@Controller
@RequestMapping("/administration")
public class AdministrationController {
	
	private IAdministrationManagerSer adminManager;
	
	@Autowired
	public AdministrationController(IAdministrationManagerSer adminManager) {
		this.adminManager = adminManager;
	}
	
	/*
	/**
	 * renvoie la vue de creation de boutique
	 *
	@RequestMapping("/creat_boutique")
	public String creationBoutique(Model model) {
		model.addAttribute("message", "this is my message");
		return "principale";
	}
	*/

	@RequestMapping(method = RequestMethod.GET)
	public String adminWelcomeHandler() {
		return "administration/welcomeAdmin";
	}
	
	@RequestMapping("/storemanager")
	public String gestionStoreManagerHandler(Model model, HttpServletRequest request) {
	//	model.addAttribute("session", request.getSession());

		return "administration/gestionStoreManager";
	}
		
	@RequestMapping(value = "/storemanager/add", method = RequestMethod.GET)
	public String showStoreManagerForm(Model model) {
		BaseUser sm = new BaseUser();
		model.addAttribute("STOREMANAGER", sm);
		
		return "administration/addStoreManager";
	}
	
	@RequestMapping(value = "/storemanager/add", method = RequestMethod.POST)
	public String processStoreManagerForm(@ModelAttribute(value = "STOREMANAGER") BaseUser sm, BindingResult result, Model model) {
		new StoreManagerValidator().validate(sm, result);
		if(result.hasErrors()) {
			return "administration/addStoreManager";
		} else {
			if(adminManager.findStoreManagerByLogin(sm.getLogin())) {
				model.addAttribute("error", "Store manager login already used");
				return "administration/addStoreManager";
			} else {
				adminManager.addStoreManager(sm);
			 	return "redirect:/administration/storemanager/list";
			}
		}
	}
	
	@RequestMapping(value = "/storemanager/list", method = RequestMethod.GET)
	public String showStoreManager(Model model) {
		ArrayList<BaseUser>  listSm = (ArrayList<BaseUser>) adminManager.listAllStoreManager();
		model.addAttribute("ListStoreManager", listSm);
		
		return "administration/listStoreManager";
	}

	
	@RequestMapping("/theme")
	public String themeHandler() { 
		
		return "administration/gestionPrint";
	}

}
