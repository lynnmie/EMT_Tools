package cn.edu.buaa.sei.SVI.struct.logic.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.function.Context;
import cn.edu.buaa.sei.SVI.struct.core.function.FunctionBody;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunction;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunctionTemplate;

public class LogicFunctionImpl implements LogicFunction{
	CompositeStruct container;
	LogicFunctionTemplate template;
	FunctionBody body;
	Context context;
	
	/**
	 * For any function, its template and container must not be null.<br>
	 * However, a function could have null <b>FunctionBody</b> if it is a Native Method.<br>
	 * Except for that, context should not be null, at least the global context, <b>even though we accept such situation</b>.<br>
	 * Children: [template,{context},{body}]
	 * */
	public LogicFunctionImpl(LogicFunctionTemplate template,FunctionBody body,
			Context context,CompositeStruct container) throws Exception{
		if(template==null)
			throw new Exception("Null Template is invalid for function integrity");
		if(container==null)
			throw new Exception("Container should not be Null");
		
		this.template=template;
		this.body=body;
		this.context=context;
		this.container=container;
		
		this.container.addChildStruct(template);
		if(this.context!=null)this.container.addChildStruct(context);
		if(this.body!=null)this.container.addChildStruct(body);
		
		this.template.setFunction(this);
		if(this.body!=null)this.body.setFunction(this);
	}

	@Override
	public Struct[] getChildrenStructs() {
		return this.container.getChildrenStructs();
	}

	@Override
	public void addChildStruct(Struct child) throws Exception {
		this.container.addChildStruct(child);
	}

	@Override
	public void removeChildStruct(Struct child) throws Exception {
		this.container.removeChildStruct(child);
	}

	@Override
	public boolean containChildStruct(Struct child) {
		return this.container.containChildStruct(child);
	}

	@Override
	public int getChildrenStructSize() {
		return this.container.getChildrenStructSize();
	}

	@Override
	public Context getContext() {
		return this.context;
	}

	@Override
	public FunctionBody getBody() {
		return this.body;
	}

	@Override
	public LogicFunctionTemplate getTemplate() {
		return this.template;
	}
	
	@Override
	public String toString(){return this.template.toString();}

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

}
