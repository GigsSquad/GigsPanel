package it.coderunner.gigs.model.spot;

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
@Table(name="spots")
public class Spot extends BaseEntity<Long> {

    private static final long serialVersionUID = 5447213465791330498L;

    @Id
    @GeneratedValue
    @Getter
    @OneToMany(mappedBy = "spots", targetEntity = Gig.class, cascade = CascadeType.ALL)
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
