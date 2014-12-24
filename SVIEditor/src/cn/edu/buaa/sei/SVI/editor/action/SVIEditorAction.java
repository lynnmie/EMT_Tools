package cn.edu.buaa.sei.SVI.editor.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;

public abstract class SVIEditorAction extends JMenuItem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected SVITreeNode node;
	
	public SVIEditorAction(SVITreeNode node){
		if(node==null)
			try {
				throw new Exception("Null node is invalid");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		this.node = node;
		
		final SVIEditorAction action = this;
		this.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					action.act();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}});
	}
	
	protected abstract void act() throws Exception;

}
