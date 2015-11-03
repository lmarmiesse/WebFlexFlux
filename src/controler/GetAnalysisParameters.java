package controler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kohsuke.args4j.Option;

import flexflux.applications.FlexfluxTest;
import formComponents.BooleanFormComponent;
import formComponents.FileFormComponent;
import formComponents.FormComponent;
import formComponents.SelectFormComponent;
import formComponents.TextFormComponent;

public class GetAnalysisParameters {

	private List<FormComponent> requiredArguments = new ArrayList<FormComponent>();
	private List<FormComponent> optionalArguments = new ArrayList<FormComponent>();
	private List<String> outputFilesArguments = new ArrayList<String>();

	public String getOutputFilesArguments() {

		return StringUtils.join(outputFilesArguments.toArray(), ",");

	}

	private String analysisName;
	private String description;

	public GetAnalysisParameters(String string) {
		analysisName = string;
	}

	public void getParams() {
		Class<?> analysisClass = AnalysesFinder.getAnalysesNamesToClasses().get(analysisName);

		for (Field f : analysisClass.getDeclaredFields()) {

			if (f.getName().equals("message")) {

				try {
					description = (String) f.get(analysisClass.newInstance());

				} catch (IllegalArgumentException | IllegalAccessException | InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			for (Annotation a : f.getDeclaredAnnotations()) {

				if (a instanceof Option) {

					Option option = (Option) a;

					String defaultVal = "";

					try {
						defaultVal = String.valueOf(f.get(analysisClass.newInstance()));
					} catch (IllegalArgumentException | IllegalAccessException | InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					String usage = option.usage();

					int startIndex = usage.indexOf("[");
					int endIndex = usage.indexOf("]");
					if (startIndex > -1 && endIndex > -1) {
						
						String toRemove = usage.substring(startIndex, endIndex+1);
						
						usage = usage.replace(toRemove, "");
					}

					FormComponent component = getRightFormComponent(option.metaVar(), option.name(), defaultVal, usage);

					if (component != null) {
						if (!f.getType().getName().equals("boolean")) {

							if (option.required()) {
								requiredArguments.add(0, component);
							} else {
								optionalArguments.add(0, component);
							}
						} else {

							if (option.required()) {
								requiredArguments.add(component);
							} else {
								optionalArguments.add(component);
							}
						}
					}
				}
			}

		}

	}

	public String getAnalysisName() {
		return analysisName;
	}

	public String getDescription() {
		return description;
	}

	public List<FormComponent> getRequiredArguments() {
		return requiredArguments;
	}

	public List<FormComponent> getOptionalArguments() {
		return optionalArguments;
	}

	private FormComponent getRightFormComponent(String metaVar, String name, String defaultVal, String description) {

		if (!metaVar.equals("")) {
			if (metaVar.contains("File") && metaVar.contains("in")) {
				return new FileFormComponent(name, defaultVal, description);
			} else if (metaVar.contains("File") && metaVar.contains("out")) {
				outputFilesArguments.add(name);
				return null;
			} else if (metaVar.equals("Solver")) {

				SelectFormComponent c = new SelectFormComponent(name, defaultVal, description);
				for (String s : FlexfluxTest.okSolvers) {
					c.addOption(s);
				}

				return c;
			} else if (metaVar.startsWith("[") && metaVar.endsWith("]")) {

				SelectFormComponent c = new SelectFormComponent(name, defaultVal, description);
				for (String s : ((String) metaVar.subSequence(1, metaVar.length() - 1)).split(",")) {
					c.addOption(s);
				}

				return c;

			}

			else {
				return new TextFormComponent(name, defaultVal, description);
			}
		}

		else {
			if (name.equals("-plot")) {
				return null;
			} else {
				return new BooleanFormComponent(name, defaultVal, description);
			}
		}

	}

}
