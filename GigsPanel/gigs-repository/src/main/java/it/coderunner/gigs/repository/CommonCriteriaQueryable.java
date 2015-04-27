package it.coderunner.gigs.repository;

import it.coderunner.gigs.model.BaseEntity;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class CommonCriteriaQueryable<T, S>  {


	private static <S> String displayIds(List<S> ids) {
		StringBuilder sb = new StringBuilder();
		for(S s : ids){
			sb.append(s).append(",");
		}
		String value = sb.toString();
		return value.substring(0, value.length()-1);
	}

	private static boolean propertyNamesContainsCollection(Set<String> properties, Set<String> collectionPropertyNames) {
		if(properties==null){
			return false;
		}
		for(String collectionName : collectionPropertyNames) {
			if(properties.contains(collectionName)){
				return true;
			}
		}
		return false;
	}

	private static boolean usePagination(Integer startingAt, Integer maxResults) {
		return startingAt!=null || maxResults!=null;
	}
	
	@SuppressWarnings("unchecked")
	public static <T,S> List<T> list(Queryable<? extends BaseEntity<? extends Serializable>, ? extends Serializable> queryable, Criteria criteria, Criteria criteria2, Set<String> collectionProperties) {
		generateOrder(queryable,criteria);
		generatePagination(criteria, queryable.startingAt, queryable.maxResults);
		generateIdRestriction(criteria, queryable.id);
		if(usePagination(queryable.startingAt, queryable.maxResults) && propertyNamesContainsCollection(queryable.loadWithPropertyNames, collectionProperties)){
			criteria.setProjection(Projections.distinct(Projections.id()));
			List<S> ids = criteria.list();
			if(CollectionUtils.isNotEmpty(ids)){
				criteria2.add(Restrictions.in("id", ids));
				generateFetchModes(criteria2, queryable.loadWithPropertyNames);
				//fake order:
				criteria2.add(Restrictions.sqlRestriction("1=1 ORDER BY FIELD("+Criteria.ROOT_ALIAS+"_.id, "+displayIds(ids)+")"));
				criteria2.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				return criteria2.list();
			} else {
				return Collections.EMPTY_LIST;
			}
		} else {
			generateFetchModes(criteria, queryable.loadWithPropertyNames);
		}
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}
	
	public static <T,S> long count(Queryable<? extends BaseEntity<? extends Serializable>, ? extends Serializable> queryable, Criteria criteria) {
		generateIdRestriction(criteria, queryable.id);
		generatePagination(criteria, queryable.startingAt, queryable.maxResults);
		criteria.setProjection(Projections.rowCount());
		Number number = (Number) criteria.uniqueResult();
		return number==null ? 0 : number.longValue();
	}

	@SuppressWarnings("unchecked")
	public static <T,S> T uniqueObject(Queryable<? extends BaseEntity<? extends Serializable>, ? extends Serializable> queryable, Criteria criteria) {
		generateIdRestriction(criteria, queryable.id);
		generatePagination(criteria, queryable.startingAt, queryable.maxResults);
		generateFetchModes(criteria, queryable.loadWithPropertyNames);
		generateOrder(queryable, criteria);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (T) criteria.uniqueResult();
	}

	public static <S> void generateIdRestriction(Criteria criteria, S id) {
		if(id != null){
			criteria.add(Restrictions.eq("id", id));
		}
	}

	public static void generateFetchModes(Criteria criteria, Set<String> propertyNames) {
		for(String property : propertyNames){
			criteria.setFetchMode(property, FetchMode.JOIN);
		}
	}

	public static void generatePagination(Criteria criteria, Integer startingAt, Integer maxResults) {
		if(startingAt!=null && maxResults!=null){
			criteria.setFirstResult(startingAt);
			criteria.setMaxResults(maxResults);
		}
	}

	public static void generateOrder(Queryable<? extends BaseEntity<? extends Serializable>, ? extends Serializable> queryable, Criteria criteria) {
		if(queryable.randomOrder!=null && queryable.randomOrder){
			//fake order:
			criteria.add(Restrictions.sqlRestriction("1=1 order by rand()"));
		}
		for(Entry<String, OrderType> entry : queryable.orderMap.entrySet()){
			Order order = entry.getValue().equals(OrderType.ascending) ? Order.asc(entry.getKey()) : Order.desc(entry.getKey());
			criteria.addOrder(order.ignoreCase());
		}
	}
}
