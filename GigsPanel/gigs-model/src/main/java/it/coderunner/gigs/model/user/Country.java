package it.coderunner.gigs.model.user;

import it.coderunner.gigs.i18n.resolver.MessageResolver;

import java.util.Arrays;
import java.util.Comparator;

import lombok.Getter;

/**
 * Zbiór państw
 */
public enum Country {

	AFGHANISTAN ("country.afghanistan.name", "af"),
	ALAND_ISLANDS ("country.aland_islands.name", "ax"),
	ALBANIA ("country.albania.name", "al"),
	ALGERIA ("country.algeria.name", "dz"),
	AMERICAN_SAMOA ("country.american_samoa.name", "as"),
	ANDORRA ("country.andorra.name", "ad"),
	ANGOLA ("country.angola.name", "ao"),
	ANGUILLA ("country.anguilla.name", "ai"),
	ANTARCTICA ("country.antarctica.name", "aq"),
	ANTIGUA_AND_BARBUDA ("country.antigua_and_barbuda.name", "ag"),
	ARGENTINA ("country.argentina.name", "ar"),
	ARMENIA ("country.armenia.name", "am"),
	ARUBA ("country.aruba.name", "aw"),
	AUSTRALIA ("country.australia.name", "au"),
	AUSTRIA ("country.austria.name", "at"),
	AZERBAIJAN ("country.azerbaijan.name", "az"),
	BAHAMAS ("country.bahamas.name", "bs"),
	BAHRAIN ("country.bahrain.name", "bh"),
	BANGLADESH ("country.bangladesh.name", "bd"),
	BARBADOS ("country.barbados.name", "bb"),
	BELARUS ("country.belarus.name", "by"),
	BELGIUM ("country.belgium.name", "be"),
	BELIZE ("country.belize.name", "bz"),
	BENIN ("country.benin.name", "bj"),
	BERMUDA ("country.bermuda.name", "bm"),
	BHUTAN ("country.bhutan.name", "bt"),
	BOLIVIA_PLURINATIONAL_STATE_OF ("country.bolivia_plurinational_state_of.name", "bo"),
	BONAIRE_SINT_EUSTATIUS_AND_SABA ("country.bonaire_sint_eustatius_and_saba.name", "bq"),
	BOSNIA_AND_HERZEGOVINA ("country.bosnia_and_herzegovina.name", "ba"),
	BOTSWANA ("country.botswana.name", "bw"),
	BOUVET_ISLAND ("country.bouvet_island.name", "bv"),
	BRAZIL ("country.brazil.name", "br"),
	BRITISH_INDIAN_OCEAN_TERRITORY ("country.british_indian_ocean_territory.name", "io"),
	BRUNEI_DARUSSALAM ("country.brunei_darussalam.name", "bn"),
	BULGARIA ("country.bulgaria.name", "bg"),
	BURKINA_FASO ("country.burkina_faso.name", "bf"),
	BURUNDI ("country.burundi.name", "bi"),
	CAMBODIA ("country.cambodia.name", "kh"),
	CAMEROON ("country.cameroon.name", "cm"),
	CANADA ("country.canada.name", "ca"),
	CAPE_VERDE ("country.cape_verde.name", "cv"),
	CAYMAN_ISLANDS ("country.cayman_islands.name", "ky"),
	CENTRAL_AFRICAN_REPUBLIC ("country.central_african_republic.name", "cf"),
	CHAD ("country.chad.name", "td"),
	CHILE ("country.chile.name", "cl"),
	CHINA ("country.china.name", "cn"),
	CHRISTMAS_ISLAND ("country.christmas_island.name", "cx"),
	COCOS_KEELING_ISLANDS ("country.cocos_keeling_islands.name", "cc"),
	COLOMBIA ("country.colombia.name", "co"),
	COMOROS ("country.comoros.name", "km"),
	CONGO ("country.congo.name", "cg"),
	CONGO_THE_DEMOCRATIC_REPUBLIC_OF_THE ("country.congo_the_democratic_republic_of_the.name", "cd"),
	COOK_ISLANDS ("country.cook_islands.name", "ck"),
	COSTA_RICA ("country.costa_rica.name", "cr"),
	COTE_DIVOIRE ("country.cote_divoire.name", "ci"),
	CROATIA ("country.croatia.name", "hr"),
	CUBA ("country.cuba.name", "cu"),
	CURACAO ("country.curacao.name", "cw"),
	CYPRUS ("country.cyprus.name", "cy"),
	CZECH_REPUBLIC ("country.czech_republic.name", "cz"),
	DENMARK ("country.denmark.name", "dk"),
	DJIBOUTI ("country.djibouti.name", "dj"),
	DOMINICA ("country.dominica.name", "dm"),
	DOMINICAN_REPUBLIC ("country.dominican_republic.name", "do"),
	ECUADOR ("country.ecuador.name", "ec"),
	EGYPT ("country.egypt.name", "eg"),
	EL_SALVADOR ("country.el_salvador.name", "sv"),
	EQUATORIAL_GUINEA ("country.equatorial_guinea.name", "gq"),
	ERITREA ("country.eritrea.name", "er"),
	ESTONIA ("country.estonia.name", "ee"),
	ETHIOPIA ("country.ethiopia.name", "et"),
	FALKLAND_ISLANDS_MALVINAS ("country.falkland_islands_malvinas.name", "fk"),
	FAROE_ISLANDS ("country.faroe_islands.name", "fo"),
	FIJI ("country.fiji.name", "fj"),
	FINLAND ("country.finland.name", "fi"),
	FRANCE ("country.france.name", "fr"),
	FRENCH_GUIANA ("country.french_guiana.name", "gf"),
	FRENCH_POLYNESIA ("country.french_polynesia.name", "pf"),
	FRENCH_SOUTHERN_TERRITORIES ("country.french_southern_territories.name", "tf"),
	GABON ("country.gabon.name", "ga"),
	GAMBIA ("country.gambia.name", "gm"),
	GEORGIA ("country.georgia.name", "ge"),
	GERMANY ("country.germany.name", "de"),
	GHANA ("country.ghana.name", "gh"),
	GIBRALTAR ("country.gibraltar.name", "gi"),
	GREECE ("country.greece.name", "gr"),
	GREENLAND ("country.greenland.name", "gl"),
	GRENADA ("country.grenada.name", "gd"),
	GUADELOUPE ("country.guadeloupe.name", "gp"),
	GUAM ("country.guam.name", "gu"),
	GUATEMALA ("country.guatemala.name", "gt"),
	GUERNSEY ("country.guernsey.name", "gg"),
	GUINEA ("country.guinea.name", "gn"),
	GUINEA_BISSAU ("country.guinea-bissau.name", "gw"),
	GUYANA ("country.guyana.name", "gy"),
	HAITI ("country.haiti.name", "ht"),
	HEARD_ISLAND_AND_MCDONALD_ISLANDS ("country.heard_island_and_mcdonald_islands.name", "hm"),
	HOLY_SEE_VATICAN_CITY_STATE ("country.holy_see_vatican_city_state.name", "va"),
	HONDURAS ("country.honduras.name", "hn"),
	HONG_KONG ("country.hong_kong.name", "hk"),
	HUNGARY ("country.hungary.name", "hu"),
	ICELAND ("country.iceland.name", "is"),
	INDIA ("country.india.name", "in"),
	INDONESIA ("country.indonesia.name", "id"),
	IRAN_ISLAMIC_REPUBLIC_OF ("country.iran_islamic_republic_of.name", "ir"),
	IRAQ ("country.iraq.name", "iq"),
	IRELAND ("country.ireland.name", "ie"),
	ISLE_OF_MAN ("country.isle_of_man.name", "im"),
	ISRAEL ("country.israel.name", "il"),
	ITALY ("country.italy.name", "it"),
	JAMAICA ("country.jamaica.name", "jm"),
	JAPAN ("country.japan.name", "jp"),
	JERSEY ("country.jersey.name", "je"),
	JORDAN ("country.jordan.name", "jo"),
	KAZAKHSTAN ("country.kazakhstan.name", "kz"),
	KENYA ("country.kenya.name", "ke"),
	KIRIBATI ("country.kiribati.name", "ki"),
	KOREA_DEMOCRATIC_PEOPLES_REPUBLIC_OF ("country.korea_democratic_peoples_republic_of.name", "kp"),
	KOREA_REPUBLIC_OF ("country.korea_republic_of.name", "kr"),
	KUWAIT ("country.kuwait.name", "kw"),
	KYRGYZSTAN ("country.kyrgyzstan.name", "kg"),
	LAO_PEOPLES_DEMOCRATIC_REPUBLIC ("country.lao_peoples_democratic_republic.name", "la"),
	LATVIA ("country.latvia.name", "lv"),
	LEBANON ("country.lebanon.name", "lb"),
	LESOTHO ("country.lesotho.name", "ls"),
	LIBERIA ("country.liberia.name", "lr"),
	LIBYAN_ARAB_JAMAHIRIYA ("country.libyan_arab_jamahiriya.name", "ly"),
	LIECHTENSTEIN ("country.liechtenstein.name", "li"),
	LITHUANIA ("country.lithuania.name", "lt"),
	LUXEMBOURG ("country.luxembourg.name", "lu"),
	MACAO ("country.macao.name", "mo"),
	MACEDONIA_THE_FORMER_YUGOSLAV_REPUBLIC_OF ("country.macedonia_the_former_yugoslav_republic_of.name", "mk"),
	MADAGASCAR ("country.madagascar.name", "mg"),
	MALAWI ("country.malawi.name", "mw"),
	MALAYSIA ("country.malaysia.name", "my"),
	MALDIVES ("country.maldives.name", "mv"),
	MALI ("country.mali.name", "ml"),
	MALTA ("country.malta.name", "mt"),
	MARSHALL_ISLANDS ("country.marshall_islands.name", "mh"),
	MARTINIQUE ("country.martinique.name", "mq"),
	MAURITANIA ("country.mauritania.name", "mr"),
	MAURITIUS ("country.mauritius.name", "mu"),
	MAYOTTE ("country.mayotte.name", "yt"),
	MEXICO ("country.mexico.name", "mx"),
	MICRONESIA_FEDERATED_STATES_OF ("country.micronesia_federated_states_of.name", "fm"),
	MOLDOVA_REPUBLIC_OF ("country.moldova_republic_of.name", "md"),
	MONACO ("country.monaco.name", "mc"),
	MONGOLIA ("country.mongolia.name", "mn"),
	MONTENEGRO ("country.montenegro.name", "me"),
	MONTSERRAT ("country.montserrat.name", "ms"),
	MOROCCO ("country.morocco.name", "ma"),
	MOZAMBIQUE ("country.mozambique.name", "mz"),
	MYANMAR ("country.myanmar.name", "mm"),
	NAMIBIA ("country.namibia.name", "na"),
	NAURU ("country.nauru.name", "nr"),
	NEPAL ("country.nepal.name", "np"),
	NETHERLANDS ("country.netherlands.name", "nl"),
	NEW_CALEDONIA ("country.new_caledonia.name", "nc"),
	NEW_ZEALAND ("country.new_zealand.name", "nz"),
	NICARAGUA ("country.nicaragua.name", "ni"),
	NIGER ("country.niger.name", "ne"),
	NIGERIA ("country.nigeria.name", "ng"),
	NIUE ("country.niue.name", "nu"),
	NORFOLK_ISLAND ("country.norfolk_island.name", "nf"),
	NORTHERN_MARIANA_ISLANDS ("country.northern_mariana_islands.name", "mp"),
	NORWAY ("country.norway.name", "no"),
	OMAN ("country.oman.name", "om"),
	PAKISTAN ("country.pakistan.name", "pk"),
	PALAU ("country.palau.name", "pw"),
	PALESTINIAN_TERRITORY_OCCUPIED ("country.palestinian_territory_occupied.name", "ps"),
	PANAMA ("country.panama.name", "pa"),
	PAPUA_NEW_GUINEA ("country.papua_new_guinea.name", "pg"),
	PARAGUAY ("country.paraguay.name", "py"),
	PERU ("country.peru.name", "pe"),
	PHILIPPINES ("country.philippines.name", "ph"),
	PITCAIRN ("country.pitcairn.name", "pn"),
	POLAND ("country.poland.name", "pl"),
	PORTUGAL ("country.portugal.name", "pt"),
	PUERTO_RICO ("country.puerto_rico.name", "pr"),
	QATAR ("country.qatar.name", "qa"),
	REUNION ("country.reunion.name", "re"),
	ROMANIA ("country.romania.name", "ro"),
	RUSSIAN_FEDERATION ("country.russian_federation.name", "ru"),
	RWANDA ("country.rwanda.name", "rw"),
	SAINT_BARTHELEMY ("country.saint_barthelemy.name", "bl"),
	SAINT_HELENA_ASCENSION_AND_TRISTAN_DA_CUNHA ("country.saint_helena_ascension_and_tristan_da_cunha.name", "sh"),
	SAINT_KITTS_AND_NEVIS ("country.saint_kitts_and_nevis.name", "kn"),
	SAINT_LUCIA ("country.saint_lucia.name", "lc"),
	SAINT_MARTIN_FRENCH_PART ("country.saint_martin_french_part.name", "mf"),
	SAINT_PIERRE_AND_MIQUELON ("country.saint_pierre_and_miquelon.name", "pm"),
	SAINT_VINCENT_AND_THE_GRENADINES ("country.saint_vincent_and_the_grenadines.name", "vc"),
	SAMOA ("country.samoa.name", "ws"),
	SAN_MARINO ("country.san_marino.name", "sm"),
	SAO_TOME_AND_PRINCIPE ("country.sao_tome_and_principe.name", "st"),
	SAUDI_ARABIA ("country.saudi_arabia.name", "sa"),
	SENEGAL ("country.senegal.name", "sn"),
	SERBIA ("country.serbia.name", "rs"),
	SEYCHELLES ("country.seychelles.name", "sc"),
	SIERRA_LEONE ("country.sierra_leone.name", "sl"),
	SINGAPORE ("country.singapore.name", "sg"),
	SINT_MAARTEN_DUTCH_PART ("country.sint_maarten_dutch_part.name", "sx"),
	SLOVAKIA ("country.slovakia.name", "sk"),
	SLOVENIA ("country.slovenia.name", "si"),
	SOLOMON_ISLANDS ("country.solomon_islands.name", "sb"),
	SOMALIA ("country.somalia.name", "so"),
	SOUTH_AFRICA ("country.south_africa.name", "za"),
	SOUTH_GEORGIA_AND_THE_SOUTH_SANDWICH_ISLANDS ("country.south_georgia_and_the_south_sandwich_islands.name", "gs"),
	SOUTH_SUDAN ("country.south_sudan.name", "ss"),
	SPAIN ("country.spain.name", "es"),
	SRI_LANKA ("country.sri_lanka.name", "lk"),
	SUDAN ("country.sudan.name", "sd"),
	SURINAME ("country.suriname.name", "sr"),
	SVALBARD_AND_JAN_MAYEN ("country.svalbard_and_jan_mayen.name", "sj"),
	SWAZILAND ("country.swaziland.name", "sz"),
	SWEDEN ("country.sweden.name", "se"),
	SWITZERLAND ("country.switzerland.name", "ch"),
	SYRIAN_ARAB_REPUBLIC ("country.syrian_arab_republic.name", "sy"),
	TAIWAN_PROVINCE_OF_CHINA ("country.taiwan_province_of_china.name", "tw"),
	TAJIKISTAN ("country.tajikistan.name", "tj"),
	TANZANIA_UNITED_REPUBLIC_OF ("country.tanzania_united_republic_of.name", "tz"),
	THAILAND ("country.thailand.name", "th"),
	TIMOR_LESTE ("country.timor-leste.name", "tl"),
	TOGO ("country.togo.name", "tg"),
	TOKELAU ("country.tokelau.name", "tk"),
	TONGA ("country.tonga.name", "to"),
	TRINIDAD_AND_TOBAGO ("country.trinidad_and_tobago.name", "tt"),
	TUNISIA ("country.tunisia.name", "tn"),
	TURKEY ("country.turkey.name", "tr"),
	TURKMENISTAN ("country.turkmenistan.name", "tm"),
	TURKS_AND_CAICOS_ISLANDS ("country.turks_and_caicos_islands.name", "tc"),
	TUVALU ("country.tuvalu.name", "tv"),
	UGANDA ("country.uganda.name", "ug"),
	UKRAINE ("country.ukraine.name", "ua"),
	UNITED_ARAB_EMIRATES ("country.united_arab_emirates.name", "ae"),
	UNITED_KINGDOM ("country.united_kingdom.name", "gb"),
	UNITED_STATES ("country.united_states.name", "us"),
	UNITED_STATES_MINOR_OUTLYING_ISLANDS ("country.united_states_minor_outlying_islands.name", "um"),
	URUGUAY ("country.uruguay.name", "uy"),
	UZBEKISTAN ("country.uzbekistan.name", "uz"),
	VANUATU ("country.vanuatu.name", "vu"),
	VENEZUELA_BOLIVARIAN_REPUBLIC_OF ("country.venezuela_bolivarian_republic_of.name", "ve"),
	VIET_NAM ("country.viet_nam.name", "vn"),
	VIRGIN_ISLANDS_BRITISH ("country.virgin_islands_british.name", "vg"),
	VIRGIN_ISLANDS_US ("country.virgin_islands_us.name", "vi"),
	WALLIS_AND_FUTUNA ("country.wallis_and_futuna.name", "wf"),
	WESTERN_SAHARA ("country.western_sahara.name", "eh"),
	YEMEN ("country.yemen.name", "ye"),
	ZAMBIA ("country.zambia.name", "zm"),
	ZIMBABWE ("country.zimbabwe.name", "zw");


	
	private Country(String name, String symbol){
		this.nameLabel=name;
		this.symbol = symbol;
	}
	
	/**
	 * kod etykiety (message) przechowujący nazwę państwa we wskazanej wersji językowej
	 */
	@Getter
	private String nameLabel;
	
	@Getter
	private String symbol;
	
	public String getNameValue(MessageResolver messageResolver){		
		return messageResolver.resolveMessage(getNameLabel());
	}
	
	/**
	 * Zwraca posortowaną listę państw wg nazwy w zależności od obowiązującej wersji językowej
	 * @return
	 */
	public static Country[] sortedByName(MessageResolver messageResolver){
		Country[] countries = values();
		Arrays.sort(countries, new NameValueComparator(messageResolver));
		return countries;
	}

}

class NameValueComparator implements Comparator<Country>{

	private MessageResolver messageResolver;
	
	public NameValueComparator(MessageResolver messageResolver){
		this.messageResolver = messageResolver;
	}
	
	@Override
	public int compare(Country o1, Country o2) {
		String label1 = messageResolver.resolveMessage(o1.getNameLabel());
		String label2 = messageResolver.resolveMessage(o2.getNameLabel());
		return messageResolver.getCollator().compare(label1, label2);
	}
	
}
