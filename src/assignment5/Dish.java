/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5;

/**
 *
 * @author arslanwaheed
 */
public class Dish {
    String name,
            desc,
            imageName;
    Double price;
    
    public Dish(String n ,String d ,String i, Double p){
        name =  n;
        desc = d;
        imageName = i;
        price = p;
    }
    
    public Dish(){
        name = "";
        desc = "";
        imageName = "";
        price = 0.0;
    }
    
    
    
    
    
}
