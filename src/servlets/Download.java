package servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import startup.StartupServlet;

/**
 * Servlet implementation class Download
 */
@WebServlet("/Download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int TAILLE_TAMPON = 10240; // 10ko
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String FILES_PATH = StartupServlet.FILES_PATH;
		
		String analysisName = request.getParameter("method");
		String uniqueNumber = request.getParameter("nb");
		String fileName = request.getParameter("file");
		
		String path = FILES_PATH + analysisName + "_" + uniqueNumber + "/out/" + fileName;
		
		File file = new File( path );
		if ( !file.exists() ) {
            response.sendError( HttpServletResponse.SC_NOT_FOUND );
            return;
        }
		
		
		/* Get file type */
        String type = getServletContext().getMimeType( file.getName() );
        if ( type == null ) {
            type = "application/octet-stream";
        }
        
        /* Initialise HTTP response */
        response.reset();
        response.setBufferSize( TAILLE_TAMPON );
        response.setContentType( type );
        response.setHeader( "Content-Length", String.valueOf( file.length() ) );
        response.setHeader( "Content-Disposition", "attachment; filename=\"" + file.getName() + "\"" );

        /* Prepare fluxes */
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            /* Open fluxes */
            entree = new BufferedInputStream( new FileInputStream( file ), TAILLE_TAMPON );
            sortie = new BufferedOutputStream( response.getOutputStream(), TAILLE_TAMPON );

            /* Reads file and writes in the HTTP response */
            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ( ( longueur = entree.read( tampon ) ) > 0 ) {
                sortie.write( tampon, 0, longueur );
            }
        } finally {
            sortie.close();
            entree.close();
        }
	}



}
