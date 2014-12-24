package cn.edu.buaa.sei.SVI.editor.action.template;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.LogicTemplateTreeNode;

public class CreateLogicTemplate extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateLogicTemplate(SVITreeNode node) {
		super(node);this.setName("logic template");
		this.setText("LogicTemplate");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new LogicTemplateTreeNode(tree,DefaultNodeNames.LOGIC_TEMP);
	}
}
