package controler;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import flexflux.applications.FlexfluxTest;
import flexflux.applications.gui.GraphicalFlexflux;

public class AnalysesFinder {

	private static List<String> analysesNames = new ArrayList<String>();
	private static List<Class<?>> analysesClasses = new ArrayList<Class<?>>();
	private static Map<String, Class<?>> analysesNamesToClasses = new HashMap<String, Class<?>>();
	private static Map<String, String> analysesNamesToDescirptions = new HashMap<String, String>();

	public static Map<String, String> getAnalysesNamesToDescirptions() {
		return analysesNamesToDescirptions;
	}

	public static List<String> getAnalysesNames() {
		return analysesNames;
	}

	public static void findAnalyses() {

		FlexfluxTest.main(null);

		try {
			analysesClasses = GraphicalFlexflux.getClasses("flexflux.applications");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		for (Class<?> c : analysesClasses) {

			String simpleName = c.getSimpleName().replace("Flexflux", "");
			
			String message="";
			try {
				Field messageFied = c.getField("message");
				
				message = (String) messageFied.get(null);
				
			} catch (NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!analysesNames.contains(simpleName)) {
				analysesNames.add(simpleName);
				analysesNamesToClasses.put(simpleName, c);
				analysesNamesToDescirptions.put(simpleName, message);
			}
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
