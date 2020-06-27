package com.lzq.designMode.creationalPattern.protoTypePattern.father;

/**
 * @Author laizhiqiang
 * @Description:原型模式，创建一个实现了Cloneable接口的抽象类
 * @Date 2020/6/12 0012 15:33
 */
public abstract class Shape implements Cloneable{
    private String id;
    protected String type;
    public abstract void draw();
    public String getId(){
        return id;
    }
    public String getType(){
        return type;
    }
    public void setId(String id){
        this.id=id;
    }

    @Override
    public Object clone(){
        Object clone=null;
        try {
            clone=super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
