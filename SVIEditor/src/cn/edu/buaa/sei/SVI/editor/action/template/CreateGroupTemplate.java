package cn.edu.buaa.sei.SVI.editor.action.template;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.group.GroupTemplateTreeNode;

public class CreateGroupTemplate extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateGroupTemplate(SVITreeNode node) {
		super(node);this.setName("group template");
		this.setText("GroupTemplate");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new GroupTemplateTreeNode(tree,DefaultNodeNames.GROUP_TEMP);
	}
}
