package it.coderunner.gigs.model.stat;

import it.coderunner.gigs.model.BaseEntity;
import it.coderunner.gigs.model.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Tabela statystyk dotyczących wyszukiwań przez użytkowników połączona z
 * użytkownikiem
 * 
 * @author Jakub
 *
 */

@Entity
@Table(name = "stats")
public class Stat extends BaseEntity<Long> {

	private static final long serialVersionUID = -7692890722461249579L;

	@Id
	@Getter
	@GeneratedValue
	private Long id;

	@Getter
	@Setter
	@JoinColumn(name = "user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@Getter
	@Setter
	@Column(name = "search_token")
	private String searchToken;

	@Getter
	@Setter
	@Enumerated(EnumType.STRING)
	private StatType type;

	@Getter
	@Setter
	private Double latitude;

	@Getter
	@Setter
	private Double longitude;

	public Stat(User user, String searchToken, StatType type, Double latitude,
			Double longitude) {
		this.user = user;
		this.searchToken = searchToken;
		this.type = type;
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
