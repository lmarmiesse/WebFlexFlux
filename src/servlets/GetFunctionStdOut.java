package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import controler.ProcessManager;

/**
 * Servlet implementation class GetFunctionStdOut
 */
@WebServlet("/GetFunctionStdOut")
public class GetFunctionStdOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetFunctionStdOut() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int uniqueNumber = Integer.parseInt(request.getParameter("nb"));

		if (request.getParameterMap().containsKey("key")) {
			String key = request.getParameter("key");

			if (!key.equals("")) {
				ProcessManager.killProcess(uniqueNumber, key);
			}
		}

		String output = ProcessManager.getProcessOutput(uniqueNumber);
		JSONObject jsonObj = new JSONObject();

		jsonObj.put("output", output);

		// System.out.println(jsonStr);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		out.print(jsonObj);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
