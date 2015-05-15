package it.coderunner.gigs.webapp.view;

import java.util.Map;

import lombok.extern.log4j.Log4j;

import org.springframework.web.servlet.support.RequestContext;
import org.thymeleaf.Arguments;
import org.thymeleaf.Configuration;
import org.thymeleaf.dom.Element;
import org.thymeleaf.spring4.naming.SpringContextVariableNames;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressionExecutionContext;
import org.thymeleaf.standard.expression.StandardExpressions;

@Log4j
public class ThymeleafProcessorUtils {

	/**
	 * Pobiera wartość elementu zadeklarowanego na widoku, np. w przypadku ${user.username} 
	 * metoda powinna zwracać Stringa (pamiętać o zrzutowaniu) np. "janKowalski"
	 * @param arguments
	 * @param element
	 * @param attributeName
	 * @return
	 */
	public static Object getSpElValue(final Arguments arguments,	final Element element, final String attributeName, boolean escape) {
		Configuration configuration = arguments.getConfiguration();
        String attributeValue = element.getAttributeValue(attributeName);
        IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
        IStandardExpression expression = parser.parseExpression(configuration, arguments, attributeValue);		
		return escape ? expression.execute(configuration, arguments, StandardExpressionExecutionContext.UNESCAPED_EXPRESSION) :  expression.execute(configuration, arguments);
	}
	
	public static Object getSpElValue(final Arguments arguments, final Element element, final String attributeName) {
		return getSpElValue(arguments, element, attributeName, false);
	}
	
	/**
	 * Zwraca mapę atrybutów modelu wrzuconą na stronę thymeleaf'a. Jeżeli nie można pobrać kontekstu, bądź leci inny wyjątek z tym związany, zwracany jest null
	 * @param arguments
	 * @return
	 */
	public static Map<String, Object> getContextModelMap(final Arguments arguments) {
		try {
			final RequestContext requestContext = (RequestContext) arguments.getContext().getVariables().get(SpringContextVariableNames.SPRING_REQUEST_CONTEXT);
			Map<String, Object> modelMap = requestContext.getModel();
			return modelMap;
		} catch (Exception e) {
			log.error("Exception ocurred",e);
			return null;
		}
	}
}
