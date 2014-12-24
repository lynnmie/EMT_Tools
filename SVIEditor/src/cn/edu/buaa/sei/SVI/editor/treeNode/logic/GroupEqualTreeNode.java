package cn.edu.buaa.sei.SVI.editor.treeNode.logic;

import javax.swing.JMenu;
import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.expr.CreateGroupExpression;
import cn.edu.buaa.sei.SVI.editor.action.function.CreateFilter;
import cn.edu.buaa.sei.SVI.editor.action.function.CreateLogicFunction;
import cn.edu.buaa.sei.SVI.editor.action.function.CreateMapper;
import cn.edu.buaa.sei.SVI.editor.action.function.CreateTableMapper;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateGroupVariable;
import cn.edu.buaa.sei.SVI.editor.treeNode.IconSet;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.BinaryOperatorTreeNode;

public class GroupEqualTreeNode extends BinaryOperatorTreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GroupEqualTreeNode(JTree tree, String name) {
		super(tree, name, IconSet.OP_GROUPEQUAL_ICON);
		this.op_init();
	}
	protected void op_init(){
		JMenu item0 = (JMenu) this.menu.getComponent(0);

		item0.add(new CreateGroupVariable(this));
		item0.add(new CreateGroupExpression(this));
		item0.add(new CreateFilter(this));
		item0.add(new CreateMapper(this));
		item0.add(new CreateTableMapper(this));
		item0.add(new CreateLogicFunction(this));
	}
}
