package cn.edu.buaa.sei.SVI.editor.action.variable;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.ReferencerTreeNode;

public class CreateReferVariable extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateReferVariable(SVITreeNode node) {
		super(node);this.setName("referencer");
		this.setText("Referencer");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new ReferencerTreeNode(tree,DefaultNodeNames.REF_VAR);
	}
}
