package cn.edu.buaa.sei.exLmf.metamodel.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassifier;
import cn.edu.buaa.sei.exLmf.metamodel.LMultipleObject;
import cn.edu.buaa.sei.exLmf.metamodel.LObject;

public class LMultipleObjectImpl extends LObjectImpl implements LMultipleObject{
	
	int lower=0,upper=UNBOUNDED;
	Boolean ordered = true,unique=false;
	
	List<LObject> array;
	Set<LObject> set;
	
	/*Check the bound and generate values set dynamically*/
	LMultipleObjectImpl(LClassifier type,int lower,int upper,Boolean ordered,Boolean unique) throws Exception {
		super(type);
		if(lower<0){
			throw this.getException("LMultipleObjectImpl(type,lower,upper,ordered,unique)", 
						"lower", "lower < 0");
		}
		if(upper<0&&upper!=UNBOUNDED){
			throw this.getException("LMultipleObjectImpl(type,lower,upper,ordered,unique)", 
						"upper", upper+" is invalid");
		}
		if(upper>0&&upper<lower){
			throw this.getException("LMultipleObjectImpl(type,lower,upper,ordered,unique)", 
						"upper", "The range is invalid: "+lower+"--"+upper);
		}
		this.lower=lower; this.upper=upper;
		this.ordered=ordered; this.unique=unique;
		
		if(!this.ordered&&this.unique)
			set = new HashSet<LObject>();
		else
			array = new ArrayList<LObject>();
	}

	@Override
	public LClassifier getParameterType() {return this.type;}

	@Override
	public int getLowerBound() {return this.lower;}
	@Override
	public int getUpperBound() {return this.upper;}
	@Override
	public Boolean isOrdered() {return this.ordered;}
	@Override
	public Boolean isUnique() {return this.unique;}

	/**
	 *	1. getAllObjects(): return all the values. [automatically select]
	 *	2. addObject(val):
	 *		- cannot add null into multiple object list.
	 *		- the val.type should match the parameter type
	 *		- unique: repeatedly adding element would cause exception.
	 *	3. removeObject(val):
	 *		- cannot remove null (no such value)
	 *		- type match as above
	 *		- cannot remove value that is not contained in LMultipleObject
	 * 	4. containObject(val): no exceptions
	 * @throws Exception 
	 */
	@Override
	public Collection<LObject> getAllObjects() throws Exception {
		if(set!=null)return set;
		else if(array!=null)return array;
		else{
			throw this.getException("getAllObjects()", "contents", "Un-Initialization");
		}
	}
	@Override
	public void addObject(LObject val) throws Exception {
		if(val==null){
			throw this.getException("addObject(val)", "val", "Null");
		}
		
		if(val.type()!=this.type){
			if(this.type instanceof LClass&&val.type() instanceof LClass){
				LClass ptype = (LClass) this.type;
				LClass vtype = (LClass) val.type();
				if(!ptype.isSuperOf(vtype)){
					throw this.getException("addObject(val)", "val.type", vtype.getName()+" is incompatible with parameter type: "+this.type.getName());
				}
			}
			else{
				throw this.getException("addObject(val)", "val.type", val.type().getName()+" is incompatible with parameter type: "+this.type.getName());
			}
		}
		
		if(this.unique){
			if(this.ordered){
				if(this.array.contains(val)){
					//throw this.getException("addObject(val)", "val", "Unique Array has contained the same elements");
				}
				else{
					this.array.add(val);
				}
			}
			else{
				if(this.set.contains(val)){
					//throw this.getException("addObject(val)", "val", "Unique Set has contained the same elements");
				}
				else
					this.set.add(val);
			}
		}
		else{
			if(this.ordered){
				this.array.add(val);
			}
			else{
				int n = this.array.size();
				Random r = new Random();
				int i = (int)(r.nextDouble()*(n+1));
				this.array.add(i, val);
			}
		}
	}
	@Override
	public void removeObject(LObject val) throws Exception {
		if(val==null){
			throw this.getException("removeObject(val)", "val", "Null");
		}
		
		if(val.type()!=this.type){
			if(this.type instanceof LClass&&val.type() instanceof LClass){
				LClass ptype = (LClass) this.type;
				LClass vtype = (LClass) val.type();
				if(!ptype.isSuperOf(vtype)){
					throw this.getException("removeObject(val)", "val.type", vtype.getName()+" is incompatible with parameter type: "+this.type.getName());
				}
			}
			else{
				throw this.getException("removeObject(val)", "val.type", val.type().getName()+" is incompatible with parameter type: "+this.type.getName());
			}
		}
		
		if(!this.ordered&&this.unique){
			if(!this.set.contains(val)){
				throw this.getException("removeObject(val)", "val", "Undefined in set");
			}
			else
				this.set.remove(val);
		}
		else{
			if(!this.array.contains(val)){
				throw this.getException("removeObject(val)", "val", "Undefined in set");
			}
			else
				this.array.remove(val);
		}
	}
	@Override
	public Boolean containObject(LObject val) {
		if(val==null)return false;
		//if(val.type()!=this.type)return false;
		
		if(this.unique&&!this.ordered)
			return this.set.contains(val);
		else return this.array.contains(val);
	}

	// Only applied to ordered multiple object
	@Override
	public LObject getByOrder(int i) throws Exception {
		if(!this.ordered){
			throw this.getException("getByOrder(i)", "this.order", 
						"Inordered Set cannot be assess by index "+i);
		}
		
		if(i<0||i>=this.array.size()){
			throw this.getException("getByOrder(i)", "i", 
						i+" is out of range: size="+this.array.size());
		}
		
		return this.array.get(i);
	}
	// Only applied to in-ordered multiple list.
	@Override
	public Iterator<LObject> getByUnordered() throws Exception {
		if(this.ordered){
			throw this.getException("getByUnordered()", "this.order", 
						"Orderred array cannot be assess through unordered functions");
		}
		if(this.unique)
			return this.set.iterator();
		else
			return this.array.iterator();
	}

	// Check the current length of list to comply with restriction of bound [lower---upper].
	// When validation failed, program crashed in exceptions.
	@Override
	public Boolean validateBound() throws Exception {
		int n = -1;
		if(this.array!=null)n = this.array.size();
		else if(this.set!=null)n = this.set.size();
		else{
			throw this.getException("validateBound()", "content", "Un-Initialization");
		}
		
		if(n<this.lower){
			throw this.getException("validateBound()", "size--lower", "Size "+n+" < Lower "+this.lower);
		}
		
		if(n>this.upper&&this.upper!=UNBOUNDED){
			throw this.getException("validateBound()", "size--upper", "Size "+n+" > Upper "+this.upper);
		}
		return true;
	}

}
