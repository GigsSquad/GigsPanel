package it.coderunner.gigs.model.gig;

import it.coderunner.gigs.model.BaseEntity;
import it.coderunner.gigs.model.artist.Artist;
import it.coderunner.gigs.model.comment.Comment;
import it.coderunner.gigs.model.spot.Spot;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
 * Tabela gigs:
 * polaczenia z tabela :
 * 	artists (ManyToOne)
 * 	spots(ManyToOne)
 * 	
 */
@Entity
@Table(name="gigs")
public class Gig extends BaseEntity<Long> {

    private static final long serialVersionUID = -933739711197268160L;

    @Id
    @GeneratedValue
    @Getter
    private Long id;

    @Getter @Setter
    @OneToMany(mappedBy = "gig", targetEntity = Comment.class, cascade = CascadeType.ALL)
    private Set<Comment> comments= new HashSet<>();
    
    @Getter
    @Setter
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_spot")
    private Spot spot;

    @Getter
    @Setter
    private Date date;

    @Getter
    @Setter
    private String agency;

    @Getter
    @Setter
    private String url;

    @Getter
    @Setter
    private Date updated;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_artist")
    @Getter @Setter
    private Artist artist;

}
