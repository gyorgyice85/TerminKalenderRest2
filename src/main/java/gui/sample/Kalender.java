package gui.sample;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.Button;
import java.awt.event.ActionListener;

public class Kalender extends Application {
    javafx.scene.control.Button days[] = new javafx.scene.control.Button[31];
    javafx.scene.control.Button dayName[] = new javafx.scene.control.Button[7];
    final String[] WEEK_DAY_NAME = { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };
    int week;
    TextArea memoArea;
    HBox hBox = new HBox();
    javafx.scene.control.Button prevMonth = new javafx.scene.control.Button("<");
    javafx.scene.control.Button nextMonth = new javafx.scene.control.Button(">");
    final int[] lastDateOfMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane maincore = new BorderPane();
        Scene scene = new Scene(maincore,300,300);
        MenuBar menuBar = new MenuBar();
        Menu addAppointment = new Menu("Termin hinzufügen");
        menuBar.getMenus().addAll(addAppointment);

        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);

        GridPane grid1 = new GridPane();
        grid1.setHgap(5);
        grid1.setVgap(5);
        hBox.getChildren().addAll(prevMonth,nextMonth);
        grid1.add(menuBar,0,0);
        grid1.add(hBox,0,1);

        for (int i = 0; i < 7; i++){
            dayName[i] = new javafx.scene.control.Button(WEEK_DAY_NAME[i]);
            grid.add(dayName[i],i,0);
        }

        for (int i = 0; i < 29; i++) {
            days[i] = new javafx.scene.control.Button(Integer.toString(i+1));
            week = i/7;
            grid.add(days[i],i%7,week+1);
        }

        memoArea = new TextArea();

        maincore.setLeft(grid);
        //maincore.setTop(menuBar);
        maincore.setTop(grid1);
        maincore.setBottom(memoArea);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
