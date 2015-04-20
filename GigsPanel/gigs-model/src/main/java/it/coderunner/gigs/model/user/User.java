package it.coderunner.gigs.model.user;

import it.coderunner.gigs.model.BaseEntity;
import it.coderunner.gigs.model.comment.Comment;

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

@Entity
@Table(name = "users")
public class User extends BaseEntity<Long> {

	private static final long serialVersionUID = -758076802868616147L;

	@Id
	@GeneratedValue
	@Getter
	private Long id;

	@Getter
	@Setter
	@OneToMany(mappedBy = "user", targetEntity = Comment.class, cascade = CascadeType.ALL)
	private Set<Comment> comments = new HashSet<>();

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private String lastname;

	@Getter
	@Setter
	private String email;

	@Getter
	@Setter
	private String birthday;

	@Getter
	@Setter
	private String location;

	@Getter
	@Setter
	@Column(name = "fb_id")
	private Long fbId;

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
