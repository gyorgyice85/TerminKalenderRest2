package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    //static boolean answer;
    public static void display(){
        Stage window = new Stage();

        //Block Window to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Close Window");
        window.setMinWidth(250);

        Label label = new Label();
        label.setText("Sure?");

        //create two button
//        Button yesButton = new Button("yes");
//        Button noButton = new Button("no");

//        yesButton.setOnAction(e -> {
//            answer = true;
//            window.close();
//        });
//
//        noButton.setOnAction(e -> {
//            answer = false;
//            window.close();
//        });


        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        //return answer;
    }
}


