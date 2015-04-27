package it.coderunner.gigs.repository;

import it.coderunner.gigs.model.BaseEntity;

import java.io.Serializable;

/**
 * Podstawowy, generyczny interfejs realizujący warstwę dostępu do repozytorium (DAO)
 * <br><br>
 * <code>@Override<br>
	public Queryable<ConfigParameter, String> findAll() {<br>
		return new CriteriaConfigParameters(this);<br>
	}</code>
 *
 * @param <T> typ klasy dziedziczącej po {@link BaseEntity}
 * @param <S> typ klucza głównego klasy
 */
public interface IRepository<T extends BaseEntity<S>, S extends Serializable> extends Serializable {

	/**
	 * Zapisuje do repozytorium nową encję i nadaje jej klucz główny (ID)
	 * @param entity - musi być nowa, tzn. nie istnieje w bazie jej odpowiednik
	 * @return
	 */
	void save(T entity);

	/**
	 * Aktualizuje dane encji.
	 * @param entity
	 * @return
	 */
	void update(T entity);

	/**
	 * Zapis albo aktualizacja. Jeśli dana encja nie istnieje w repozytorium, 
	 * zapisuje ją i nadaje klucz główny (ID). Jeżeli encja istnieje, aktualizuje ją.
	 * @param entity
	 * @return
	 */
	void saveOrUpdate(T entity);

	/**
	 * Usuwa encję z repozytorium.
	 * @param entity
	 * @return
	 */
	void delete(T entity);
	
	/**
	 * Usuwa z sesji bieżący obiekt. WYkozystywane w celu zabezpieczenia przed podwójnym ładowaniem tego samego 
	 * obiektu do sesji (np. poprzez walidacji czy sprawdzania zmian z niezapisanym obiektem
	 * @param entity
	 */
	public void evict(T entity);
	
	Queryable<T, S> findAll();
}
