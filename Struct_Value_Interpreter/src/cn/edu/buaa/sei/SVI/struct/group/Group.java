package cn.edu.buaa.sei.SVI.struct.group;

import java.util.Iterator;

/**
 * <i>Group</i> is a value to present a group of elements {for any types}<br>
 * 1. EnumerateGroup: Group that contains numbers of elements stored in it and could be listed
 * out, as called "Enumerated".<br>
 * 2. ConditionGroup: Group that contains elements which satisfy a specified Logic Function, like
 * "{x|P(x)}". ConditionGroup could be very likely a Unbounded-Group with infinite elements in it.
 * */
public interface Group {
	/**
	 * Return the cardinality of the set
	 * */
	public int size();
	/**
	 * Checking whether a given val is in the set.
	 * */
	public Boolean contains(Object val);
	/**
	 * Add a new element into the group.
	 * */
	public void add(Object obj);
	/**
	 * Remove a existing element from the group.
	 * */
	public void remove(Object obj);
	/**
	 * Add all the elements in group grp into this group.
	 * */
	public void addAll(Group grp);
	/**
	 * Remove all the element that in both of these groups from this group.
	 * */
	public void removeAll(Group grp);
	/**
	 * Return an iterator that runs all the elements in the group.
	 * */
	Iterator<Object> iterator();
}
