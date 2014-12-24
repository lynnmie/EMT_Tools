import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import cn.edu.buaa.sei.SVI.editor.treeNode.SVIEditorPanel;
import cn.edu.buaa.sei.SVI.interpreter.logic.Inferencer;
import cn.edu.buaa.sei.SVI.manage.IStructAssigner;
import cn.edu.buaa.sei.SVI.manage.InterpreterRegisterMachine;
import cn.edu.buaa.sei.SVI.manage.impl.SVIManageFactory;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.function.Function;
import cn.edu.buaa.sei.SVI.struct.core.function.impl.FunctionExecutor;
import cn.edu.buaa.sei.SVI.struct.core.variable.Variable;
import cn.edu.buaa.sei.SVI.struct.logic.LogicFunctionTemplate;
import cn.edu.buaa.sei.emt.exLmf.viewer.model.ExLMFEditorPane;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassObject;
import cn.edu.buaa.sei.exLmf.metamodel.LMultipleObject;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.ogm.IObjectWorld;


public class Launcher {
	
	final static InterpreterRegisterMachine rm = SVIManageFactory.getRegisterMachine();
	
	@SuppressWarnings("static-access")
	public static void MH(){
		try {
			BeautyEyeLNFHelper.launchBeautyEyeLNF();
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.translucencyAppleLike;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		
		MH();
		
		final ExLMFEditorPane db_pan = new ExLMFEditorPane();
		final SVIEditorPanel svi_pan = new SVIEditorPanel();
		
		
		
		db_pan.setBorder(new TitledBorder("Model-based Data Manager"));
		svi_pan.setBorder(new TitledBorder("Logic Expression Editor"));
		
		JButton assign = new JButton("assign");
		assign.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					assign(db_pan.getModel(),db_pan.getData(),svi_pan.getResult().getTopStructs().iterator().next());
					JOptionPane.showMessageDialog(null, "Assignment complete!", "assign", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}});
		JButton compute = new JButton("Computation");
		compute.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Struct top = svi_pan.getResult().getTopStructs().iterator().next();
				try {
					Inferencer inferencer = (Inferencer) rm.get(top);
					Boolean result = (Boolean) inferencer.interpret(top);
					JOptionPane.showMessageDialog(null, "Computation Result: "+result, "Result", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}});
		
		JFrame f = new JFrame();
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(1,2));
		pan.add(svi_pan);pan.add(db_pan);
		JPanel sp = new JPanel();
		sp.add(assign);sp.add(compute);
		
		BorderLayout frame = new BorderLayout();
		frame.setHgap(20);
		f.setLayout(frame);
		f.setTitle("DO178Logic");
		f.add(BorderLayout.CENTER,pan);
		f.add(BorderLayout.SOUTH,sp);
		
		f.setSize(300, 300);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void assign(LPackage model,IObjectWorld db,Struct top) throws Exception{
		if(model==null||db==null||top==null)throw new Exception("Null model|data|top is invalid");
		
		LClass HLRequirement = (LClass) model.getClassifierByName("HLRequirement");
		LClass LLRequirement = (LClass) model.getClassifierByName("LLRequirement");
		Set<LClassObject> hlrs = db.getObjectGroup(HLRequirement).getObjects();
		Set<LClassObject> llrs = db.getObjectGroup(LLRequirement).getObjects();
		
		IStructAssigner assigner = SVIManageFactory.createStructAssigner();
		assigner.assignByName(top, "HLR", hlrs, 0);
		assigner.assignByName(top, "LLR", llrs, 0);
		
		Function trace = assigner.getFunctionByName(top, "traceable", 0);
		assigner.assignFunction(trace, new FunctionExecutor(){
			@Override
			public void execute() throws Exception {
				LogicFunctionTemplate template = (LogicFunctionTemplate) this.getFunction().getTemplate();
				Variable hlr = template.getArguments()[0];
				Variable llr = template.getArguments()[1];
				LClassObject hobj = (LClassObject) hlr.read();
				LClassObject lobj = (LClassObject) llr.read();
				if(hobj==null||lobj==null)throw new Exception("Invalid arguments in traceable(hlr,llr)");
				
				LMultipleObject llrs = (LMultipleObject) hobj.get(hobj.getType().getFeatureByName("llrs"));
				Boolean res = llrs.containObject(lobj);
				template.getOutput().assign(res);
			}});
	}
}
