package cn.edu.buaa.sei.SVI.manage.impl.xml_struct;

public class XMLStructTags {
	/**
	 * Root Element Tag Name
	 * */
	public static final String ROOT = "StructGroup";
	
	/**
	 * Core Attribute List
	 * */
	public static final String ID = "_id";
	public static final String REF = "ref";
	public static final String TYPE = "type";
	
	/**
	 * Variable Tag Name
	 * */
	public static final String VARIABLE = "Variable";
	public static final String NAME = "name";
	
	public static final String BOOLEAN_TYPE = "boolean";
	public static final String INTEGER_TYPE = "int";
	public static final String LONG_TYPE = "long";
	public static final String FLOAT_TYPE = "float";
	public static final String DOUBLE_TYPE = "double";
	public static final String STRING_TYPE = "String";
	public static final String CHARACTER_TYPE = "char";
	public static final String LIST_TYPE = "List";
	public static final String SET_TYPE = "Set";
	public static final String MAP_TYPE = "Map";
	
	public static final String FREE_TYPE = "free";
	public static final String REF_TYPE = "refer";
	
	public static final String LOGIC_TYPE = "logic";
	public static final String DISCOURSE_DOMAIN = "domain";
	public static final String DISCOURSE_DOMAIN_ITER = "iterator";
	
	public static final String NUM_NATURAL_TYPE = "Natural";
	public static final String NUM_INTEGER_TYPE = "Integer";
	public static final String NUM_RATIONAL_TYPE = "Rational";
	public static final String NUM_REAL_TYPE = "Real";
	
	public static final String GROUP_TYPE = "Group";
	
	/**
	 * Expression Tag Name
	 * */
	public static final String EXPRESSION = "Expression";
	
	public static final String LOGIC_EXPR_TYPE = "logic";
	public static final String NUMERIC_EXPR_TYPE = "numeric";
	public static final String GROUP_EXPR_TYPE = "group";
	
	public static final String CONJUNCTION = "Conjunction";
	public static final String DISJUNCTION = "Disjunction";
	public static final String NEGATION = "Negation";
	public static final String IMPLICATION = "Implication";
	public static final String EQUIVALENCE = "Equivalence";
	public static final String UNIVERSAL = "Universal";
	public static final String EXISTENTIAL = "Existential";
	public static final String ATMOST = "AtMost";
	public static final String ATLEAST = "AtLeast";
	public static final String BETWEEN = "Between";
	
	public static final String UPBOUND = "upper";
	public static final String LOWBOUND = "lower";
	
	public static final String ADD = "Addition";
	public static final String SUB = "Substraction";
	public static final String MUL = "Multiplication";
	public static final String DIV = "Division";
	public static final String MOD = "Mod";
	
	public static final String BIGGER = "Bigger";
	public static final String EBIGGER = "EBigger";
	public static final String EQUAL = "Equal";
	public static final String ESMALLER = "ESmaller";
	public static final String SMALLER = "Smaller";
	
	public static final String INTERSECTION = "Intersection";
	public static final String UNION = "Union";
	public static final String DIFFERENCE = "Difference";
	public static final String COMPLEMENT = "Complement";
	public static final String CARTESIAN_PRODUCT = "CartesianProduct";
	
	public static final String CARDINALITY = "Cardinality";
	public static final String CONTAIN = "Contain";
	public static final String INCLUDE = "Include";
	public static final String GROUP_EQUAL = "GroupEqual";
	
	/**
	 * Function Tag Name
	 * */
	public static final String FUNCTION = "Function";
	public static final String TEMPLATE = "Template";
	
	public static final String FILTER = "FilterFunction";
	public static final String MAPPER = "MapFunction";
	public static final String TABLE_MAPPER = "TableMapFunction";
	
	public static final String LOGIC_FUNCTION_TYPE = "logic";
	public static final String NUM_FUNCTION_TYPE = "numeric";
	public static final String GROUP_FUNCTION_TYPE = "group";
}
