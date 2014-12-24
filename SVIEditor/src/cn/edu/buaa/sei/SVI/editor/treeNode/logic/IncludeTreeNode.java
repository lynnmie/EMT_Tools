package cn.edu.buaa.sei.SVI.editor.treeNode.logic;

import javax.swing.JMenu;
import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.expr.CreateGroupExpression;
import cn.edu.buaa.sei.SVI.editor.action.function.CreateFilter;
import cn.edu.buaa.sei.SVI.editor.action.function.CreateLogicFunction;
import cn.edu.buaa.sei.SVI.editor.action.function.CreateMapper;
import cn.edu.buaa.sei.SVI.editor.action.function.CreateTableMapper;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateBooleanVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateCharVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateDoubleVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateFloatVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateFreeVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateGroupVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateIntegerVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateListVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateLogicVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateLongVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateMapVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateNaturalVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateRationalVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateRealVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateReferVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateSetVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateStringVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateZIntVariable;
import cn.edu.buaa.sei.SVI.editor.treeNode.IconSet;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.BinaryOperatorTreeNode;

public class IncludeTreeNode extends BinaryOperatorTreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IncludeTreeNode(JTree tree, String name) {
		super(tree, name, IconSet.OP_GROUPEQUAL_ICON);
		this.op_init();
	}
	protected void op_init(){
		/**/
		JMenu item0 = (JMenu) this.menu.getComponent(0);
		
		JMenu i00 = new JMenu("element");
		JMenu item01 = new JMenu("group");
		
		item0.add(i00); item0.add(item01);

		item0.add(new CreateGroupVariable(this));
		item0.add(new CreateGroupExpression(this));
		item0.add(new CreateFilter(this));
		item0.add(new CreateMapper(this));
		item0.add(new CreateTableMapper(this));
		item0.add(new CreateLogicFunction(this));
		
		/*JMenu i00 = new JMenu(); i00.setText("variable");
		JMenu i01 = new JMenu(); i01.setText("expression");
		JMenu i02 = new JMenu(); i02.setText("function");
		item00.add(i00); item01.add(i01); item00.add(i02);*/
		
		i00.add(new CreateBooleanVariable(this));
		i00.add(new CreateIntegerVariable(this));
		i00.add(new CreateLongVariable(this));
		i00.add(new CreateFloatVariable(this));
		i00.add(new CreateDoubleVariable(this));
		i00.add(new CreateCharVariable(this));
		i00.add(new CreateStringVariable(this));
		i00.add(new CreateFreeVariable(this));
		i00.add(new CreateReferVariable(this));
		i00.add(new CreateListVariable(this));
		i00.add(new CreateMapVariable(this));
		i00.add(new CreateSetVariable(this));
		i00.add(new CreateLogicVariable(this));
		i00.add(new CreateGroupVariable(this));
		i00.add(new CreateNaturalVariable(this));
		i00.add(new CreateZIntVariable(this));
		i00.add(new CreateRationalVariable(this));
		i00.add(new CreateRealVariable(this));
		
		/*i01.add(new JMenuItem("LogicExpression"));
		i01.add(new JMenuItem("NumericExpression"));
		i01.add(new JMenuItem("GroupExpression"));
		
		i02.add(new JMenuItem("LogicFunction"));
		i02.add(new JMenuItem("GroupFunction"));
		i02.add(new JMenuItem("NumericFunction"));*/
		/*
		i02.add(new JMenuItem("ZIntegerFunction"));
		i02.add(new JMenuItem("RationalFunction"));
		i02.add(new JMenuItem("RealFunction"));
		*/
		/*i02.add(new JMenuItem("Filter"));
		i02.add(new JMenuItem("Mapper"));
		i02.add(new JMenuItem("TableMapper"));*/
	}
}
