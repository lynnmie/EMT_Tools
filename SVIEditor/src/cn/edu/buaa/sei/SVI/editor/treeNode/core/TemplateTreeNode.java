package cn.edu.buaa.sei.SVI.editor.treeNode.core;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorRemoveAction;
import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorValidateAction;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateBooleanVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateCharVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateDoubleVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateFloatVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateFreeVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateGroupVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateIntegerVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateListVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateLogicVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateLongVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateMapVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateNaturalVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateRationalVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateRealVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateReferVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateSetVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateStringVariable;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateZIntVariable;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;

public class TemplateTreeNode extends SVITreeNode{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected final JPopupMenu menu = new JPopupMenu();

	public TemplateTreeNode(JTree tree, String name, ImageIcon icon) {
		super(tree, name, icon);
		this._init();
	}
	protected void _init(){
		
		JMenu i00 = new JMenu("create");
		JMenuItem item1 = new SVIEditorRemoveAction(this);
		JMenuItem item2 = new SVIEditorValidateAction(this);
		
		i00.add(new CreateBooleanVariable(this));
		i00.add(new CreateIntegerVariable(this));
		i00.add(new CreateLongVariable(this));
		i00.add(new CreateFloatVariable(this));
		i00.add(new CreateDoubleVariable(this));
		i00.add(new CreateCharVariable(this));
		i00.add(new CreateStringVariable(this));
		i00.add(new CreateFreeVariable(this));
		i00.add(new CreateReferVariable(this));
		i00.add(new CreateListVariable(this));
		i00.add(new CreateMapVariable(this));
		i00.add(new CreateSetVariable(this));
		i00.add(new CreateLogicVariable(this));
		i00.add(new CreateGroupVariable(this));
		i00.add(new CreateNaturalVariable(this));
		i00.add(new CreateZIntVariable(this));
		i00.add(new CreateRationalVariable(this));
		i00.add(new CreateRealVariable(this));
		
		menu.add(i00); menu.add(item1); menu.add(item2);
		
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
	public boolean validate() {
		int n = this.getChildCount();
		for(int i=0;i<n;i++){
			TreeNode node = this.getChildAt(i);
			if(!(node instanceof VariableTreeNode)){
				return false;
			}
		}
		return true;
	}
	@Override
	public int requiredChildrenCount() {return -1;}
	
	@Override
	public JPopupMenu getPopupMenu() {return this.menu;}
	@Override
	public boolean isEditable() {return true;}

}
