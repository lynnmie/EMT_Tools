package cn.edu.buaa.sei.SVI.editor.treeNode.core;

import javax.swing.JMenuItem;
import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorReferAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.IconSet;

public class ReferencerTreeNode extends VariableTreeNode{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	VariableTreeNode ref_node;
	
	public ReferencerTreeNode(JTree tree,String name) {
		super(tree,name,IconSet.REF_VAR_ICON);
		this.regist();
	}
	
	public void regist(){
		JMenuItem item = new SVIEditorReferAction(this);
		this.menu.add(item);
		
		/*final VariableTreeNode node = this;
		item.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				TreeNode root = node.getRoot();
				Queue<TreeNode> queue = new LinkedList<TreeNode>();
				
				queue.add(root); 
				List<TreeNode> nodes = new ArrayList<TreeNode>();
				while(!queue.isEmpty()){
					TreeNode p = queue.poll();
					if(nodes.contains(p))continue;
					nodes.add(p);
					
					if(p instanceof VariableTreeNode){
						nodes.add(p);
					}
					
					int n = p.getChildCount();
					for(int i=0;i<n;i++){
						queue.add(p.getChildAt(i));
					}
				}
				System.out.println("Extracting all related "+nodes.size()+" variables...");
				
				*//**
				 * Showing the options of Dialog...
				 * *//*
				
			}});*/
	}

	public void setRefer(VariableTreeNode ref_node){
		this.ref_node = ref_node;
	}
	public VariableTreeNode getRefer(){return this.ref_node;}
	@Override
	public boolean validate() {
		return ref_node!=null;
	}
	@Override
	public int requiredChildrenCount() {return 0;}
}
