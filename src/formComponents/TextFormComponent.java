package formComponents;

public class TextFormComponent extends FormComponent {

	public TextFormComponent(String name, String defaultVal, String descr) {
		super(name, defaultVal, descr);
	}

	public String getHTMLstring() {
		
		return "<label for='" + name + "'>" + name + " : </label> <input type='text' name='" + name + "' value='"+defaultVal+"'>";
	
	}

}
