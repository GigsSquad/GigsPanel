package it.coderunner.gigs.webapp.utils;

import lombok.Getter;

import org.apache.commons.lang3.StringUtils;

public class CaptchaService { //TODO przerefaktorować, klasa nie jest wstrzykiwana 

	@Getter
	private String hash;
	
	public CaptchaService(String hash){
		this.hash=hash;
	}
	
	public boolean isValid(String response){
		if(StringUtils.isBlank(hash) || StringUtils.isBlank(response)){
			return false;
		}
		return rpHash(response).equals(hash);
	}
	
	private String rpHash(String value) { 
	    int hash = 5381; 
	    value = value.toUpperCase(); 
	    for(int i = 0; i < value.length(); i++) { 
	        hash = ((hash << 5) + hash) + value.charAt(i); 
	    } 
	    return String.valueOf(hash); 
	} 
}
