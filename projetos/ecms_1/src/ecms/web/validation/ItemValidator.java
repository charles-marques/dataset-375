package ecms.web.validation;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import ecms.domain.Item;

public class ItemValidator {
	
	public void validate(Item i, Errors errors) {
		
		if (!StringUtils.hasText(i.getName())) {
			errors.rejectValue("name", "error.required", "required");
		} else if(i.getName().length() > 50) {
			errors.rejectValue("name", "error.length", "length");
		}
		
		if (!StringUtils.hasText(i.getCategorie())) {
			errors.rejectValue("categorie", "error.required", "required");
		} else if(i.getCategorie().length() > 50) {
			errors.rejectValue("categorie", "error.length", "length");
		}
		
		if (!StringUtils.hasText(i.getDescription())) {
			errors.rejectValue("description", "error.required", "required");
		} else if(i.getDescription().length() > 50) {
			errors.rejectValue("description", "error.length", "length");
		}
		
		if (i.getPrice() < 0 || i.getPrice() == 0) {
			errors.rejectValue("price", "error.required", "required");
		}
		if (i.getStock() < 0 ) {
			errors.rejectValue("stock", "error.negative", "negative");
		}
		if (i.getTva() == 0 || i.getTva() < 0) {
			errors.rejectValue("tva", "error.required", "required");
		}
		
		
	}

}
