package it.coderunner.gigs.model.gig;

import it.coderunner.gigs.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Jakub on 2015-04-14.
 */
public class Gig extends BaseEntity<Long> {

    private static final long serialVersionUID = -933739711197268160L;

    @Id
    @GeneratedValue
    @Getter
    private Long id;

    //todo relacja
    @Getter
    @Setter
    @Column(name = "id_artist") //kto wymyślał te nazwy, raz jest id_artist, raz artist_id
    private Long artistId;

    //todo relacja
    @Getter
    @Setter
    @Column(name = "id_spot")
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
}
