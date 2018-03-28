/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playermp3;

/**
 *
 * @author Umer
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
        
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
        
import javafx.scene.layout.VBox;
/**
 *
 * @author Umer
 */
public class Alert {
 static Stage window;
 static Button button,button1;
 static Scene scene; 
 static boolean  answer;
    public static boolean display(String title,String message)
    {
    
       window=new Stage();
       //window=primaryStage;
       
       window.initModality(Modality.APPLICATION_MODAL);
       window.setTitle("Hello there!");
       window.setMinWidth(250);
       
       Label label1=new Label();
       label1.setText("hi there");
       
       button =new Button ("Close window");
       button1 =new Button ("nobutton");
       
       button.setOnAction(e->
       {
           window.close();
       answer=true;
       
                   });
       
      button1.setOnAction(e->
       {
           window.close();
       answer=false;
       
                   });
      window.getOnCloseRequest();
      
      window.setOnCloseRequest(e->
      {
         e.consume();
           window.close();
       
         answer=false;
         
      });
              
       
       VBox layout=new VBox(20);
       layout.getChildren().addAll(label1, button,button1);
       
      scene =new Scene(layout,200,200);
       window.setScene(scene);
       window.showAndWait();
       return answer;
    }
}
