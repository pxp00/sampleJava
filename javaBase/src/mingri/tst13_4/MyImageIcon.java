package mingri.tst13_4;

import java.awt.Container;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class MyImageIcon extends JFrame {

	private static final long serialVersionUID = 1L;

	public MyImageIcon() {
		// getContentPane jFrame-> obj_container
		Container container = getContentPane();

		// create JLabel
		JLabel jl = new JLabel("这是一个JFrame窗体", JLabel.CENTER);

		// get Url
		URL url = MyImageIcon.class.getResource("imageButton.jpg");
		Icon icon = new ImageIcon(url); // 实例化Icon对象

		// label setIcon
		jl.setIcon(icon); // 为标签设置图片
		jl.setHorizontalAlignment(SwingConstants.CENTER);// 设置文字放置在标签中间
		jl.setOpaque(true); // 设置标签为不透明状态

		// add to container
		container.add(jl); // 将标签添加到容器中

		// setJFrame
		setSize(250, 100); // 设置窗体大小
		setVisible(true); // 使窗体可见
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// 设置窗体关闭模式
	}

	public static void main(String args[]) {
		new MyImageIcon(); // 实例化MyImageIcon对象
	}
}
