package com.mxxy.game.config;

import com.mxxy.game.domain.PlayerVO;
import com.mxxy.game.domain.Profile;
import com.mxxy.game.utils.FilterByJava;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author javaman
 */
public class ProfileImpl implements IProfileManager {

    @Override
    public void save(Profile proflie) {
        File file = getProfileFile(proflie.getFilename(), true);
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            // 创建时间
            oos.writeObject(new java.util.Date());
            // 创建时间
            // 场景
            // oos.writeUTF(proflie.getSceneId());
            // 人物数据
            oos.writeObject(proflie.getPlayerVO());
            oos.close();
            proflie.setFilename(file.getAbsolutePath());
            System.err.println("游戏存档完毕: " + file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            System.err.println("游戏存档失败,找不到文件！" + proflie.getFilename());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("游戏存档失败，IO错误！" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Profile load(String filename) {
        File file = getProfileFile(filename, false);
        return loadProfile(file);
    }

    @Override
    public void romeve(String filename) {

    }

    private Profile loadProfile(File file) {
        if (file == null || !file.exists() || file.length() == 0) {
            return null;
        }
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            Date createDate = (Date) ois.readObject();
            // String sceneId = ois.readUTF();
            PlayerVO playerData = (PlayerVO) ois.readObject();
            ois.close();
            Profile profile = new Profile();
            // profile.setName(trimSuffix(file.getName()));
            profile.setFilename(file.getAbsolutePath());
            profile.setCreateDate(createDate);
            profile.setPlayerVO(playerData);
            // profile.setSceneId(sceneId);
            return profile;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private File getProfileFile(String name, boolean create) {
        String path = "save/" + name + ".jxd";
        File file = new File(path);
        if ((file == null || !file.exists()) && create) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    @Override
    public List<Profile> listProfiles() {
        List<Profile> profiles = new ArrayList<>();
        File dir = new File("save");
        if (dir != null && dir.isDirectory()) {
            File[] files = dir.listFiles(new FilterByJava(".jxd"));
            for (int i = 0; (files != null && i < files.length); i++) {
                try {
                    Profile profile = loadProfile(files[i]);
                    profiles.add(profile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return profiles;
    }

}
