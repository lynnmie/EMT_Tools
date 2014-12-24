package cn.edu.buaa.sei.SVI.editor.action.expr;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.NumericExpressionTreeNode;

public class CreateNumericExpression extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateNumericExpression(SVITreeNode node) {
		super(node);this.setName("numeric expression");
		this.setText("NumericExpression");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new NumericExpressionTreeNode(tree,DefaultNodeNames.NUM_EXPR);
	}
}
