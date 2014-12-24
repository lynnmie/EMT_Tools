package cn.edu.buaa.sei.emt.exLmf.viewer.model;
import javax.swing.JTree;

public class LPackageNode extends ModelNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LPackageNode(JTree tree, String name) {
		super(tree, name, IconSet.PACKAGE_ICON);
	}

}
