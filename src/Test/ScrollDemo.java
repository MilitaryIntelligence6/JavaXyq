package Test;

import java.awt.Dimension;

import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mxxy.game.widget.ScrollBarUI;

public class ScrollDemo extends JFrame {

	public ScrollDemo() {
		init();
	}

	public static void main(String[] args) {
		new ScrollDemo();
	}

	public void init() {
		JScrollPane jScrollPane = new JScrollPane();
		JScrollBar jScrollBar = new JScrollBar();
		jScrollBar.setOrientation(JScrollBar.VERTICAL);
		jScrollBar.setUI(new ScrollBarUI());
		jScrollBar.setOpaque(false);
		jScrollBar.setBounds(0, 0, 20, 100);
		final JList jList = new JList(new MyListModel());
		jList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!jList.getValueIsAdjusting()) { // 设置只有释放鼠标时才触发
					System.out.println(jList.getSelectedValue());
				}
			}
		});
		// jList.setLocation(0, 50);
		// jList.setAlignmentY(20);

		jList.setFixedCellHeight(30);
		jList.setOpaque(false);
		jScrollPane.getViewport().setOpaque(false);
		jScrollPane.setVerticalScrollBar(jScrollBar);
		jScrollPane.setOpaque(false);
		jScrollPane.setPreferredSize(new Dimension(200, 200));
		jScrollPane.setViewportView(jList);
		add(jScrollPane);
		setSize(200, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}

class MyListModel extends AbstractListModel {

	@Override
	public Object getElementAt(int index) {
		// TODO Auto-generated method stub
		return index;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 100;
	}
}