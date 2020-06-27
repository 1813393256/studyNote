package com.lzq.designMode.creationalPattern.builderPattern;

import com.lzq.designMode.creationalPattern.builderPattern.father.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author laizhiqiang
 * @Description:建造者模式，创建一个Meal类，带有上面定义的Item对象
 * @Date 2020/6/12 0012 14:57
 */
public class Meal {
    private List<Item>items=new ArrayList<>();
    public void addItem(Item item){
        items.add(item);
    }

    public float getCost(){
        float cost=0.0f;
        for (Item item:items){
            cost+=item.price();
        }
        return cost;
    }

    public void showItems(){
        items.forEach(item -> {
            System.out.print("Item:"+item.name());
            System.out.print(",Packing:"+item.packing().pack());
            System.out.println(",Price:"+item.price());
        });
    }
}
