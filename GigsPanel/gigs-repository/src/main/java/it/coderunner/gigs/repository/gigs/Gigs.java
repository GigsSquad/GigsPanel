package it.coderunner.gigs.repository.gigs;

import it.coderunner.gigs.model.artist.Artist;
import it.coderunner.gigs.model.gig.Gig;
import it.coderunner.gigs.model.spot.Spot;
import it.coderunner.gigs.repository.OrderType;
import it.coderunner.gigs.repository.Queryable;

import java.util.Date;
import java.util.List;

public abstract class Gigs extends Queryable<Gig, Long>{

	private static final long serialVersionUID = 512865023606696265L;
	
	protected Artist artist;
	protected Spot spot;
	protected Date date;
	protected Date fromDate;
	protected Date tillDate;
	
	protected Gigs() {};
	
	public Gigs withArtist(Artist artist){
		this.artist = artist;
		return this;
	}
	
	public Gigs withDate(Date date){
		this.date = date;
		return this;
	}
	
	public Gigs withSpot(Spot spot)
	{
		this.spot = spot;
		return this;
	}
	
	public Gigs fromDate(Date fromDate){
		this.fromDate = fromDate;
		return this;
	}
	
	public Gigs toDate(Date tillDate){
		this.tillDate = tillDate;
		return this;
	}
	
	public Gigs withId(Long id) {
		return (Gigs) super.withId(id);
	}

	public Gigs addOrder(OrderType orderType, String sortProperty) {
		return (Gigs) super.addOrder(orderType, sortProperty);
	}

	public Gigs loadWith(String... propertyNames) {
		return (Gigs) super.loadWith(propertyNames);
	}

	public Gigs paginate(int startingAt, int maxResults) {
		return (Gigs) super.paginate(startingAt, maxResults);
	}

	public Gigs randomOrder() {
		return (Gigs) super.randomOrder();
	}
	
	public Gigs merge(Gigs other) {
		super.merge(other);
		if (other.artist != null){
			this.artist = other.artist;
		}
		if (other.date != null){
			this.date = other.date;
		}
		if (other.fromDate != null){
			this.fromDate = other.fromDate;
		}
		if (other.tillDate != null){
			this.tillDate = other.tillDate;
		}
		return this;
	}
	
	public static Gigs findAll() {
		return new Gigs() {

			private static final long serialVersionUID = -6804576481886404146L;

			@Override
			public Gig uniqueObject() {
				return null;
			}

			@Override
			public List<Gig> list() {
				return null;
			}

			@Override
			public long count() {
				return 0;
			}
		};
	}

}
