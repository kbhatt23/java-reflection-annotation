package common;

public class PrivateConstructorClass implements EmptyInterface , Runnable{

	 private /* public */ PrivateConstructorClass() {
		System.out.println("private constructor called");
	}

	@Override
	public void run() {

		System.out.println("running taks in private constructor class");
	}
}
