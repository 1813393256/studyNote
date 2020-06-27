package com.lzq.designMode.creationalPattern.builderPattern;

import com.lzq.designMode.creationalPattern.builderPattern.builder.MealBuilder;

/**
 * @Author laizhiqiang
 * @Description:建造者模式，使用MealBuilder演示建造者模式
 * @Date 2020/6/12 0012 15:11
 */
public class BuilderPatternDemo {
    public static void main(String...args){
        MealBuilder mealBuilder = new MealBuilder();
        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal");
        vegMeal.showItems();
        System.out.println("Total Cost:"+vegMeal.getCost());

        Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
        System.out.println("\nNon-Veg Meal");
        nonVegMeal.showItems();
        System.out.println("Total Cost:"+nonVegMeal.getCost());
    }
}
