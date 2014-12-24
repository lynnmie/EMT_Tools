package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.EqualTreeNode;
public class CreateEqual extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateEqual(SVITreeNode node) {
		super(node);this.setName("==");
		this.setText("Equal");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new EqualTreeNode(tree,DefaultNodeNames.EQUAL);
	}
}
