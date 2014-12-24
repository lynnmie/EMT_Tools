package cn.edu.buaa.sei.SVI.editor.treeNode;

public class TreeNodeFactory {
	/*public static SVITreeNode createBooleanVariable(){
		return new SVITreeNode(TypeCode.BOOL_VAR,IconSet.BOOL_VAR_ICON);
	}
	public static SVITreeNode createIntVariable(){
		return new SVITreeNode(TypeCode.INT_VAR,IconSet.INT_VAR_ICON);
	}
	public static SVITreeNode createLongVariable(){
		return new SVITreeNode(TypeCode.LONG_VAR,IconSet.LONG_VAR_ICON);
	}
	public static SVITreeNode createFloatVariable(){
		return new SVITreeNode(TypeCode.FLOAT_VAR,IconSet.FLOAT_VAR_ICON);
	}
	public static SVITreeNode createDoubleVariable(){
		return new SVITreeNode(TypeCode.DOUBLE_VAR,IconSet.DOUBLE_VAR_ICON);
	}
	public static SVITreeNode createCharVariable(){
		return new SVITreeNode(TypeCode.CHAR_VAR,IconSet.CHAR_VAR_ICON);
	}
	public static SVITreeNode createStringVariable(){
		return new SVITreeNode(TypeCode.STRING_VAR,IconSet.STRING_VAR_ICON);
	}
	public static SVITreeNode createListVariable(){
		return new SVITreeNode(TypeCode.LIST_VAR,IconSet.LIST_VAR_ICON);
	}
	public static SVITreeNode createSetVariable(){
		return new SVITreeNode(TypeCode.SET_VAR,IconSet.SET_VAR_ICON);
	}
	public static SVITreeNode createMapVariable(){
		return new SVITreeNode(TypeCode.MAP_VAR,IconSet.MAP_VAR_ICON);
	}
	public static SVITreeNode createLogicVariable(){
		return new SVITreeNode(TypeCode.LOGIC_VAR,IconSet.LOGIC_VAR_ICON);
	}
	public static SVITreeNode createGroupVariable(){
		return new SVITreeNode(TypeCode.GROUP_VAR,IconSet.GROUP_VAR_ICON);
	}
	public static SVITreeNode createNaturalVariable(){
		return new SVITreeNode(TypeCode.NATURAL_VAR,IconSet.NATURAL_VAR_ICON);
	}
	public static SVITreeNode createRationalVariable(){
		return new SVITreeNode(TypeCode.RATIONAL_VAR,IconSet.RATIONAL_VAR_ICON);
	}
	public static SVITreeNode createZIntVariable(){
		return new SVITreeNode(TypeCode.ZINT_VAR,IconSet.ZINT_VAR_ICON);
	}
	public static SVITreeNode createRealVariable(){
		return new SVITreeNode(TypeCode.REAL_VAR,IconSet.REAL_VAR_ICON);
	}
	public static SVITreeNode createReferenceVariable(){
		return new SVITreeNode(TypeCode.REF_VAR,IconSet.REF_VAR_ICON);
	}
	
	public static SVITreeNode createLogicExpression(){
		return new SVITreeNode(TypeCode.LOGIC_EXPR,IconSet.LOGIC_EXPR_ICON);
	}
	public static SVITreeNode createNumericExpression(){
		return new SVITreeNode(TypeCode.NUM_EXPR,IconSet.NUM_EXPR_ICON);
	}
	public static SVITreeNode createGroupExpression(){
		return new SVITreeNode(TypeCode.GROUP_EXPR,IconSet.GROUP_EXPR_ICON);
	}
	
	public static SVITreeNode createLogicFunction(){
		return new SVITreeNode(TypeCode.LOGIC_FUNC,IconSet.LOGIC_FUNC_ICON);
	}
	public static SVITreeNode createGroupFunction(){
		return new SVITreeNode(TypeCode.REAL_VAR,IconSet.REAL_VAR_ICON);
	}
	public static SVITreeNode createNumericFunction(){
		return new SVITreeNode(TypeCode.NUM_FUNC,IconSet.NUM_FUNC_ICON);
	}
	public static SVITreeNode createLogicTemplate(){
		return new SVITreeNode(TypeCode.LOGIC_TEMP,IconSet.LOGIC_TEMP_ICON);
	}
	public static SVITreeNode createGroupTemplate(){
		return new SVITreeNode(TypeCode.GROUP_FUNC,IconSet.GROUP_FUNC_ICON);
	}
	public static SVITreeNode createNaturalTemplate(){
		return new SVITreeNode(TypeCode.NATURAL_TEMP,IconSet.NATURAL_TEMP_ICON);
	}
	public static SVITreeNode createZIntTemplate(){
		return new SVITreeNode(TypeCode.ZINT_TEMP,IconSet.ZINT_TEMP_ICON);
	}
	public static SVITreeNode createRationalTemplate(){
		return new SVITreeNode(TypeCode.RATIONAL_TEMP,IconSet.RATIONAL_TEMP_ICON);
	}
	
	public static SVITreeNode createRealTemplate(){
		return new SVITreeNode(TypeCode.REAL_TEMP,IconSet.REAL_TEMP_ICON);
	}
	public static SVITreeNode createFilter(){
		return new SVITreeNode(TypeCode.FILTER,IconSet.FILTER_ICON);
	}
	public static SVITreeNode createMapper(){
		return new SVITreeNode(TypeCode.MAPPER,IconSet.MAPPER_ICON);
	}
	public static SVITreeNode createTableMapper(){
		return new SVITreeNode(TypeCode.TMAPPER,IconSet.TABLE_MAPPER_ICON);
	}
	
	public static SVITreeNode createNegation(){
		return new SVITreeNode(TypeCode.NEGATION,IconSet.OP_NEGATION_ICON);
	}
	public static SVITreeNode createConjunction(){
		return new SVITreeNode(TypeCode.CONJUNCTION,IconSet.OP_CONJUNCTION_ICON);
	}
	public static SVITreeNode createDisjunction(){
		return new SVITreeNode(TypeCode.DISJUNCTION,IconSet.OP_DISJUNCTION_ICON);
	}
	public static SVITreeNode createImplication(){
		return new SVITreeNode(TypeCode.IMPLICATION,IconSet.OP_IMPLICATION_ICON);
	}
	public static SVITreeNode createEquivalence(){
		return new SVITreeNode(TypeCode.EQUIVALENCE,IconSet.OP_EQUIVALENCE_ICON);
	}
	public static SVITreeNode createSmaller(){
		return new SVITreeNode(TypeCode.SMALLER,IconSet.OP_SMALLER_ICON);
	}
	public static SVITreeNode createESmaller(){
		return new SVITreeNode(TypeCode.ESMALLER,IconSet.OP_ESMALLER_ICON);
	}
	public static SVITreeNode createEqual(){
		return new SVITreeNode(TypeCode.EQUAL,IconSet.OP_EQUAL_ICON);
	}
	public static SVITreeNode createEBigger(){
		return new SVITreeNode(TypeCode.EBIGGER,IconSet.OP_EBIGGER_ICON);
	}
	public static SVITreeNode createBigger(){
		return new SVITreeNode(TypeCode.BIGGER,IconSet.OP_BIGGER_ICON);
	}
	public static SVITreeNode createGroupEqual(){
		return new SVITreeNode(TypeCode.GROUP_EQUAL,IconSet.OP_GROUPEQUAL_ICON);
	}
	public static SVITreeNode createContain(){
		return new SVITreeNode(TypeCode.CONTAIN,IconSet.OP_CONTAIN_ICON);
	}
	public static SVITreeNode createInclude(){
		return new SVITreeNode(TypeCode.INCLUDE,IconSet.OP_INCLUDE_ICON);
	}
	
	public static SVITreeNode createAddition(){
		return new SVITreeNode(TypeCode.ADD,IconSet.OP_ADD_ICON);
	}
	public static SVITreeNode createSubstraction(){
		return new SVITreeNode(TypeCode.SUB,IconSet.OP_SUB_ICON);
	}
	public static SVITreeNode createMultiplication(){
		return new SVITreeNode(TypeCode.MUL,IconSet.OP_MUL_ICON);
	}
	public static SVITreeNode createDivision(){
		return new SVITreeNode(TypeCode.DIV,IconSet.OP_DIV_ICON);
	}
	public static SVITreeNode createMod(){
		return new SVITreeNode(TypeCode.MOD,IconSet.OP_MOD_ICON);
	}
	public static SVITreeNode createCardinality(){
		return new SVITreeNode(TypeCode.CARDINALITY,IconSet.OP_CARDINALITY_ICON);
	}
	
	public static SVITreeNode createIntersection(){
		return new SVITreeNode(TypeCode.INTERSECTION,IconSet.OP_INTERSECTION_ICON);
	}
	public static SVITreeNode createUnion(){
		return new SVITreeNode(TypeCode.UNION,IconSet.OP_UNION_ICON);
	}
	public static SVITreeNode createDifference(){
		return new SVITreeNode(TypeCode.DIFFERENCE,IconSet.OP_DIFFERENCE_ICON);
	}
	public static SVITreeNode createComplement(){
		return new SVITreeNode(TypeCode.COMPLEMENT,IconSet.OP_COMPLEMENT_ICON);
	}
	
	public static SVITreeNode createUniversal(){
		return new SVITreeNode(TypeCode.UNIVERSAL,IconSet.OP_UNIVERSAL_ICON);
	}
	public static SVITreeNode createExistential(){
		return new SVITreeNode(TypeCode.EXISTENTIAL,IconSet.OP_EXISTENTIAL_ICON);
	}
	public static SVITreeNode createAtLeast(){
		return new SVITreeNode(TypeCode.ATLEAST,IconSet.OP_ATLEAST_ICON);
	}
	public static SVITreeNode createAtMost(){
		return new SVITreeNode(TypeCode.ATMOST,IconSet.OP_ATMOST_ICON);
	}
	public static SVITreeNode createBetween(){
		return new SVITreeNode(TypeCode.BETWEEN,IconSet.OP_BETWEEN_ICON);
	}*/
	
}
