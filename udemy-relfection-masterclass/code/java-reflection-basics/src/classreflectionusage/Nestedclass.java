package classreflectionusage;

public class Nestedclass {

	public static class InnerClass{
		
		private int property1;
		
		public String property2;

		public int getProperty1() {
			return property1;
		}
		private int privateMEthod() {
			return -1;
		}

		public void setProperty1(int property1) {
			this.property1 = property1;
		}

		public String getProperty2() {
			return property2;
		}

		public void setProperty2(String property2) {
			this.property2 = property2;
		}
		
		
	}
}
