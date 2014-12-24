package cn.edu.buaa.sei.SVI.editor.action.variable;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.ZIntVariableTreeNode;

public class CreateZIntVariable extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateZIntVariable(SVITreeNode node) {
		super(node);this.setName("ZInteger");
		this.setText("ZInteger");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new ZIntVariableTreeNode(tree,DefaultNodeNames.ZINT_VAR);
	}
}
