package it.coderunner.gigs.repository.spots;

import it.coderunner.gigs.model.gig.Gig;
import it.coderunner.gigs.model.spot.Spot;
import it.coderunner.gigs.repository.OrderType;
import it.coderunner.gigs.repository.Queryable;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public abstract class Spots extends Queryable<Spot, Long> {

	private static final long serialVersionUID = 80164152842863393L;
	protected String city;
	protected String address;
	protected String club;
	protected String country;

	protected Gig gis;



	public Spots withCity(String city) {
		this.city = city;
		return this;
	}

	public Spots withAddress(String address) {
		this.address = address;
		return this;
	}
	
	public Spots withClub(String club) {
		this.club = club;
		return this;
	}

	public Spots withCountry(String country) {
		this.country = country;
		return this;
	}

	public Spots withId(Long id) {
		return (Spots) super.withId(id);
	}

	public Spots addOrder(OrderType orderType, String sortProperty) {
		return (Spots) super.addOrder(orderType, sortProperty);
	}

	public Spots paginate(int startingAt, int maxResults) {
		return (Spots) super.paginate(startingAt, maxResults);
	}

	public Spots randomOrder() {
		return (Spots) super.randomOrder();
	}

	public Spots merge(Spots other) {
		super.merge(other);

		if (StringUtils.isNotBlank(other.city)) {
			this.city = other.city;
		}
		if (StringUtils.isNotBlank(other.address)) {
			this.address = other.address;
		}
		if (StringUtils.isNotBlank(other.club)) {
			this.club = other.club;
		}
		if (StringUtils.isNotBlank(other.country)) {
			this.country = other.country;
		}

		return this;
	}

	public static Spots findAll() {
		return new Spots() {

			private static final long serialVersionUID = -6804576481886404146L;

			@Override
			public Spot uniqueObject() {
				return null;
			}

			@Override
			public List<Spot> list() {
				return null;
			}

			@Override
			public long count() {
				return 0;
			}
		};
	}

}
