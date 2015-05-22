package it.coderunner.gigs.model.config;

import it.coderunner.gigs.model.BaseEntity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="mail_queue")
public class MailQueue extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;

	public static final int EMAIL_MAX_LENGTH = 255;
	public static final int SUBJECT_MAX_LENGTH = 255;
	public static final int CONTENT_PLAIN_MAX_LENGTH = 65000;
	public static final int CONTENT_HTML_MAX_LENGTH = 65000;
	
	@Id
	@GeneratedValue
	@Getter @Setter
	private Long id;
	
	@Column(nullable=false, length=EMAIL_MAX_LENGTH)
	@Getter @Setter
	private String email;
	
	@Column(nullable=false, length=SUBJECT_MAX_LENGTH)
	@Getter @Setter
	private String subject;
	
	@Column(name="content_plain", nullable=false, length = CONTENT_PLAIN_MAX_LENGTH)
	@Getter @Setter
	private String contentPlain;
	
	@Column(name="content_html", nullable=false, length = CONTENT_HTML_MAX_LENGTH)
	@Getter @Setter
	private String contentHtml;
	
	@Column(name="queued", nullable=false)
	@Getter @Setter
	private Date queuedDate;
	
	@Getter @Setter
	private Date testDate;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	@Getter @Setter
	private MailQueueStatus status;
	
}
