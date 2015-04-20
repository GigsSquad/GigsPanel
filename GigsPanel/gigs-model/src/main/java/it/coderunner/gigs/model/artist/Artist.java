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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Created by Jakub on 2015-04-14.
 */

@Entity
@Table(name="artists")
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
    
    @OneToMany(mappedBy = "artist", targetEntity = Gig.class, cascade = CascadeType.ALL)
    @Getter @Setter
    private Set<Gig> gig = new HashSet<Gig>();

    //todo tutaj będą relacje
    @Getter
    @Setter
    @Column(name = "tag_id_1")
    private Long tagID1;

    @Getter
    @Setter
    @Column(name = "tag_id_2")
    private Long tagID2;

    @Getter
    @Setter
    @Column(name = "tag_id_3")
    private Long tagID3;

    @Getter
    @Setter
    @Column(name = "tag_id_4")
    private Long tagID4;

    @Getter
    @Setter
    @Column(name = "tag_id_5")
    private Long tagID5;

}
