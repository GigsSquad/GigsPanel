package it.coderunner.gigs.webapp.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class MultipartFileUtils {

	public static boolean isBlank(MultipartFile file) {
		if(file==null){
			return true;
		}
		return StringUtils.isBlank(file.getOriginalFilename()) && file.getSize()==0;
	}
	
	public static boolean isNotBlank(MultipartFile file){
		return !isBlank(file);
	}
	
	public static boolean hasValidExtension (MultipartFile file, String[] extensionsArray){
		String fileName = file.getOriginalFilename();
		for(String ext : extensionsArray){
			String extension = ext.trim().toLowerCase();
			if (!StringUtils.isBlank(extension)) {
				if (!extension.startsWith(".")) {
					extension = "." + extension;
				}
				if (fileName.toLowerCase().endsWith(extension)) {
					return true;
				}
			}
		}
		return false;
	}
}
