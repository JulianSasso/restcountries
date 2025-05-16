package eu.fayder.restcountries.domain.countryinfo.country;

import lombok.Getter;

/**
 * Created by fayder on 05/03/2017.
 */
@Getter
// TODO: Esto deberia ser un Map<Enum, String> o algo asi
public class Translations {

    private String br;
    private String pt;
    private String nl;
    private String hr;
    private String fa;
    private String de;
	private String es;
	private String fr;
	private String ja;
	private String it;

	public void setDe(String de) {
		this.de = de;
	}

	public void setEs(String es) {
		this.es = es;
	}

	public void setFr(String fr) {
		this.fr = fr;
	}

	public void setJa(String ja) {
		this.ja = ja;
	}

	public void setIt(String it) {
		this.it = it;
	}

}
