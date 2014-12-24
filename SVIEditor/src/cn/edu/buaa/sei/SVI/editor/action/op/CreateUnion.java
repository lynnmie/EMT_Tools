package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.group.UnionTreeNode;

public class CreateUnion extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateUnion(SVITreeNode node) {
		super(node);this.setName("union");
		this.setText("Union");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new UnionTreeNode(tree,DefaultNodeNames.UNION);
	}
}
