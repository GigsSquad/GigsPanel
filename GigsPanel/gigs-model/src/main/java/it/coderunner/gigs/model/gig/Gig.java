package it.coderunner.gigs.model.gig;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.coderunner.gigs.model.BaseEntity;
import it.coderunner.gigs.model.artist.Artist;
import it.coderunner.gigs.model.comment.Comment;
import it.coderunner.gigs.model.spot.Spot;
import lombok.Getter;
import lombok.Setter;

/**
 * Lista koncertów, połączona ze spots oraz artists
 *
 * @author Jakub
 */

@Entity
@Table(name = "gigs")
public class Gig extends BaseEntity<Long> {

    private static final long serialVersionUID = -933739711197268160L;

    @Id
    @GeneratedValue
    @Getter
    private Long id;

    @Getter
    @Setter
    @OneToMany(mappedBy = "gig", targetEntity = Comment.class, cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    @Getter
    @Setter
    @JoinColumn(name = "id_spot")
    @ManyToOne(fetch = FetchType.LAZY)
    private Spot spot;

    @Getter
    @Setter
    private Date date;

    @Getter
    @Setter
    @Enumerated
    private Agency agency;

    @Getter
    @Setter
    private String url;

    @Getter
    @Setter
    @Column(name = "entrance_fee")
    private EntranceFee entranceFee;

    @Getter
    @Setter
    private Date updated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_artist")
    @Setter
    private Artist artist;

    public Artist getArtist() {
        return artist;
    }

    public Gig() {
    }

    public Gig(Artist artist, Spot spot, Date date, Agency agency, String url) {
        this.artist = artist;
        this.spot = spot;
        this.date = date;
        this.agency = agency;
        this.url = url;
    }
}
