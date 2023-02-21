package com.tricky;

/*
 * when static / instant block is called 
 * 
 */
public class StaticNInstanstBlock {

	static {
		System.out.println("Static1");
	}

	static {
		System.out.println("Static2");
	}

	{

		System.out.println("Instant1");
	}

	{

		System.out.println("Instant2");
	}

	StaticNInstanstBlock() {
		System.out.println("const");
	}

	{

		System.out.println("Instant3");
	}

	static {
		System.out.println("Static3");
	}

	public static void main(String[] args) {

		StaticNInstanstBlock q = new StaticNInstanstBlock();  // without creating instance, instant block will be executed 

		System.out.println("Main");

	}

}
