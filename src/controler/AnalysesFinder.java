package controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import flexflux.applications.FlexfluxTest;
import flexflux.applications.gui.GraphicalFlexflux;

public class AnalysesFinder {

	private static List<String> analysesNames = new ArrayList<String>();
	private static List<Class<?>> analysesClasses = new ArrayList<Class<?>>();
	private static Map<String,Class<?>> analysesNamesToClasses = new HashMap<String,Class<?>>();

	public static List<String> getAnalysesNames() {
		return analysesNames;
	}

	public static void setAnalysesNames(List<String> analysesNames) {
		AnalysesFinder.analysesNames = analysesNames;
	}

	public static void findAnalyses() {
		
		FlexfluxTest.main(null);

		try {
			analysesClasses = GraphicalFlexflux.getClasses("flexflux.applications");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Class<?> c : analysesClasses) {
			
			String simpleName = c.getSimpleName().replace("Flexflux", "");
			analysesNames.add(simpleName);
			analysesNamesToClasses.put(simpleName, c);
		}

	}

	public static List<Class<?>> getAnalysesClasses() {
		return analysesClasses;
	}

	public static void setAnalysesClasses(List<Class<?>> analysesClasses) {
		AnalysesFinder.analysesClasses = analysesClasses;
	}

	public static Map<String, Class<?>> getAnalysesNamesToClasses() {
		return analysesNamesToClasses;
	}

	public static void setAnalysesNamesToClasses(Map<String, Class<?>> analysesNamesToClasses) {
		AnalysesFinder.analysesNamesToClasses = analysesNamesToClasses;
	}

}
