package it.coderunner.gigs.worker.backend.mail.impl;

import it.coderunner.gigs.worker.backend.mail.MailConfigurationProvider;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;

import lombok.extern.log4j.Log4j;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Klasa dostarczająca konfigurację javax.mail w oparciu o DI Springa
 */
@Component
@Log4j
public class SystemPropertiesMailConfigurationProvider implements MailConfigurationProvider{

	@Value("${mail.sender.user}")
	private String user;
	@Value("${mail.sender.password}")
	private String password;
	@Value("${mail.sender.host}")
	private String host;
	@Value("${mail.sender.mail}")
	private String mail;
	@Value("${mail.sender.name}")
	private String name;
	@Value("${mail.sender.port}")
	private String port;
	@Value("${mail.sender.tls}")
	private boolean useTls;
	@Value("${mail.sender.auth}")
	private boolean useAuth;
	@Value("${mail.admin.recipientaddress}")
	private String adminRecipientAddress;
	@Value("${mail.logo.path}")
	private String mailLogoPath;
	
	@Override
	public Session getCurrentSession() {
		Properties properties = System.getProperties();

		properties.setProperty("mail.smtp.host", host);
		properties.put("mail.smtp.auth", ((useAuth) ? "true" : "false"));
		if (useTls) {
			properties.put("mail.smtp.starttls.enable", "true");
		} else {
			properties.setProperty("mail.smtp.ssl.trust", "smtpserver");
		}
		properties.put("mail.smtp.port", port);
		
		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		return session;
	}

	@Override
	public Address getSenderAddress() {
		try {
			return new InternetAddress(mail, name);
		} catch (UnsupportedEncodingException e) {
			log.error("Error getting mail sender address", e);
			return null;
		}
	}

	@Override
	public String getAdminRecipientAddress() {
		return adminRecipientAddress;
	}

	@Override
	public String getMailLogoPath() {
		return mailLogoPath;
	}

	@Override
	public String generateHTML(String content){
		try {
			//TODO co to kurwa jest example.com?
			Document doc = Jsoup.parse(ClassLoader.getSystemClassLoader().getResourceAsStream("mail_template.html"), "UTF-8", "http://example.com/");
			Element mainParagraph = doc.select("p").first();
			mainParagraph.prepend(content);
			return doc.html();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
