package servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controler.AnalysesFinder;
import controler.GetAnalysisParameters;
import controler.SystemExitControl;
import flexflux.applications.FlexfluxFBA;

/**
 * Servlet implementation class Run
 */
@WebServlet("/Run")
public class Run extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Run() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = request.getParameter("method");

		GetAnalysisParameters gap = new GetAnalysisParameters(method);

		gap.getParams();

		request.setAttribute("AnalysisParameters", gap);

		this.getServletContext().getRequestDispatcher("/WEB-INF/run.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String[] args = new String[request.getParameterMap().size() * 2 - 2];

		int index = 0;
		for (String paramName : request.getParameterMap().keySet()) {
			if (!paramName.equals("method")) {
				args[index] = paramName;
				index++;
				args[index] = request.getParameter(paramName);
				index++;
			}
		}

		// Stop the flexflux application from calling system.exit
		SystemExitControl.forbidSystemExitCall();
		try {
			Class<?> analysisClass = AnalysesFinder.getAnalysesNamesToClasses().get(request.getParameter("method"));
			Method main = analysisClass.getMethod("main", String[].class);

			main.invoke(null, (Object) args);

			// FlexfluxFBA.main(args);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			System.out.println("Error in analysis");
//			e.printStackTrace();
		}
		SystemExitControl.enableSystemExitCall();

	}

}
