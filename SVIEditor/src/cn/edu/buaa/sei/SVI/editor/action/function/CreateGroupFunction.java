package cn.edu.buaa.sei.SVI.editor.action.function;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.group.GroupFunctionTreeNode;

public class CreateGroupFunction extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateGroupFunction(SVITreeNode node) {
		super(node);this.setName("group function");
		this.setText("GroupFunction");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new GroupFunctionTreeNode(tree,DefaultNodeNames.GROUP_FUNC);
	}
}
