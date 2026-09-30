package M6_UF1_A4.M6_UF1_A4;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Client {
	@JacksonXmlProperty(isAttribute = true)
	private String id;
	private String nom;
    private String adreca;
    
    public Client() {
	}
	public String getNom() {
    	return this.nom;
    }
    public String getAdreca() {
    	return this.adreca;
    }
}

