package classreflectionusage;

public class DefaultPAckageClass {

	private int defaultConstructor;
	private String privateconstructor;
	public DefaultPAckageClass() {
		this.defaultConstructor=99;
		this.privateconstructor="99";
	}
	
	DefaultPAckageClass(int defaultConstructor ){
		this.defaultConstructor=defaultConstructor;
	}
	
	private DefaultPAckageClass(String privateconstructor ){
		this.privateconstructor=privateconstructor;
	}

	@Override
	public String toString() {
		return "DefaultPAckageClass [defaultConstructor=" + defaultConstructor + ", privateconstructor="
				+ privateconstructor + "]";
	}
	
	
}
