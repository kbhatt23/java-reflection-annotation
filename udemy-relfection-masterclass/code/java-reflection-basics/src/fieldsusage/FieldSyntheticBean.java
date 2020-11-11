package fieldsusage;

public class FieldSyntheticBean {

	private String mainProperty;
	
	
	public class InnerClass{
		
		private String innerProperty;
		
		//compiler inserts one synthetic file holding instnce of outer class
		public InnerClass(String innerProperty) {
			this.innerProperty=innerProperty;
		}
		
		public String printall()
		{
			return this.innerProperty+" : "+mainProperty;
		}

		@Override
		public String toString() {
			return printall();
		}
		
	}
}
