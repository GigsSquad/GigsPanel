package it.coderunner.gigs.model.comment;

import java.sql.Date;

import it.coderunner.gigs.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Jakub on 2015-04-14.
 */

//TODO to nie jest skończone, brakuje AI ID, artist jako id?
@Entity
@Table(name="comments")
public class Comment extends BaseEntity<Long> {

    private static final long serialVersionUID = 7000239090602311328L;

    @Id
    @Getter
    @GeneratedValue
    private Long id;
    
    
    @Getter@Setter
    @Column(name="user_id")
    @ManyToOne(fetch=FetchType.LAZY)
    private Long userId;	
    
    
    @Getter@Setter
    @ManyToOne(fetch=FetchType.LAZY)
    @Column(name="concert_id")
    private Long concertId;
    
    @Getter@Setter
    private String comment;
    
    @Getter@Setter
    private Date added;
}
