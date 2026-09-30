package M6_UF1_A4.M6_UF1_A4;

import java.util.ArrayList;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "comandes")
public class Comandes {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "comanda")
    private ArrayList<Comanda> comandes = new ArrayList<>();

    public Comandes() {}
    public ArrayList<Comanda> getComandes() { 
    	return comandes; 
	}
}
