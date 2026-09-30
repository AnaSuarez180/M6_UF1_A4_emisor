package M6_UF1_A4.M6_UF1_A4;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonPropertyOrder({"client", "producte"})
public class Comanda {
    @JacksonXmlProperty(isAttribute = true)
    private int id;
    private ClientComanda client;
    private ProducteComanda producte;

    public Comanda() {}
    public Comanda(int id, ClientComanda client, ProducteComanda producte) {
        this.id = id; this.client = client; this.producte = producte;
    }
    public int getId() { 
    	return id; 
	}
    public ClientComanda getClient() { 
    	return client; 
	}
    public ProducteComanda getProducte() { 
    	return producte; 
	}
}
