package utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Mensagens {
	public void addMessage(String mensagem1, String campo, String mensagem2) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,	mensagem1, mensagem2);
		FacesContext.getCurrentInstance().addMessage(campo, message);
	}
	public void addMessage(String mensagem1) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,	mensagem1, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	public void addErrorMessage(String mensagem1) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem1, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
