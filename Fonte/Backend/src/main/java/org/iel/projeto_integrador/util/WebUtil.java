package org.iel.projeto_integrador.util;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * classe utilitária utilizada para exibir mensagens
 * @author Andre
 *
 */
public class WebUtil implements Serializable {

    private static final long serialVersionUID = 1;
    public static final String MESSAGE_KEY_ERROR = "generalMessages";

    private WebUtil() {
		throw new IllegalStateException("Utility class");
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static HttpSession getSession() {
        return WebUtil.getRequest().getSession(true);
    }

    public static Object getSessionAttribute(String param) {
        return WebUtil.getRequest().getSession().getAttribute(param);
    }

    public static void redirect(String page) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(page);
    }

    public static void addErrorMessage(String message) {
        WebUtil.addMessage(FacesMessage.SEVERITY_ERROR, message);
    }

    public static void addErrorMessage(String message, String key) {
        WebUtil.addMessage(FacesMessage.SEVERITY_ERROR, message, key);
    }

    public static void addInfoMessage(String message) {
        WebUtil.addMessage(FacesMessage.SEVERITY_INFO, message);
    }

    public static void addInfoMessage(String message, String key) {
        WebUtil.addMessage(FacesMessage.SEVERITY_INFO, message, key);
    }

    public static void addMessage(Severity severityInfo, String message) {
    	WebUtil.addMessage(severityInfo, message, MESSAGE_KEY_ERROR);
    }

    public static void addMessage(Severity severityInfo, String message, String key) {
        FacesContext.getCurrentInstance().addMessage(key, new FacesMessage(severityInfo, message, ""));
    }
}
