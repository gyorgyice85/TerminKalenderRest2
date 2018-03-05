package gui;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ConfirmBox {

    static boolean answer;
    public static boolean display(){
        Stage window = new Stage();

        //Block Window to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Close Window");
        window.setMinWidth(250);

        Label label = new Label("Sure");

        //create two button
        Button yesButton = new Button("yes");
        Button noButton = new Button("no");

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });


        /*Button closeButton = new Button("Sure");
        closeButton.setOnAction(e -> window.close());
*/
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
