package it.coderunner.gigs.model.spot;

import it.coderunner.gigs.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Jakub on 2015-04-14.
 */
public class Spot extends BaseEntity<Long> {

    private static final long serialVersionUID = 5447213465791330498L;

    @Id
    @GeneratedValue
    @Getter
    @Column(name = "id_spot")
    private Long id;

    @Getter
    @Setter
    private String city;

    @Getter
    @Setter
    private String spot;

    @Getter
    @Setter
    private double lat;

    @Getter
    @Setter
    private double lon;

    @Getter
    @Setter
    private String country;
}
