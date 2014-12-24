package cn.edu.buaa.sei.emt.exLmf.viewer.model;

import javax.swing.ImageIcon;

public interface IconSet {
	public static final ImageIcon PACKAGE_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/package.gif"));
	public static final ImageIcon CLASS_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/class.gif"));
	public static final ImageIcon ENUM_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/list.gif"));
	public static final ImageIcon ATTRIBUTE_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/attribute.gif"));
	public static final ImageIcon REFERENCE_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/reference.gif"));
	public static final ImageIcon LITERAL_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/literal.gif"));
	
}
