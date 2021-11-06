package Test;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class TestBindKey {
	JFrame jf = new JFrame("测试");
	JTextArea ta = new JTextArea(3, 30);
	JButton bt = new JButton("发送");
	JTextField tf = new JTextField(15);

	public void init() {
		jf.add(ta);
		JPanel jp = new JPanel();
		jp.add(tf);
		jp.add(bt);
		jf.add(jp, BorderLayout.SOUTH);

		AbstractAction sendMsg = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				ta.append(tf.getText() + "\n");
				tf.setText("");
			}
		};

		// bt.addActionListener(sendMsg);
		// 将Ctrl+Enter键和“send”关联
		// tf.getInputMap().put(KeyStroke.getKeyStroke('\n',java.awt.event.InputEvent.CTRL_MASK),"send");

		tf.getInputMap().put(KeyStroke.getKeyStroke("alt A"), "send");
		// 将"send"和sendMsg Action关联
		tf.getActionMap().put("send", sendMsg);

		jf.pack();
		jf.setVisible(true);

	}

	public static void main(String[] args) {
		new TestBindKey().init();
		System.out.println("ActionMap and InputMap!");
	}
}