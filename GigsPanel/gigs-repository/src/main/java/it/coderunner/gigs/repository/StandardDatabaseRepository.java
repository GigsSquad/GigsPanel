package it.coderunner.gigs.repository;

import it.coderunner.gigs.model.BaseEntity;

import java.io.Serializable;

import lombok.Setter;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Klasa odpowiedzialna za komunikację z bazą danych (repozytorium). Zawiera generyczne metody
 * odpowiedzialne za podstawową komunikację z bazą danych (odczyt, zapis, usuwanie etc.)
 * @author andrzej
 *
 * @param <T> typ klasy dziedziczącej po {@link BaseEntity}
 * @param <S> typ klucza głównego klasy
 */
public abstract class StandardDatabaseRepository<T extends BaseEntity<S>, S extends Serializable> implements Serializable {

	private static final long serialVersionUID 	= 1L;
	
	/**
	 * Zwraca klasę {@link Class} encji
	 * @return
	 */
	public abstract Class<? extends BaseEntity<S>> getEntityClass();

	/**
	 * Fabryka sesji bazodanowej.
	 */
	@Autowired	
	@Setter
	protected SessionFactory sessionFactory;

	/**
	 * Zwraca sesję z fabryki. Domyślnie zwraca bieżącą sesję
	 * @return
	 */
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	/**
	 * Tworzy zapytanie do bazy oparte o język HQL.
	 * @param query - zapytanie
	 * @return
	 */
	protected Query createQuery(String query){
		return getSession().createQuery(query);
	}
	
	/**
	 * Tworzy zapytanie do bazy oparte o standardowy język SQL.
	 * @param query - zapytanie
	 * @return
	 */
	protected SQLQuery createSQLQuery(String query){
		return getSession().createSQLQuery(query);
	}
	
	/**
	 * Tworzy zapytanie oparte o Hibernate Criteria
	 * @return
	 */
	protected Criteria createCriteria(){
		return getSession().createCriteria(getEntityClass());
	}
	
	/**
	 * Tworzy zapytanie oparte o Hibernate Criteria w zadanym aliasie
	 * @return
	 */
	protected Criteria createCriteria(String alias){
		return getSession().createCriteria(getEntityClass(), alias);
	}
	
	/**
	 * Zapisuje do repozytorium nową encję i nadaje jej klucz główny (ID)
	 * @param entity - musi być nowa, tzn. nie istnieje w bazie jej odpowiednik
	 * @return
	 */
	public void save(T entity) {
		getSession().save(entity);
		getSession().flush();
	}

	/**
	 * Aktualizuje dane encji.
	 * @param entity
	 * @return
	 */
	public void update(T entity) {
		getSession().update(entity);
		getSession().flush();
	}

	/**
	 * Zapis albo aktualizacja. Jeśli dana encja nie istnieje w repozytorium, 
	 * zapisuje ją i nadaje klucz główny (ID). Jeżeli encja istnieje, aktualizuje ją.
	 * @param entity
	 * @return
	 */
	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
		getSession().flush();
	}

	/**
	 * Usuwa encję z repozytorium.
	 * @param entity
	 * @return
	 */
	public void delete(T entity) {
		getSession().delete(entity);
		//getSession().flush();
	}

	/**
	 * Usuwa z sesji bieżący obiekt. Wykozystywane w celu zabezpieczenia przed podwójnym ładowaniem tego samego 
	 * obiektu do sesji (np. poprzez walidacji czy sprawdzania zmian z niezapisanym obiektem
	 * @param entity
	 */
	public void evict(T entity){
		if (entity!=null)getSession().evict(entity);
	}

}
