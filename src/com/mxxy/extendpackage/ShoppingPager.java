package com.mxxy.extendpackage;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.mxxy.game.domain.ItemInstance;
import com.mxxy.game.domain.MedicineList;
import com.mxxy.game.event.PanelEvent;
import com.mxxy.game.handler.AbstractPanelHandler;
import com.mxxy.game.modler.MedicineMolder;
import com.mxxy.game.utils.FileUtils;
import com.mxxy.game.utils.JsonUtils;
import com.mxxy.game.utils.SpriteFactory;
import com.mxxy.game.widget.ItemDetailLabel;
import com.mxxy.game.widget.ItemLabel;
import com.mxxy.game.widget.Label;

final public class ShoppingPager extends AbstractPanelHandler<MedicineMolder> implements DocumentListener {

	private String[] items = { "四叶花", "草果", "山药", "佛手" };

	private Label selectedBorder;

	private Label selectingBorder;

	private ItemDetailLabel detailLabel = new ItemDetailLabel();

	private Label price_lable, total_lable, moeny_lable;

	private double totalCost = 0; //总金额

	private int amount = 0;

	private double price = 0;

	private JTextField amount_tx;

	private ItemInstance currentItem;

	@Override
	protected void initView() {

		price_lable = findViewById("price");

		moeny_lable = findViewById("moeny");

		total_lable = findViewById("total");

		amount_tx = findViewById("amount");

		MedicineList parses = JsonUtils.parses(loadJson("uiconfig/medicine.json"), MedicineList.class);

		selectedBorder = new Label(SpriteFactory.loadAnimation("res/wzife/button/itemselected.tcp"));  //选中图标

		selectingBorder = new Label(SpriteFactory.loadAnimation("res/wzife/button/itemselecting.tcp"));  //未选中图标

		int x0 = 8, y0 = 36;

		int rows = 1, cols = 4;

		for (int y = 0; y < rows; y++) {

			for (int x = 0; x < cols; x++) {

				ItemLabel label = new ItemLabel(modler.createItem(items[y * cols + x], parses));
				label.setName("item-" + items[y * cols + x]);
				label.setSize(50, 50);
				label.setLocation(x0 + x * 51, y0 + y * 51);
				panel.add(label, 0);
				label.addMouseListener(this);
				label.addMouseMotionListener(this);
			}
		}

		moeny_lable.setText(String.valueOf(player.getPalyVo().getMoeny()));

		amount_tx.getDocument().addDocumentListener(this);
		setAutoUpdate(true);
	}

	public String loadJson(String fString) {
		String read = null;
		try {
			read = FileUtils.readFile(new File(fString), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return read;
	}

	@Override
	public void update(PanelEvent evt) {
		super.update(evt);
		this.totalCost = amount * price;
		price_lable.setText(String.valueOf(price));
		total_lable.setText(String.valueOf(totalCost));
		amount_tx.setText(String.valueOf(amount));
	}

	private void setSelectedItem(ItemInstance item) {
		this.currentItem = item;
		ItemLabel label = (ItemLabel) findViewById("item-" + item.getName());
		selectedBorder.setLocation(label.getX() - 1, label.getY() - 1);
		panel.add(selectedBorder, 0);
		this.price = item.getPrice();
		this.amount = 1;
		update(null);
		amount_tx.setText(String.valueOf(amount));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		ItemLabel label = (ItemLabel) e.getSource();
		ItemInstance item = label.getItem();
		if (this.currentItem == item) {
			int n = e.isShiftDown() ? 10 : 1;
			this.amount += e.getButton() == MouseEvent.BUTTON1 ? n : -n;  //左键 & 右键
			if (this.amount > 99) {
				this.amount = 99;
			}
			if (this.amount < 1) {
				this.amount = 1;
			}
			update(null);
			amount_tx.setText(String.valueOf(amount));
		} else {
			setSelectedItem(item);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		super.mouseExited(e);
		panel.remove(selectingBorder);
		uihelp.hideToolTip(detailLabel);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		ItemLabel label = (ItemLabel) e.getSource();
		selectingBorder.setLocation(label.getX() - 1, label.getY() - 1);
		panel.add(selectingBorder, 0);
		detailLabel.setItem(label.getItem());
		uihelp.showToolTip(detailLabel, label, e);
	}

	private void syncAmount() {
		try {
			this.amount = Integer.parseInt(amount_tx.getText());
		} catch (Exception ex) {
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		syncAmount();
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		syncAmount();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		syncAmount();
	}
}
