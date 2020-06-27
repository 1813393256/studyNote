package com.lzq.designMode.creationalPattern.builderPattern.builder;

import com.lzq.designMode.creationalPattern.builderPattern.Meal;
import com.lzq.designMode.creationalPattern.builderPattern.children.itemImpl.burgerExtend.ChickenBurger;
import com.lzq.designMode.creationalPattern.builderPattern.children.itemImpl.burgerExtend.VegBurger;
import com.lzq.designMode.creationalPattern.builderPattern.children.itemImpl.coldDrinkExtend.Coke;
import com.lzq.designMode.creationalPattern.builderPattern.children.itemImpl.coldDrinkExtend.Pepsi;

/**
 * @Author laizhiqiang
 * @Description:建造者模式，创建一个MealBuilder类，实际的builder类负责创建Meal对象
 * @Date 2020/6/12 0012 15:08
 */
public class MealBuilder {
    public Meal prepareVegMeal(){
        Meal meal=new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }
    public Meal prepareNonVegMeal(){
        Meal meal=new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}
