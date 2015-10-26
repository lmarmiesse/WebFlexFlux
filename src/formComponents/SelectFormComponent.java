package formComponents;

import java.util.ArrayList;
import java.util.List;

public class SelectFormComponent extends FormComponent{
	
	private List<String> options = new ArrayList<String>();
	
	public SelectFormComponent(String name, String defaultVal, String descr) {
		super(name, defaultVal, descr);
	}

	public String getHTMLstring() {
		
		String HTMLstring = "<label for='"+name+"'>"+name+" : </label> <select>"; 
		
		for (String opt: options){
			HTMLstring+="<option value='"+opt+"'>"+opt+"</option>";
		}
		
		HTMLstring+="</select>";
		
		return HTMLstring;
	}
	
	public void addOption(String option){
		options.add(option);
	}

}
