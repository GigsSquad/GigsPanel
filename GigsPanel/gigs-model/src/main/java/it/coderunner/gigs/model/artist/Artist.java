package it.coderunner.gigs.model.artist;

import it.coderunner.gigs.model.BaseEntity;
import it.coderunner.gigs.model.gig.Gig;
import it.coderunner.gigs.model.tag.Tag;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Jakub on 2015-04-14.
 */

@Entity
@Table(name = "artists")
public class Artist extends BaseEntity<Long> {

	private static final long serialVersionUID = 562030051334550950L;

	
	
	@Id
	@GeneratedValue
	@Getter
	@Column(name = "id_artist")
	private Long id;

	@Getter
	@Setter
	private String artist;

	@Getter
	@Setter
	@OneToMany(mappedBy = "artist", targetEntity = Gig.class, cascade = CascadeType.ALL)
	private Set<Gig> gig = new HashSet<Gig>();	
	
	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tag")
	private Tag tag;

}
