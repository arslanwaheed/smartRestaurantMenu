/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5;

import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
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
            ArrayList<Dish> dishes = new ArrayList<>();
            
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
            
            
            ArrayList<GridPane> grids = new ArrayList<>();
            ArrayList<Button> nextButtons = new ArrayList<>();
            ArrayList<Button> prevButtons = new ArrayList<>();
            ArrayList<Scene> scenes = new ArrayList<>();
            
            //define screen here
            int counter = 0;
            for (Dish dish : dishes) {
               //adding image and setting its size
               ImageView iview = new ImageView();
               Image img = new Image(dish.imageName);               
               iview.setImage(img);
               iview.setFitHeight(300);
               iview.setFitWidth(300);
               
               //labels
               Label titleLbl = new Label(dish.name);
               titleLbl.setId("title");
               Label descLbl = new Label(dish.desc);              
               descLbl.setWrapText(true);
               descLbl.setMaxWidth(300);
               Label priceLbl = new Label("Price: $"+dish.price);
               
               //buttons creation
               Button nb = new Button("Next >");
               nextButtons.add(nb);
               
               Button pb = new Button("< Prev");
               prevButtons.add(pb);
               
               
               //buttons event handlers
               nextButtons.get(counter).setOnAction((ActionEvent event) -> {
                   primaryStage.close();
                   int next = 0;
                   
                   for (int i = 0; i < grids.size(); i++) {
                       if(event.getSource().equals(nextButtons.get(i))){
                           next = i+1;
                       }
                   }
                   
                   if(event.getSource().equals(nextButtons.get(grids.size()-1))){
                       next = 0;
                   }
                   
                   primaryStage.setScene(scenes.get(next));
                   primaryStage.show();
               });
               
               prevButtons.get(counter).setOnAction((ActionEvent event) -> {
                   primaryStage.close();
                   int prev = 0;
                   
                   for (int i = 0; i < grids.size(); i++) {
                       if(event.getSource().equals(prevButtons.get(i))){
                           prev = i-1;
                       }
                   }
                   
                   if(event.getSource().equals(prevButtons.get(0))){
                       prev = grids.size()-1;
                   }
                   
                   primaryStage.setScene(scenes.get(prev));
                   primaryStage.show();
               });
               
               //Adding everything to gridpane
               GridPane grid = new GridPane();
               grid.setHgap(10);
               grid.setVgap(10);
               
               grid.add(titleLbl,1,1,2,1);
               grid.add(descLbl, 2, 2);
               grid.add(priceLbl,2,3);
               grid.add(iview,1,2);
               
               
               
               grid.add(prevButtons.get(counter),1,4);
               grid.add(nextButtons.get(counter),3,4);
               
               grids.add(grid);
               
               Scene scene = new Scene(grids.get(counter),700,500);
               scene.getStylesheets().add(Driver.class.getResource("/resources/myStyle.css").toExternalForm());
               
               scenes.add(scene);
               
               counter++;
            }
  
            primaryStage.setTitle("SmartRestaurant Table 21");
            primaryStage.setScene(scenes.get(0));
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
