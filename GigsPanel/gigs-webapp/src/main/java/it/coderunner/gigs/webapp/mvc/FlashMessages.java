package it.coderunner.gigs.webapp.mvc;

import it.coderunner.gigs.i18n.resolver.MessageResolver;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.messageresolver.IMessageResolver;

/**
 * Klasa przechowująca komunikaty wyświetlane na warstwie widoku dla użytkownika
 * po skończonej akcji (np. wysłaniu formularza). Komunikaty powinny być
 * wyświetlane tylko jednorazowo (tzw. flash scope).
 */
public class FlashMessages implements Serializable {

	private static final long serialVersionUID = -8938372442533695316L;

	@Getter
	private Set<FlashNotice> messages;
	private MessageResolver messageResolver;
	private Model model;

	public final static String MESSAGES_NAME = "flash_messages";

	public FlashMessages(Model model, MessageResolver messageResolver) {
		this.model = model;
		this.messages = new HashSet<FlashNotice>();
		this.messageResolver = messageResolver;
	}

	public boolean hasMessages() {
		return messages.size() > 0;
	}

	public void clearMessages() {
		this.messages.clear();
	}

	public void addMessage(String messageKey, Severity severity) {
		String messageValue = messageResolver.resolveMessage(messageKey);
		addText(messageValue, severity);
	}

	private void addText(String plainText, Severity severity) {
		messages.add(new FlashNotice(plainText + " <br/>", severity));
		if (model instanceof RedirectAttributes) {
			((RedirectAttributes) model).addFlashAttribute("info_" + MESSAGES_NAME, getBySeverity(Severity.INFO));
			((RedirectAttributes) model).addFlashAttribute("warning_" + MESSAGES_NAME, getBySeverity(Severity.WARNING));
			((RedirectAttributes) model).addFlashAttribute("error_" + MESSAGES_NAME, getBySeverity(Severity.ERROR));
			((RedirectAttributes) model).addFlashAttribute("success_" + MESSAGES_NAME, getBySeverity(Severity.SUCCESS));
		} else {
			model.addAttribute("info_" + MESSAGES_NAME, getBySeverity(Severity.INFO));
			model.addAttribute("warning_" + MESSAGES_NAME, getBySeverity(Severity.WARNING));
			model.addAttribute("error_" + MESSAGES_NAME, getBySeverity(Severity.ERROR));
			model.addAttribute("success_" + MESSAGES_NAME, getBySeverity(Severity.SUCCESS));
		}
	}

	private Set<FlashNotice> getBySeverity(Severity severity) {
		Set<FlashNotice> filteredMessages = new HashSet<FlashNotice>();
		for (FlashNotice fn : messages) {
			if (fn.getSeverity().equals(severity)) {
				filteredMessages.add(fn);
			}
		}
		return filteredMessages;
	}
}
