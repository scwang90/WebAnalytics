package com.simpletech.webanalytics.model.entity;

/**
 * 分享传播图 点
 * Created by 树朾 on 2015/9/29.
 */
public class MapPointValue {

    private String Id;      //节点ID
    private int cl;         //分类 0 起始点 1 中间点 2 叶子节点
    private int pv;         //本节点的 PV 量
    private int sh;         //被分享数
    private String mk;      //remark 备注

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Integer getCl() {
        return cl;
    }

    public void setCl(Integer cl) {
        this.cl = cl;
    }

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public int getSh() {
        return sh;
    }

    public void setSh(int sh) {
        this.sh = sh;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }
}
