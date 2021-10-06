package mingri.tst13_1;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;

// reqs: JFrame:title and JLabel: text
// flow: new JFrame -> getContainer; new JLabel -> container.add(jl);
public class Example1 extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		JFrame jf = new JFrame("hello world !");
		Container container = jf.getContentPane();
		JLabel jl = new JLabel("hello world");
	}
}
// public class Example1 extends JFrame {
// private static final long serialVersionUID = 1L;
//
// // 定义一个类继承JFrame类
// public void CreateJFrame(String title) { // 定义一个CreateJFrame()方法
// JFrame jf = new JFrame(title); // 实例化一个JFrame对象
// Container container = jf.getContentPane(); // 获取一个容器
// JLabel jl = new JLabel("label: 这是一个JFrame窗体"); // 创建一个JLabel标签
//
// jl.setHorizontalAlignment(SwingConstants.RIGHT);// 使标签上的文字居中
//
// container.add(jl); // 将标签添加到容器中
// container.setBackground(Color.red);// 设置容器的背景颜色
//
// jf.setVisible(true); // 使窗体可视
// jf.setSize(200, 500); // 设置窗体大小
// jf.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);// 设置窗体关闭方式
// }
//
// public static void main(String args[]) {// 在主方法中调用createJFrame()方法
// new Example1().CreateJFrame("title: 创建一个JFrame窗体");
// }
// }
