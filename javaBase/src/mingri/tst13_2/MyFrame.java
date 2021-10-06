package mingri.tst13_2;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

class MyJDialog extends JDialog { // 创建新类继承JDialog类

	private static final long serialVersionUID = 1L;

	public MyJDialog(MyFrame frame) {
		// 实例化一个JDialog类对象，指定对话框的父窗体、窗体标题和类型
		super(frame, "第一个JDialog窗体", true);
		Container container = getContentPane(); // 创建一个容器
		container.add(new JLabel("这是一个对话框")); // 在容器中添加标签
		setBounds(120, 120, 100, 100); // 设置对话框窗体大小
	}
}

public class MyFrame extends JFrame { // 创建新类

	private static final long serialVersionUID = 1L;

	public MyFrame() {
		Container container = getContentPane(); // 创建一个容器
		container.setLayout(null);
		JLabel jl = new JLabel("这是一个JFrame窗体"); // 在窗体中设置标签
		// 将标签的文字置于标签中间位置
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		container.add(jl);
		JButton bl = new JButton("弹出对话框"); // 定义一个按钮
		bl.setBounds(10, 10, 100, 21);
		bl.addActionListener(new ActionListener() { // 为按钮添加鼠标单击事件
			public void actionPerformed(ActionEvent e) {
				// 使MyJDialog窗体可见
				new MyJDialog(MyFrame.this).setVisible(true);
			}
		});
		container.add(bl); // 将按钮添加到容器中

		// container.add(bl);
		container.setBackground(Color.green);
		setSize(200, 200);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String args[]) {
		new MyFrame(); // 实例化MyJDialog类对象
	}
}
