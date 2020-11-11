package customjacksonAPI;

import recursivedependecyinjection.Opponent;
import recursivedependecyinjection.Options;
import recursivedependecyinjection.Player;
import recursivedependecyinjection.Weapon;

public class CompleJSONBean {
	public static void main(String[] args) throws Exception {
		CompleJSONBean instance = new CompleJSONBean("kannu", 23, 100.12, new BasicJSONBean("inner kannu", 32, 99.99));
		String convertToString = JacksonUtil.convertToString(instance);
		System.out.println(convertToString);
		//System.out.println(JacksonUtil.convertStringToObject(convertToString, CompleJSONBean.class));
		
		Player p = new Player(new Weapon(), new Options(new Opponent()));
		
		System.out.println(JacksonUtil.convertToString(p));
	}
	
	private String name;
	private int age;
	private double price;
	private BasicJSONBean basicJSONBean;
	public CompleJSONBean(String name, int age, double price, BasicJSONBean basicJSONBean) {
		this.name = name;
		this.age = age;
		this.price = price;
		this.basicJSONBean=basicJSONBean;
	}
	
	public CompleJSONBean() {
	}

	@Override
	public String toString() {
		return "CompleJSONBean [name=" + name + ", age=" + age + ", price=" + price + ", basicJSONBean=" + basicJSONBean
				+ "]";
	}

	
	
}
