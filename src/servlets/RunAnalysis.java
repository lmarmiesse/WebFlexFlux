package servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controler.AnalysesFinder;
import controler.FileUpload;
import startup.StartupServlet;

/**
 * Servlet implementation class RunAnalysis
 */
@WebServlet("/RunAnalysis")
public class RunAnalysis extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RunAnalysis() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String FILES_PATH = StartupServlet.FILES_PATH;
		String analysisName = request.getParameter("method");
		int uniqueNumber = StartupServlet.getUniqueNumber();
		String path = FILES_PATH + analysisName + "_" + uniqueNumber + "/";
		String outputPath = FILES_PATH + analysisName + "_" + uniqueNumber + "/out/";

		createDir(path);
		createDir(outputPath);

		List<String> args = new ArrayList<String>();

		List<Part> parts = new ArrayList<Part>(request.getParts());
		for (Part part : parts) {

			String fileName = FileUpload.uploadFile(part, path);

			if (fileName != null && !fileName.equals("")) {
				args.add(part.getName());
				args.add(fileName);
			}
		}

		for (String paramName : request.getParameterMap().keySet()) {
			if (!paramName.equals("method") && !paramName.equals("outputFiles")) {
				args.add(paramName);
				args.add(request.getParameter(paramName));
			}
		}

		// the output files to create
		String[] outputFiles = request.getParameter("outputFiles").split(",");
		for (String outFile : outputFiles) {
			args.add(outFile);
			args.add(outputPath + outFile.replace("-", "") + ".txt");
		}

		String[] agrsArray = new String[args.size()];
		int i = 0;

		String commandLine = "";

		for (String s : args) {

			commandLine += s + " ";

			agrsArray[i] = s;
			i++;
		}

		// System.out.println(commandLine);
		Class<?> analysisClass = AnalysesFinder.getAnalysesNamesToClasses().get(analysisName);

		// // Stop the flexflux application from calling system.exit
		// SystemExitControl.forbidSystemExitCall();
		// try {
		// Method main = analysisClass.getMethod("main", String[].class);
		//
		// main.invoke(null, (Object) agrsArray);
		//
		// // FlexfluxFBA.main(args);
		// } catch (NoSuchMethodException e) {
		// e.printStackTrace();
		// } catch (SecurityException e) {
		// e.printStackTrace();
		// } catch (IllegalAccessException e) {
		// e.printStackTrace();
		// } catch (IllegalArgumentException e) {
		// e.printStackTrace();
		// } catch (InvocationTargetException e) {
		// System.out.println("Error in analysis");
		// // e.printStackTrace();
		// }
		// SystemExitControl.enableSystemExitCall();

		String jvm = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
		String classpath = System.getProperty("java.class.path");
		String libpath = System.getProperty("java.library.path");

		List<String> command = new ArrayList<String>();
		command.add(jvm);
		command.add("-Djava.library.path=" + libpath);
		command.add("-cp");
		classpath+=":"+getServletContext().getRealPath("/")+"/WEB-INF/lib/flexflux.jar";
		command.add(classpath);
		command.add(analysisClass.getName());
		command.addAll(Arrays.asList(agrsArray));

		String[] commandArray = new String[command.size()];

		int j = 0;
		for (String a : command) {
			commandArray[j] = a;
			j++;
		}
		Process p;
		ProcessBuilder builder = new ProcessBuilder(commandArray);
		builder.redirectErrorStream(true);
		p = builder.start();

		try {
			p.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File outputFolder = new File(outputPath);
		File[] listOfFiles = outputFolder.listFiles();

		
		List<String> fileNames = new ArrayList<String>();
		
		for (File f : listOfFiles) {
			fileNames.add(f.getName());
		}

		request.setAttribute("analysisName", analysisName);
		request.setAttribute("uniqueNumer", uniqueNumber);
		request.setAttribute("fileNames", fileNames);

		this.getServletContext().getRequestDispatcher("/WEB-INF/result.jsp").forward(request, response);

	}

	private void createDir(String path) {
		File theDir = new File(path);
		// if the directory does not exist, create it
		if (!theDir.exists()) {
//			System.out.println("creating directory: " + path);
			boolean result = false;
			try {
				theDir.mkdir();
				result = true;
			} catch (SecurityException se) {
				// handle it
			}
			if (result) {
//				System.out.println("DIR created");
			}
		}

	}

}
