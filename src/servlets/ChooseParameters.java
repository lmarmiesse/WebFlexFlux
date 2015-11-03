package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;

import controler.GetAnalysisParameters;
import startup.StartupServlet;

/**
 * Servlet implementation class Run
 */
@WebServlet("/Run")
public class ChooseParameters extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChooseParameters() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int uniqueNumber = StartupServlet.getUniqueNumber();
		
		String method = request.getParameter("method");
		
		//key generated for security
		String key = generateKey();

		GetAnalysisParameters gap = new GetAnalysisParameters(method);

		gap.getParams();

		request.setAttribute("AnalysisParameters", gap);
		request.setAttribute("uniqueNumber", uniqueNumber);
		request.setAttribute("key", key);

		this.getServletContext().getRequestDispatcher("/WEB-INF/params.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}
	
	private static String generateKey() {

		return RandomStringUtils.random(30, true, true);

	}

}
