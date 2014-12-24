package cn.edu.buaa.sei.exLmf.metamodel.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassObject;
import cn.edu.buaa.sei.exLmf.metamodel.LMultipleObject;
import cn.edu.buaa.sei.exLmf.metamodel.LObject;
import cn.edu.buaa.sei.exLmf.metamodel.LStructuralFeature;

public class LClassObjectImpl extends LObjectImpl implements LClassObject{
	Map<LStructuralFeature,LObject> feature_val = new HashMap<LStructuralFeature,LObject>();
	Map<LStructuralFeature,Boolean> status_map = new HashMap<LStructuralFeature,Boolean>();
	
	LClassObjectImpl(LClass type) throws Exception {
		super(type);
		
		/**
		 * Initialization
		 * 	1) set all single attribute/reference as the feature.getDefaultValue() [perhaps automatically located to the default value of the value type]
		 * 	2) set all multiple attribute/reference the empty LMultipleObject, set its parameter type and bound numbers.
		 * 	3) initialize the status of attribute/reference values.
		 */
		List<LStructuralFeature> features = type.getAllFeatures();
		for(int i=0;i<features.size();i++){
			status_map.put(features.get(i), false);
			if(features.get(i).getUpperBound()>1||
					features.get(i).getUpperBound()==LMultipleObject.UNBOUNDED){
				LStructuralFeature feature = features.get(i);
				LMultipleObject val = new LMultipleObjectImpl(feature.getType(),feature.getLowerBound(),
						feature.getUpperBound(),feature.isOrdered(),feature.isUnique());
				feature_val.put(features.get(i), val);
			}
			else{
				feature_val.put(features.get(i), features.get(i).getDefaultValue());
			}
		}
	}

	@Override
	public LClass getType() {return (LClass) this.type;}

	// If no value is set, then return null.
	@Override
	public LObject get(LStructuralFeature feature) throws Exception {
		if(feature==null){
			throw this.getException("get(feature)", "feature", "Null");
		}
		
		LClass ctype = (LClass) this.type;
		if(!this.feature_val.containsKey(feature)){
			throw this.getException("get(feature)", "feature", "Class <"+ctype.getName()
						+"> does not define feature <"+feature.getName()+":"+feature.getFeatureID()+">");
		}
		return this.feature_val.get(feature);
	}
	// We can set null in any un-multiple object.
	@Override
	public void set(LStructuralFeature feature, LObject value) throws Exception {
		// Null Check
		if(feature==null){
			throw this.getException("set(feature,value)", "feature", "Null");
		}
		// Existence of Feature
		LClass ctype = (LClass) this.type;
		if(!this.status_map.containsKey(feature)){
			throw this.getException("set(feature,value)", "feature", "Class <"+ctype.getName()
						+"> does not define feature <"+feature.getName()+":"+feature.getFeatureID()+">");
		}
		// Type Match
		if(value!=null&&value.type()!=feature.getType()){
			if(value.type() instanceof LClass&&feature.getType() instanceof LClass){
				LClass ftype = (LClass) feature.getType();
				LClass vtype = (LClass) value.type();
				if(!ftype.isSuperOf(vtype)){
					throw this.getException("set(feature,value)", "feature-value", "Value Type <"+value.type().getName()
								+"> does not match <"+feature.getType().getName()+">");
				}
			}
			else{
				throw this.getException("set(feature,value)", "feature-value", "Value Type <"+value.type().getName()
							+"> does not match <"+feature.getType().getName()+">");
			}
		}
		// Constant Check 
		if(!feature.isChangable()&&
				this.status_map.get(feature)==true){
			throw this.getException("set(feature,value)", "feature", "try to update unchangable feature value");
		}
		// Multiple Check
		if(feature.getUpperBound()>1||feature.getUpperBound()==LMultipleObject.UNBOUNDED){
			/*LMultipleObject val = (LMultipleObject) this.feature_val.get(feature);
			val.addObject(value);*/
			throw this.getException("set(feature,value)", "feature-value", "Multiple Object Set cannot be set directly");
		}
		else{
			this.feature_val.put(feature, value);
		}
		this.status_map.put(feature, true);
	}
	@Override
	public Boolean isSet(LStructuralFeature feature) throws Exception {
		if(feature==null){
			throw this.getException("isSet(feature)", "feature", "Null");
		}
		
		LClass ctype = (LClass) this.type;
		if(!this.status_map.containsKey(feature)){
			throw this.getException("isSet(feature)", "feature", "Class <"+ctype.getName()
						+"> does not define feature <"+feature.getName()+":"+feature.getFeatureID()+">");
		}
		return this.status_map.get(feature);
	}
	@Override
	public void unSet(LStructuralFeature feature) throws Exception {
		if(feature==null){
			throw this.getException("unSet(feature)", "feature", "Null");
		}
		
		LClass ctype = (LClass) this.type;
		if(!this.status_map.containsKey(feature)){
			throw this.getException("unSet(feature)", "feature", "Class <"+ctype.getName()
						+"> does not define feature <"+feature.getName()+":"+feature.getFeatureID()+">");
		}
		this.status_map.put(feature, false);
	}

	@Override
	public void add(LStructuralFeature feature, LObject val) throws Exception {
		if(feature==null||(feature.getUpperBound()<=1
				&&feature.getUpperBound()!=LMultipleObject.UNBOUNDED)){
			throw this.getException("add(feature,val)","feature", "feature is not a multiple feature");
		}
		
		if(!this.feature_val.containsKey(feature)){
			throw this.getException("add(feature,val)","feature", feature.getName()+" is not defined");
		}
		
		LMultipleObject list = (LMultipleObject) this.feature_val.get(feature);
		list.addObject(val);
		this.status_map.put(feature, true);
	}
	@Override
	public void remove(LStructuralFeature feature, LObject val) throws Exception {
		if(feature==null||!this.feature_val.containsKey(feature)){
			throw this.getException("remove(feature,val)", "feature", "Undefined");
		}
		
		if(feature.getUpperBound()>1||feature.getUpperBound()==LMultipleObject.UNBOUNDED){
			LMultipleObject list = (LMultipleObject) this.feature_val.get(feature);
			list.removeObject(val);
		}
		else{
			this.feature_val.put(feature, null);
		}
		this.status_map.put(feature, true);
	}
}
