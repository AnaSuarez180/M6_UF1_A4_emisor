package M6_UF1_A4.M6_UF1_A4;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dades {

    @JacksonXmlElementWrapper(localName = "clients")
    @JacksonXmlProperty(localName = "client")
    private ArrayList<Client> clients;

    @JacksonXmlElementWrapper(localName = "productes")
    @JacksonXmlProperty(localName = "producte")
    private ArrayList<Producte> productes;

    public Dades() {}
    
    public ArrayList<Client> getClients() {
    	return clients;
    }
    public ArrayList<Producte> getProductes() {
    	return productes;
    }

}