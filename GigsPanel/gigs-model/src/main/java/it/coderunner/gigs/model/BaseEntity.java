package it.coderunner.gigs.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Transient;

import org.hibernate.proxy.HibernateProxyHelper;

import lombok.Getter;
import lombok.Setter;

/**
 * Podstawowa klasa, po której powinny dziedziczyć wszystkie encje zapisywalne
 * do repozytorium.
 * 
 * @param <T>- typ klucza głównego
 */
public abstract class BaseEntity<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Zwraca klucz główny (ID) encji.
	 * 
	 * @return
	 */
	public abstract T getId();

	/**
	 * Sprawdza, czy encja jest nowa (nie widnieje w repozytorium)
	 * @return
	 */
	public boolean isNew(){
		return getId()==null;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (this.getId() != null ? this.getId().hashCode() : 0);
		return hash;
	}
	
	@SuppressWarnings("rawtypes")
	@Override  
    public boolean equals(Object object) {  
    if (this == object)  
            return true;  
        if (object == null)  
            return false;  
        Class thisClass = HibernateProxyHelper.getClassWithoutInitializingProxy(this);
        Class objectClass = HibernateProxyHelper.getClassWithoutInitializingProxy(object);
        if (thisClass != objectClass)  
            return false;  
        BaseEntity other = (BaseEntity) object;  
        if (this.getId() != other.getId() && (this.getId() == null || !this.getId().equals(other.getId()))) {  
            return false;  
        }  
        return true;  
    }  
	
	/**
	 * Mapa dodatkowych obiektów, jakie możemy "dorzucić" obiektowi. Przydatne np. podczas wywoływania 
	 * RESTowych metod i dorzucaniu dodatkowych parametrów do obiektu, które sam nie przechowuje. 
	 * Mapa parametrów nie jest przechowywana w repozytorium i jest tracona po straceniu referencji do obiektu.
	 */
	@Transient
	@Getter @Setter
	private Map<String, Object> extraFields = new HashMap<String, Object>();		
}
