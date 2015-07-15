package it.coderunner.gigs.repository.tags;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.coderunner.gigs.model.tag.Tag;
import it.coderunner.gigs.repository.OrderType;
import it.coderunner.gigs.repository.Queryable;

public abstract class Tags extends Queryable<Tag, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4528664818874596449L;
	protected String tagName;
	
	
	public Tags withTagName(String name){
		this.tagName = name;
		return this;
	}
	public Tags withId(Long id) {
		return (Tags) super.withId(id);
	}
	
	public Tags addOrder(OrderType orderType, String sortProperty) {
		return (Tags) super.addOrder(orderType, sortProperty);
	}

	public Tags paginate(int startingAt, int maxResults) {
		return (Tags) super.paginate(startingAt, maxResults);
	}

	public Tags randomOrder() {
		return (Tags) super.randomOrder();
	}

	public Tags merge(Tags other) {
		super.merge(other);

		if (StringUtils.isNotBlank(other.tagName)) {
			this.tagName = other.tagName;
		}
		return this;
	}

	public static Tags findAll() {
		return new Tags() {

			private static final long serialVersionUID = -6804576481886404146L;

			@Override
			public Tag uniqueObject() {
				return null;
			}

			@Override
			public List<Tag> list() {
				return null;
			}

			@Override
			public long count() {
				return 0;
			}
		};
	}

}
