package br.com.levymoreira.util;

import java.util.Iterator;
import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

public class FacesUtil {
	
	public static void addErroMsg(String sumary, String detail) {
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_ERROR, sumary, detail));
	}

	public static void addInfoMsg(String sumary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_INFO, sumary, detail));
	}

	/*Metodo criado pelo RafaelPonte  https://github.com/rponte
	 * so retirei a dependencia do spring e deixei 
	 * o metodo como de classe*/
	public static boolean possuiMensagem(String msg) {
		Iterator<FacesMessage> messages = FacesContext.getCurrentInstance().getMessages();
		while (messages.hasNext()) {
			FacesMessage message = messages.next();
			boolean confere = message.getDetail().equals(msg);
			if (confere)
				return true;
		}
		return false;
	}

	/*Metodo criado pelo RafaelPonte  https://github.com/rponte
	 * so retirei a dependencia do spring e deixei 
	 * o metodo como de classe*/
	/**
	 * Limpa os dados dos componentes de edição e de seus filhos,
	 * recursivamente. Checa se o componente é instância de EditableValueHolder
	 * e 'reseta' suas propriedades.
	 * <p>
	 * Quando este método, por algum motivo, não funcionar, parta para
	 * ignorância e limpe o componente assim:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * component.getChildren().clear()
	 * </pre>
	 * 
	 * </blockquote> :-)
	 */
	public static void cleanSubmittedValues(UIComponent component) {
		if (component instanceof EditableValueHolder) {
			EditableValueHolder evh = (EditableValueHolder) component;
			evh.setSubmittedValue(null);
			evh.setValue(null);
			evh.setLocalValueSet(false);
			evh.setValid(true);
		}
		if (component.getChildCount() > 0) {
			for (UIComponent child : component.getChildren()) {
				cleanSubmittedValues(child);
			}
		}
	}
	
	public static void putInRequestMap(String key, Object value){
	    FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(key, value);
	}
	
	public static Object getInRequestMap(String key){
		return FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(key);  
	}

}
