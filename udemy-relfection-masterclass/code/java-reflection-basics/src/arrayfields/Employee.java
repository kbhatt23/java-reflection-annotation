package arrayfields;

import java.util.Arrays;

public class Employee extends SuperEmployee{
	private int id;
	
	private String name;
	
	private String[] favouriteFootballers;
	
	//private Colleague[] colleague;
	
	private Colleague colleague;
	
	private Colleague[] colleagues;


	public Employee(int id, String name, String[] favouriteFootballers, Colleague colleague) {
		super();
		this.id = id;
		this.name = name;
		this.favouriteFootballers = favouriteFootballers;
		this.colleague = colleague;
		this.colleagues=new Colleague[] {new Colleague(2, "external 2"),new Colleague(3, "external 3"),new Colleague(4, "external 4")};
	}

	public Employee() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getFavouriteFootballers() {
		return favouriteFootballers;
	}

	public void setFavouriteFootballers(String[] favouriteFootballers) {
		this.favouriteFootballers = favouriteFootballers;
	}

	public Colleague getColleague() {
		return colleague;
	}

	public void setColleague(Colleague colleague) {
		this.colleague = colleague;
	}
	

	public Colleague[] getColleagues() {
		return colleagues;
	}

	public void setColleagues(Colleague[] colleagues) {
		this.colleagues = colleagues;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", favouriteFootballers="
				+ Arrays.toString(favouriteFootballers) + ", colleague=" + colleague + ", colleagues="
				+ Arrays.toString(colleagues) + "]";
	}


	
}
