package cn.edu.buaa.sei.SVI.editor.action.variable;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.group.GroupVariableTreeNode;

public class CreateGroupVariable extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateGroupVariable(SVITreeNode node) {
		super(node);this.setName("group");
		this.setText("Group");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new GroupVariableTreeNode(tree,DefaultNodeNames.GROUP_VAR);
	}
}
