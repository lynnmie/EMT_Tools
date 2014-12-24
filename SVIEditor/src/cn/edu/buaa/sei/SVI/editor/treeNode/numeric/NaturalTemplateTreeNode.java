package cn.edu.buaa.sei.SVI.editor.treeNode.numeric;
import javax.swing.JTree;
import cn.edu.buaa.sei.SVI.editor.treeNode.IconSet;

public class NaturalTemplateTreeNode extends NumericTemplateTreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NaturalTemplateTreeNode(JTree tree, String name) {
		super(tree, name, IconSet.NATURAL_TEMP_ICON);
	}
	
}
