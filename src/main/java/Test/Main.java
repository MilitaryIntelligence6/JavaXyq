package Test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicListUI;

public class Main {
	public static void main(String[] args) {
		Main m = new Main();
	}

	public Main() {
		JFrame f = new JFrame();
		f.setLayout(new BorderLayout());
		f.setSize(400, 800);
		JList l = new JList();
		DefaultListModel model = new DefaultListModel();
		model.addElement("1");
		model.addElement("2");
		l.setModel(model);
		CustomerUI ui = new CustomerUI();
		l.setUI(ui);
		l.setCellRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {

				System.out.println(index);
				if (isSelected) {
					this.setBorder(new LineBorder(Color.yellow));
					((CustomerUI) list.getUI()).setCellHeight(index, 40, 20);
					// TODO 添加图片
					return this;
				} else {
					this.setBorder(new LineBorder(Color.red));
					((CustomerUI) list.getUI()).setCellHeight(index, 20);
					this.setOpaque(true);
					return this;
				}
			}
		});
		f.add(l);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}

class CustomerUI extends BasicListUI {
	public CustomerUI() {
		super();
		cellHeights = new int[2];
	}

	public void setCellHeight(int index, int value, int defaultHeight) {
		for (int i = 0; i < cellHeights.length; i++) {
			cellHeights[i] = defaultHeight;
		}
		cellHeights[index] = value;
	}

	void setCellHeight(int index, int i) {
		cellHeights[index] = i;
	}
}