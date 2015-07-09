package it.coderunner.gigs.model.spot;

import it.coderunner.gigs.i18n.resolver.impl.LocalePropertiesMessageResolver;
import it.coderunner.gigs.model.BaseEntity;
import it.coderunner.gigs.model.gig.Gig;
import it.coderunner.gigs.model.user.Country;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Jakub on 2015-04-14.
 */
@Entity
@Table(name = "spots")
public class Spot extends BaseEntity<Long> {

	private static final long serialVersionUID = 5447213465791330498L;

	@Id
	@GeneratedValue
	@Getter
	@Column(name = "id_spot")
	private Long id;

	@Getter
	@Setter
	@OneToMany(mappedBy = "spot", targetEntity = Gig.class, cascade = CascadeType.ALL)
	private Set<Gig> gigs = new HashSet<Gig>();

	@Getter
	@Setter
	private String city;

	@Getter
	@Setter
	private String address;

	@Getter
	@Setter
	private String club;

	@Getter
	@Setter
	private double lat;

	@Getter
	@Setter
	private double lon;

	@Getter
	@Setter
	private Country country;

	//TODO: Locale jest wstawione na sztywno, poprawić
	public Country[] getCountryList() {
		System.out.println("LOCALE: " + Locale.ENGLISH + " - "
				+ Locale.ENGLISH.getLanguage().toLowerCase());
		return Country.sortedByName(new LocalePropertiesMessageResolver(
				Locale.ENGLISH));
	}

	public Spot() {
	};

	public Spot(String city, String address, String club, Country country) {
		this.city = city;
		this.address = address;
		this.club = club;
		this.country = country;
	}
}
