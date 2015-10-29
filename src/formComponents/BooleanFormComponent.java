package formComponents;

public class BooleanFormComponent extends FormComponent {
	


	public BooleanFormComponent(String name, String defaultVal, String descr) {
		super(name, defaultVal, descr);
	}

	public String getHTMLstring() {
		

        String checked = "";
		if (defaultVal.equals("true")){
			checked = "checked";
		}
		
		return "<input type='checkbox' name='"+name+"' "+checked+">";
	}

}
