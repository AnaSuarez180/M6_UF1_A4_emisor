package M6_UF1_A4.M6_UF1_A4;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "descripcio", "quantitat", "preu"})
public class ProducteComanda {
	private String id;
    private String descripcio;
    private int quantitat;
    private double preu;

    public ProducteComanda() {}
    public ProducteComanda(String id, String descripcio, int quantitat, double preu) {
        this.id = id; this.descripcio = descripcio;
        this.quantitat = quantitat; this.preu = preu;
    }

    public String getId() { 
    	return id; 
	}
    public String getDescripcio() { 
    	return descripcio; 
	}
    public int getQuantitat() { 
    	return quantitat; 
	}
    public double getPreu() { 
    	return preu; 
	}
}
