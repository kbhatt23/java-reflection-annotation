package fieldsusage;

public class FieldUsageBean extends SuperFieldClass{
	//get methods return only public methgids, child or super class
	private String privateString;
	
	public String publicString;
	
	String defaultString;

	@Override
	public String toString() {
		return "FieldUsageBean [privateString=" + privateString + ", publicString=" + publicString + ", defaultString="
				+ defaultString + "]";
	}
	
	
}
