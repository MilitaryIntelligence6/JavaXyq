package com.mxxy.extendpackage;

import com.mxxy.game.base.Panel;
import com.mxxy.game.config.IProfileManager;
import com.mxxy.game.config.ProfileImpl;
import com.mxxy.game.domain.PlayerVO;
import com.mxxy.game.domain.Profile;
import com.mxxy.game.event.PanelEvent;
import com.mxxy.game.handler.AbstractPanelHandler;
import com.mxxy.game.modler.CreteRoleMolder;
import com.mxxy.game.resources.Constant;
import com.mxxy.game.sprite.Players;
import com.mxxy.game.sprite.Weapon;
import com.mxxy.game.ui.ContainersPanel;
import com.mxxy.game.utils.ComponentFactory;
import com.mxxy.game.widget.ImageComponentButton;
import com.mxxy.game.widget.Label;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * CreateRolePanel (创建人物)
 *
 * @author ZAB 邮箱 ：624284779@qq.com
 */
final public class CreateRole extends AbstractPanelHandler<CreteRoleMolder> {

    int[] colorations = new int[3];
    int index;
    int direction = 4;
    private JTextField names;
    private Label player, selectperson, characterIndex, playerDesc;
    private Players person;
    private ImageComponentButton source;
    private IProfileManager iProfileManager;
    private Weapon mWeapon;
    private String current;
    /**
     * 改变状态 (stand,walk,attack)
     *
     * @param e
     */
    private ImageComponentButton changeindex;

    @Override
    public void init(PanelEvent evt) {
        super.init(evt);
        iProfileManager = (ProfileImpl) object[3];
    }

    @Override
    protected void initView() {
        player = findViewById("player");
        player.setHorizontalAlignment(SwingConstants.CENTER);
        names = ComponentFactory.createTextName();
        panel.add(names, 0);
        selectperson = findViewById("selectperson");
        characterIndex = findViewById("characterIndex");
        playerDesc = findViewById("playerDesc");
        playerDesc.setBounds(283, 455, 274, 90);
        playerDesc.setFont(new Font("宋体", Font.PLAIN, 12));
        playerDesc.setText("");
    }

    public void createPlayer(ActionEvent e) {
        source = (ImageComponentButton) e.getSource();

        if (current != source.getName()) {
            String desc = propertiesConfigManager.get(source.getName());
            playerDesc.setText(desc);
            ImageIcon imageIcon = new ImageIcon("res/componentsRes/createimage/headbackground.png");
            selectperson.setIcon(imageIcon);
            selectperson.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
            ImageIcon headImageIcon = new ImageIcon("res/componentsRes/createimage/" + source.getName() + ".png");
            characterIndex.setIcon(headImageIcon);
            characterIndex.setSize(headImageIcon.getIconWidth(), headImageIcon.getIconHeight());
            PlayerVO playerVO = new PlayerVO();
            playerVO.setCharacter(source.getName());
            mWeapon = new Weapon();
            mWeapon.setWeaponIndex("58");
            playerVO.setmWeapon(mWeapon);
            playerVO.setState(Players.STATE_STAND);
            person = dataStoreManager.createPlayer(playerVO);
            person.setShadow(false);
            current = source.getName();
        }
    }

    public void changeState(ActionEvent e) {
        changeindex = (ImageComponentButton) e.getSource();
        // changeindex.setIcon(new
        // ImageIcon(changeindex.getFrames().get(0).getImage()));
        // changeindex.setIcon(new
        // ImageIcon(changeindex.getFrames().get(2).getImage()));
        if (person != null) {
            person.setState(changeindex.getName());
        }
    }

    public void changeColor(ActionEvent e) {
        String name = changeindex == null ? "stand" : changeindex.getName();
        switch (name) {
            case "stand":
                index++;
                if (index > 6) {
                    index = 0;
                }
                colorations[0] = index;
                break;
            case "walk":
                index++;
                if (index > 6) {
                    index = 0;
                }
                colorations[1] = index;
                break;
            case "attack":
                index++;
                if (index > 6) {
                    index = 0;
                }
                colorations[2] = index;
                break;
        }
        if (person != null) {
            person.setState("stand");
            person.setColorations(colorations, true);
        }
    }

    public void changeDirection(ActionEvent e) {
        person.setDirection(direction);
        direction += 1;
    }

    private String newProfileName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
        return sdf.format(new Date());
    }

    /**
     * 上一步
     *
     * @param e
     */
    public void back(ActionEvent e) {
        Panel selectRole = uihelp.getPanel("SelectRole");
        uihelp.hidePanel(panel);
        uihelp.showPanel(selectRole);
    }

    /**
     * 下一步
     *
     * @param e
     */
    public void next(ActionEvent e) {
        String trim = names.getText().trim();
        if (person == null) {
            uihelp.prompt(null, Constant.getString("PleaseClickRole"), 2000);
            return;
        }
        if (trim.length() <= 0) {
            uihelp.prompt(null, Constant.getString("PleaseLeftEditText"), 2000);
            return;
        }

        Profile p = new Profile();
        p.setFilename(newProfileName());
        PlayerVO playerVO = new PlayerVO();
        playerVO.setSceneLocation(new Point(69, 26));
        playerVO.setName(trim);// 设置姓名
        playerVO.setColorations(colorations);// 设置着色器
        playerVO.setDirection(4);// 设置方向
        playerVO.setCharacter(source.getName());// 文件id
        playerVO.setmWeapon(mWeapon);
        playerVO.setState(Players.STATE_STAND);// 设置人物站立
        playerVO.setDescribe("护城小兵");// 设置人物称谓
        playerVO.setHp(100);
        playerVO.setMoeny(10000000);
        playerVO.setSceneId(Constant.SCENE_DHW);
        p.setPlayerVO(playerVO);
        p.setCreateDate(new Date());
        iProfileManager.save(p);
        uihelp.prompt(null, "人物创建成功！", 2000);
        application.enterGame(playerVO);
    }

    @Override
    protected String setConfigFileName() {
        return "value/playerdesc.properties";
    }

    @SuppressWarnings("serial")
    @Override
    public JPanel getContainersPanel() {
        return new ContainersPanel(80, 400, 200, 200) {
            @Override
            protected void draw(Graphics2D g, long elapsedTime) {
                if (person != null && g != null) {
                    person.draw(g, 70, 100);
                    person.setDirection(direction);
                    person.update(elapsedTime);
                }
            }
        };
    }
}
