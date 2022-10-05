import java.io.File;

public class Driver { 
	
	public static void printArr(int[] arr) {
		System.out.println("Int Array Start;");
		for (int i = 0; i<arr.length; i++)
		{
			System.out.println(arr[i]);
		}
	}
	public static void printArr(double[] arr) {
		System.out.println("Double Array Start;");
		for (int i = 0; i<arr.length; i++)
		{
			System.out.println(arr[i]);
		}
	}
	public static double[] arrAdd(double[] arr1, double[] arr2) {
		double[] result;
		if (arr1.length>=arr2.length) {
			result = new double[arr1.length];
			for (int i=0; i<result.length; i++)
			{
				result[i] = arr1[i];
			}
			for (int i=0; i<arr2.length;i++)
			{
				result[i] = result[i]+arr2[i];
			}
		}
		else {
			result = new double[arr2.length];
			for (int i=0; i<result.length; i++)
			{
				result[i] = arr2[i];
			}
			for (int i=0; i<arr1.length;i++)
			{
				result[i] = result[i]+arr1[i];
			}
		}
		return (result);
	}
	 public static void main(String [] args) throws Exception { 
		 double[] coefficeints_1 = { 5, -3 };
	        double[] coefficeints_2 = { 2, 7, 9 };
	        int[] exponents_1 = { 1, 0 };
	        int[] exponents_2 = { 0, 1, 3 };
	        Polynomial p1 = new Polynomial(coefficeints_1, exponents_1);
	        System.out.println(p1.evaluate(3.0));
	        Polynomial p2 = new Polynomial(coefficeints_2, exponents_2);
	        System.out.println(p2.evaluate(1.0));
	        Polynomial add = p1.add(p2);
	        System.out.println("add 15 = " + add.evaluate(15));
	        Polynomial multiply_1 = p1.multiply(p2);
	        System.out.println("multiply by 6 = " + multiply_1.evaluate(6));
	        multiply_1.saveToFile("test.txt");
	        Polynomial new_ = new Polynomial(new File("test.txt"));

	       
	    }

//	  Polynomial p = new Polynomial(); 
//	  System.out.println(p.evaluate(3)); 
//	  double [] c1 = {6,0,0,5}; 
//	  Polynomial p1 = new Polynomial(c1); 
//	  double [] c2 = {0,-2,0,0,-9}; 
//	  Polynomial p2 = new Polynomial(c2); 
//	  Polynomial s = p1.add(p2); 
//	  System.out.println("s(0.1) = " + s.evaluate(0.1)); 
//	  if(s.hasRoot(1)) 
//	   System.out.println("1 is a root of s"); 
//	  else 
//	   System.out.println("1 is not a root of s"); 
	 } 
