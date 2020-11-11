package recursivedependecyinjection;

public class Options {
	private Opponent opponent;
	public Options(Opponent opponent) {
		this.opponent = opponent;
	}
	@Override
	public String toString() {
		return "Options [opponent=" + opponent + "]";
	}
	
	
}
