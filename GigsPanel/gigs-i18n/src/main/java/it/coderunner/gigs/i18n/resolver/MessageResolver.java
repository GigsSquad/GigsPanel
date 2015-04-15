package it.coderunner.gigs.i18n.resolver;

import java.text.Collator;

public interface MessageResolver {

	/**
	 * Zwraca wartość etykiety za pomocą unikatowego kodu
	 * @param msgCode
	 * @return
	 */
	String resolveMessage(String msgCode);
	
	Collator getCollator();
}
