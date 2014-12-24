package cn.edu.buaa.sei.SVI.editor.treeNode.core;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorValidateAction;
import cn.edu.buaa.sei.SVI.editor.action.expr.CreateGroupExpression;
import cn.edu.buaa.sei.SVI.editor.action.expr.CreateLogicExpression;
import cn.edu.buaa.sei.SVI.editor.action.expr.CreateNumericExpression;
import cn.edu.buaa.sei.SVI.editor.action.function.CreateFilter;
import cn.edu.buaa.sei.SVI.editor.action.function.CreateGroupFunction;
import cn.edu.buaa.sei.SVI.editor.action.function.CreateLogicFunction;
import cn.edu.buaa.sei.SVI.editor.action.function.CreateMapper;
import cn.edu.buaa.sei.SVI.editor.action.function.CreateNumericFunction;
import cn.edu.buaa.sei.SVI.editor.action.function.CreateTableMapper;
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
import cn.edu.buaa.sei.SVI.editor.treeNode.IconSet;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;

public class StructRootTreeNode extends SVITreeNode{

	final JPopupMenu menu = new JPopupMenu();
	
	void init(){
		JMenu item0 = new JMenu(); item0.setText("create");
		JMenuItem item2 = new SVIEditorValidateAction(this);
		menu.add(item0);
		menu.add(item2);
		
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
			}});*/
		
		JMenu i00 = new JMenu(); i00.setText("variable");
		JMenu i01 = new JMenu(); i01.setText("expression");
		JMenu i02 = new JMenu(); i02.setText("function");
		
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
		
		i01.add(new CreateLogicExpression(this));
		i01.add(new CreateNumericExpression(this));
		i01.add(new CreateGroupExpression(this));
		
		i02.add(new CreateLogicFunction(this));
		i02.add(new CreateNumericFunction(this));
		i02.add(new CreateGroupFunction(this));
		/*
		i02.add(new JMenuItem("ZIntegerFunction"));
		i02.add(new JMenuItem("RationalFunction"));
		i02.add(new JMenuItem("RealFunction"));
		*/
		i02.add(new CreateFilter(this));
		i02.add(new CreateMapper(this));
		i02.add(new CreateTableMapper(this));
		
		item0.add(i00); item0.add(i01); item0.add(i02);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public StructRootTreeNode(JTree tree,String name) {
		super(tree,name, IconSet.STRUCT_GROUP_ICON);
		this.init();
	}

	@Override
	public boolean validate() {
		int n = this.getChildCount();
		for(int i=0;i<n;i++){
			if(this.getChildAt(i) instanceof SVITreeNode){
				boolean res = ((SVITreeNode)this.getChildAt(i)).validate();
				if(!res)return false;
			}
			else return false;
		}
		return true;
	}
	@Override
	public int requiredChildrenCount() {return -1;}
	@Override
	public JPopupMenu getPopupMenu() {return menu;}
	@Override
	public boolean isEditable() {return false;}

	public void reset(StructRootTreeNode node) throws Exception{
		if(node==null)throw new Exception("Null root is invalid");
		if(this.tree==null)throw new Exception("Null tree: not ready for reset");
		
		DefaultTreeModel model = (DefaultTreeModel) this.getTree().getModel();
		List<SVITreeNode> children = new ArrayList<SVITreeNode>();
		for(int i=0;i<this.getChildCount();i++){
			children.add((SVITreeNode) this.getChildAt(i));
		}
		System.out.println("Initialization complete...");
		
		for(int i=0;i<children.size();i++){
			model.removeNodeFromParent(children.get(i));
		}
		children.clear();
		System.out.println("Removing all original children...");
		
		for(int i=0;i<node.getChildCount();i++){
			model.insertNodeInto((MutableTreeNode) node.getChildAt(i), this, this.getChildCount());
		}
		System.out.println("Adding all new nodes...");
		
		Queue<SVITreeNode> list = new LinkedList<SVITreeNode>();
		list.add(this);
		
		while(!list.isEmpty()){
			SVITreeNode cnode = list.poll();
			if(cnode==null)continue;
			
			cnode.setTree(this.tree);
			
			for(int i=0;i<cnode.getChildCount();i++)
				list.add((SVITreeNode) cnode.getChildAt(i));
		}
		
		model.reload();
		this.getTree().repaint();
	}
}
