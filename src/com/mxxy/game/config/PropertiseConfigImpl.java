package com.mxxy.game.config;

import java.io.*;
import java.util.Map.Entry;
import java.util.Properties;

public class PropertiseConfigImpl implements IPropertiseManager {

    private String filename;

    private Properties properties;

    public PropertiseConfigImpl() {
        properties = new Properties();
    }

    @Override
    public void loadConfigs() {
        File file = new File(filename);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            InputStream in = new BufferedInputStream(new FileInputStream(file));
            properties.load(new InputStreamReader(in, "utf-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String get(String key) {
        return properties.getProperty(key);
    }

    @Override
    public void put(String key, Object object) {
        properties.setProperty(key, (String) object);
    }

    @Override
    public void saveConfig() {
        File file = new File(filename);
        try {
            properties.store(new FileOutputStream(file), "java");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean contains(String key) {
        return properties.containsKey(key);
    }

    @Override
    public boolean loadCheckUser(String user, String cipher) {
        for (Entry<Object, Object> entrySet : properties.entrySet()) {
            if (entrySet.getKey().equals(user) && entrySet.getValue().equals(cipher)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getPropertiseSize() {
        return properties.size();
    }

    @Override
    public String getType() {
        return "properties";
    }

    @Override
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
