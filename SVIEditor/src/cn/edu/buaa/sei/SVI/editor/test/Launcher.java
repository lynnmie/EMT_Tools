package cn.edu.buaa.sei.SVI.editor.test;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVIEditorPanel;

public class Launcher {
	public static void main(String[] args){
		MH();
		testWindow();
	}
	
	@SuppressWarnings("static-access")
	public static void MH(){
		try {
			BeautyEyeLNFHelper.launchBeautyEyeLNF();
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.translucencyAppleLike;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void testWindow(){
		MH();
		/***/
		JPanel pan = new SVIEditorPanel();
		final JFrame f = new JFrame();
		f.setSize(300, 300);
		f.add(pan);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
