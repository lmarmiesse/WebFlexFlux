package controler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.Part;

public class FileUpload {

	public static final int SIZE_TAMPON = 10240;

	public static String uploadFile(Part part, String filesPath) throws IOException {

		String fileName = getFileName(part);

		if (fileName == null || fileName.equals("")) {
			return "";
		}

		String uploadedPath = writeFile(part, fileName, filesPath);

		File f = new File(uploadedPath);
		if (f.exists()) {
			return uploadedPath;
		} else {
			System.out.println("Error in file upload");
			return "";
		}
	}

	private static String getFileName(Part part) {
		for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
			if (contentDisposition.trim().startsWith("filename")) {
				String name = contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"",
						"");
				// For IE ...
				name = name.substring(name.lastIndexOf('/') + 1).substring(name.lastIndexOf('\\') + 1);
				return name;
			}
		}
		return null;
	}

	private static String writeFile(Part part, String nomFichier, String chemin) throws IOException {
		BufferedInputStream entree = null;
		BufferedOutputStream sortie = null;
		try {
			entree = new BufferedInputStream(part.getInputStream(), SIZE_TAMPON);
			System.out.println(chemin + nomFichier);
			sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), SIZE_TAMPON);

			byte[] tampon = new byte[SIZE_TAMPON];
			int longueur;
			while ((longueur = entree.read(tampon)) > 0) {
				sortie.write(tampon, 0, longueur);
			}
		} finally {
			try {
				sortie.close();
			} catch (IOException ignore) {
			}
			try {
				entree.close();
			} catch (IOException ignore) {
			}
		}

		return chemin + nomFichier;
	}

}
