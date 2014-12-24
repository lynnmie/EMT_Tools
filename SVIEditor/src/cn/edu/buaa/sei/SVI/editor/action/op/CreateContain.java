package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.ContainTreeNode;

public class CreateContain extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateContain(SVITreeNode node) {
		super(node);
		this.setName("contain");
		this.setText("Contain");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new ContainTreeNode(tree,DefaultNodeNames.CONTAIN);
	}
}
