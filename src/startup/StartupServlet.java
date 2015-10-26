package startup;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import controler.AnalysesFinder;
import flexflux.applications.FlexfluxTest;

public class StartupServlet extends javax.servlet.http.HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		System.out.println("Yo !");
		
		FlexfluxTest.doUnitTests = false;
		AnalysesFinder.findAnalyses();
	}

	public void destroy() {
		System.out.println("Bye !");
	}
}