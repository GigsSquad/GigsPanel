package it.coderunner.gigs.repository.stat;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.coderunner.gigs.model.stat.Stat;
import it.coderunner.gigs.model.stat.StatType;
import it.coderunner.gigs.model.user.User;
import it.coderunner.gigs.repository.OrderType;
import it.coderunner.gigs.repository.Queryable;

public abstract class Stats extends Queryable<Stat, Long> {

	private static final long serialVersionUID = 8981650151375773613L;

	protected User user;
	protected String searchToken;
	protected StatType type;
	protected Double latitude;
	protected Double longitude;

	public Stats withUser(User user) {
		this.user = user;
		return this;
	}

	public Stats withSearchToken(String token) {
		searchToken = token;
		return this;
	}

	public Stats withType(StatType type) {
		this.type = type;
		return this;
	}

	public Stats withLatitude(double latitude) {
		this.latitude = latitude;
		return this;
	}

	public Stats withLongitude(double longitude) {
		this.longitude = longitude;
		return this;
	}

	public Stats withId(Long id) {
		return (Stats) super.withId(id);
	}

	public Stats addOrder(OrderType orderType, String sortProperty) {
		return (Stats) super.addOrder(orderType, sortProperty);
	}

	public Stats paginate(int startingAt, int maxResults) {
		return (Stats) super.paginate(startingAt, maxResults);
	}

	public Stats randomOrder() {
		return (Stats) super.randomOrder();
	}

	public Stats merge(Stats other) {
		super.merge(other);

		if (other.user != null) {
			this.user = other.user;
		}
		if (StringUtils.isNotBlank(other.searchToken)) {
			this.searchToken = other.searchToken;
		}
		if (other.type != null) {
			this.type = other.type;
		}
		if(other.latitude != null){
			this.latitude = other.latitude;
		}
		if(other.longitude != null){
			this.longitude = other.longitude;
		}
		return this;
	}
	
	public static Stats findAll(){
		return new Stats(){

			private static final long serialVersionUID = 8102379367118621561L;

			@Override
			public long count() {
				return 0;
			}

			@Override
			public List<Stat> list() {
				return null;
			}

			@Override
			public Stat uniqueObject() {
				return null;
			}
			
		};
	}
}
