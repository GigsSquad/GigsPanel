package it.coderunner.gigs.webapp.controller.profile.form;

import it.coderunner.gigs.webapp.mvc.AbstractForm;
import lombok.Getter;
import lombok.Setter;

import org.springframework.web.multipart.MultipartFile;

public class AvatarForm extends AbstractForm {

	private static final long serialVersionUID = -5869640158342812940L;

	// @Getter
	// @Setter
	private MultipartFile file;

	@Override
	public String getID() {
		return null;
	}

	public MultipartFile getFile() {
		System.out.println("w form: getFile()");
		return file;
	}

	public void setFile(MultipartFile file) {
		System.out.println("w form: setFile()");
		this.file = file;
	}

}
