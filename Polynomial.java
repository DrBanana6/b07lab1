public class Polynomial{
	double[] coef;
	public Polynomial() {
		coef = new double[1];
		
	}
	public Polynomial(double[] list) {
		coef = new double[list.length];
		for (int i=0; i<list.length; i++)
		{
			coef[i] = list[i];
		}
	}
	public Polynomial add(Polynomial poly) {
		double[] result;
		if (poly.coef.length>=this.coef.length) {
			result = new double[poly.coef.length];
			for (int i=0; i<result.length; i++)
			{
				result[i] = poly.coef[i];
			}
			for (int i=0; i<this.coef.length;i++)
			{
				result[i] = result[i]+this.coef[i];
			}
		}
		else {
			result = new double[this.coef.length];
			for (int i=0; i<result.length; i++)
			{
				result[i] = this.coef[i];
			}
			for (int i=0; i<poly.coef.length;i++)
			{
				result[i] = result[i]+poly.coef[i];
			}
		}
		return new Polynomial(result);
		
		
		
	}
	public double evaluate(double x) {
		double total = 0;
		for (int i = 0; i<coef.length;i++)
		{
			total = total + coef[i]*Math.pow(x, i);
		}
		return total;
	}
	public boolean hasRoot(double x)
	{
		return evaluate(x)==0;
	}
	
}