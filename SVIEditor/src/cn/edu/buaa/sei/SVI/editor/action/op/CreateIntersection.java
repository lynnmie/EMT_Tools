package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.group.IntersectionTreeNode;

public class CreateIntersection extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateIntersection(SVITreeNode node) {
		super(node);this.setName("intersection");
		this.setText("Intersection");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new IntersectionTreeNode(tree,DefaultNodeNames.INTERSECTION);
	}
}
