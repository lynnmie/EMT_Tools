package cn.edu.buaa.sei.SVI.editor.treeNode;

import javax.swing.ImageIcon;

public interface IconSet {
	public static final ImageIcon ERROR_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/error_dir.gif"));
	
	public static final ImageIcon STRUCT_GROUP_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/root.gif"));
	public static final ImageIcon BOOL_VAR_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/logic.gif"));
	public static final ImageIcon INT_VAR_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/int.gif"));
	public static final ImageIcon LONG_VAR_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/int.gif"));
	public static final ImageIcon FLOAT_VAR_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/float.gif"));
	public static final ImageIcon DOUBLE_VAR_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/float.gif"));
	public static final ImageIcon CHAR_VAR_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/char.gif"));
	public static final ImageIcon STRING_VAR_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/string.gif"));
	public static final ImageIcon FREE_VAR_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/box.gif"));
	public static final ImageIcon LIST_VAR_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/list.gif"));
	public static final ImageIcon SET_VAR_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/set.gif"));
	public static final ImageIcon MAP_VAR_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/list.gif"));
	public static final ImageIcon LOGIC_VAR_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/logic.gif"));
	public static final ImageIcon GROUP_VAR_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/set.gif"));
	public static final ImageIcon NATURAL_VAR_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/int.gif"));
	public static final ImageIcon ZINT_VAR_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/int.gif"));
	public static final ImageIcon REAL_VAR_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/float.gif"));
	public static final ImageIcon REF_VAR_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/ref.gif"));
	public static final ImageIcon RATIONAL_VAR_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/int.gif"));
	
	public static final ImageIcon LOGIC_EXPR_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/expression.gif"));
	public static final ImageIcon NUM_EXPR_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/expression.gif"));
	public static final ImageIcon GROUP_EXPR_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/expression.gif"));
	
	public static final ImageIcon LOGIC_FUNC_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/function.gif"));
	public static final ImageIcon NUM_FUNC_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/function.gif"));
	public static final ImageIcon GROUP_FUNC_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/function.gif"));
	
	public static final ImageIcon LOGIC_TEMP_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/template.gif"));
	public static final ImageIcon GROUP_TEMP_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/template.gif"));
	public static final ImageIcon NATURAL_TEMP_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/template.gif"));
	public static final ImageIcon ZINT_TEMP_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/template.gif"));
	public static final ImageIcon RATIONAL_TEMP_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/template.gif"));
	public static final ImageIcon REAL_TEMP_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/template.gif"));
	
	public static final ImageIcon OP_ADD_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/add.gif"));
	public static final ImageIcon OP_SUB_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/sub.gif"));
	public static final ImageIcon OP_MUL_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/mul.gif"));
	public static final ImageIcon OP_DIV_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/div.gif"));
	public static final ImageIcon OP_MOD_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/mod.gif"));
	
	public static final ImageIcon OP_INTERSECTION_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/intersection.gif"));
	public static final ImageIcon OP_UNION_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/add.gif"));
	public static final ImageIcon OP_DIFFERENCE_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/sub.gif"));
	public static final ImageIcon OP_COMPLEMENT_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/sub.gif"));
	public static final ImageIcon OP_CARDINALITY_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/cardinality.gif"));
	
	public static final ImageIcon OP_NEGATION_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/not.gif"));
	public static final ImageIcon OP_CONJUNCTION_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/and.gif"));
	public static final ImageIcon OP_DISJUNCTION_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/or.gif"));
	public static final ImageIcon OP_IMPLICATION_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/implies.gif"));
	public static final ImageIcon OP_EQUIVALENCE_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/equivalence.gif"));
	public static final ImageIcon OP_EQUAL_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/equal.gif"));
	public static final ImageIcon OP_BIGGER_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/bigger.gif"));
	public static final ImageIcon OP_EBIGGER_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/bigger.gif"));
	public static final ImageIcon OP_ESMALLER_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/smaller.gif"));
	public static final ImageIcon OP_SMALLER_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/smaller.gif"));
	public static final ImageIcon OP_GROUPEQUAL_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/equal.gif"));
	public static final ImageIcon OP_CONTAIN_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/in.gif"));
	public static final ImageIcon OP_INCLUDE_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/in.gif"));
	
	public static final ImageIcon OP_UNIVERSAL_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/universal.gif"));
	public static final ImageIcon OP_EXISTENTIAL_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/universal.gif"));
	public static final ImageIcon OP_ATLEAST_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/universal.gif"));
	public static final ImageIcon OP_ATMOST_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/universal.gif"));
	public static final ImageIcon OP_BETWEEN_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/universal.gif"));
	public static final ImageIcon OP_UPPER_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/upper.gif"));
	public static final ImageIcon OP_LOWER_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/lower.gif"));
	public static final ImageIcon DISCOURSE_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/domain.gif"));
	
	public static final ImageIcon FILTER_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/filter.gif"));
	public static final ImageIcon MAPPER_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/map.gif"));
	public static final ImageIcon TABLE_MAPPER_ICON = new ImageIcon(
			IconSet.class.getClassLoader().getResource("icons/map.gif"));
	
}
