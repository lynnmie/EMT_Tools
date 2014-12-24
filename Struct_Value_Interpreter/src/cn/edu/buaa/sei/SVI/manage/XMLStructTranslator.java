package cn.edu.buaa.sei.SVI.manage;

import org.w3c.dom.Element;

import cn.edu.buaa.sei.SVI.struct.core.Struct;

public interface XMLStructTranslator {
	/**
	 * Translate the Struct to a XML Element.
	 * */
	public Element translate(Struct struct) throws Exception;
	/**
	 * Process the referencer...
	 * */
	public void update(Struct struct) throws Exception;
	/**
	 * Reset the state of the translator
	 * */
	public void restart();
	/**
	 * Translate an Element to Struct
	 * */
	public Struct retranslate(Element element) throws Exception;
	public void reupdate(Element top) throws Exception;
}
