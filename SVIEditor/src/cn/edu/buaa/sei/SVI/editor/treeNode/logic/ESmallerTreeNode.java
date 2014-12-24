package cn.edu.buaa.sei.SVI.editor.treeNode.logic;

import javax.swing.JMenu;
import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.expr.CreateNumericExpression;
import cn.edu.buaa.sei.SVI.editor.action.function.CreateNumericFunction;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateNaturalVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateRationalVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateRealVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateZIntVariable;
import cn.edu.buaa.sei.SVI.editor.treeNode.IconSet;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.BinaryOperatorTreeNode;

public class ESmallerTreeNode extends BinaryOperatorTreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ESmallerTreeNode(JTree tree, String name) {
		super(tree, name, IconSet.OP_ESMALLER_ICON);
		this.op_init();
	}
	protected void op_init(){
		JMenu item0 = (JMenu) this.menu.getComponent(0);

		item0.add(new CreateNaturalVariable(this));
		item0.add(new CreateZIntVariable(this));
		item0.add(new CreateRationalVariable(this));
		item0.add(new CreateRealVariable(this));
		item0.add(new CreateNumericExpression(this));
		item0.add(new CreateNumericFunction(this));
	}

}
