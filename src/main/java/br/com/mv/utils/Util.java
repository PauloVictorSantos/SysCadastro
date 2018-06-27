package br.com.mv.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Util {
	public static void setMensagem(String titulo, String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensagem));
	}
}
