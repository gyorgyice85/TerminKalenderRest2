package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TextBox {

    public static void display()  {
        Stage stage = new Stage();


        //creating label WHEN
        Label when = new Label("When:");

        //creating label WHERE
        Label where = new Label("Where:");

        //creating label Name
        Label name = new Label("Termin Name:");

        //creating label invitation
        Label invitation = new Label("Invitation:");

        //creating Text Filed for when
        TextField whenField = new TextField();

        //creating Text Filed for where
        TextField whereField = new TextField();

        //creating Text Filed for Appointment name
        TextField nameTerminField = new TextField();

        //creating Text Filed for invitation
        TextField invitationField = new TextField();

        //Creating Buttons
        Button buttonSubmit = new Button("Submit");
        Button buttonClose = new Button("Close");

        stage.setOnCloseRequest(e ->{
            e.consume();
            Boolean answer = ConfirmBox.display();
            if(answer)
                stage.close();
        });

        buttonClose.setOnAction(e -> {
            Boolean answer = ConfirmBox.display();
            if(answer)
                stage.close();
        });

        //Creating a Grid Pane
        GridPane gridPane = new GridPane();

        //Setting size for the pane
        gridPane.setMinSize(400, 200);

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid
        gridPane.add(when, 0, 0);
        gridPane.add(whenField, 1, 0);
        gridPane.add(where, 0, 1);
        gridPane.add(whereField, 1, 1);
        gridPane.add(name, 0, 2);
        gridPane.add(nameTerminField, 1, 2);
        gridPane.add(invitation, 0, 3);
        gridPane.add(invitationField, 1, 3);
        gridPane.add(buttonSubmit, 0, 4);
        gridPane.add(buttonClose, 1, 4);

        //Styling nodes
        buttonSubmit.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        buttonClose.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

        when.setStyle("-fx-font: normal bold 20px 'serif' ");
        where.setStyle("-fx-font: normal bold 20px 'serif' ");
        name.setStyle("-fx-font: normal bold 20px 'serif' ");
        invitation .setStyle("-fx-font: normal bold 20px 'serif' ");

        gridPane.setStyle("-fx-background-color: BEIGE;");

        //Creating a scene object
        Scene scene = new Scene(gridPane);

        //Setting title to the Stage
        stage.setTitle("Termin");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.showAndWait();
    }




}
