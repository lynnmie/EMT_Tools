package cn.edu.buaa.sei.SVI.editor.action.core;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.SVIEditorAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVICellRenderer;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.ReferencerTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.VariableTreeNode;

public class SVIEditorReferAction extends SVIEditorAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SVIEditorReferAction(SVITreeNode node) {
		super(node);
		this.setName("refer");
		this.setText("Refer To");
	}

	@Override
	protected void act() throws Exception {
		if(node==null)throw new Exception("Null node is invalid");
		final JTree tree = new JTree(node.getRoot());
		tree.setCellRenderer(new SVICellRenderer());
		
		JPanel pan = new JPanel();
		pan.setLayout(new BorderLayout());
		JPanel up = new JPanel();
		up.setLayout(new BorderLayout());
		up.add(BorderLayout.CENTER,new JScrollPane(tree)); 
		//up.add(BorderLayout.EAST,new JScrollPane());
		//up.add(BorderLayout.SOUTH,new JScrollPane());
		pan.add(up,BorderLayout.CENTER);
		
		JButton ok = new JButton("Select");
		JButton no = new JButton("Cancel");
		JPanel down = new JPanel();
		down.add(ok); down.add(no);
		pan.add(down,BorderLayout.SOUTH);
		
		final JFrame frame = new JFrame();
		frame.add(pan);
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		final ReferencerTreeNode rnode = (ReferencerTreeNode) node;
		
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!(tree.getLastSelectedPathComponent() instanceof SVITreeNode)){
					frame.dispose();
					return;
				}
				SVITreeNode selected = (SVITreeNode) tree.getLastSelectedPathComponent();
				if(selected==null){
					frame.dispose();
					return;
				}
				
				if(selected instanceof VariableTreeNode){
					rnode.setRefer((VariableTreeNode) selected);
					System.out.println("Refer to: "+selected.getUserObject().toString());
				}
				else{
					JOptionPane.showMessageDialog(null, "Selected one is not variable: #"+node.getUserObject().toString(),
							"Validation", JOptionPane.ERROR_MESSAGE);
				}
				
				frame.dispose();
				return;
			}});
		no.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				return;
			}});
		
	}

	
}
