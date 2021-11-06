package com.mxxy.game.ui;

import com.mxxy.game.base.IPanelDraw;
import com.mxxy.game.config.Context;
import com.mxxy.game.resources.Constant;
import com.mxxy.game.utils.SpriteFactory;
import com.mxxy.game.utils.UIHelp;

import javax.swing.*;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.awt.event.*;

/**
 * 游戏主窗体
 *
 * @author ZAB 邮箱 ：624284779@qq.com
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame implements IWindows {
    public boolean isfristApplication = true;
    private Dimension preferredSize;
    private Image cursorImage;
    private TrayIcon trayIcon;
    private UIHelp uihelp;
    private Image icon;
    private JDialog dialog;
    private IPanelDraw draw;

    @Override
    public void initContent(Context context) {
        context.setWindows(this);
        this.uihelp = new UIHelp(this);
        icon = new ImageIcon(
                SpriteFactory.loadImage("res/componentsRes/title.png"))
                .getImage();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(icon);
        super.setTitle(Constant.getString("MainTitle"));
        uihelp.setTopParent(this);
        dialog = new JDialog(this);
        setResizable(false);
        hideCursor();
        showSystemtTray();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void show() {
        super.show();
    }

    @Override
    public Container getContainers() {
        return draw.getComponent();
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(Constant.getString("MainTitle") + title);
    }

    @Override
    public void showPanel(IPanelDraw panel) {
        if (getPanel() != null) {
            getPanel().getComponent().removeAll();
        }
        this.draw = panel;
        int width = panel.getClass().getSimpleName().equals("LoadingPanel") ? panel
                .getScreenWidth() : Constant.WINDOW_WIDTH;
        int height = panel.getClass().getSimpleName().equals("LoadingPanel") ? panel
                .getScernHeight() : Constant.WINDOW_HEIGHT;

        preferredSize = new Dimension(width, height);

        if (isfristApplication) {
            dispose();
            setSize(preferredSize);
            setUndecorated(panel.getClass().getSimpleName()
                    .equals("LoadingPanel"));
        }

        if (panel instanceof GamePanel || panel instanceof BattlePanel) {
            uihelp.setDialog(dialog);
            dialog.setContentPane(uihelp.getPanel("MessageNotificationPanel"));
            dialog.setTitle(Constant.getString("MessageNotification"));
            dialog.setIconImage(new ImageIcon(SpriteFactory
                    .loadImage("res/componentsRes/tts.png")).getImage());
            Point p = new Point(this.getLocation().x + Constant.WINDOW_WIDTH
                    + 5, this.getLocation().y - 5);
            dialog.setLocation(p);
            dialog.setVisible(true);
            dialog.pack();
        }

        JComponent component = panel.getComponent();
        setContentPane(component);
        component.requestFocusInWindow();
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
        setVisible(true);
    }

    public void setIsfristApplication(boolean isfristApplication) {
        this.isfristApplication = isfristApplication;
    }

    @Override
    public synchronized void setLocation(int x, int y) {
        Point now = new Point(x, y);
        super.setLocation(now.x, now.y);

        if (dialog != null && (uihelp.isSnapDialog()) || (dialog != null && !dialog.isShowing())) {
            Point dis = uihelp.getDialogPoint();
            if (dis != null) {
                dialog.setLocation(now.x + dis.x, now.y + dis.y);
            }
        }
    }

    @Override
    public UIHelp getUIHelp() {
        return uihelp;
    }

    @Override
    public void hideCursor() {
        cursorImage = Toolkit.getDefaultToolkit().getImage("");
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(cursorImage,
                new Point(0, 0), "CURSOR"));
    }

    @Override
    public void showSystemtTray() {
        if (SystemTray.isSupported()) {
            try {
                trayIcon = new TrayIcon(icon, Constant.getString("MainTitle"),
                        createPopupMenu());
                trayIcon.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
                            if (getExtendedState() == JFrame.ICONIFIED) {
                                setVisible(true);
                                setAlwaysOnTop(true);
                                setExtendedState(JFrame.NORMAL);
                            } else {
                                setExtendedState(JFrame.ICONIFIED);
                            }
                        } else if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
                        }
                    }
                });
                trayIcon.setImageAutoSize(true);
                SystemTray systemTray = SystemTray.getSystemTray();
                systemTray.add(trayIcon);
                trayIcon.displayMessage(Constant.getString("MainTitle"), "梦想",
                        MessageType.INFO);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        } else {
            return;
        }
    }

    private PopupMenu createPopupMenu() {
        PopupMenu popup = new PopupMenu();
        MenuItem open = new MenuItem("打开");
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
                setExtendedState(JFrame.NORMAL);
            }
        });
        MenuItem close = new MenuItem("关闭");
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        popup.add(open);
        popup.addSeparator();
        popup.add(close);
        return popup;
    }

    @Override
    public IPanelDraw getPanel() {
        return draw;
    }

    @Override
    public JFrame getFrame() {
        return this;
    }
}
