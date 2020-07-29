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
import ecms.service.IStoreManagerManagerSer;
import ecms.web.validation.ItemValidator;


@Controller
@RequestMapping("/storemanager")
public class StoreManagerController {
	
	private IStoreManagerManagerSer storeManagerManager;
	
	@Autowired
	public StoreManagerController(IStoreManagerManagerSer storeManagerManager) {
		this.storeManagerManager = storeManagerManager;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String storeManagerWelcomeHandler() {
		return "storemanager/welcomeStoreManager";
	}
	
	
	
	@RequestMapping("/order/list")
	public String showOrderList(Model model) {
		
		return "storemanager/listOrder";
	}
	
	
	
	
	@RequestMapping("/stock/list")
	public String showStockList(Model model) {
		ArrayList<Item>  listItem = (ArrayList<Item>) storeManagerManager.listAllItem();
		model.addAttribute("ListItem", listItem);
		return "storemanager/listStock";
	}
	
	@RequestMapping("/customer/list")
	public String showCustomerList(Model model) {
		ArrayList<BaseUser>  listCust = (ArrayList<BaseUser>) storeManagerManager.listAllCustomer();
		model.addAttribute("ListCustomer", listCust);
		return "storemanager/listCustomer";
	}
	
	@RequestMapping(value = "/item/add", method = RequestMethod.GET)
	public String addItem(Model model) {
		Item i = new Item();
		i.setTva(19.6);
		i.setStock(1);
		model.addAttribute("ITEM", i);
		
		return "storemanager/addItem";
	}
	
	@RequestMapping(value = "/item/add", method = RequestMethod.POST)
	public String processItemForm(@ModelAttribute(value = "ITEM") Item i, BindingResult result, Model model) {
		new ItemValidator().validate(i, result);
		if(result.hasErrors()) {
			return "storemanager/addItem";
		} else {
			if(storeManagerManager.existItemByName(i.getName())) {
				model.addAttribute("error", "Item name already used");
				return "storemanager/addItem";
			} else {
				storeManagerManager.addItem(i);
			 	return "redirect:/storemanager/stock/list";
			}
		}
	}
	
}