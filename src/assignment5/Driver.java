/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author arslanwaheed
 */
public class Driver extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try{
            String name = "",
                    imageName = "",
                    desc = "";
            double price = 0.0;
            ArrayList<Dish> dishes = new ArrayList<Dish>();
            
            Scanner sc = new Scanner(getClass().getResourceAsStream("/resources/config.txt"));            

            //read number of dishes present in the config.txt file
            int size = sc.nextInt();     
            sc.nextLine();

            //this for loop will read dish data from file and store it     
            for (int i = 0; i < size; i++) {
                sc.nextLine();
                
                for (int j = 0; j < 4; j++) {
                    String line = sc.nextLine();
                    //System.out.println(line);
                    
                    if(line.contains("Name: ")){
                        name = line.substring(6);
                        //System.out.println(name);
                    }else if(line.contains("Description: ")){
                        desc = line.substring(13);
                        //System.out.println(desc);
                    }else if(line.contains("Price: ")){
                        price = Double.parseDouble(line.substring(7));
                        //System.out.println(""+price);
                    }else if(line.contains("Image: ")){
                        imageName = "/images/" + line.substring(7);
                        //System.out.println(imageName);
                    }
                    
                }//end for
                
                Dish temp = new Dish(name,desc,imageName,price);
                dishes.add(temp);
            }//end of for loop
            
            
            ArrayList<GridPane> grids = new ArrayList<GridPane>();
            
            
            //define screen here
            for (Dish dish : dishes) {
               GridPane grid = new GridPane();
               ImageView iview = new ImageView();
               Image img = new Image(dish.imageName);
               
               iview.setImage(img);
               iview.setFitHeight(500);
               iview.setFitWidth(500);
               
               Label titleLbl = new Label(dish.name);
               Label descLbl = new Label(dish.desc);
               Label priceLbl = new Label(""+dish.price);
               
               titleLbl.setId("title");
               
               grid.add(titleLbl,1,1);
               grid.add(iview,1,2);
               
               grid.add(descLbl, 2, 2);
               
               grids.add(grid);
            }
            
            Scene scene = new Scene(grids.get(3),800,700);
            
            scene.getStylesheets().add(Driver.class.getResource("/resources/myStyle.css").toExternalForm());
            
            primaryStage.setTitle("SmartRestaurant Table 21");
            primaryStage.setScene(scene);
            primaryStage.show();
            
        }
        catch(Exception ex){
            System.out.println("Exception " + ex + " caught!");
        }
        
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
