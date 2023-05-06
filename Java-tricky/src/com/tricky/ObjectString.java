public class ObjectString {

	public static void main(String[] args) {

		fun("Hello");
		fun(8);
		fun(null);

	
	}
	

	public static void fun(String s)
	{
		System.out.println("String");
	}

	public static void fun(Integer s) {
		System.out.println("Integer");
	}

	public static void fun(Object s) {
		System.out.println("Object");
	}
}
