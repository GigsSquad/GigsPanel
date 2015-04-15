package it.coderunner.gigs.model.stat;

import it.coderunner.gigs.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Jakub on 2015-04-14.
 */
public class Stat extends BaseEntity<Long> {

    //todo nie ma tam w ogóle kolumny z AI ID, więc trzeba dorobić
    @Id
    @GeneratedValue
    @Getter
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

    @Getter
    @Setter
    private String spotscol; //ja nie wiem co to jest

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}
}
