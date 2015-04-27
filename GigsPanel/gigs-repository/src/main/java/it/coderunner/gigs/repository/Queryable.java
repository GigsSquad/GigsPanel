package it.coderunner.gigs.repository;

import it.coderunner.gigs.model.BaseEntity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

public abstract class Queryable<T extends BaseEntity<S>, S extends Serializable> implements Serializable {

	private static final long serialVersionUID = 2211185376179268695L;
	
	public static final char KEY_DELIMETER = ';';
	
	protected Integer startingAt;
	protected Integer maxResults;
	protected Map<String, OrderType> orderMap = new HashMap<String, OrderType>();
	protected Set<String> loadWithPropertyNames = new TreeSet<String>();
	protected S id;
	protected Boolean randomOrder;
	
	protected Queryable<T, S> addOrder(OrderType orderType, String sortProperty) {
		orderMap.put(sortProperty, orderType);
		return this;
	}

	protected Queryable<T, S> withId(S s) {
		this.id = s;
		return this;
	}

	protected Queryable<T, S> loadWith(String... propertyNames) {
		for(String property : propertyNames){
			this.loadWithPropertyNames.add(property);
		}
		return this;
	}

	protected Queryable<T, S> paginate(int startingAt, int maxResults) {
		this.startingAt=startingAt;
		this.maxResults=maxResults;
		return this;
	}
	
	protected Queryable<T, S> merge(Queryable<T, S> other) {
		if(other.id!=null){
			this.id=other.id;
		}
		if(other.startingAt!=null){
			this.startingAt=other.startingAt;
		}
		if(other.maxResults!=null){
			this.maxResults=other.maxResults;
		}
		if(CollectionUtils.isNotEmpty(other.loadWithPropertyNames)){
			this.loadWithPropertyNames.addAll(other.loadWithPropertyNames);
		}
		if(MapUtils.isNotEmpty(other.orderMap)){
			this.orderMap.putAll(other.orderMap);
		}
		if(other.randomOrder!=null){
			this.randomOrder=other.randomOrder;
		}
		return this;
	}
	
	protected Queryable<T, S> randomOrder(){
		this.randomOrder = true;
		return this;
	}
	
	public String generateKey() {
		return null;
	}
	
	public boolean allowCaching(){
		return false;
	}
	
	protected boolean areAllDefaultFiltersUnused(){
		return id==null&&CollectionUtils.isEmpty(loadWithPropertyNames) 
				&& MapUtils.isEmpty(orderMap) 
				&& startingAt==null 
				&& maxResults==null
				&& randomOrder==null;
	}
	
	public abstract long count();	
	public abstract List<T> list();	
	public abstract T uniqueObject();
}
