package formComponents;

public class FileFormComponent extends FormComponent{


	public FileFormComponent(String name, String defaultVal, String descr) {
		super(name, defaultVal, descr);
	}

	public String getHTMLstring() {
		
		return "<label for='"+name+"'>"+name+" : </label> <input type='file' name='"+name+"'>";

	}

}
