package it.coderunner.gigs.model.user;

import it.coderunner.gigs.model.BaseEntity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="users")
public class User extends BaseEntity<Long>{
	
	private static final long serialVersionUID = -758076802868616147L;
	
	@Id
	@GeneratedValue
	@Getter
	private Long id;
	
	@Getter @Setter
	private String name;
	
	@Getter @Setter
	private String lastname;
	
	@Getter @Setter
	private String email;
	
	@Getter @Setter
	private String birthday;
	
	@Getter @Setter
	private String location;
	
	@Getter @Setter
	@Column(name="fb_id")
	private Long fbId;
	
	@Getter @Setter
	private Date registered;

}
