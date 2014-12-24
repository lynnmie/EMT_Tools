package cn.edu.buaa.sei.SVI.editor.treeNode;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public class SVICellRenderer extends DefaultTreeCellRenderer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override  
    public Component getTreeCellRendererComponent(JTree tree, Object value,  
            boolean sel, boolean expanded, boolean leaf, int row,  
            boolean hasFocus)
    {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,  
                row, hasFocus);
		setText(value.toString()); 
		if(sel)setForeground(getTextSelectionColor());
        else setForeground(getTextNonSelectionColor());
		
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 14);
		this.setFont(f);
		
		if(value instanceof SVITreeNode){
			SVITreeNode node = (SVITreeNode) value;
			this.setIcon(node.getIcon());
		}
		
        return this;  
    }
}
