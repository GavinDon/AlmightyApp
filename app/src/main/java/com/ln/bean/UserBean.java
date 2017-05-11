package com.ln.bean;

import android.view.View;
import android.widget.Toast;

/**
 * description:
 * Created by liNan on 2017/5/4 10:05
 */

public class UserBean {

    private String  top;
    private String boke;
    private String wenda;
    private String luntan;
    private String mine;

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }
    public void showToast(View view){
        Toast.makeText(view.getContext(), "viewiew", Toast.LENGTH_SHORT).show();
    }
    private boolean isShow;

    public UserBean(String top, String wenda, String boke, String mine,boolean isShow) {
        this.top=top;
        this.wenda=wenda;
        this.boke=boke;
        this.mine=mine;
        this.isShow=isShow;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getBoke() {
        return boke;
    }

    public void setBoke(String boke) {
        this.boke = boke;
    }

    public String getWenda() {
        return wenda;
    }

    public void setWenda(String wenda) {
        this.wenda = wenda;
    }

    public String getLuntan() {
        return luntan;
    }

    public void setLuntan(String luntan) {
        this.luntan = luntan;
    }

    public String getMine() {
        return mine;
    }

    public void setMine(String mine) {
        this.mine = mine;
    }
}
