package com.tricky;

public class StaticVar {

		static int a =50;
		
		public static void main(String[] args) {
		int a =10;
		
		System.out.println(a);
		System.out.println(StaticVar.a);
		
		a = StaticVar.a=90;
		
		System.out.println(a);
		System.out.println(StaticVar.a);

	}
		
		public static int  getVar() {
			
			// CTE as Illegal modifier for parameter b; only final is permitted,
			//we can not declare static varibale under static method
		  static int b = 20; 
			return b;
		}

}
