package cn.edu.buaa.sei.emt.exLmf.viewer.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

import cn.edu.buaa.sei.exLmf.metamodel.LAttribute;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassifier;
import cn.edu.buaa.sei.exLmf.metamodel.LEnum;
import cn.edu.buaa.sei.exLmf.metamodel.LEnumLiteral;
import cn.edu.buaa.sei.exLmf.metamodel.LModelElement;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.metamodel.LReference;
import cn.edu.buaa.sei.exLmf.metamodel.LStructuralFeature;

public class ModelPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JTree tree;
	protected ModelNode root_node;
	
	public ModelPanel(){
		this.root_node = new LPackageNode(null,"root");
		this.tree = new JTree(this.root_node);
		tree.setCellRenderer(new ExLMFCellRenderer());
		this.root_node.setTree(this.tree);
		this.add(tree);
	}
	
	public JTree getTree(){return this.tree;}
	
	public void update(LPackage root) throws Exception{
		if(root==null)throw new Exception("Null root model is invalid");
		
		this.clearTree();
		
		this.root_node.setUserObject(root.getName());
		
		final DefaultTreeModel model = (DefaultTreeModel) this.tree.getModel();
		List<LPackage> sps = root.getSubPackages();
		for(int i=0;i<sps.size();i++){
			ModelNode newOne = this.getNode(sps.get(i));
			model.insertNodeInto(newOne, this.root_node, this.root_node.getChildCount());
		}
		List<LClassifier> types = root.getTypes();
		for(int i=0;i<types.size();i++){
			ModelNode newOne = this.getNode(types.get(i));
			model.insertNodeInto(newOne, this.root_node, this.root_node.getChildCount());
		}
		
		
	}
	protected void clearTree(){
		final DefaultTreeModel model = (DefaultTreeModel) this.tree.getModel();
		int n = this.root_node.getChildCount();
		
		List<MutableTreeNode> nlist = new ArrayList<MutableTreeNode>();
		for(int i=0;i<n;i++)
			nlist.add((MutableTreeNode) root_node.getChildAt(i));
		
		for(int i=0;i<n;i++){
			model.removeNodeFromParent(nlist.get(i));
		}
		
		this.root_node.removeAllChildren();
		this.tree.repaint();
		this.repaint();
	}
	
	protected ModelNode getNode(LModelElement element) throws Exception{
		if(element==null)throw new Exception("Null element is invalid");
		
		if(element instanceof LPackage){return this.getNodeFromPackage((LPackage) element);}
		else if(element instanceof LClass){return this.getNodeFromClass((LClass) element);}
		else if(element instanceof LAttribute){return this.getNodeFromAttribute((LAttribute) element);}
		else if(element instanceof LReference){return this.getNodeFromReference((LReference) element);}
		else if(element instanceof LEnum){return this.getNodeFromEnum((LEnum) element);}
		else if(element instanceof LEnumLiteral){return this.getNodeFromLiteral((LEnumLiteral) element);}
		else throw new Exception("Unknown model elemnet: "+element.getClass().getCanonicalName());
	}
	protected LPackageNode getNodeFromPackage(LPackage p) throws Exception{
		if(p==null)throw new Exception("Null package is invalid");
		
		LPackageNode node = new LPackageNode(this.root_node.getTree(),p.getName());
		
		List<LPackage> sub_packs = p.getSubPackages();
		for(int i=0;i<sub_packs.size();i++){
			LPackage sp = sub_packs.get(i);
			ModelNode snode = this.getNode(sp);
			node.add(snode);
		}
		List<LClassifier> children = p.getTypes();
		for(int i=0;i<children.size();i++){
			LClassifier type = children.get(i);
			ModelNode cnode = this.getNode(type);
			node.add(cnode);
		}
		
		return node;
	}
	protected LClassNode getNodeFromClass(LClass type) throws Exception{
		if(type==null)throw new Exception("Null type is invalid");
		
		StringBuilder code = new StringBuilder();
		code.append(type.getName());
		if(!type.getSuperTypes().isEmpty()){
			code.append("-->[");
			for(int i=0;i<type.getSuperTypes().size();i++){
				code.append(type.getSuperTypes().get(i).getName());
				if(i!=type.getSuperTypes().size()-1)
					code.append(", ");
			}
			code.append("]");
		}
		
		LClassNode node = new LClassNode(this.root_node.getTree(),code.toString());
		
		List<LStructuralFeature> fs = type.getFeatures();
		for(int i=0;i<fs.size();i++){
			ModelNode cnode = this.getNode(fs.get(i));
			node.add(cnode);
		}
		
		return node;
	}
	protected LEnumNode getNodeFromEnum(LEnum e) throws Exception{
		if(e==null)throw new Exception("Null enum is invalid");
		
		LEnumNode node = new LEnumNode(this.root_node.getTree(),e.getName());
		
		List<LEnumLiteral> ls = e.getLiterals();
		for(int i=0;i<ls.size();i++){
			ModelNode lnode = this.getNode(ls.get(i));
			node.add(lnode);
		}
		
		return node;
	}
	protected LAttributeNode getNodeFromAttribute(LAttribute attr) throws Exception{
		if(attr==null)throw new Exception("Null attribute is invalid");
		LAttributeNode node = new LAttributeNode(this.root_node.getTree(),attr.getName()+":"+attr.getDataType().getName());
		return node;
	}
	protected LEnumLiteralNode getNodeFromLiteral(LEnumLiteral l) throws Exception{
		if(l==null)throw new Exception("Null literal is invalid");
		
		LEnumLiteralNode node = new LEnumLiteralNode(this.root_node.getTree(),l.getName()+" ["+l.getValue()+"]");
		return node;
	}
	protected LReferenceNode getNodeFromReference(LReference ref) throws Exception{
		if(ref==null)throw new Exception("Null reference is invalid");
		
		LReferenceNode node = new LReferenceNode(this.root_node.getTree(),ref.getName()+": "+ref.getLClass().getName());
		return node;
	}
	
}
