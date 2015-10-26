package formComponents;

public abstract class FormComponent {
	
	protected String name;
	protected String defaultVal;
	protected String descr;
	
	public FormComponent(String name,String defaultVal,String descr){
		this.name=name;
		this.defaultVal = defaultVal;
		this.descr = descr;
	}

	public String getDescr() {
		return descr;
	}

	public abstract String getHTMLstring();
	
	
}
