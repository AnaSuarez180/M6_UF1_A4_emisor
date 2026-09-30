package M6_UF1_A4.M6_UF1_A4;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Producte {
	@JacksonXmlProperty(isAttribute = true)
    private String id;
    private String descripcio;
    private double preu;
    
    Producte() {    	
    }
    
    public String getId() {
    	return this.id;
    }
    public String getDescripcio() {
    	return this.descripcio;
    }
    public double getPreu() {
    	return this.preu;
    }
}
