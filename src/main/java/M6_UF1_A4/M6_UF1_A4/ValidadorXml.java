package M6_UF1_A4.M6_UF1_A4;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

public class ValidadorXml {
    public static boolean esValid(File xml, String xsdResource) {
        try {
            Schema schema = SchemaFactory
                    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(ValidadorXml.class.getResource(xsdResource));
            schema.newValidator().validate(new StreamSource(xml));
            return true;
        } catch (SAXException e) {
            System.err.println("XML inválido: " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.err.println("No se puede leer el archivo: " + e.getMessage());
            return false;
        }
    }
}
