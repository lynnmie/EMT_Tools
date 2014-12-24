package cn.edu.buaa.sei.SVI.editor.action.function;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.NumericFunctionTreeNode;

public class CreateNumericFunction extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateNumericFunction(SVITreeNode node) {
		super(node);
		this.setName("numeric function");
		this.setText("NumericFunction");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new NumericFunctionTreeNode(tree,DefaultNodeNames.NUM_FUNC);
	}
}
