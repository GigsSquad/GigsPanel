package it.coderunner.gigs.repository.comments;

import java.util.Date;
import java.util.List;

import it.coderunner.gigs.model.comment.Comment;
import it.coderunner.gigs.model.gig.Gig;
import it.coderunner.gigs.model.user.User;
import it.coderunner.gigs.repository.OrderType;
import it.coderunner.gigs.repository.Queryable;

public abstract class Comments extends Queryable<Comment, Long>{

	private static final long serialVersionUID = 8756974324975202567L;
	protected String comment;
	protected Date dateAdded;
	protected Gig gig;
	protected User user;
	
	protected Comments() {};
	
	public Comments withComment(String comment){
		this.comment = comment;
		return this;
	}
	
	public Comments withDate(Date date){
		this.dateAdded = date;
		return this;
	}
	
	public Comments withGig(Gig gig){
		this.gig = gig;
		return this;
	}
	
	public Comments withUser(User user){
		this.user= user;
		return this;
	}
	
	public Comments withId(Long id){
		return (Comments) super.withId(id);
	}
	
	public Comments addOrder(OrderType orderType, String sortProperty) {
		return (Comments) super.addOrder(orderType, sortProperty);
	}

	public Comments loadWith(String... propertyNames) {
		return (Comments) super.loadWith(propertyNames);
	}

	public Comments paginate(int startingAt, int maxResults) {
		return (Comments) super.paginate(startingAt, maxResults);
	}

	public Comments randomOrder() {
		return (Comments) super.randomOrder();
	}

	public Comments merge(Comments other) {
		super.merge(other);
		if (other.comment != null){
			this.comment = other.comment;
		}
		if (other.dateAdded != null){
			this.dateAdded = other.dateAdded;
		}
		if (other.gig != null){
			this.gig = other.gig;
		}
		if (other.user != null){
			this.user = other.user;
		}
		return this;
	}
	public static Comments findAll() {
		return new Comments() {

			private static final long serialVersionUID = -6804576481886404146L;

			@Override
			public Comment uniqueObject() {
				return null;
			}

			@Override
			public List<Comment> list() {
				return null;
			}

			@Override
			public long count() {
				return 0;
			}
		};
	}
}
