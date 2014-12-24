package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.GroupEqualTreeNode;

public class CreateGroupEqual extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateGroupEqual(SVITreeNode node) {
		super(node);this.setName("group equal");
		this.setText("GroupEqual");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new GroupEqualTreeNode(tree,DefaultNodeNames.GROUPEQUAL);
	}
}
