package com.example.springweb.dao;

import java.io.Serializable;

public class HelloApp implements Serializable {
    private String userId;
    private String name;
    private String range;
    private String huanJie;
    private String knowledge;

    public HelloApp(){
        userId = null;
        name = null;
        range = null;
        huanJie = null;
        knowledge = null;
    }
    public HelloApp(String user_id,String name,String range, String huanJie, String knowledge){
        this.userId = user_id;
        this.name = name;
        this.range = range;
        this.huanJie = huanJie;
        this.knowledge = knowledge;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String id) {
        this.userId= id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }
    public void setHuanJie(String a){this.huanJie = a;}
    public String getHuanJie(){return this.huanJie; }
    public void setKnowledge(String a){this.knowledge = a;}
    public String getKnowledge(){return knowledge;}

    @Override
    public String toString() {

        return userId + "," + name + "," + range+","+huanJie+","+knowledge;
    }
}
