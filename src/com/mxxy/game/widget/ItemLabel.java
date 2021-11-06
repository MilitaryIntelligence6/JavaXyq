package com.mxxy.game.widget;

import java.awt.Color;
import java.awt.Font;

import com.mxxy.game.domain.ItemInstance;
import com.mxxy.game.utils.SpriteFactory;

public class ItemLabel extends Label{
	
	public enum CellType {
		/**背包栏 */
		BAG, 
		/**装备栏 */
		EQUIP
	};
	
	private Font foregroundFont= new Font("宋体", Font.PLAIN, 14);
	
	private ItemInstance item;
	
	private CellType cellType = CellType.BAG;
	
	public ItemLabel() {
		this(null);
	}
	
	public ItemLabel(ItemInstance item) {
		super("");
		this.setItem(item);
	}
	
	public void setItem(ItemInstance item) {
		this.item = item;
		if(item!=null) {
			Animation anim = SpriteFactory.loadAnimation("res/item/item50/"+item.getItemId()+".tcp"); 
			setAnim(anim);
		}else {
			setAnim(null);
		}
		setSize(51,51);
	}
	
	public ItemInstance getItem() {
		return item;
	}
	
	public CellType getCellType() {
		return cellType;
	}

	public void setCellType(CellType cellType) {
		this.cellType = cellType;
	}

	protected void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		if(item!=null && item.getAmount() >1) {
			g.setColor(Color.BLACK);
			g.setFont(foregroundFont);
			String str = String.valueOf(item.getAmount());
			g.drawString(str, 5-1, 15);
			g.drawString(str, 5+1, 15);
			g.drawString(str, 5, 15-1);
			g.drawString(str, 5, 15+1);
			g.setColor(Color.WHITE);
			g.setFont(foregroundFont);
			g.drawString(str, 5, 15);
		}
	}
}