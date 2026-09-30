package M6_UF1_A4.M6_UF1_A4;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

public class App {
	private static final String FTP_HOST = "localhost";
	private static final int FTP_PORT = 21;
	private static final String FTP_USER = "emisora";
	private static final String FTP_KEY = "emisora";
	
    public static void main( String[] args ) {
    	
    	XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.enable(ToXmlGenerator.Feature.WRITE_XML_DECLARATION);

        Scanner teclado = new Scanner(System.in);

        try (InputStream in = App.class.getResourceAsStream("/dades.xml")) {
            Dades dades = mapper.readValue(in, Dades.class);

            boolean seguir = true;
            while (seguir) {
                // 1. Lista de clientes
                System.out.println("\n=== CLIENTES ===");
                for (int i = 0; i < dades.getClients().size(); i++) {
                    System.out.println((i + 1) + ". " + dades.getClients().get(i).getNom());
                }
                Client clienteSeleccionado = dades.getClients().get(
                        leerInt(teclado, "Client: ", 1, dades.getClients().size()) - 1);

                // 2. Lista de productos
                System.out.println("\n=== PRODUCTOS ===");
                for (int i = 0; i < dades.getProductes().size(); i++) {
                    Producte producto = dades.getProductes().get(i);
                    System.out.println((i + 1) + ". " + producto.getDescripcio() + " (" + producto.getPreu() + " €)");
                }
                Producte productoSeleccionado = dades.getProductes().get(
                        leerInt(teclado, "Producto: ", 1, dades.getProductes().size()) - 1);

                // 3. Cantidad
                int cantidad = leerInt(teclado, "Cantidad: ", 1, 100000);

                // 4. Crear, guardar i validar
                int idComanda = (int) (System.currentTimeMillis() / 1000);
                Comandes comandes = new Comandes();
                comandes.getComandes().add(new Comanda(idComanda,
                        new ClientComanda(clienteSeleccionado.getNom(), clienteSeleccionado.getAdreca()),
                        new ProducteComanda(productoSeleccionado.getId(), productoSeleccionado.getDescripcio(), cantidad, productoSeleccionado.getPreu())));

                Files.createDirectories(Paths.get("comandes"));
                String nom = "comanda_" + LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".xml";
                File fichero = new File("comandes", nom);
                mapper.writeValue(fichero, comandes);

                if (ValidadorXml.esValid(fichero, "/comanda.xsd")) {
                    System.out.println("Comanda guardada y validada: " + fichero.getPath());
                    // 5. Espacio para el envio por FTP
                    boolean enviado = EnviarFTP.subir(fichero, FTP_HOST, FTP_PORT, FTP_USER, FTP_KEY);
                    if (enviado) {
                        System.out.println("Comanda enviada por FTP.");
                    } else {
                        System.out.println("No se pudo enviar. El archivo está en " + fichero.getPath());
                    }
                    
                    
                } else {
                    System.out.println("La comanda no es vàlida, así que no se enviará.");
                }

                System.out.print("\nAñadir otra comanda? (s/n): ");
                seguir = teclado.nextLine().trim().equalsIgnoreCase("s");
            }
        } catch (IOException e) {
            System.err.println("Error al leer o escribir los archivos: " + e.getMessage());
        }
    

    }
    private static int leerInt(Scanner teclado, String mensaje, int min, int max) {
        while (true) {
            System.out.print(mensaje);
            try {
                int n = Integer.parseInt(teclado.nextLine().trim());
                if (n >= min && n <= max) return n;
            } catch (NumberFormatException e) { /* cae al mensaje de abajo */ }
            System.out.println("Introdueix un número entre " + min + " i " + max + ".");
        }
    }
}
