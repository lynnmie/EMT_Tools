package cn.edu.buaa.sei.SVI.editor.action.template;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.ZIntTemplateTreeNode;

public class CreateZIntTemplate extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateZIntTemplate(SVITreeNode node) {
		super(node);this.setName("zint template");
		this.setText("ZIntegerTemplate");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new ZIntTemplateTreeNode(tree,DefaultNodeNames.ZINT_TEMP);
	}
}
