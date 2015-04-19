package it.coderunner.gigs.model.gig;

import it.coderunner.gigs.model.BaseEntity;
import it.coderunner.gigs.model.artist.Artist;
import it.coderunner.gigs.model.comment.Comment;
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

import net.sf.ehcache.transaction.xa.commands.Command;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    @OneToMany(mappedBy = "gigs", targetEntity = Comment.class, cascade = CascadeType.ALL)
    private Set<Comment> comments= new HashSet<>();
    
    @Getter
    @Setter
    @Column(name = "id_spot") // to jest chyba, zeby dobrze mapowal do bazy
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_spot") //to jest do joina?
    private Long spotId;

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
    
    @Column(name = "id_artist")
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_artist")
    @Getter @Setter
    private Artist artist;

}
