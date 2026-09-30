package M6_UF1_A4.M6_UF1_A4;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"nom", "adreca"})
public class ClientComanda {
	private String nom;
    private String adreca;

    public ClientComanda() {}
    public ClientComanda(String nom, String adreca) { 
    	this.nom = nom; this.adreca = adreca; 
	}
    public String getNom() { 
    	return nom; 
	}
    public String getAdreca() { 
    	return adreca; 
	}
}
