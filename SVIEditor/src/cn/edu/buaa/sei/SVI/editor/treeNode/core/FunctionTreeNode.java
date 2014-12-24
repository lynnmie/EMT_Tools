package cn.edu.buaa.sei.SVI.editor.treeNode.core;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorRemoveAction;
import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorValidateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;

public abstract class FunctionTreeNode extends SVITreeNode{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final JPopupMenu menu = new JPopupMenu();

	public FunctionTreeNode(JTree tree,String name, ImageIcon icon) {
		super(tree,name, icon);
		this._init();
	}
	
	protected void _init(){
		JMenu item0 = new JMenu("create");
		JMenuItem item1 = new SVIEditorRemoveAction(this);
		JMenuItem item2 = new SVIEditorValidateAction(this);
		
		menu.add(item0); menu.add(item1); menu.add(item2);
		
		/*final SVITreeNode node = this;
		item2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(node.validate()){
					SVITreeNode p = node;
					SVITreeNode root = (SVITreeNode) node.getRoot();
					while(p!=null){
						p.recover();
						if(p==root)break;
						p = (SVITreeNode) p.getParent();
					}
					JOptionPane.showMessageDialog(null, "Validation Passed at: #"+node.getUserObject().toString(),
							"Validation", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					SVITreeNode p = node;
					SVITreeNode root = (SVITreeNode) node.getRoot();
					while(p!=null){
						p.back();
						p.setIcon(IconSet.ERROR_ICON);
						if(p==root)break;
						p = (SVITreeNode) p.getParent();
					}
					JOptionPane.showMessageDialog(null, "Validation Failed at: #"+node.getUserObject().toString(),
							"Validation", JOptionPane.ERROR_MESSAGE); 
				}
				node.getTree().repaint();
			}});
		
		
		final DefaultTreeModel model = (DefaultTreeModel) this.tree.getModel();
		item1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				model.removeNodeFromParent(node);
			}
		});*/
	}

	@Override
	public int requiredChildrenCount() {return 1;}
	@Override
	public JPopupMenu getPopupMenu() {return menu;}
	@Override
	public boolean isEditable() {return false;}

}
