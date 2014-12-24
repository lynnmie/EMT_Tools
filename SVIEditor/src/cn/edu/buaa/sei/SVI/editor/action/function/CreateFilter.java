package cn.edu.buaa.sei.SVI.editor.action.function;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.group.FilterTreeNode;

public class CreateFilter extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateFilter(SVITreeNode node) {
		super(node);this.setName("filter");
		this.setText("Filter");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new FilterTreeNode(tree,DefaultNodeNames.FILTER);
	}
}
