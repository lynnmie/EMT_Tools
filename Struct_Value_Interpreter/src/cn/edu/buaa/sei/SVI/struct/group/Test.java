package cn.edu.buaa.sei.SVI.struct.group;

import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.GroupStruct;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.core.variable.impl.VariableFactory;
import cn.edu.buaa.sei.SVI.struct.group.impl.GroupFactory;
import cn.edu.buaa.sei.SVI.struct.logic.LogicExpression;
import cn.edu.buaa.sei.SVI.struct.logic.impl.LogicFactory;

public class Test {

	public static void main(String[] args) {
		try {
			Struct p = create3();
			System.out.println(p.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Struct create1() throws Exception{
		GroupVariable A = GroupFactory.createGroupVariable("A");
		GroupVariable B = GroupFactory.createGroupVariable("B");
		GroupVariable C = GroupFactory.createGroupVariable("C");
		GroupVariable D = GroupFactory.createGroupVariable("D");
		GroupVariable E = GroupFactory.createGroupVariable("E");
		GroupVariable F = GroupFactory.createGroupVariable("F");
		
		GroupExpression t3 = GroupFactory.createDifference(C, D);
		GroupExpression t41 = GroupFactory.createUnion(new GroupStruct[]{B,E});
		GroupExpression t42 = GroupFactory.createComplement(A,C);
		GroupExpression t4 = GroupFactory.createDifference(t41, t42);
		
		GroupExpression x = GroupFactory.createIntersection(new GroupStruct[]{A,B,t3,t4});
		GroupExpression y = GroupFactory.createDifference(F, B);
		
		return GroupFactory.createCartesianProduct(new GroupStruct[]{x,y});
	}
	
	public static Struct create2() throws Exception{
		GroupVariable A = GroupFactory.createGroupVariable("A");
		GroupVariable B = GroupFactory.createGroupVariable("B");
		GroupVariable C = GroupFactory.createGroupVariable("C");
		
		GroupExpression x = GroupFactory.createUnion(new GroupStruct[]{A,B,C});
		return GroupFactory.createCardinality(x);
	}
	
	public static Struct create3() throws Exception{
		Variable x = VariableFactory.createFreeVariable("x");
		GroupVariable A = GroupFactory.createGroupVariable("A");
		GroupVariable B = GroupFactory.createGroupVariable("B");
		GroupVariable C = GroupFactory.createGroupVariable("C");
		GroupVariable D = GroupFactory.createGroupVariable("D");
		
		LogicExpression t1 = GroupFactory.createInclude(x, A);
		LogicExpression t2 = GroupFactory.createContain(B, C);
		LogicExpression t3 = GroupFactory.createGroupEqual(C, D);
		
		return LogicFactory.createConjunction(new LogicStruct[]{t1,t2,t3});
	}
}
