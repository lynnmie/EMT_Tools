package cn.edu.buaa.sei.SVI.manage;

import java.util.Set;

import cn.edu.buaa.sei.SVI.struct.core.Struct;

public interface StructManager {
	/*public Struct get(String id) throws Exception;
	public void put(String id,Struct element) throws Exception;
	
	public boolean contain(String id);
	public boolean contain(Struct element);
	
	public String getIdOf(Struct element) throws Exception;
	public Set<String> getAllIDs();
	public Set<Struct> getAllStructs();
	
	public void clearSpace();*/
	
	/**
	 * Checking whether a specified struct is in one of the top struct or it is that one.
	 * */
	public boolean contain(Struct struct);
	/**
	 * Return all the top structs
	 * */
	public Set<Struct> getTopStructs();
	/**
	 * Add a new Struct as a top of the manager.
	 * @exception Exception struct==null
	 * @exception Exception struct is not top && struct in one of the tops.
	 * */
	public void putTopStruct(Struct struct) throws Exception;
	/**
	 * Remove one top from the manager.
	 * @exception Exception struct is not one top in the manager.
	 * */
	public void removeTopStruct(Struct struct) throws Exception;
	/**
	 * Clear all the tops of the manager.
	 * */
	public void clear();
}
