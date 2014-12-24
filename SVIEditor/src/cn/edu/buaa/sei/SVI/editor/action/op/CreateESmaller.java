package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.ESmallerTreeNode;

public class CreateESmaller extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateESmaller(SVITreeNode node) {
		super(node);this.setName("<=");
		this.setText("ESmaller");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new ESmallerTreeNode(tree,DefaultNodeNames.ESMALLER);
	}
}
