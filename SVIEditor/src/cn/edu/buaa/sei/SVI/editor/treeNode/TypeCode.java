package cn.edu.buaa.sei.SVI.editor.treeNode;

public interface TypeCode {
	public static final int STRUCT_GROUP = 0;
	
	public static final int BOOL_VAR = 0;
	public static final int INT_VAR = 0;
	public static final int LONG_VAR = 0;
	public static final int FLOAT_VAR = 0;
	public static final int DOUBLE_VAR = 0;
	public static final int CHAR_VAR = 0;
	public static final int STRING_VAR = 0;
	public static final int LIST_VAR = 0;
	public static final int SET_VAR = 0;
	public static final int MAP_VAR = 0;
	public static final int LOGIC_VAR = 0;
	public static final int GROUP_VAR = 0;
	public static final int NATURAL_VAR = 0;
	public static final int RATIONAL_VAR = 0;
	public static final int ZINT_VAR = 0;
	public static final int REAL_VAR = 0;
	public static final int REF_VAR = 0;
	public static final int DISCOURSE_VAR = 0;
	
	public static final int LOGIC_EXPR = 0;
	public static final int GROUP_EXPR = 0;
	public static final int NUM_EXPR = 0;
	
	public static final int LOGIC_FUNC = 0;
	public static final int GROUP_FUNC = 0;
	public static final int NUM_FUNC = 0;
	public static final int FILTER = 0;
	public static final int MAPPER = 0;
	public static final int TMAPPER = 0;
	
	public static final int LOGIC_TEMP = 0;
	public static final int GROUP_TEMP = 0;
	public static final int NATURAL_TEMP = 0;
	public static final int RATIONAL_TEMP = 0;
	public static final int ZINT_TEMP = 0;
	public static final int REAL_TEMP = 0;
	
	public static final int NEGATION = 0;
	public static final int CONJUNCTION = 0;
	public static final int DISJUNCTION = 0;
	public static final int IMPLICATION = 0;
	public static final int EQUIVALENCE = 0;
	public static final int SMALLER = 0;
	public static final int ESMALLER = 0;
	public static final int EQUAL = 0;
	public static final int EBIGGER = 0;
	public static final int BIGGER = 0;
	public static final int GROUP_EQUAL = 0;
	public static final int CONTAIN = 0;
	public static final int INCLUDE = 0;
	public static final int ADD = 0;
	public static final int SUB = 0;
	public static final int MUL = 0;
	public static final int DIV = 0;
	public static final int MOD = 0;
	public static final int CARDINALITY = 0;
	public static final int INTERSECTION = 0;
	public static final int UNION = 0;
	public static final int DIFFERENCE = 0;
	public static final int COMPLEMENT = 0;
	
	public static final int UNIVERSAL = 0;
	public static final int EXISTENTIAL = 0;
	public static final int ATLEAST = 0;
	public static final int ATMOST = 0;
	public static final int BETWEEN = 0;
}
