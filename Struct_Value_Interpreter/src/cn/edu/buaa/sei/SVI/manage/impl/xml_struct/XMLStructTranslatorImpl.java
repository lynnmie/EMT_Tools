package cn.edu.buaa.sei.SVI.manage.impl.xml_struct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cn.edu.buaa.sei.SVI.manage.XMLStructTranslator;
import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.expression.BinaryOperator;
import cn.edu.buaa.sei.SVI.struct.core.expression.Expression;
import cn.edu.buaa.sei.SVI.struct.core.expression.FlexibleOperator;
import cn.edu.buaa.sei.SVI.struct.core.expression.MultipleOperator;
import cn.edu.buaa.sei.SVI.struct.core.expression.Operator;
import cn.edu.buaa.sei.SVI.struct.core.expression.UnaryOperator;
import cn.edu.buaa.sei.SVI.struct.core.extend.GroupStruct;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;
import cn.edu.buaa.sei.SVI.struct.core.extend.NumericStruct;
import cn.edu.buaa.sei.SVI.struct.core.function.Function;
import cn.edu.buaa.sei.SVI.struct.core.function.FunctionTemplate;
import cn.edu.buaa.sei.SVI.struct.core.variable.FreeVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.ReferenceVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.core.variable.base.BooleanVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.base.CharacterVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.base.DoubleVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.base.FloatVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.base.IntegerVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.base.ListVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.base.LongVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.base.MapVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.base.SetVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.base.StringVariable;
import cn.edu.buaa.sei.SVI.struct.core.variable.impl.VariableFactory;
import cn.edu.buaa.sei.SVI.struct.group.Cardinality;
import cn.edu.buaa.sei.SVI.struct.group.CartesianProduct;
import cn.edu.buaa.sei.SVI.struct.group.Complement;
import cn.edu.buaa.sei.SVI.struct.group.Contain;
import cn.edu.buaa.sei.SVI.struct.group.Difference;
import cn.edu.buaa.sei.SVI.struct.group.GroupEqual;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunction;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunctionTemplate;
import cn.edu.buaa.sei.SVI.struct.group.GroupOperator;
import cn.edu.buaa.sei.SVI.struct.group.GroupVariable;
import cn.edu.buaa.sei.SVI.struct.group.Include;
import cn.edu.buaa.sei.SVI.struct.group.Intersection;
import cn.edu.buaa.sei.SVI.struct.group.Union;
import cn.edu.buaa.sei.SVI.struct.group.extend.FilterTemplate;
import cn.edu.buaa.sei.SVI.struct.group.extend.MapTemplate;
import cn.edu.buaa.sei.SVI.struct.group.extend.TableMapTemplate;
import cn.edu.buaa.sei.SVI.struct.group.impl.GroupFactory;
import cn.edu.buaa.sei.SVI.struct.logic.AtLeast;
import cn.edu.buaa.sei.SVI.struct.logic.AtMost;
import cn.edu.buaa.sei.SVI.struct.logic.Between;
import cn.edu.buaa.sei.SVI.struct.logic.Conjunction;
import cn.edu.buaa.sei.SVI.struct.logic.DiscourseDomain;
import cn.edu.buaa.sei.SVI.struct.logic.Disjunction;
import cn.edu.buaa.sei.SVI.struct.logic.Equivalence;
import cn.edu.buaa.sei.SVI.struct.logic.Existential;
import cn.edu.buaa.sei.SVI.struct.logic.Implication;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunction;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunctionTemplate;
import cn.edu.buaa.sei.SVI.struct.logic.LogicOperator;
import cn.edu.buaa.sei.SVI.struct.logic.LogicVariable;
import cn.edu.buaa.sei.SVI.struct.logic.Negation;
import cn.edu.buaa.sei.SVI.struct.logic.QuantifierOperator;
import cn.edu.buaa.sei.SVI.struct.logic.Universal;
import cn.edu.buaa.sei.SVI.struct.logic.impl.LogicFactory;
import cn.edu.buaa.sei.SVI.struct.numeric.Addition;
import cn.edu.buaa.sei.SVI.struct.numeric.Division;
import cn.edu.buaa.sei.SVI.struct.numeric.Mod;
import cn.edu.buaa.sei.SVI.struct.numeric.Multiplication;
import cn.edu.buaa.sei.SVI.struct.numeric.NaturalVariable;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericFunction;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericFunctionTemplate;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericOperator;
import cn.edu.buaa.sei.SVI.struct.numeric.RationalVariable;
import cn.edu.buaa.sei.SVI.struct.numeric.RealVariable;
import cn.edu.buaa.sei.SVI.struct.numeric.Substraction;
import cn.edu.buaa.sei.SVI.struct.numeric.ZIntegerVariable;
import cn.edu.buaa.sei.SVI.struct.numeric.impl.NumericFactory;
import cn.edu.buaa.sei.SVI.struct.numeric.logic.Bigger;
import cn.edu.buaa.sei.SVI.struct.numeric.logic.EBigger;
import cn.edu.buaa.sei.SVI.struct.numeric.logic.ESmaller;
import cn.edu.buaa.sei.SVI.struct.numeric.logic.Equal;
import cn.edu.buaa.sei.SVI.struct.numeric.logic.Smaller;

public class XMLStructTranslatorImpl implements XMLStructTranslator{
	
	Document doc;
	
	/**
	 * Translation code
	 * */
	Map<Struct,Element> tmap = new HashMap<Struct,Element>();
	
	public XMLStructTranslatorImpl(Document doc) throws Exception{
		if(doc==null)throw new Exception("Null document is invalid");
		this.doc=doc;
		this.init();
	}
	
	private void init(){
		this.tmap.clear();
		this.id = 0L;
		this.linked_set.clear();
		this.id_elements.clear();
		this.rmap.clear();
		this.ref_elms.clear();
	}
	@Override
	public void restart() {
		this.init();
	}

