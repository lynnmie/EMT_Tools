package cn.edu.buaa.sei.SVI.editor.treeNode.logic;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorValidateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.IconSet;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;

public class LowerTreeNode extends SVITreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final JPopupMenu menu = new JPopupMenu();

	public LowerTreeNode(JTree tree, String name) {
		super(tree, name, IconSet.OP_LOWER_ICON);
		this.init();
	}
	protected void init(){
		JMenuItem item = new SVIEditorValidateAction(this);
		this.menu.add(item);
		
		/*final SVITreeNode node = this;
		item.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(node.validate()){
					node.setIcon(node.getIcon());
				}
				else{
					node.setIcon(IconSet.ERROR_ICON);
				}
			}});*/
	}

	@Override
	public boolean validate() {
		try{
			Integer i = Integer.parseInt(this.getUserObject().toString());
			return i>=0;
		}catch(Exception e){return false;}
	}
	@Override
	public int requiredChildrenCount() {return 0;}

	@Override
	public JPopupMenu getPopupMenu() {return this.menu;}
	@Override
	public boolean isEditable() {return true;}

}
