package cn.edu.buaa.sei.exLmf.metamodel;

/*
 *	LClassObject {
 *		- *****
 *		- type (LClass)
 *		- map <LStructrualFeature, value>: feature from type, value == null or value's type == feature.valueType
 *		- set/get --> feature (0..1)
 *		- add/remove --> feature (0..n)
 *		- isSet/unSet: initialization (isSet==false), set/add/remove (isSet=true), unSet (isSet==false)
 *	} 
 */
public interface LClassObject extends LObject{
	public LClass getType();
	
	public LObject get(LStructuralFeature feature) throws Exception;
	public void set(LStructuralFeature feature, LObject value) throws Exception;
	public Boolean isSet(LStructuralFeature feature) throws Exception;
	public void unSet(LStructuralFeature feature) throws Exception;
	
	public void add(LStructuralFeature feature,LObject val) throws Exception;
	public void remove(LStructuralFeature feature,LObject val) throws Exception;
	/*public Boolean isMultiple(LStructuralFeature feature);
	public Collection<LObject> getFromMultiple(LStructuralFeature mul_feature);
	public void appendMultiple(LStructuralFeature mul_feature,LObject val);
	public void removeMultiple(LStructuralFeature mul_feature,LObject val);*/
}
