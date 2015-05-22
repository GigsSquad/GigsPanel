package it.coderunner.gigs.worker.backend.mail.impl;

import it.coderunner.gigs.model.config.MailQueue;
import it.coderunner.gigs.worker.backend.mail.MailConfigurationProvider;
import it.coderunner.gigs.worker.backend.mail.MailSender;
import it.coderunner.gigs.worker.backend.mail.PreparingMailException;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Komponent wysyłający e-mail w oparciu o technologię javax.mail
 */
@Component
@Log4j
public class JavaxMailSender implements MailSender {

	@Autowired
	private MailConfigurationProvider mailConfigurationProvider;

	@Override
	public void sendMail(MailQueue mailQueue) throws PreparingMailException {
		MimeMessage message = new MimeMessage(mailConfigurationProvider.getCurrentSession());
		try {
			message.setHeader("Content-Encoding", "utf-8");
			message.setFrom(mailConfigurationProvider.getSenderAddress());
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailQueue.getEmail()));
			message.addRecipient(RecipientType.BCC, new InternetAddress(mailConfigurationProvider.getAdminRecipientAddress()));
			message.setSubject(mailQueue.getSubject(), "utf-8");

			MimeMultipart contentPart = new MimeMultipart("related");
			Multipart partMessage = new MimeMultipart("alternative");

			MimeBodyPart plainMessage = new MimeBodyPart();
			plainMessage.setContent(mailQueue.getContentPlain(), "text/plain; charset=utf-8");

			MimeBodyPart htmlMessage = new MimeBodyPart();
			htmlMessage.setContent(mailConfigurationProvider.generateHTML(mailQueue.getContentHtml()), "text/html; charset=utf-8");

			partMessage.addBodyPart(plainMessage);
			partMessage.addBodyPart(htmlMessage);

			MimeBodyPart bodycontent = new MimeBodyPart();
			bodycontent.setContent(partMessage);

			contentPart.addBodyPart(bodycontent);

			// TODO: obrazki, zalączniki
			// MimeBodyPart imagePart = new MimeBodyPart();
			// imagePart.attachFile(mailConfigurationProvider.getMailLogoPath());
			// imagePart.setContentID("<coop_logo>");
			// imagePart.setDisposition(MimeBodyPart.INLINE);
			// contentPart.addBodyPart(imagePart);

			message.setContent(contentPart);
			log.info("Wysylanie maila do " + mailQueue.getEmail());
			Transport.send(message);
			log.info("Mail zostal wyslany");
		} catch (Exception e) {
			log.error("Blad przy przygotowywaniu maila do wyslania", e);
			e.printStackTrace();
			throw new PreparingMailException(e);
		}
	}
}
