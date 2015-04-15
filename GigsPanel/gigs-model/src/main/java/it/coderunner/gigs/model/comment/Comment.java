package it.coderunner.gigs.model.comment;

import it.coderunner.gigs.model.BaseEntity;
import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Jakub on 2015-04-14.
 */

//TODO to nie jest skończone, brakuje AI ID, artist jako id?
public class Comment extends BaseEntity<Long> {

    private static final long serialVersionUID = 7000239090602311328L;

    @Id
    @Getter
    @GeneratedValue
    private Long id;

}
