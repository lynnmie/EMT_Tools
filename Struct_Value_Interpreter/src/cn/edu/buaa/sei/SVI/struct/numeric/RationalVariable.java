package cn.edu.buaa.sei.SVI.struct.numeric;

public interface RationalVariable extends NumericVariable{
	/**
	 * Rational is the number.
	 * */
	public static class Rational extends Number{
		
		/**
		 * Serial ID for Rational
		 */
		private static final long serialVersionUID = -4692500537336350430L;
		/**
		 * To figure out whether 
		 * */
		boolean negative;
		Integer numerator,denominator;
		
		/**
		 * A Rational is some number like a/b in which b should not be zero.
		 * @exception Exception denominator==0
		 * */
		public Rational(Integer numerator,Integer denominator) throws Exception{
			if(denominator==0)throw new Exception("Denomiator should not be zero!");
			negative = (numerator>0&&denominator<0)||(numerator<0&&denominator>0);
			
			if(numerator<0)numerator=-numerator;
			if(denominator<0)denominator=-denominator;
			
			if(numerator<0||denominator<0)
				throw new Exception("Out of Long Range");
			
			Integer t = this.gcd(numerator, denominator);
			this.numerator = numerator/t;
			this.denominator = denominator/t;
		}
		
		Integer gcd(Integer a,Integer b){
			if(a<b){
				Integer t = a;
				a = b;
				b = t;
			}
			
			Integer c = a%b;
			while(c!=0){
				a = b;
				b = c;
				c = a%b;
			}
			
			return b;
		}
		
		@Override
		public double doubleValue() {
			double a = this.numerator;
			double b = this.denominator;
			
			if(negative)return (-a)/b;
			else return a/b;
		}

		@Override
		public float floatValue() {
			float a = this.numerator;
			float b = this.denominator;
			
			if(negative)return (-a)/b;
			else return a/b;
		}

		@Override
		public int intValue() {
			int a = this.numerator;
			int b = this.denominator;
			
			if(negative)return (-a)/b;
			else return a/b;
		}

		@Override
		public long longValue() {
			long a = this.numerator;
			long b = this.denominator;
			
			if(negative)return (-a)/b;
			else return a/b;
		}
	
		public String toString(){
			if(this.negative)
				return "-"+this.numerator+"/"+this.denominator;
			else
				return this.numerator+"/"+this.denominator;
		}
	}

	/**
	 * Return the value to which the bindable refers.
	 * @exception Exception {Referencer} null referring variable.
	 * */
	public Rational read() throws Exception;
	/**
	 * Write the value to the bindable space.
	 * @exception Exception {TypedVariable} class cast failed.
	 * */
	public void assign(Rational val) throws Exception;
}
