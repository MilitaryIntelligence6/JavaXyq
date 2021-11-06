package com.mxxy.game.domain;

import java.awt.*;

public class SnapObject {

    private int location;//表示方位
    private Component com;//表示吸附的对象,也就是要吸到谁的身上去

    public SnapObject(int location, Component com) {
        this.location = location;
        this.com = com;
    }

    public Component getCom() {
        return com;
    }

    public int getLocation() {
        return location;
    }

    public String toString() {
        return "方位是:" + location + ",自己是:" + com;
    }
}
