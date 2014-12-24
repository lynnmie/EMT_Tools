package cn.edu.buaa.sei.SVI.editor.action.expr;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.LogicExpressionTreeNode;

public class CreateLogicExpression extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateLogicExpression(SVITreeNode node) {
		super(node);
		this.setName("logic expression");
		this.setText("LogicExpression");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new LogicExpressionTreeNode(tree,DefaultNodeNames.LOGIC_EXPR);
	}
}
