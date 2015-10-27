package startup;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import controler.AnalysesFinder;
import flexflux.applications.FlexfluxTest;

public class StartupServlet extends javax.servlet.http.HttpServlet {

	private static AtomicInteger uniqueNumber = new AtomicInteger(0);

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

		FlexfluxTest.doUnitTests = false;
		AnalysesFinder.findAnalyses();
	}

	public void destroy() {
		System.out.println("Bye !");
	}
}