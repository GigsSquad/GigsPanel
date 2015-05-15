package it.coderunner.gigs.webapp.validator;

import it.coderunner.gigs.util.SequenceUtils;
import it.coderunner.gigs.webapp.utils.MultipartFileUtils;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

/**
 * Standardowy walidator formy, w której dokonuje się walidacji przesyłanych plików
 *
 */
public abstract class MultipartFileCommonValidator extends CommonValidator {

	/**
	 * Maksymalna ilość plików, jaką dopuszczamy.
	 * null - nie walidujemy ilości
	 * @return
	 */
	public abstract Integer getMaxFilesLimit();
	/**
	 * Maksymalny rozmiar plików, jaką dopuszczamy
	 * null - nie walidujemy rozmiaru
	 * @return
	 */
	public abstract Long getMaxSizeInBytes();
	/**
	 * Maksymalna długość nazwy plików, jaką dopuszczamy
	 * null - nie walidujemy nazw
	 * @return
	 */
	public abstract Integer getMaxFilenameLength();
	/**
	 * Tablica rozszerzeń plików, jaką dopuszczamy
	 * <br/>
	 * <b>Format</b> "rozszerzenie", bez kropek i gwiazdek (".rozszerzenie", "*.rozszerzenie")
	 * <br/>
	 * Przykład poprawnej deklaracji :
	 * <br/>
	 * <code>return new String[] {"pdf", "doc"};</code>
	 * null - nie walidujemy rozszerzeń
	 * @return
	 */
	public abstract String[] getCorrectExtensionsArray();
	/**
	 * Lista plików do zwrócenia
	 * @return
	 */
	public abstract List<MultipartFile> getMultipartFiles(Object target);
	/**
	 * Nazwa pola formatki, do której należy przypisać ewentualne błędy
	 * @return
	 */
	public abstract String getPropertyNameToDisplayErrors();
	
	@Override
	public void validate(Object target, Errors errors) {
		validateForm(target, errors);
		validateMultipartFiles(errors, getMaxFilesLimit(), getMaxSizeInBytes(), getMaxFilenameLength(), getCorrectExtensionsArray(), getMultipartFiles(target), getPropertyNameToDisplayErrors());
		this.errors = errors;
	}
	
	/**
	 * Walidacja listy plików wrzucanych do formy, traktowanych jako listę obiektów {@link MultipartFile}
	 * @param maxFilesLimit - maksymalna ilość plików do wrzucenia na formie
	 * @param maxSizeInBytes - maksymalny rozmiar pojedynczego pliku
	 * @param maxFileNameLength - maksymalna długość nazwy pliku
	 * @param correctExtensions - tablica dopuszcalnych rozszerzeń pliku
	 * @param files - lista plików
	 * @param propertyNameToDisplayErrors
	 */
	public void validateMultipartFiles(Errors errors, Integer maxFilesLimit, Long maxSizeInBytes, Integer maxFileNameLength, String[] correctExtensions, List<MultipartFile> files, String propertyNameToDisplayErrors){
		if(maxFilesLimit!=null && files.size() > maxFilesLimit){
			errors.rejectValue(propertyNameToDisplayErrors, "validator.multipartfile.toomany", new Object[] {maxFilesLimit}, null);
		}
		for(MultipartFile file : files){
			validateMultipartFile(errors, maxSizeInBytes, maxFileNameLength, correctExtensions, file, propertyNameToDisplayErrors);
		}
	}

	private void validateMultipartFile(Errors errors, Long maxSizeInBytes, Integer maxFileNameLength, String[] correctExtensions, MultipartFile file, String propertyNameToDisplayErrors) {
		if(MultipartFileUtils.isNotBlank(file)){
			if(maxSizeInBytes!=null && file.getSize() > maxSizeInBytes){
				errors.rejectValue(propertyNameToDisplayErrors, "validator.multipartfile.size.notvalid",	new Object[] { file.getOriginalFilename(),  SequenceUtils.getSizeString(maxSizeInBytes) + "" }, null);
			}
			if(maxFileNameLength!=null && file.getOriginalFilename().length() > maxFileNameLength){
				errors.rejectValue(propertyNameToDisplayErrors, "validator.multipartfile.name.toolong", new Object[] { file.getOriginalFilename(), maxFileNameLength} , null);
			}
			if(correctExtensions!=null && !MultipartFileUtils.hasValidExtension(file, correctExtensions)){
				errors.rejectValue(propertyNameToDisplayErrors, "validator.multipartfile.extension.notvalid", new Object[] { file.getOriginalFilename(), SequenceUtils.displayArray(correctExtensions)} , null);
			}
		}
	}
}
