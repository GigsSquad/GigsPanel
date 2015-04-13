package it.coderunner.gigs.i18n.resolver.impl;

import it.coderunner.gigs.i18n.resolver.MessageResolver;

import java.io.IOException;
import java.io.InputStream;
import java.text.Collator;
import java.util.Locale;
import java.util.Properties;

import lombok.Getter;
import lombok.extern.log4j.Log4j;

@Log4j
public class LocalePropertiesMessageResolver implements MessageResolver{

	private Properties properties;
	@Getter
	private Collator collator;
	
	public LocalePropertiesMessageResolver(Locale locale){
		this.collator = Collator.getInstance(locale);
		InputStream is = null;
		try {
			ClassLoader loader = this.getClass().getClassLoader();
			this.properties = new Properties();
			String filePath = "messages_" + locale.getLanguage().toLowerCase()	+ ".properties";
			is = loader.getResourceAsStream(filePath);
			properties.load(is);
		} catch (IOException e) {
			log.error("Error while creating propertiesPlaceholderMessageResolver", e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				log.error("Error while closing input stream", e);
			}
		}
	}

	@Override
	public String resolveMessage(String msgCode) {
		return properties.getProperty(msgCode);
	}
}
