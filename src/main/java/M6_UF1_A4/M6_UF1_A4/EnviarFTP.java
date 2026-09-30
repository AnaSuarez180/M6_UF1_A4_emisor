package M6_UF1_A4.M6_UF1_A4;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class EnviarFTP {
	 public static boolean subir(File archivo, String host, int port, String user, String key) {
	        FTPClient ftp = new FTPClient();
	        boolean enviado = false;

	        try {
	            ftp.connect(host, port);

	            if (ftp.login(user, key)) {
	                ftp.enterLocalPassiveMode();
	                ftp.setFileType(FTP.BINARY_FILE_TYPE);

	                try (InputStream in = new FileInputStream(archivo)) {
	                    enviado = ftp.storeFile(archivo.getName(), in);
	                }
	                ftp.logout();
	            } else {
	                System.err.println("Credenciales incorrectas");
	            }
	            desconnectar(ftp);

	        } catch (IOException e) {
	            System.err.println("Error FTP: " + e.getMessage());
	            desconnectar(ftp);
	        }

	        return enviado;
	 }
	 private static void desconnectar(FTPClient ftp) {
	        if (ftp.isConnected()) {
	            try {
	                ftp.disconnect();
	            } catch (IOException e) {
	                System.err.println(e);
	            }
	        }
	    }
}
