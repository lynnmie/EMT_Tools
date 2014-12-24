package cn.edu.buaa.sei.SVI.struct.group.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.function.Context;
import cn.edu.buaa.sei.SVI.struct.core.function.FunctionBody;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunction;
import cn.edu.buaa.sei.SVI.struct.group.GroupFunctionTemplate;

public class GroupFunctionImpl implements GroupFunction{
	
	CompositeStruct container;
	GroupFunctionTemplate template;
	Context context;
	FunctionBody body;
	
	/**
	 * Children: [template,context,container]
	 * */
	public GroupFunctionImpl(GroupFunctionTemplate template,
			Context context,FunctionBody body,CompositeStruct container) throws Exception{
		if(template==null||container==null)
			throw new Exception("Template|Container should not be null");
		
		this.template=template;
		this.container=container;
		this.context=context;
		this.body=body;
		
		this.container.addChildStruct(this.template);
		if(this.context!=null)this.container.addChildStruct(this.context);
		if(this.body!=null)this.container.addChildStruct(this.body);
		
		this.template.setFunction(this);
		if(this.body!=null)this.body.setFunction(this);
	}

	@Override
	public Struct[] getChildrenStructs() {return this.container.getChildrenStructs();}
	@Override
	public void addChildStruct(Struct child) throws Exception {this.container.addChildStruct(child);}
	@Override
	public void removeChildStruct(Struct child) throws Exception {this.container.removeChildStruct(child);}
	@Override
	public boolean containChildStruct(Struct child) {return this.container.containChildStruct(child);}
	@Override
	public int getChildrenStructSize() {return this.container.getChildrenStructSize();}

	@Override
	public Context getContext() {return this.context;}
	@Override
	public FunctionBody getBody() {return this.body;}

	@Override
	public void setContext(Context context) {
		if(this.body!=null){
			try {
				this.container.removeChildStruct(this.body);
				this.body.setFunction(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(this.context!=null)
			try {
				
				this.container.removeChildStruct(this.context);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		this.context=context;
		if(this.context!=null)
			try {
				this.container.addChildStruct(this.context);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		if(this.body!=null){
			try {
				this.container.addChildStruct(this.body);
				this.body.setFunction(this);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void setBody(FunctionBody body) {
		if(this.body!=null)
			try {
				this.container.removeChildStruct(this.body);
				this.body.setFunction(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		this.body=body;
		if(this.body!=null)
			try {
				this.container.addChildStruct(this.body);
				this.body.setFunction(this);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public GroupFunctionTemplate getTemplate() {return this.template;}

	@Override
	public String toString(){return "{"+this.template.toString()+"}";}
}
