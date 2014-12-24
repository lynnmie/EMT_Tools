package cn.edu.buaa.sei.SVI.struct.numeric;

import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.NumericStruct;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.numeric.RationalVariable.Rational;
import cn.edu.buaa.sei.SVI.struct.numeric.impl.NumericFactory;

public class Test {

	public static void main(String[] args) {
		try {
			testVariable();
//			Struct x = create3();
//			System.out.println(x.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static NumericStruct create1() throws Exception{
		NaturalVariable a = NumericFactory.createNaturalVariable("a");
		RationalVariable b = NumericFactory.createRationalVariable("b");
		ZIntegerVariable c = NumericFactory.createZIntegerVariable("c");
		RealVariable d = NumericFactory.createRealVariable("d");
		
		NumericExpression t221 = NumericFactory.createMultiplication(c, d);
		NumericExpression t222 = NumericFactory.createDivision(a, b);
		NumericExpression t22 = NumericFactory.createSubstract(t221, t222);
		NumericExpression t2 = NumericFactory.createAddition(b, t22);
		
		return NumericFactory.createAddition(a, t2);
	}
	
	public static NumericStruct create2() throws Exception{
		RealVariable x = NumericFactory.createRealVariable("x");
		RealVariable y = NumericFactory.createRealVariable("y");
		RealVariable z = NumericFactory.createRealVariable("z");
		
		NumericFunctionTemplate gtemp = NumericFactory.createNaturalFunctionTemplate("g", new Variable[]{x});
		NumericFunction g = NumericFactory.createNumericFunction(gtemp, null, null);
		
		//NumericExpression t1 = NumericFactory.createMultiplication(y, z);
		
		NumericFunctionTemplate ftemp = NumericFactory.createRealFunctionTemplate("f", new Variable[]{y,z});
		//ftemp.assign(new Object[]{t1,x});
		NumericFunction f = NumericFactory.createNumericFunction(ftemp, null, null);
		
		return NumericFactory.createAddition(g, f);
	}
	
	public static Struct create3() throws Exception{
		NaturalVariable a = NumericFactory.createNaturalVariable("a");
		NaturalVariable b = NumericFactory.createNaturalVariable("b");
		NaturalVariable c = NumericFactory.createNaturalVariable("c");
		NaturalVariable d = NumericFactory.createNaturalVariable("d");
		
		NumericExpression x = NumericFactory.createAddition(a, b);
		
		NumericExpression y1 = NumericFactory.createMultiplication(a, c);
		NumericExpression y = NumericFactory.createSubstract(y1, d);
		
		return NumericFactory.createEqual(x, y);
	}
	
	public static void testVariable() throws Exception{
		RealVariable x = NumericFactory.createRealVariable("x");
		System.out.println(printVariable(x));
		
		x.assign(25.64);
		System.out.println(printVariable(x));
		
		x.assign(151.458f);
		System.out.println(printVariable(x));
		
		x.assign(54);
		System.out.println(printVariable(x));
		
		x.assign(540L);
		System.out.println(printVariable(x));
		
		Rational r = new Rational(15,-6);
		System.out.println(r);
		System.out.println(r.floatValue());
		
		RationalVariable y = NumericFactory.createRationalVariable("y");
		y.assign(r);
		System.out.println(printVariable(y));
		
		y.assign(null);
		System.out.println(printVariable(y));
		
		y.assign(new Rational(18,9));
		System.out.println(printVariable(y));
		
		y.assign(new Rational(12,0));
		System.out.println(printVariable(y));
	}
	
	static String printVariable(Variable x) throws Exception{
		if(x==null)return null;
		else return x.getName()+": "+x.read();
	}
	
	
	
	
}
