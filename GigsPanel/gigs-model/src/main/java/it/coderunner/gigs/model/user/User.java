package it.coderunner.gigs.model.user;

import it.coderunner.gigs.model.BaseEntity;
import it.coderunner.gigs.model.comment.Comment;
import it.coderunner.gigs.model.stat.Stat;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User extends BaseEntity<Long> {

	private static final long serialVersionUID = -758076802868616147L;

	public User(){};
	
	public User(String username, String email, String password){
		this.username=username;
		this.email=email;
		this.password=password;
	}
	
	@Id
	@Getter
	@GeneratedValue
	private Long id;

	@Getter
	@Setter
	@OneToMany(mappedBy = "user", targetEntity = Comment.class, cascade = CascadeType.ALL)
	private Set<Comment> comments = new HashSet<>();
	
	@Getter
	@Setter
	@OneToMany(mappedBy = "user", targetEntity = Stat.class, cascade = CascadeType.ALL)
	private Set<Stat> stats = new HashSet<>();

	@Getter
	@Setter
	private String firstname;

	@Getter
	@Setter
	private String lastname;

	@Getter
	@Setter
	@Column(nullable=false, unique=true)
	private String email;
	
	/**
	 * Hasło użytkownika - minimum 6 znaków
	 */
	@JsonIgnore
	@Column(nullable=false)
	@Getter	@Setter
	private String password;
	
	/**
	 * Hash wykorzystywany podczas zmiany hasła
	 */
	@JsonIgnore
	@Column(name = "remind_password_hash")
	@Getter	@Setter
	private String remindPasswordHash;
	
	@Getter @Setter
	@Column(nullable=false, unique = true)
	private String username;
	
	@Getter
	@Setter
	private String birthday;

	@Getter
	@Setter
	private String location;
	
	/**
	 * Państwo, z którego loguje się użytkownik (powiązane ze strefą czasową)
	 */
	@Enumerated(EnumType.STRING)
	@Getter	@Setter
	private Country country;
	
	@Getter @Setter
	private String activationHash;

	@Getter
	@Setter
	@Column(name = "login_type")
	@Enumerated(EnumType.STRING)
	private UserLogin loginType;
	
	@Getter
	@Setter
	@Column(name = "date_registered")
	private Date dateRegistered;

	@Enumerated(EnumType.STRING)
	@Getter
	@Setter
	private UserStatus status;

	@Getter
	@Setter
	@Enumerated(EnumType.STRING)
	private UserLocale locale = UserLocale.pl;

	/**
	 * Rola użytkownika
	 */
	@ElementCollection(targetClass = UserRole.class, fetch = FetchType.LAZY)
	@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), uniqueConstraints = @UniqueConstraint(columnNames = {
			"user_id", "role" }))
	@Column(name = "role", columnDefinition = "TEXT")
	@Enumerated(EnumType.STRING)
	@Getter
	@Setter
	private Set<UserRole> roles = new HashSet<>();

	/**
	 * Dodaje zbiór ról do zbioru ról posiadanych przez użytkownika
	 * 
	 * @param roles
	 * @return zwraca true, jeżeli zbiór ról posiadanych przez użytkownika
	 *         zmienił się po operacji dodawania.
	 */
	public boolean add(UserRole... roles) {
		return this.roles.addAll(Arrays.asList(roles));
	}

	/**
	 * Usuwa zbiór ról ze zbioru ról posiadanych przez użytkownika
	 * 
	 * @param roles
	 * @return zwraca true, jeżeli zbiór ról posiadanych przez użytkownika
	 *         zmienił się po operacji usuwania.
	 */
	public boolean remove(UserRole... roles) {
		return this.roles.removeAll(Arrays.asList(roles));
	}

	/**
	 * Sprawdza, czy użytkownik posiada wszystkie role zadeklarowane jako
	 * parametry metod. Jeżeli nie posiada choćby jednej, metoda zwraca false.
	 * 
	 * @param roles
	 * @return
	 */
	public boolean hasRoles(UserRole... roles) {
		for (UserRole role : roles) {
			if (!this.getRoles().contains(role)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Sprawdza, czy użytkownik posiada dany status
	 * 
	 * @param status
	 * @return Jeżeli użytkownik
	 */
	public boolean hasStatus(UserStatus status) {
		if (status == null || this.status == null) {
			return false;
		}
		return this.status.equals(status);
	}
}
