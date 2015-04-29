package it.coderunner.gigs.repository.artists;

import it.coderunner.gigs.model.artist.Artist;
import it.coderunner.gigs.model.gig.Gig;
import it.coderunner.gigs.repository.OrderType;
import it.coderunner.gigs.repository.Queryable;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public abstract class Artists extends Queryable<Artist, Long> {

	private static final long serialVersionUID = -3529201558178625791L;
	protected String name;
	protected Gig gig;

	protected Artists() {
	};

	public Artists withName(String name) {
		this.name = name;
		return this;
	}

	public Artists withGig(Gig gig) {
		this.gig = gig;
		return this;
	}

	public Artists withId(Long id) {
		return (Artists) super.withId(id);
	}

	public Artists addOrder(OrderType orderType, String sortProperty) {
		return (Artists) super.addOrder(orderType, sortProperty);
	}

	public Artists loadWith(String... propertyNames) {
		return (Artists) super.loadWith(propertyNames);
	}

	public Artists paginate(int startingAt, int maxResults) {
		return (Artists) super.paginate(startingAt, maxResults);
	}

	public Artists randomOrder() {
		return (Artists) super.randomOrder();
	}

	public Artists merge(Artists other) {
		super.merge(other);
		if (StringUtils.isNotBlank(other.name)) {
			this.name = other.name;
		}
		if (other.gig != null) {
			this.gig = other.gig;
		}
		return this;
	}

	public static Artists findAll() {
		return new Artists() {

			private static final long serialVersionUID = -6804576481886404146L;

			@Override
			public Artist uniqueObject() {
				return null;
			}

			@Override
			public List<Artist> list() {
				return null;
			}

			@Override
			public long count() {
				return 0;
			}
		};
	}

}
