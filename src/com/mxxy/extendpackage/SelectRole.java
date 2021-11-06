package com.mxxy.extendpackage;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.mxxy.game.base.Panel;
import com.mxxy.game.config.IDataManager;
import com.mxxy.game.config.IProfileManager;
import com.mxxy.game.config.ProfileImpl;
import com.mxxy.game.domain.PlayerVO;
import com.mxxy.game.domain.Profile;
import com.mxxy.game.event.PanelEvent;
import com.mxxy.game.event.PlayerEvent;
import com.mxxy.game.event.PlayerListenerAdapter;
import com.mxxy.game.handler.AbstractPanelHandler;
import com.mxxy.game.resources.Constant;
import com.mxxy.game.sprite.Players;
import com.mxxy.game.ui.ContainersPanel;
import com.mxxy.game.widget.ImageComponentButton;
import com.mxxy.game.widget.Label;

/**
 * SelectRole (选择角色)
 * 
 * @author ZAB 邮箱 ：624284779@qq.com
 */
final public class SelectRole extends AbstractPanelHandler {

	private ImageComponentButton crateButton;

	private int presonCount;

	private PlayerVO playerVO;

	private List<Players> listPlayers;

	private Label name, head, headbackground;

	private ClickHandler clickHandler;

	@Override
	public void init(PanelEvent evt) {
		super.init(evt);
		IProfileManager profileImp = (ProfileImpl) object[3];
		presonCount = getListProfiles(profileImp).size();
		IDataManager dataManager = (IDataManager) object[0];
		clickHandler = new ClickHandler();
		listPlayers = getListPlayers(profileImp, dataManager);
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
	}

	@Override
	protected void initView() {
		crateButton = findViewById("CreateRole");
		name = findViewById("name");
		head = findViewById("head");
		headbackground = findViewById("headbackground");
		period = 50;
		setAutoUpdate(true);
	}

	/**
	 * 获取存档列表
	 * 
	 * @param profileImp
	 * @return
	 */
	public List<Profile> getListProfiles(IProfileManager profileImp) {
		return profileImp.listProfiles();
	}

	/**
	 * 获取序列化对象并且实例
	 * 
	 * @param profileImp
	 *            //存档管理器
	 * @param dataManager
	 *            //数据管理器
	 * @return
	 */
	public List<Players> getListPlayers(IProfileManager profileImp, IDataManager dataManager) {
		List<Players> listPlayers = new ArrayList<Players>(6);
		List<Profile> listProfiles = getListProfiles(profileImp);
		for (int i = 0; i < listProfiles.size(); i++) {
			Profile profile = listProfiles.get(i);
			Players createPlayer = dataManager.createPlayer(profile.getPlayerVO());
			if (i < 3) {
				createPlayer.setLocation(350 + 100 * i, 190);
			} else {
				createPlayer.setLocation(350 + 100 * (i - 3), 315);
			}
			if (clickHandler != null) {
				clickHandler.setSceneId(profile.getSceneId());
				createPlayer.addPlayerListener(clickHandler);
			}
			createPlayer.setRect(new Rectangle(createPlayer.getX() - 50, createPlayer.getY() - 100, 97, 127));
			listPlayers.add(createPlayer);
		}
		return listPlayers;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (int i = 0; i < listPlayers.size(); i++) {
			Players comp = listPlayers.get(i);
			boolean b = comp.rect.contains(e.getPoint());
			if (b) {
				this.selectIndex = i;
				comp.fireEvent(new PlayerEvent(comp, PlayerEvent.CLICK));
				return;
			}
		}
	}

	public int selectIndex;

	public boolean isHover(Players players) {
		Point point = panel.getMousePosition();
		if (point == null) {
			return false;
		}
		boolean hover = players.contains(point.x, point.y);
		return hover;
	}

	public class ClickHandler extends PlayerListenerAdapter {

		public String sceneId;

		@Override
		public void click(PlayerEvent evt) {
			player = evt.getPlayer();
//			player.index = selectIndex;
			playerVO = player.getPalyVo();
			name.setText(playerVO.getName());
			head.setIcon(new ImageIcon("res/componentsRes/createimage/" + playerVO.getCharacter() + ".png"));
			headbackground.setIcon(new ImageIcon("res/componentsRes/createimage/headbackground.png"));
		}

		public void setSceneId(String sceneId) {
			this.sceneId = sceneId;
		}
	}

	/**
	 * 上一步
	 * @param e
	 */
	public void back(ActionEvent e) {
		Panel dlg = uihelp.getPanel(e);
		showHide(dlg);
	}

	/**
	 * 下一步(开始进入游戏)
	 * @param e
	 */
	public void next(ActionEvent e) {
		if (playerVO != null) {
			application.enterGame(playerVO);
		}
	}

	/**
	 * 创建人物
	 * @param e
	 */
	public void create(ActionEvent e) {
		if (presonCount >= 6) {
			uihelp.prompt(null, Constant.getString("RoleErr"), 2000);
			return;
		}
		Panel createRole = uihelp.getPanel(e);
		showHide(createRole);
	}

	/**
	 * 当选择栏没有人物时提示
	 * @param creates
	 */
	int count;

	public void promptRegistPlayer(ImageComponentButton creates) {
		creates.start(count % creates.getFrames().size());
		count++;
	}

	@Override
	public void update(PanelEvent evt) {
		if (presonCount <= 0) {
			promptRegistPlayer(crateButton);
		}
	}

	@Override
	public JPanel getContainersPanel() {
		return new ContainersPanel(0, 0, panel.getWidth(), panel.getHeight()) {
			@Override
			protected void draw(Graphics2D g, long elapsedTime) {
				if (listPlayers.size() > 0) {
					for (int i = 0; i < listPlayers.size(); i++) {
						Players players = listPlayers.get(i);
						players.setHideName(true);
						players.draw(g, players.getX(), players.getY());
						players.setDirection(4);
						players.update(elapsedTime);
					}
				}
				// g.setColor(Color.red);
				// g.fillRect(300, 90, 10, 10);
			}
		};
	}
}
