package it.coderunner.gigs.repository;

import lombok.Getter;

/**
 * Rodzaj sortowania
 * @author andrzej
 *
 */
public enum OrderType {

	/**
	 * malejąco
	 */
	descending("ordertype.descending"),
	
	/**
	 * rosnąco
	 */
	ascending("ordertype.ascending");
	
	private OrderType(String nameLabel){
		this.nameLabel = nameLabel;
	}
	@Getter
	private String nameLabel;
}
