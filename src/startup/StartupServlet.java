package startup;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import controler.AnalysesFinder;
import flexflux.applications.FlexfluxTest;

public class StartupServlet extends javax.servlet.http.HttpServlet {

	private static AtomicInteger uniqueNumber = new AtomicInteger(0);
	
	
	//Need to find a way not to have to change that every time ... 
	public static final String FILES_PATH = "/home/lmarmiesse/Documents/uploads/";
//	public static final String FILES_PATH = "/home/files/";
	


	public static int getUniqueNumber() {
		uniqueNumber.incrementAndGet();
		return uniqueNumber.intValue();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		System.out.println("Yo !");
		System.err.println("Upload files path ="+FILES_PATH);
		
		FlexfluxTest.doUnitTests = false;
		AnalysesFinder.findAnalyses();
	}

	public void destroy() {
		System.out.println("Bye !");
	}
}