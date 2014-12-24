package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.group.ComplementTreeNode;

public class CreateComplement extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateComplement(SVITreeNode node) {
		super(node);
		this.setName("complement");
		this.setText("Complement");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new ComplementTreeNode(tree,DefaultNodeNames.COMPLEMENT);
	}
}
