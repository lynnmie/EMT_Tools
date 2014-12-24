package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.UniversalTreeNode;

public class CreateUniversal extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateUniversal(SVITreeNode node) {
		super(node);this.setName("universal");
		this.setText("Universal");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new UniversalTreeNode(tree,DefaultNodeNames.UNIVERSAL);
	}
}
