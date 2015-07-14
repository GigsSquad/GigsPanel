package it.coderunner.gigs.model.tag;

import java.util.HashSet;
import java.util.Set;

import it.coderunner.gigs.model.BaseEntity;
import it.coderunner.gigs.model.artist.Artist;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * To będzie lista dostępnych tagów, z niej będziemy wybierać najlepsze dla danego artysty
 * @author Jakub
 *
 */

@Entity
@Table(name = "tags")
public class Tag extends BaseEntity<Long>{

	private static final long serialVersionUID = 1291640208250665458L;

	@Id
	@GeneratedValue
	@Getter
	private Long id;
	
	@Getter @Setter
	private String tag;
	
	@Getter
	@Setter
	@OneToMany(mappedBy = "tag", targetEntity = Artist.class, cascade = CascadeType.ALL)
	private Set<Artist> artists = new HashSet<>();
	
	public Tag(){
		this.tag = "blank";
	}
	public Tag(String tagName){
		this.tag = tagName;
	}
}
