package it.coderunner.gigs.model.comment;

import it.coderunner.gigs.model.BaseEntity;
import it.coderunner.gigs.model.gig.Gig;
import it.coderunner.gigs.model.user.User;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Jakub on 2015-04-14.
 */

@Entity
@Table(name="comments")
public class Comment extends BaseEntity<Long> {

    private static final long serialVersionUID = 7000239090602311328L;

    @Id
    @Getter
    @GeneratedValue
    private Long id;
    
    @Getter @Setter
    @JoinColumn(name="user_id")
    @ManyToOne(fetch=FetchType.LAZY)
    private User user;	
    
    @Getter @Setter
    @JoinColumn(name="gig_id")
    @ManyToOne(fetch=FetchType.LAZY)
    private Gig gig;
    
    @Getter @Setter
    private String comment;
    
    @Getter @Setter
    @Column(name="date_added")
    private Date dateAdded;
}
