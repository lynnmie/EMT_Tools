package cn.edu.buaa.sei.SVI.editor.treeNode.numeric;
import javax.swing.JTree;
import cn.edu.buaa.sei.SVI.editor.treeNode.IconSet;

public class RealTemplateTreeNode extends NumericTemplateTreeNode{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RealTemplateTreeNode(JTree tree, String name) {
		super(tree, name, IconSet.REAL_TEMP_ICON);
	}
	
}
