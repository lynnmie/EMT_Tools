package cn.edu.buaa.sei.SVI.struct.logic;

import cn.edu.buaa.sei.SVI.struct.core.ProtoStruct;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;

/**
 * <i>AtomicLogicStruct</i> is the basic <i>LogicStruct</i> without children Structs.<br>
 * It could be: <b>LogicVariable (-->BooleanVariable)</b> or <b>DiscourseDomain (-->SetVariable)</b>
 * */
public interface AtomicLogicStruct extends ProtoStruct,LogicStruct{
}
