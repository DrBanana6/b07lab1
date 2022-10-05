import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class Polynomial{
	double[] coef;
	int[] exp;
	public Polynomial() {
		coef = new double[1];
		exp = new int[1];
	}
	public Polynomial(double[] coef, int[] exp) {
		this.coef = new double[coef.length];
		this.exp = new int[exp.length];
		for (int i=0; i<coef.length; i++)
		{
			this.coef[i] = coef[i];
			this.exp[i] = exp[i];
		}
	}
	public Polynomial(File f) throws Exception {
		Scanner s = new Scanner(f);
		String str = s.nextLine();
		s.close();
		int count = 0;
		for (int i = 1; i < str.length();i++)
		{
			if (str.charAt(i) == '+' || str.charAt(i) == '-'){
				count++;
			}
		}
		coef = new double[count+1];
		exp = new int[count+1];
		int ind = 0;
		boolean isExpo = false;
		String numStr = "";
		for (int i = 0; i<str.length(); i++) {
			if (str.charAt(i) == 'x') {
				coef[ind] = Double.parseDouble(numStr);
				numStr = "";
				isExpo = true;
			}
			else if (str.charAt(i) == '-' || str.charAt(i) == '+') {
				if (!numStr.equals("")) {
					if (!isExpo)
					{
						coef[ind] = Double.parseDouble(numStr);
						exp[ind] = 0;
					}
					else {
						exp[ind] = Integer.parseInt(numStr);
						isExpo = false;
					}
					ind++;
				}
				if (str.charAt(i) == '-') {
					numStr = "-";
				}
				else {
					numStr = "";
				}
			}
			else {
				numStr += str.charAt(i);
			}
		}
		if(!isExpo) {
			coef[ind] = Double.parseDouble(numStr);
			exp[ind] = 0;
		}
		else {
			exp[ind] = Integer.parseInt(numStr);
		}
		
		

	}
	public int largestVal(int[] arr) {
		int result = 0;
		for (int i = 0; i<arr.length; i++)
		{
			if (arr[i]>result)
			{
				result = arr[i];
			}
		}
		return result;
	}
	public int indexOf(int[] arr, int x)
	{
		int result = -1;
		for (int i = 0; i< arr.length; i++)
		{
			if (arr[i]==x) {
				result = i;
			}
		}
		return result;
	}
	
	public double[] convertToOld(double[] coef, int[] exp) {
		double[] oldCoef= new double[largestVal(exp)+1];
		for (int i = 0; i<oldCoef.length;i++)
		{
			if (indexOf(exp, i) != -1) {
				oldCoef[i] = coef[indexOf(exp, i)];
			}
			else {
				oldCoef[i] = 0;
			}
		}
		return oldCoef;
		
		
	}
	public Polynomial convertToNew(double[] old) {
		int count = 0;
		for (int i = 0; i<old.length; i++)
		{
			if (old[i] != 0) {
				count++;
			}
		}
		double[] resultCoef = new double[count];
		int[] resultExp = new int[count];
		int step=0;
		for (int i = 0; i<old.length; i++)
		{
			if (old[i] != 0) {
				resultCoef[step] = old[i];
				resultExp[step] = i;
				step++;
			}
		}
		return new Polynomial(resultCoef, resultExp);
	}
	public Polynomial add(Polynomial poly) {
		double[] oldPoly = poly.convertToOld(poly.coef, poly.exp);
		double[] oldCall = this.convertToOld(this.coef, this.exp);
		double[] result;
		if (oldPoly.length>=oldCall.length) {
			result = new double[oldPoly.length];
			for (int i=0; i<result.length; i++)
			{
				result[i] = oldPoly[i];
			}
			for (int i=0; i<oldCall.length;i++)
			{
				result[i] = result[i]+oldCall[i];
			}
		}
		else {
			result = new double[oldCall.length];
			for (int i=0; i<result.length; i++)
			{
				result[i] = oldCall[i];
			}
			for (int i=0; i<oldPoly.length;i++)
			{
				result[i] = result[i]+oldPoly[i];
			}
		}
		return this.convertToNew(result);
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
	public Polynomial multiply(Polynomial p) {
		int highest_p = p.largestVal(p.exp);
		int highest_call = this.largestVal(this.exp);
		double[] oldP = p.convertToOld(p.coef, p.exp);
		double[] oldCall = this.convertToOld(this.coef, this.exp);
		double[] result = new double[highest_p+highest_call+1];
		for (int i=0; i<oldP.length; i++)
		{
			double[] temp = new double[highest_p+highest_call+1];
			for (int j=0; j<oldCall.length; j++) {
				temp[i+j] = oldP[i]*oldCall[j];
			}
			result = arrAdd(temp, result);
			
		}
		return this.convertToNew(result);
	}
	public void saveToFile(String fName) throws Exception{
		String temp = "";
		for (int i = 0; i<this.coef.length; i++) {
			if (this.exp[i] == 0) {
				temp = Double.toString(coef[i]);
			}
			else {
				temp = Double.toString(coef[i]) + 'x' + Integer.toString(exp[i]);
			}
			if (i < coef.length-1 && coef[i+1]>=0) {
				temp += "+";
			}
		}
		File f = new File(fName);
		f.createNewFile();
		FileWriter fw = new FileWriter(fName);
		fw.write(temp);
		fw.close();
	}
	public double evaluate(double x) {
		double[] oldCall = this.convertToOld(this.coef, this.exp);
		double total = 0;
		for (int i = 0; i<oldCall.length;i++)
		{
			total = total + oldCall[i]*Math.pow(x, i);
		}
		return total;
	}
	public boolean hasRoot(double x)
	{
		return evaluate(x)==0;
	}
	
}