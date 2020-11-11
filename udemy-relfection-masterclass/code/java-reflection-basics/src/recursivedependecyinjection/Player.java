package recursivedependecyinjection;
//assuming all the classes have only single constructor, either no arg or with specific arguments
public class Player {

	private Weapon weapon;
	private Options options;
	
	public Player(Weapon weapon,Options options) {
		this.weapon = weapon;
		this.options = options;
	}

	@Override
	public String toString() {
		return "Player [weapon=" + weapon + ", options=" + options + "]";
	}
	
	
	
}
