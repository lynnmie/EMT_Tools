package cn.edu.buaa.sei.SVI.editor.action.core;

import javax.swing.JOptionPane;
import cn.edu.buaa.sei.SVI.editor.action.SVIEditorAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.IconSet;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;

public class SVIEditorValidateAction extends SVIEditorAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SVIEditorValidateAction(SVITreeNode node) {
		super(node);
		this.setName("validate");
		this.setText("validate");
	}

	@Override
	public void act() throws Exception {
		if(node==null)throw new Exception("Null node is invalid");
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
		
	}

}
