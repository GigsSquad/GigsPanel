package it.coderunner.gigs.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class SequenceUtils {

	public static final String SPECIAL_CHARACTERS_FOR_USER_PASSWORD = "!@#$%^&*()_.";
	public static final char[] SPECIAL_CHARACTERS_FOR_API_APPLICATIONS = new char[]{'!','@','$','*', '^'};
	
	public static final String[] IMAGE_EXTENSIONS = {".png", ".jpg", ".jpeg", ".bmp", ".gif"};

	private static final String SPECIAL_CHARACTERS_POSTCODE = "!@$%^&*()_.";
	private static final String SPECIAL_CHARACTERS_VATID = "!#@$%^&*()_.";
	
	public static boolean isPostalValid(String postcode){
		if (postcode == null)
			return false;
		if (Pattern.compile(".*[" + Pattern.quote(SPECIAL_CHARACTERS_POSTCODE) + "].*").matcher(postcode).matches()) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean isImage(String filename){
		if(filename==null || StringUtils.isBlank(filename)){
			return false;
		}
		return ArrayUtils.contains(IMAGE_EXTENSIONS, extractExtension(filename));
	}
	
	public static boolean isVatValid(String vat){
		if (vat == null)
			return false;
		if (Pattern.compile(".*[" + Pattern.quote(SPECIAL_CHARACTERS_VATID) + "].*").matcher(vat).matches()) {
			return false;
		} else {
			return true;
		}
	}
	
	private static final String URL_NICK_NAME_PATTERN = "^[a-z0-9A-Z_]*$";
	
	/**
	 * Metoda sprawdzająca czy CooperativeName jest poprawna
	 * @param value cooperativeName
	 * @return true jeśli jest poprawna, false jeśli niepoprawna lub null
	 */
	public static boolean isCooperativeNameValid(String value){
		return value==null?false:!value.contains("@");
	}
	
	
	/**
	 * Metoda sprawdzająca czy UrlNickName jest poprawny
	 * @param value urlNickName
	 * @return true jeśli poprawny, false w przeciwnym wypadku
	 */
	public static boolean isUrlNickNameValid(String value){
		return value==null?false:value.matches(URL_NICK_NAME_PATTERN);
	}
	
	/**
	 * Sprawdza, czy ciąg znaków spełnia limit n znaków
	 * @param value
	 * @param limit
	 * @return
	 */
	public static boolean hasAtLeastNCharacters(String value, int limit){
		return value!=null && value.trim().length()>=limit;
	}
	
	public static String displayArray(String[] array){
		StringBuilder sb = new StringBuilder();
		for(String s : array){
			sb.append(s).append(",");
		}
		String value = sb.toString();
		return value.substring(0, value.length()-1);
	}

	/**
	 * Sprawdza, czy ci��g znak��w zawiera przynajmniej n-cyfr
	 * 
	 * @param value
	 * @param limit
	 * @return
	 */
	public static boolean hasAtLeastNDigits(String value, int limit) {
		if (value == null){
			return false;
		}
		int counter = 0;
		for (char c : value.toCharArray()) {
			if (Character.isDigit(c)){
				counter++;
			}				
		}
		return counter >= limit;
	}
	
	public static boolean containsOnlyDigits(String value) {
		for (char c : value.toCharArray()) {
			if (!Character.isDigit(c)){
				return false;
			}		
		}
		return true;
	}
	
	/**
	 * metoda zwracający zaokrągloną wartość wraz z przedrostkiem wielokrotności
	 * 
	 * @param size
	 *            rozmiar w bajtach
	 * @return
	 */
	public static String getSizeString(long size) {
		String suffix = "";
		float s = 0;
		if (size > 1024 * 1024) {
			s = (float) size / (1024 * 1024);
			suffix = "M";
		} else if (size > 1024) {
			s = (float) size / 1024;
			suffix = "k";
		} else {
			s = size;
		}

		DecimalFormat format = new DecimalFormat("0.##", DecimalFormatSymbols.getInstance(Locale.forLanguageTag("en")));
		return format.format(s) + suffix + "B";
	}

	/**
	 * Sprawdza, czy ci��g znak��w zawiera przynajmniej n-du��ych liter
	 * 
	 * @param value
	 * @param limit
	 * @return
	 */
	public static boolean hasAtLeastNUpperChars(String value, int limit) {
		if (value == null){
			return false;
		}
		int counter = 0;
		for (char c : value.toCharArray()) {
			if (Character.isUpperCase(c))
				counter++;
		}
		return counter >= limit;
	}

	/**
	 * Sprawdza, czy ci��g znak��w zawiera przynajmniej n-ma��ych liter
	 * 
	 * @param value
	 * @param limit
	 * @return
	 */
	public static boolean hasAtLeastNLowerChars(String value, int limit) {
		if (value == null){
			return false;
		}			
		int counter = 0;
		for (char c : value.toCharArray()) {
			if (Character.isLowerCase(c))
				counter++;
		}
		return counter >= limit;
	}

	/**
	 * Validate password with regular expression looking for special symbols
	 * 
	 * @param password
	 *            for validation
	 * @return true valid password, false invalid password
	 */
	public static boolean containsSpecialCharacters(String value) {
		if (value == null)
			return false;
		if (Pattern.compile(".*[" + Pattern.quote(SPECIAL_CHARACTERS_FOR_USER_PASSWORD) + "].*").matcher(value).matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean hasAtLeastNSpecialCharacters(String value, char[] specialCharactersArray, int limit){
		if (value == null){
			return false;
		}
		int counter = 0;
		for (char c : value.toCharArray()) {
			for(char x : specialCharactersArray){
				if(x == c){
					counter++;
					break;
				}
			}
		}
		return counter >= limit;
	}
	
	/**
	 * Tworzy klucz (hasło) na podstawie zadanych parametrów
	 * @param length - długość ciągu znaków
	 * @param minUpper - minimalna ilość małych liter
	 * @param minLower - minimalna ilość wielkich liter
	 * @param minNumber - minimalna ilość cyfr
	 * @param minSpecial - minimalna ilość znaków specjalnych
	 * @return wygenerowane hasło
	 */
	public static String generateKey(int length, int minUpper, int minLower, int minNumber, int minSpecial){
		int[]actualParamValues;		// indeks 0 - ilość dużych znaków, indeks 1 - ilość małych znaków, indeks 2 - ilość cyfr, indeks 3 - ilość znakó specjalnych
		do{
			actualParamValues = genNumbers(4, length);
		}while(minUpper > actualParamValues[0] ||
				minLower > actualParamValues[1] ||
				minNumber > actualParamValues[2] ||
				minSpecial > actualParamValues[3]);
		List<Character> list = new ArrayList<Character>();
		for(int i=0;i<actualParamValues[0];i++){
			//random uppercase:
			list.add((char)((int)'A'+Math.random()*((int)'Z'-(int)'A'+1)));
		}
		for(int i=0;i<=actualParamValues[1];i++){
			//random lowercase:
			Random r = new Random();
			char c = (char)(r.nextInt(26) + 'a');
			list.add(c);
		}
		for(int i=0;i<actualParamValues[2];i++){
			//random digit:
			list.add((new Random().nextInt(10)+"").charAt(0));
		}
		
		for(int i=0;i<actualParamValues[3];i++){
			list.add(SPECIAL_CHARACTERS_FOR_API_APPLICATIONS[new Random().nextInt(SPECIAL_CHARACTERS_FOR_API_APPLICATIONS.length)]);
		}
		Collections.shuffle(list);
		StringBuffer sb = new StringBuffer();
		Iterator<Character> iterator = list.iterator();
		while(iterator.hasNext()){
			char c = iterator.next();
			sb.append(c);
		}
		return sb.toString();
	}
	
	public static String stripAccents(String text) {
		if(StringUtils.isBlank(text)){
			return null;
		}
		String result = text.replace("ł", "l").replace("Ł", "L");
		return StringUtils.stripAccents(result);
	}
	
	public static int[] genNumbers(int n, int sum){
        int[] nums = new int[n];
        int upperbound = Long.valueOf(Math.round(sum*1.0/n)).intValue();
        int offset = Long.valueOf(Math.round(0.5*upperbound)).intValue();
       
        int cursum = 0;
        Random random = new Random(new Random().nextInt());
        for(int i=0 ; i < n ; i++){
            int rand = random.nextInt(upperbound) + offset;
            if( cursum + rand > sum || i == n - 1) {
                rand = sum - cursum;
            }
            cursum += rand;
            nums[i]=rand;
            if(cursum == sum){
                break;
            }
        }
        return nums;
    }
	
	/**
	 * Sprawdza poprawność numeru karty kredytowej algorytmem Luhna.
	 * @param ccNumber - numer karty kredytowej
	 * @return
	 */
	public static boolean checkCardNumber(String ccNumber){
		if(StringUtils.isBlank(ccNumber)){
			return false;
		}
		ccNumber = ccNumber.replaceAll("\\s+","");
		if(!containsOnlyDigits(ccNumber)){
			return false;
		}
		int sum = 0;
		boolean alternate = false;
		for (int i = ccNumber.length() - 1; i >= 0; i--){
			int n = Integer.parseInt(ccNumber.substring(i, i + 1));
			if (alternate){
				n *= 2;
				if (n > 9){
					n = (n % 10) + 1;
				}
			}
			sum += n;
			alternate = !alternate;
		}
		return (sum % 10 == 0);
    }
	
	public static String shortCutSentence(String value, int limit){
		if(StringUtils.isBlank(value)){
			return "";
		} 
		if(value.length()<=limit){
			return value;
		}
		char[] chars = value.toCharArray();
		for(int i = limit; i>=0; i--){
			if(Character.isWhitespace(chars[i])){				
				return value.substring(0, i)+ " ...";
			}
		}
		return value.substring(0, limit)+ " ...";
	}
	
	public static String randomAlphaNumericalString(int length){
		char[] symbols;
		StringBuilder tmp = new StringBuilder();
		for (char ch = '0'; ch <= '9'; ++ch){
			tmp.append(ch);
		}
		for (char ch = 'a'; ch <= 'z'; ++ch){
			tmp.append(ch);
		}
		symbols = tmp.toString().toCharArray();
		final Random random = new Random();
		final char[] buf = new char[length];
		for (int idx = 0; idx < buf.length; ++idx){
			buf[idx] = symbols[random.nextInt(symbols.length)];
		}
		return new String(buf);
	}
	
	public static String extractExtension(String fileName){
		return fileName.substring(fileName.lastIndexOf('.')).toLowerCase();
	}
	
	public static double formatDouble(double value, int length){
		String pattern = "#0.";
		for(int i=0;i<length;i++){
			pattern+="0";
		}
		String format = new DecimalFormat(pattern).format(value).replace(",", ".");
		return Double.parseDouble(format);
	}
	
	public static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String USER_NAME_PATTERN = "^[a-zA-Z0-9_]*$";
	
	/**
	 * Metoda sprawdzająca czy adres email jest poprawny
	 * @param value adres email
	 * @return true jeśli adres jest poprawny, false jeśli niepoprawny lub null
	 */
	public static boolean isEmailValid(String value){
		return value==null?false:value.matches(EMAIL_PATTERN);
	}
	
	
	/**
	 * Metoda sprawdzająca czy podana nazwa użytkownika jest poprawna
	 * @param value nazwa użytkownika
	 * @return true jeśli nazwa jest poprawna, false w przeciwnym wypadku
	 */
	public static boolean isUserNameValid(String value){
		return value==null?false:value.matches(USER_NAME_PATTERN);
	}
}