	@Override
	public Element translate(Struct struct) throws Exception {
		if(struct==null)throw new Exception("Null struct is invalid");
		
		Element root = this.createElement(struct);
		this.linkAll(struct);
		
		return root;
	}
	@Override
	public void update(Struct top) throws Exception{
		if(top==null||!this.tmap.containsKey(top))
			throw new Exception("Null|Undefined top is invalid");
		
		Queue<Struct> queue = new LinkedList<Struct>();
		queue.add(top);
		
		while(!queue.isEmpty()){
			top = queue.poll();
			Element element = this.tmap.get(top);
			
			if(top instanceof ReferenceVariable){
				if(((ReferenceVariable) top).getRefer()!=null){
					Element ref_elm = this.createElement(((ReferenceVariable) top).getRefer());
					if(ref_elm==null)throw new Exception("Element for refer is failed");
					
					String id = ref_elm.getAttribute(XMLStructTags.ID);
					if(id==null||id.trim().length()==0){
						id = nextID();
						ReferenceVariable ref = ((ReferenceVariable) top);
						System.out.println(ref.getName()+"--> "+
						ref.getRefer().getName()+"@"+ref.getRefer().hashCode()+": "+ref_elm.hashCode()+"<"+id+">");
						
						ref_elm.setAttribute(XMLStructTags.ID, id);
						this.linked_set.put(ref.getRefer(), id);
					}
					element.setTextContent(id);
				}
			}
			
			if(top instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) top).getChildrenStructs();
				int n = ((CompositeStruct) top).getChildrenStructSize();
				for(int i=0;i<n;i++)
					queue.add(children[i]);
			}
			
		}
	}
	
	protected Element createElement(Struct top) throws Exception{
		if(top==null)throw new Exception("Null top is invalid");
		if(this.tmap.containsKey(top))return this.tmap.get(top);
		
		Queue<Struct> queue = new LinkedList<Struct>();
		queue.add(top);
		
		Struct root = top;
		
		while(!queue.isEmpty()){
			top = queue.poll();
			if(this.tmap.containsKey(top))continue;
			
			if(top instanceof Variable){this.createVariable((Variable) top);}
			else if(top instanceof Expression){this.createExpression((Expression) top);}
			else if(top instanceof Function){this.createFunction((Function) top);}
			else if(top instanceof FunctionTemplate){this.createTemplate((FunctionTemplate) top);}
			else if(top instanceof Operator){this.createOperator((Operator) top);}
			
			if(top instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) top).getChildrenStructs();
				int n = ((CompositeStruct) top).getChildrenStructSize();
				for(int i=0;i<n;i++)
					queue.add(children[i]);
			}
		}
		
		return this.tmap.get(root);
	}
	protected Element createVariable(Variable variable) throws Exception{
		if(variable==null)throw new Exception("Null variable is invalid");
		if(this.tmap.containsKey(variable)){
			return this.tmap.get(variable);
		}
		Element element = doc.createElement(XMLStructTags.VARIABLE);
		
		String name = variable.getName();
		String type = this.getType(variable);
		if(type==null)throw new Exception("Unknown type");
		element.setAttribute(XMLStructTags.NAME, name);
		element.setAttribute(XMLStructTags.TYPE, type);
		
		if(variable instanceof DiscourseDomain){
			Variable itor = ((DiscourseDomain) variable).getIterator();
			Element ie = doc.createElement(XMLStructTags.DISCOURSE_DOMAIN_ITER);
			ie.setAttribute(XMLStructTags.NAME, itor.getName());
			ie.setAttribute(XMLStructTags.TYPE, XMLStructTags.FREE_TYPE);
			this.tmap.put(itor, ie);
			//System.out.println(itor.getName()+": "+itor.hashCode()+"{"+ie.hashCode()+"}");
		}
		/*else if(variable instanceof ReferenceVariable){
			if(((ReferenceVariable) variable).getRefer()!=null){
				Element ref_elm = this.createElement(((ReferenceVariable) variable).getRefer());
				if(ref_elm==null)throw new Exception("Element for refer is failed");
				
				String id = ref_elm.getAttribute(XMLStructTags.ID);
				if(id==null||id.trim().length()==0){
					id = nextID();
					ref_elm.setAttribute(XMLStructTags.ID, id);
					//System.out.println("Generating new id:"+id);
				}
				element.setTextContent(id);
				//System.out.println("Writting refer variable id: "+ref_elm.getAttribute(XMLStructTags.ID));
			}
		}*/
		
		this.tmap.put(variable, element);
		//System.out.println("###"+variable.getName()+": "+variable.hashCode());
		return element;
	}
	protected String getType(Variable var) throws Exception{
		if(var==null)throw new Exception("Null var is invalid");
		
		if(var instanceof LogicVariable){return XMLStructTags.LOGIC_TYPE;}
		else if(var instanceof GroupVariable){return XMLStructTags.GROUP_TYPE;}
		else if(var instanceof DiscourseDomain){return XMLStructTags.DISCOURSE_DOMAIN;}
		else if(var instanceof NaturalVariable){return XMLStructTags.NUM_NATURAL_TYPE;}
		else if(var instanceof ZIntegerVariable){return XMLStructTags.NUM_INTEGER_TYPE;}
		else if(var instanceof RationalVariable){return XMLStructTags.NUM_RATIONAL_TYPE;}
		else if(var instanceof RealVariable){return XMLStructTags.NUM_REAL_TYPE;}
		else if(var instanceof BooleanVariable){return XMLStructTags.BOOLEAN_TYPE;}
		else if(var instanceof IntegerVariable){return XMLStructTags.INTEGER_TYPE;}
		else if(var instanceof LongVariable){return XMLStructTags.LONG_TYPE;}
		else if(var instanceof CharacterVariable){return XMLStructTags.CHARACTER_TYPE;}
		else if(var instanceof StringVariable){return XMLStructTags.STRING_TYPE;}
		else if(var instanceof FloatVariable){return XMLStructTags.FLOAT_TYPE;}
		else if(var instanceof DoubleVariable){return XMLStructTags.DOUBLE_TYPE;}
		else if(var instanceof ListVariable){return XMLStructTags.LIST_TYPE;}
		else if(var instanceof SetVariable){return XMLStructTags.SET_TYPE;}
		else if(var instanceof MapVariable){return XMLStructTags.MAP_TYPE;}
		else if(var instanceof FreeVariable){return XMLStructTags.FREE_TYPE;}
		else if(var instanceof ReferenceVariable){return XMLStructTags.REF_TYPE;}
		else throw new Exception("Unknown Variable Type: {"+var.getClass().getCanonicalName()+"}@"+var.getName());
	}
	protected Element createExpression(Expression expr) throws Exception{
		if(expr==null)throw new Exception("Null expression is invalid");
		if(this.tmap.containsKey(expr))return this.tmap.get(expr);
		
		Element element = doc.createElement(XMLStructTags.EXPRESSION);
		this.tmap.put(expr, element);
		return element;
	}
	protected Element createOperator(Operator op) throws Exception{
		if(op==null)throw new Exception("Null op is invalid");
		if(this.tmap.containsKey(op))return this.tmap.get(op);
		
		Element element = null;
		if(op instanceof LogicOperator){
			if(op instanceof Negation){element = doc.createElement(XMLStructTags.NEGATION);}
			else if(op instanceof Conjunction){element = doc.createElement(XMLStructTags.CONJUNCTION);}
			else if(op instanceof Disjunction){element = doc.createElement(XMLStructTags.DISJUNCTION);}
			else if(op instanceof Implication){element = doc.createElement(XMLStructTags.IMPLICATION);}
			else if(op instanceof Equivalence){element = doc.createElement(XMLStructTags.EQUIVALENCE);}
			else if(op instanceof Universal){element = doc.createElement(XMLStructTags.UNIVERSAL);}
			else if(op instanceof Existential){element = doc.createElement(XMLStructTags.EXISTENTIAL);}
			else if(op instanceof AtLeast){
				element = doc.createElement(XMLStructTags.ATLEAST);
				Integer lower = ((AtLeast) op).getLowerBound();
				element.setAttribute(XMLStructTags.LOWBOUND, lower.toString());
			}
			else if(op instanceof AtMost){
				element = doc.createElement(XMLStructTags.ATMOST);
				Integer upper = ((AtMost) op).getUpperBound();
				element.setAttribute(XMLStructTags.UPBOUND, upper.toString());
			}
			else if(op instanceof Between){
				element = doc.createElement(XMLStructTags.BETWEEN);
				Integer lower = ((Between) op).getLowerBound();
				Integer upper = ((Between) op).getUpperBound();
				element.setAttribute(XMLStructTags.LOWBOUND, lower.toString());
				element.setAttribute(XMLStructTags.UPBOUND, upper.toString());
			}
			else if(op instanceof Bigger){element = doc.createElement(XMLStructTags.BIGGER);}
			else if(op instanceof EBigger){element = doc.createElement(XMLStructTags.EBIGGER);}
			else if(op instanceof Equal){element = doc.createElement(XMLStructTags.EQUAL);}
			else if(op instanceof ESmaller){element = doc.createElement(XMLStructTags.ESMALLER);}
			else if(op instanceof Smaller){element = doc.createElement(XMLStructTags.SMALLER);}
			else if(op instanceof Include){element = doc.createElement(XMLStructTags.INCLUDE);}
			else if(op instanceof Contain){element = doc.createElement(XMLStructTags.COMPLEMENT);}
			else if(op instanceof GroupEqual){element = doc.createElement(XMLStructTags.GROUP_EQUAL);}
			else throw new Exception("Unknown operator type: "+op.getClass().getCanonicalName());
		}
		else if(op instanceof NumericOperator){
			if(op instanceof Addition){element = doc.createElement(XMLStructTags.ADD);}
			else if(op instanceof Substraction){element = doc.createElement(XMLStructTags.SUB);}
			else if(op instanceof Multiplication){element = doc.createElement(XMLStructTags.MUL);}
			else if(op instanceof Division){element = doc.createElement(XMLStructTags.DIV);}
			else if(op instanceof Mod){element = doc.createElement(XMLStructTags.MOD);}
			else if(op instanceof Cardinality){element = doc.createElement(XMLStructTags.CARDINALITY);}
			else throw new Exception("Unknown operator type: "+op.getClass().getCanonicalName());
		}
		else if(op instanceof GroupOperator){
			if(op instanceof Intersection){element = doc.createElement(XMLStructTags.INTERSECTION);}
			else if(op instanceof Union){element = doc.createElement(XMLStructTags.UNION);}
			else if(op instanceof Difference){element = doc.createElement(XMLStructTags.DIFFERENCE);}
			else if(op instanceof Complement){element = doc.createElement(XMLStructTags.COMPLEMENT);}
			else if(op instanceof CartesianProduct){element = doc.createElement(XMLStructTags.CARTESIAN_PRODUCT);}
			else throw new Exception("Unknown operator type: "+op.getClass().getCanonicalName());
		}
		else throw new Exception("Unknown operator type: "+op.getClass().getCanonicalName());
		
		if(element==null)
			throw new Exception("Element generation failed");
		
		this.tmap.put(op, element);
		return element;
	}
	protected Element createFunction(Function function) throws Exception{
		if(function==null)throw new Exception("Null function is invalid");
		if(this.tmap.containsKey(function))return this.tmap.get(function);
		
		Element element = null;
		
		if(function.getTemplate() instanceof FilterTemplate){element = doc.createElement(XMLStructTags.FILTER);}
		else if(function.getTemplate() instanceof MapTemplate){element = doc.createElement(XMLStructTags.MAPPER);}
		else if(function.getTemplate() instanceof TableMapTemplate){element = doc.createElement(XMLStructTags.TABLE_MAPPER);}
		else{
			element = doc.createElement(XMLStructTags.FUNCTION);
			//String a =XMLStructTags.LOGIC_EXPR_TYPE;
			if(function instanceof LogicFunction){element.setAttribute(XMLStructTags.TYPE, XMLStructTags.LOGIC_FUNCTION_TYPE);}
			else if(function instanceof GroupFunction){element.setAttribute(XMLStructTags.TYPE, XMLStructTags.GROUP_FUNCTION_TYPE);}
			else if(function instanceof NumericFunction){element.setAttribute(XMLStructTags.TYPE, XMLStructTags.NUM_FUNCTION_TYPE);}
			else throw new Exception("Unknown function type: "+function.getClass().getCanonicalName());
		}
		
		this.tmap.put(function, element);
		return element;
	}
	protected Element createTemplate(FunctionTemplate template) throws Exception{
		if(template==null)throw new Exception("Null template is invalid");
		if(this.tmap.containsKey(template))return this.tmap.get(template);
		
		Element element = doc.createElement(XMLStructTags.TEMPLATE);
		element.setAttribute(XMLStructTags.NAME,template.getName());
		
		this.tmap.put(template, element);
		return element;
	}
	
	Map<Struct,String> linked_set = new HashMap<Struct,String>();
	Long id=0L;
	
	private String nextID(){
		long times = 0;
		while(this.linked_set.containsValue(id.toString())){
			if(times>Long.MAX_VALUE)
				try {
					throw new Exception("ID Space has been used out.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			id++;times++;
		}
		return id.toString();
	}
	private void linkStruct(Struct src,Struct trg) throws Exception{
		if(src==null||trg==null)throw new Exception("Null src|trg is invalid");
		if(!this.tmap.containsKey(src)||!this.tmap.containsKey(trg))
			throw new Exception("Invalid src|trg: not in current space");
		
		Element src_elm = this.tmap.get(src);
		Element trg_elm = null;
		if(this.linked_set.containsKey(trg)){
			String id = this.linked_set.get(trg);
			
			Element oe = this.tmap.get(trg);
			oe.setAttribute(XMLStructTags.ID, id);
			
			trg_elm = doc.createElement(oe.getTagName());
			trg_elm.setAttribute(XMLStructTags.REF, id);
		}
		else{
			trg_elm = this.tmap.get(trg);
			this.linked_set.put(trg, nextID());
		}
		src_elm.appendChild(trg_elm);
	}
	
	protected void linkVariable(Variable x) throws Exception{
		if(x==null)throw new Exception("Null variable is invalid");
		if(!this.tmap.containsKey(x)) throw new Exception("Invalid variable: not created");
		
		if(x instanceof DiscourseDomain){
			this.linkStruct(x, ((DiscourseDomain) x).getIterator());
		}
	}
	protected void linkExpression(Expression x) throws Exception{
		if(x==null)throw new Exception("Null expression is invalid");
		if(!this.tmap.containsKey(x)) throw new Exception("Invalid expression: not created");
		this.linkStruct(x, x.getOperator());
	}
	protected void linkOperator(Operator op)throws Exception{
		if(op==null) throw new Exception("Null operator is invalid");
		if(!this.tmap.containsKey(op))throw new Exception("Invalid operator: not created");
		
		if(op instanceof UnaryOperator){
			this.linkStruct(op, ((UnaryOperator) op).getOperand());
			if(op instanceof Complement)
				this.linkStruct(op, ((Complement) op).getDomain());
		}
		else if(op instanceof BinaryOperator){
			if(op instanceof QuantifierOperator){
				this.linkStruct(op, ((QuantifierOperator) op).getDomain());
				this.linkStruct(op, ((QuantifierOperator) op).getScope());
			}
			else{
				this.linkStruct(op, ((BinaryOperator) op).getLeftOperand());
				this.linkStruct(op, ((BinaryOperator) op).getRightOperand());
			}
		}
		else if(op instanceof MultipleOperator){
			Struct[] operands = ((MultipleOperator) op).getOperands();
			int n = ((MultipleOperator) op).getDimension();
			for(int i=0;i<n;i++)
				this.linkStruct(op, operands[i]);
		}
		else if(op instanceof FlexibleOperator){
			Struct[] operands = ((FlexibleOperator) op).getOperands();
			int n = ((FlexibleOperator) op).getOperands().length;
			for(int i=0;i<n;i++)
				this.linkStruct(op, operands[i]);
		}
		else throw new Exception("Unknown operator type: "+op.getClass().getCanonicalName());
	}
	protected void linkFunction(Function function)throws Exception{
		if(function==null)throw new Exception("Null function is invalid");
		if(!this.tmap.containsKey(function))throw new Exception("Invalid function: not created");
		this.linkStruct(function, function.getTemplate());
	}
	protected void linkTemplate(FunctionTemplate template) throws Exception{
		if(template==null)throw new Exception("Null template is invalid");
		if(!this.tmap.containsKey(template))throw new Exception("Invalid template: not created");
		
		/*if(template instanceof LogicFunctionTemplate);
		else if(template instanceof GroupFunctionTemplate);
		else if(template instanceof NumericFunctionTemplate);
		else parent.appendChild(this.tmap.get(template.getOutput()));*/
		
		if(template instanceof NumericFunctionTemplate){
			Variable out = (Variable) template.getOutput();
			if(out!=null)
				this.linkStruct(template, out);
		}
		
		Variable[] arguments = template.getArguments();
		if(arguments!=null)
			for(int i=0;i<arguments.length;i++){
				if(!this.tmap.containsKey(arguments[i]))
					throw new Exception("Invalid argument: not created");
				this.linkStruct(template, arguments[i]);
			}
	}
	protected void linkAll(Struct top) throws Exception{
		if(top==null)throw new Exception("Null struct is invalid");
		
		Queue<Struct> queue = new LinkedList<Struct>();
		queue.add(top);
		
		Set<Struct> link_record = new HashSet<Struct>();
		
		while(!queue.isEmpty()){
			Struct cur = queue.poll();
			if(cur==null)throw new Exception("Null struct is invalid");
			if(link_record.contains(cur))continue;
			
			link_record.add(cur);
			
			if(cur instanceof Variable){this.linkVariable((Variable) cur);}
			else if(cur instanceof Expression){this.linkExpression((Expression) cur);}
			else if(cur instanceof Operator){this.linkOperator((Operator) cur);}
			else if(cur instanceof Function){this.linkFunction((Function) cur);}
			else if(cur instanceof FunctionTemplate){this.linkTemplate((FunctionTemplate) cur);}
			else throw new Exception("Unknown linked Struct: "+cur.getClass().getCanonicalName());
			
			if(cur instanceof CompositeStruct){
				Struct[] children = ((CompositeStruct) cur).getChildrenStructs();
				int n = ((CompositeStruct) cur).getChildrenStructSize();
				for(int i=0;i<n;i++)
					if(children[i]!=null)
						queue.add(children[i]);
			}
		}
	}
	
	/**
	 * Re-translation code
	 * */
	
	Map<String,Element> id_elements = new HashMap<String,Element>();
	Map<Element,Struct> rmap = new HashMap<Element,Struct>();
	Set<Element> ref_elms = new HashSet<Element>();
	
	@Override
	public Struct retranslate(Element top) throws Exception {
		this.analysis(top);
		System.out.println("Analysis Complete...");
		
		Set<Element> records = new HashSet<Element>();
		Queue<Element> queue = new LinkedList<Element>();
		Stack<Element> A = new Stack<Element>();
		Stack<Element> B = new Stack<Element>();
		
		queue.add(top);
		while(!queue.isEmpty()){
			Element cur = queue.poll();
			if(records.contains(cur))continue;
			
			records.add(cur);
			A.push(cur);
			
			NodeList children = cur.getChildNodes();
			int n = children.getLength();
			for(int i=0;i<n;i++){
				if(children.item(i) instanceof Element)
					queue.add((Element) children.item(i));
			}
		}
		
		while(!A.isEmpty()){
			while(!A.isEmpty()){
				Element ei = A.pop();
				Struct result = this.generate(ei);
				if(result==null){B.push(ei);}
			}
			while(!B.isEmpty()){
				A.push(B.pop());
			}
		}
		
		return this.rmap.get(top);
	}
	
	protected void analysis(Element top) throws Exception{
		if(top==null)throw new Exception("Null top is invalid");
		
		Queue<Element> queue = new LinkedList<Element>();
		queue.add(top);
		
		Set<Element> refs = new HashSet<Element>();
		
		while(!queue.isEmpty()){
			top = queue.poll();
			String id = top.getAttribute(XMLStructTags.ID);
			String ref = top.getAttribute(XMLStructTags.REF);
			
			if(id!=null&&id.length()>0){
				if(this.id_elements.containsKey(id))
					throw new Exception("Duplicated Id: "+id+" at <"+top.getTagName()+">");
				
				this.id_elements.put(id, top);
			}
			if(ref!=null&&ref.length()>0)
				refs.add(top);
			
			NodeList children = top.getChildNodes();
			int n = children.getLength();
			for(int i=0;i<n;i++){
				if(children.item(i) instanceof Element)
					queue.add((Element) children.item(i));
			}
		}
		
		for(Element ei:refs){
			String ref = ei.getAttribute(XMLStructTags.REF);
			if(!this.id_elements.containsKey(ref))
				throw new Exception("Undefined ref id: "+ref+" at <"+ei.getTagName()+">");
		}
	}
	
	private Element getOriginal(Element element)throws Exception{
		if(element==null)return null;
		
		String ref = element.getAttribute(XMLStructTags.REF);
		if(ref!=null&&ref.length()>0){
			if(this.id_elements.containsKey(ref))
				return this.id_elements.get(ref);
			else throw new Exception("Undefined ref id: "+ref+" at <"+element.getTagName()+">");
		}
		else return element;
	}
	private List<Struct> getChildren(Element parent) throws Exception{
		if(parent==null)throw new Exception("Null node is invalid");
		
		NodeList list = parent.getChildNodes();
		List<Struct> slist = new ArrayList<Struct>();
		
		int n = list.getLength();
		for(int i=0;i<n;i++){
			if(list.item(i) instanceof Element){
				Element ei = this.getOriginal((Element) list.item(i));
				Struct ri = this.rmap.get(ei);
				if(ri==null)return null;
				slist.add(ri);
			}
		}
		
		return slist;
	}
	
	protected Struct generate(Element element) throws Exception{
		if(element==null)throw new Exception("Null element is invalid");
		
		element = this.getOriginal(element);
		if(this.rmap.containsKey(element))return this.rmap.get(element);
		
		String tag = element.getTagName();
		Struct result = null;
		
		if(tag.equals(XMLStructTags.VARIABLE)){result = this.generateVariable(element);}
		else if(tag.equals(XMLStructTags.DISCOURSE_DOMAIN_ITER)){result = this.generateVariable(element);}
		else if(tag.equals(XMLStructTags.EXPRESSION)){result = this.generateExpression(element);}
		else if(tag.equals(XMLStructTags.FUNCTION)){result = this.generateFunction(element);}
		else if(tag.equals(XMLStructTags.FILTER)){result = GroupFactory.createFilter();}
		else if(tag.equals(XMLStructTags.MAPPER)){result = GroupFactory.createMap();}
		else if(tag.equals(XMLStructTags.TABLE_MAPPER)){result = GroupFactory.createTableMap();}
		//else throw new Exception("Unknown tag: <"+tag+">");
		
		if(result!=null)
			this.rmap.put(element, result);
		return result;
	}
	
	protected Variable generateVariable(Element e) throws Exception{
		if(e==null)throw new Exception("Null element is invalid");
		
		String tag = e.getTagName();
		String name = e.getAttribute(XMLStructTags.NAME);
		String type = e.getAttribute(XMLStructTags.TYPE);
		Variable result = null;
		
		if(tag.equals(XMLStructTags.VARIABLE)){
			if(name==null||name.length()==0)throw new Exception("Null name attribute is invalid in <"+tag+">");
			if(type==null||type.length()==0)throw new Exception("Null type attribute is invalid in <"+tag+">");
			
			if(type.equals(XMLStructTags.LOGIC_TYPE)){result = LogicFactory.createLogicVariable(name);}
			else if(type.equals(XMLStructTags.DISCOURSE_DOMAIN)){result = LogicFactory.createDiscourseDomain(name);}
			else if(type.equals(XMLStructTags.NUM_NATURAL_TYPE)){result = NumericFactory.createNaturalVariable(name);}
			else if(type.equals(XMLStructTags.NUM_INTEGER_TYPE)){result = NumericFactory.createZIntegerVariable(name);}
			else if(type.equals(XMLStructTags.NUM_RATIONAL_TYPE)){result = NumericFactory.createRationalVariable(name);}
			else if(type.equals(XMLStructTags.NUM_REAL_TYPE)){result = NumericFactory.createRealVariable(name);}
			else if(type.equals(XMLStructTags.GROUP_TYPE)){result = GroupFactory.createGroupVariable(name);}
			else if(type.equals(XMLStructTags.BOOLEAN_TYPE)){result = VariableFactory.createBoolean(name);}
			else if(type.equals(XMLStructTags.INTEGER_TYPE)){result = VariableFactory.createInteger(name);}
			else if(type.equals(XMLStructTags.LONG_TYPE)){result = VariableFactory.createLong(name);}
			else if(type.equals(XMLStructTags.FLOAT_TYPE)){result = VariableFactory.createFloat(name);}
			else if(type.equals(XMLStructTags.DOUBLE_TYPE)){result = VariableFactory.createDouble(name);}
			else if(type.equals(XMLStructTags.CHARACTER_TYPE)){result = VariableFactory.createCharacter(name);}
			else if(type.equals(XMLStructTags.STRING_TYPE)){result = VariableFactory.createString(name);}
			else if(type.equals(XMLStructTags.LIST_TYPE)){result = VariableFactory.createList(name);}
			else if(type.equals(XMLStructTags.SET_TYPE)){result = VariableFactory.createSet(name);}
			else if(type.equals(XMLStructTags.MAP_TYPE)){result = VariableFactory.createMap(name);}
			else if(type.equals(XMLStructTags.REF_TYPE)){
				result = VariableFactory.createReference(name);
				this.ref_elms.add(e);
			}
			else throw new Exception("Unknown type attribute: "+type);
		}
		else if(tag.equals(XMLStructTags.DISCOURSE_DOMAIN_ITER)){
			DiscourseDomain domain = (DiscourseDomain) this.generate((Element) e.getParentNode());
			if(domain==null)throw new Exception("DiscourseDomain generation failed --> iterator generation failed");
			result = domain.getIterator();
		}
		else throw new Exception("Unknown tag name: "+tag);
		
		if(result!=null)this.rmap.put(e, result);
		
		return result;
	}
	protected Expression generateExpression(Element e)throws Exception{
		if(e==null)throw new Exception("Null element is invalid");
		
		Expression result = null;
		String tag = e.getTagName();
		
		if(tag.equals(XMLStructTags.EXPRESSION)){
			Element op_elm = null;
			NodeList nlist = e.getChildNodes();
			if(nlist!=null)
				for(int i=0;i<nlist.getLength();i++)
					if(nlist.item(i) instanceof Element){
						op_elm = (Element) nlist.item(i);
						break;
					}
			
			if(op_elm==null)throw new Exception("Invalid Expression Element: at least 1 child for operator.");
			if(this.rmap.containsKey(op_elm)){
				result = ((Operator)this.rmap.get(op_elm)).getExpression();
			}
			else{
				List<Struct> children = this.getChildren(op_elm);
				if(children==null)return result;
				
				tag = op_elm.getTagName();
				
				if(tag.equals(XMLStructTags.NEGATION)){
					if(children.size()!=1)throw new Exception("Exactly 1 operands is required under <"+tag+">");
					result = LogicFactory.createNegation((LogicStruct) children.get(0));
				}
				else if(tag.equals(XMLStructTags.CONJUNCTION)){
					if(children.size()<2)throw new Exception("At least 2 operands are required under <"+tag+">");
					
					LogicStruct[] clist = new LogicStruct[children.size()];
					for(int i=0;i<children.size();i++)clist[i]=(LogicStruct) children.get(i);
					
					result = LogicFactory.createConjunction(clist);
				}
				else if(tag.equals(XMLStructTags.DISJUNCTION)){
					if(children.size()<2)throw new Exception("At least 2 operands are required under <"+tag+">");
					
					LogicStruct[] clist = new LogicStruct[children.size()];
					for(int i=0;i<children.size();i++)clist[i]=(LogicStruct) children.get(i);
					
					result = LogicFactory.createDisjunction(clist);
				}
				else if(tag.equals(XMLStructTags.IMPLICATION)){
					if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
					result = LogicFactory.createImplication((LogicStruct)children.get(0), (LogicStruct)children.get(1));
				}
				else if(tag.equals(XMLStructTags.EQUIVALENCE)){
					if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
					result = LogicFactory.createEquivalence((LogicStruct)children.get(0), (LogicStruct)children.get(1));
				}
				else if(tag.equals(XMLStructTags.UNIVERSAL)){
					if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
					result = LogicFactory.createUniversal((DiscourseDomain)children.get(0), (LogicStruct)children.get(1));
				}
				else if(tag.equals(XMLStructTags.EXISTENTIAL)){
					if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
					result = LogicFactory.createExistential((DiscourseDomain)children.get(0), (LogicStruct)children.get(1));
				}
				else if(tag.equals(XMLStructTags.ATLEAST)){
					if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
					if(op_elm.getAttribute(XMLStructTags.LOWBOUND)==null||op_elm.getAttribute(XMLStructTags.LOWBOUND).length()==0)
						throw new Exception("Attribute lower is required under <"+tag+">");
					int lower = Integer.parseInt(op_elm.getAttribute(XMLStructTags.LOWBOUND));
					result = LogicFactory.createAtLeast((DiscourseDomain)children.get(0), (LogicStruct)children.get(1),lower);
				}
				else if(tag.equals(XMLStructTags.ATMOST)){
					if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
					if(op_elm.getAttribute(XMLStructTags.UPBOUND)==null||op_elm.getAttribute(XMLStructTags.UPBOUND).length()==0)
						throw new Exception("Attribute upper is required under <"+tag+">");
					int upper = Integer.parseInt(op_elm.getAttribute(XMLStructTags.UPBOUND));
					result = LogicFactory.createAtMost((DiscourseDomain)children.get(0), (LogicStruct)children.get(1),upper);
				}
				else if(tag.equals(XMLStructTags.BETWEEN)){
					if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
					if(op_elm.getAttribute(XMLStructTags.LOWBOUND)==null||op_elm.getAttribute(XMLStructTags.LOWBOUND).length()==0)
						throw new Exception("Attribute lower is required under <"+tag+">");
					int lower = Integer.parseInt(op_elm.getAttribute(XMLStructTags.LOWBOUND));
					if(op_elm.getAttribute(XMLStructTags.UPBOUND)==null||op_elm.getAttribute(XMLStructTags.UPBOUND).length()==0)
						throw new Exception("Attribute upper is required under <"+tag+">");
					int upper = Integer.parseInt(op_elm.getAttribute(XMLStructTags.UPBOUND));
					result = LogicFactory.createBetween((DiscourseDomain)children.get(0), (LogicStruct)children.get(1), upper, lower);
				}
				else if(tag.equals(XMLStructTags.BIGGER)){
					if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
					result = NumericFactory.createBigger((NumericStruct)children.get(0), (NumericStruct)children.get(1));
				}
				else if(tag.equals(XMLStructTags.EBIGGER)){
					if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
					result = NumericFactory.createEBigger((NumericStruct)children.get(0), (NumericStruct)children.get(1));
				}
				else if(tag.equals(XMLStructTags.EQUAL)){
					if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
					result = NumericFactory.createEqual((NumericStruct)children.get(0), (NumericStruct)children.get(1));
				}
				else if(tag.equals(XMLStructTags.ESMALLER)){
					if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
					result = NumericFactory.createESmaller((NumericStruct)children.get(0), (NumericStruct)children.get(1));
				}
				else if(tag.equals(XMLStructTags.SMALLER)){
					if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
					result = NumericFactory.createSmaller((NumericStruct)children.get(0), (NumericStruct)children.get(1));
				}
				else if(tag.equals(XMLStructTags.CONTAIN)){
					if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
					result = GroupFactory.createContain((GroupStruct)children.get(0), (GroupStruct)children.get(1));
				}
				else if(tag.equals(XMLStructTags.INCLUDE)){
					if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
					result = GroupFactory.createInclude(children.get(0), (GroupStruct)children.get(1));
				}
				else if(tag.equals(XMLStructTags.GROUP_EQUAL)){
					if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
					result = GroupFactory.createGroupEqual((GroupStruct)children.get(0), (GroupStruct)children.get(1));
				}
				else if(tag.equals(XMLStructTags.ADD)){
					if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
					result = NumericFactory.createAddition((NumericStruct)children.get(0), (NumericStruct)children.get(1));
				}
				else if(tag.equals(XMLStructTags.SUB)){
					if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
					result = NumericFactory.createSubstract((NumericStruct)children.get(0), (NumericStruct)children.get(1));
				}
				else if(tag.equals(XMLStructTags.MUL)){
					if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
					result = NumericFactory.createMultiplication((NumericStruct)children.get(0), (NumericStruct)children.get(1));
				}
				else if(tag.equals(XMLStructTags.DIV)){
					if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
					result = NumericFactory.createDivision((NumericStruct)children.get(0), (NumericStruct)children.get(1));
				}
				else if(tag.equals(XMLStructTags.MOD)){
					if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
					result = NumericFactory.createMod((NumericStruct)children.get(0), (NumericStruct)children.get(1));
				}
				else if(tag.equals(XMLStructTags.CARDINALITY)){
					if(children.size()!=1)throw new Exception("Exactly 2 operands are required under <"+tag+">");
					result = GroupFactory.createCardinality((GroupStruct) children.get(0));
				}
				else if(tag.equals(XMLStructTags.INTERSECTION)){
					if(children.size()<2)throw new Exception("At least 2 operands element under <"+tag+">");
					GroupStruct[] operands = new GroupStruct[children.size()];
					for(int i=0;i<children.size();i++)
						operands[i]=(GroupStruct) children.get(i);
					
					result = GroupFactory.createIntersection(operands);
				}
				else if(tag.equals(XMLStructTags.UNION)){
					if(children.size()<2)throw new Exception("At least 2 operands element under <"+tag+">");
					GroupStruct[] operands = new GroupStruct[children.size()];
					for(int i=0;i<children.size();i++)
						operands[i]=(GroupStruct) children.get(i);
					
					result = GroupFactory.createUnion(operands);
				}
				else if(tag.equals(XMLStructTags.DIFFERENCE)){
					if(children.size()!=2)throw new Exception("Exactly 2 operands element under <"+tag+">");
					result = GroupFactory.createDifference((GroupStruct)children.get(0), (GroupStruct)children.get(1));
				}
				else if(tag.equals(XMLStructTags.COMPLEMENT)){
					if(children.size()!=2)throw new Exception("Exactly 2 operands element under <"+tag+">");
					result = GroupFactory.createComplement((GroupStruct)children.get(0), (GroupStruct)children.get(1));
				}
				else if(tag.equals(XMLStructTags.CARTESIAN_PRODUCT)){
					if(children.size()<2)throw new Exception("At least 2 operands element under <"+tag+">");
					GroupStruct[] operands = new GroupStruct[children.size()];
					for(int i=0;i<children.size();i++)
						operands[i]=(GroupStruct) children.get(i);
					
					result = GroupFactory.createCartesianProduct(operands);
				}
				else throw new Exception("Invalid Operator tag: "+tag);
				
				if(result!=null)this.rmap.put(op_elm, result.getOperator());
			}
		}
		else throw new Exception("Invalid tag: <"+tag+"> in expression");
		
		if(result!=null)
			this.rmap.put(e, result);
		return result;
	}
	
	/*protected LogicExpression generateLogicOperator(Element op_elm) throws Exception{
		if(op_elm==null)throw new Exception("Null operator element is invalid");
		if(this.rmap.containsKey(op_elm))return ((LogicOperator)this.rmap.get(op_elm)).getExpression();
		
		String tag = op_elm.getTagName();
		LogicExpression result = null;
		List<Struct> children = this.getChildren(op_elm);
		if(children==null)return result;
		
		if(tag.equals(XMLStructTags.NEGATION)){
			if(children.size()!=1)throw new Exception("Exactly 1 operands is required under <"+tag+">");
			result = LogicFactory.createNegation((LogicStruct) children.get(0));
		}
		else if(tag.equals(XMLStructTags.CONJUNCTION)){
			if(children.size()<2)throw new Exception("At least 2 operands are required under <"+tag+">");
			
			LogicStruct[] clist = new LogicStruct[children.size()];
			for(int i=0;i<children.size();i++)clist[i]=(LogicStruct) children.get(i);
			
			result = LogicFactory.createConjunction(clist);
		}
		else if(tag.equals(XMLStructTags.DISJUNCTION)){
			if(children.size()<2)throw new Exception("At least 2 operands are required under <"+tag+">");
			
			LogicStruct[] clist = new LogicStruct[children.size()];
			for(int i=0;i<children.size();i++)clist[i]=(LogicStruct) children.get(i);
			
			result = LogicFactory.createDisjunction(clist);
		}
		else if(tag.equals(XMLStructTags.IMPLICATION)){
			if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
			result = LogicFactory.createImplication((LogicStruct)children.get(0), (LogicStruct)children.get(1));
		}
		else if(tag.equals(XMLStructTags.EQUIVALENCE)){
			if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
			result = LogicFactory.createEquivalence((LogicStruct)children.get(0), (LogicStruct)children.get(1));
		}
		else if(tag.equals(XMLStructTags.UNIVERSAL)){
			if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
			result = LogicFactory.createUniversal((DiscourseDomain)children.get(0), (LogicStruct)children.get(1));
		}
		else if(tag.equals(XMLStructTags.EXISTENTIAL)){
			if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
			result = LogicFactory.createExistential((DiscourseDomain)children.get(0), (LogicStruct)children.get(1));
		}
		else if(tag.equals(XMLStructTags.ATLEAST)){
			if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
			if(op_elm.getAttribute(XMLStructTags.LOWBOUND)==null||op_elm.getAttribute(XMLStructTags.LOWBOUND).length()==0)
				throw new Exception("Attribute lower is required under <"+tag+">");
			int lower = Integer.parseInt(op_elm.getAttribute(XMLStructTags.LOWBOUND));
			result = LogicFactory.createAtLeast((DiscourseDomain)children.get(0), (LogicStruct)children.get(1),lower);
		}
		else if(tag.equals(XMLStructTags.ATMOST)){
			if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
			if(op_elm.getAttribute(XMLStructTags.UPBOUND)==null||op_elm.getAttribute(XMLStructTags.UPBOUND).length()==0)
				throw new Exception("Attribute upper is required under <"+tag+">");
			int upper = Integer.parseInt(op_elm.getAttribute(XMLStructTags.UPBOUND));
			result = LogicFactory.createAtMost((DiscourseDomain)children.get(0), (LogicStruct)children.get(1),upper);
		}
		else if(tag.equals(XMLStructTags.BETWEEN)){
			if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
			if(op_elm.getAttribute(XMLStructTags.LOWBOUND)==null||op_elm.getAttribute(XMLStructTags.LOWBOUND).length()==0)
				throw new Exception("Attribute lower is required under <"+tag+">");
			int lower = Integer.parseInt(op_elm.getAttribute(XMLStructTags.LOWBOUND));
			if(op_elm.getAttribute(XMLStructTags.UPBOUND)==null||op_elm.getAttribute(XMLStructTags.UPBOUND).length()==0)
				throw new Exception("Attribute upper is required under <"+tag+">");
			int upper = Integer.parseInt(op_elm.getAttribute(XMLStructTags.UPBOUND));
			result = LogicFactory.createBetween((DiscourseDomain)children.get(0), (LogicStruct)children.get(1), upper, lower);
		}
		else if(tag.equals(XMLStructTags.BIGGER)){
			if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
			result = NumericFactory.createBigger((NumericStruct)children.get(0), (NumericStruct)children.get(1));
		}
		else if(tag.equals(XMLStructTags.EBIGGER)){
			if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
			result = NumericFactory.createEBigger((NumericStruct)children.get(0), (NumericStruct)children.get(1));
		}
		else if(tag.equals(XMLStructTags.EQUAL)){
			if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
			result = NumericFactory.createEqual((NumericStruct)children.get(0), (NumericStruct)children.get(1));
		}
		else if(tag.equals(XMLStructTags.ESMALLER)){
			if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
			result = NumericFactory.createESmaller((NumericStruct)children.get(0), (NumericStruct)children.get(1));
		}
		else if(tag.equals(XMLStructTags.SMALLER)){
			if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
			result = NumericFactory.createSmaller((NumericStruct)children.get(0), (NumericStruct)children.get(1));
		}
		else if(tag.equals(XMLStructTags.CONTAIN)){
			if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
			result = GroupFactory.createContain((GroupStruct)children.get(0), (GroupStruct)children.get(1));
		}
		else if(tag.equals(XMLStructTags.INCLUDE)){
			if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
			result = GroupFactory.createInclude(children.get(0), (GroupStruct)children.get(1));
		}
		else if(tag.equals(XMLStructTags.GROUP_EQUAL)){
			if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
			result = GroupFactory.createGroupEqual((GroupStruct)children.get(0), (GroupStruct)children.get(1));
		}
		else throw new Exception("Invalid Logic Operator tag: "+tag);
		
		if(result!=null)this.rmap.put(op_elm, result.getOperator());
		
		return result;
	}*/
	/*protected NumericExpression generateNumericOperator(Element op_elm) throws Exception{
		if(op_elm==null)throw new Exception("Null operator element is invalid");
		if(this.rmap.containsKey(op_elm))return ((NumericOperator)this.rmap.get(op_elm)).getExpression();
		
		String tag = op_elm.getTagName();
		NumericExpression result = null;
		List<Struct> children = this.getChildren(op_elm);
		if(children==null)return null;
		
		if(tag.equals(XMLStructTags.ADD)){
			if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
			result = NumericFactory.createAddition((NumericStruct)children.get(0), (NumericStruct)children.get(1));
		}
		else if(tag.equals(XMLStructTags.SUB)){
			if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
			result = NumericFactory.createSubstract((NumericStruct)children.get(0), (NumericStruct)children.get(1));
		}
		else if(tag.equals(XMLStructTags.MUL)){
			if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
			result = NumericFactory.createMultiplication((NumericStruct)children.get(0), (NumericStruct)children.get(1));
		}
		else if(tag.equals(XMLStructTags.DIV)){
			if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
			result = NumericFactory.createDivision((NumericStruct)children.get(0), (NumericStruct)children.get(1));
		}
		else if(tag.equals(XMLStructTags.MOD)){
			if(children.size()!=2)throw new Exception("Exactly 2 operands are required under <"+tag+">");
			result = NumericFactory.createMod((NumericStruct)children.get(0), (NumericStruct)children.get(1));
		}
		else if(tag.equals(XMLStructTags.CARDINALITY)){
			if(children.size()!=1)throw new Exception("Exactly 2 operands are required under <"+tag+">");
			result = GroupFactory.createCardinality((GroupStruct) children.get(0));
		}
		else throw new Exception("Invalid Numeric Operator tag: "+tag);
		
		if(result!=null)this.rmap.put(op_elm, result.getOperator());
		
		return result;
	}*/
	/*protected GroupExpression generateGroupOperator(Element op_elm)throws Exception{
		if(op_elm==null)throw new Exception("Null operator element is invalid");
		if(this.rmap.containsKey(op_elm))return ((GroupOperator)this.rmap.get(op_elm)).getExpression();
		
		String tag = op_elm.getTagName();
		GroupExpression result = null;
		List<Struct> children = this.getChildren(op_elm);
		if(children==null)return null;
		
		if(tag.equals(XMLStructTags.INTERSECTION)){
			if(children.size()<2)throw new Exception("At least 2 operands element under <"+tag+">");
			GroupStruct[] operands = new GroupStruct[children.size()];
			for(int i=0;i<children.size();i++)
				operands[i]=(GroupStruct) children.get(i);
			
			result = GroupFactory.createIntersection(operands);
		}
		else if(tag.equals(XMLStructTags.UNION)){
			if(children.size()<2)throw new Exception("At least 2 operands element under <"+tag+">");
			GroupStruct[] operands = new GroupStruct[children.size()];
			for(int i=0;i<children.size();i++)
				operands[i]=(GroupStruct) children.get(i);
			
			result = GroupFactory.createUnion(operands);
		}
		else if(tag.equals(XMLStructTags.DIFFERENCE)){
			if(children.size()!=2)throw new Exception("Exactly 2 operands element under <"+tag+">");
			result = GroupFactory.createDifference((GroupStruct)children.get(0), (GroupStruct)children.get(1));
		}
		else if(tag.equals(XMLStructTags.COMPLEMENT)){
			if(children.size()!=2)throw new Exception("Exactly 2 operands element under <"+tag+">");
			result = GroupFactory.createComplement((GroupStruct)children.get(0), (GroupStruct)children.get(1));
		}
		else if(tag.equals(XMLStructTags.CARTESIAN_PRODUCT)){
			if(children.size()<2)throw new Exception("At least 2 operands element under <"+tag+">");
			GroupStruct[] operands = new GroupStruct[children.size()];
			for(int i=0;i<children.size();i++)
				operands[i]=(GroupStruct) children.get(i);
			
			result = GroupFactory.createCartesianProduct(operands);
		}
		else throw new Exception("Invalid Group Operator tag: "+tag);
		
		if(result!=null)this.rmap.put(op_elm, result.getOperator());
		
		return result;
	}*/
	
	protected Function generateFunction(Element e) throws Exception{
		if(e==null)throw new Exception("Null element is invalid");
		if(this.rmap.containsKey(e))return (Function) this.rmap.get(e);
		
		String tag = e.getTagName();
		
		if(!tag.equals(XMLStructTags.FUNCTION))
			throw new Exception("Invalid tag for function: "+tag);
		
		String type = e.getAttribute(XMLStructTags.TYPE);
		if(type==null||type.length()==0)
			throw new Exception("Attribute type is requried under <"+tag+">");
		
		Element op_elm = (Element) e.getElementsByTagName(XMLStructTags.TEMPLATE).item(0);
		if(op_elm==null)throw new Exception("<template> is required under <"+tag+">");
		
		Function result = null;
		if(type.equals(XMLStructTags.LOGIC_FUNCTION_TYPE)){result = this.generateLogicFunction(op_elm);}
		else if(type.equals(XMLStructTags.NUM_FUNCTION_TYPE)){result = this.generateNumericFunction(op_elm);}
		else if(type.equals(XMLStructTags.GROUP_FUNCTION_TYPE)){result = this.generateGroupFunction(op_elm);}
		else throw new Exception("Invalid type for Function: "+type);
		
		if(result!=null)
			this.rmap.put(e, result);
		return result;
	}
	protected LogicFunction generateLogicFunction(Element op_elm) throws Exception{
		if(op_elm==null)throw new Exception("Null operator element is invalid");
		if(this.rmap.containsKey(op_elm))return ((LogicFunctionTemplate)this.rmap.get(op_elm)).getFunction();
		
		String tag = op_elm.getTagName();
		
		if(!tag.equals(XMLStructTags.TEMPLATE))
			throw new Exception("Invalid tag for template: <"+tag+">");
		
		String name = op_elm.getAttribute(XMLStructTags.NAME);
		if(name==null||name.length()==0)
			throw new Exception("Attribute name is required at <"+tag+">");
		
		List<Struct> children = this.getChildren(op_elm);
		if(children==null)return null;
		
		Variable[] arguments = new Variable[children.size()];
		for(int i=0;i<children.size();i++)
			arguments[i]=(Variable) children.get(i);
		
		LogicFunctionTemplate template = LogicFactory.createLogicFunctionTemplate(name, arguments);
		this.rmap.put(op_elm, template);
		LogicFunction function = LogicFactory.createLogicFunction(template);
		
		return function;
	}
	protected NumericFunction generateNumericFunction(Element op_elm) throws Exception{
		if(op_elm==null)throw new Exception("Null operator element is invalid");
		if(this.rmap.containsKey(op_elm))return ((NumericFunctionTemplate)this.rmap.get(op_elm)).getFunction();
		
		String tag = op_elm.getTagName();
		
		if(!tag.equals(XMLStructTags.TEMPLATE))
			throw new Exception("Invalid tag for template: <"+tag+">");
		
		String name = op_elm.getAttribute(XMLStructTags.NAME);
		if(name==null||name.length()==0)
			throw new Exception("Attribute name is required at <"+tag+">");
		
		List<Struct> children = this.getChildren(op_elm);
		if(children==null)return null;
		
		Variable[] arguments = new Variable[children.size()];
		for(int i=0;i<children.size();i++)
			arguments[i]=(Variable) children.get(i);
		
		NumericFunctionTemplate template = NumericFactory.createNaturalFunctionTemplate(name, arguments);
		this.rmap.put(op_elm, template);
		NumericFunction function = NumericFactory.createNumericFunction(template,null,null);
		
		return function;
	}
	protected GroupFunction generateGroupFunction(Element op_elm) throws Exception{
		if(op_elm==null)throw new Exception("Null operator element is invalid");
		if(this.rmap.containsKey(op_elm))return ((GroupFunctionTemplate)this.rmap.get(op_elm)).getFunction();
		
		String tag = op_elm.getTagName();
		
		if(!tag.equals(XMLStructTags.TEMPLATE))
			throw new Exception("Invalid tag for template: <"+tag+">");
		
		String name = op_elm.getAttribute(XMLStructTags.NAME);
		if(name==null||name.length()==0)
			throw new Exception("Attribute name is required at <"+tag+">");
		
		List<Struct> children = this.getChildren(op_elm);
		if(children==null)return null;
		
		Variable[] arguments = new Variable[children.size()];
		for(int i=0;i<children.size();i++)
			arguments[i]=(Variable) children.get(i);
		
		GroupFunctionTemplate template = GroupFactory.createGroupFunctionTemplate(name, arguments);
		this.rmap.put(op_elm, template);
		GroupFunction function = GroupFactory.createGroupFunction(template);
		
		return function;
	}
	@Override
	public void reupdate(Element top) throws Exception{
		if(top==null)throw new Exception("Null top is invalid");
		
		for(Element ref_elm:this.ref_elms){
			ReferenceVariable var = (ReferenceVariable) this.generate(ref_elm);
			String id = ref_elm.getTextContent();
			System.out.println("Finding refer: "+id);
			
			if(id==null||id.trim().length()==0)continue;
			
			if(!this.id_elements.containsKey(id))throw new Exception("Undefined id cannot be refered: "+id);
			Element re = this.id_elements.get(id);
			Variable target = (Variable) this.generate(re);
			if(target==null)throw new Exception("Generation failed fot refer: "+id);
			
			var.refer(target);
			System.out.println("Refer to target: "+target.toString());
		}
	}
	
}
