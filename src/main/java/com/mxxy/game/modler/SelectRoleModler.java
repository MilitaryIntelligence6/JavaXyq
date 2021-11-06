// package com.mxxy.game.modler;
//
// import java.awt.Rectangle;
// import java.util.ArrayList;
// import java.util.List;
//
// import com.mxxy.game.config.IDataManager;
// import com.mxxy.game.config.IProfileManager;
// import com.mxxy.game.config.Profile;
// import com.mxxy.game.sprite.Players;
// import com.pp.SelectRole.ClickHandler;
//
// public class SelectRoleModler {
// /**
// * 获取存档列表
// * @param profileImp
// * @return
// */
// public List<Profile> getListProfiles(IProfileManager profileImp) {
// return profileImp.listProfiles();
// }
//
// /**
// * 获取序列化对象并且实例
// * @param profileImp //存档管理器
// * @param dataManager //数据管理器
// * @return
// */
// public List<Players> getListPlayers(IProfileManager profileImp, IDataManager
// dataManager) {
// List<Players>listPlayers=new ArrayList<Players>(6);
// List<Profile> listProfiles = getListProfiles(profileImp);
// for (int i = 0; i < listProfiles.size(); i++) {
// Profile profile = listProfiles.get(i);
// Players createPlayer = dataManager.createPlayer(profile.getPlayerVO());
// if(i<3){
// createPlayer.setLocation(350+100*i, 190);
// }else{
// createPlayer.setLocation(350+100*(i-3), 315);
// }
// if(clickHandler!=null){
// clickHandler.setSceneId(profile.getSceneId());
// createPlayer.addPlayerListener(clickHandler);
// }
// createPlayer.setRect(new
// Rectangle(createPlayer.getX()-50,createPlayer.getY()-100,97,127));
// listPlayers.add(createPlayer);
// }
// return listPlayers;
// }
//
// private ClickHandler clickHandler;
//
// public void setPlayListener(ClickHandler clickHandler) {
// this.clickHandler=clickHandler;
// }
// }
