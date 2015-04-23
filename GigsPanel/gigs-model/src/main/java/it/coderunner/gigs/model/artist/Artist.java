package it.coderunner.gigs.model.artist;

import java.util.HashSet;
import java.util.Set;

import it.coderunner.gigs.model.BaseEntity;
import it.coderunner.gigs.model.gig.Gig;
import lombok.Getter;
import lombok.Setter;

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
	@Column(name = "tag_id_1")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tags")
	private Long tagID1;

	@Getter
	@Setter
	@Column(name = "tag_id_2")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tags")
	private Long tagID2;

	@Getter
	@Setter
	@Column(name = "tag_id_3")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tags")
	private Long tagID3;

	@Getter
	@Setter
	@Column(name = "tag_id_4")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tags")
	private Long tagID4;

	@Getter
	@Setter
	@Column(name = "tag_id_5")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tags")
	private Long tagID5;

}
