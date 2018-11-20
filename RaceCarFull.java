/*
Author: Rocky Mazorow
Date: 2/13/2018

This class implements the RaceCarPane class, adds labels for the desired letters,
and uses event handlers to move the car forward or backward
(Full class using all letters)
*/
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.HPos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class RaceCarFull extends Application {

    private EventHandler<KeyEvent> keyHandler;
    private EventHandler<KeyEvent> spaceHandler;
    private EventHandler<KeyEvent> escHandler;
    String[] p1Keys = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "K", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    String[] p2Keys = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "k", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    String curr1 = "";
    String curr2 = "";

    Pane shell = new Pane();
    RaceCarPane car1;
    RaceCarPane car2;
    Label l1;
    Label l2;
    Label score;
    Label restart;
    Scene scene;

    @Override
    public void start(Stage primaryStage) {
        startRace();

        scene = new Scene(shell, 900, 400);
        primaryStage.setTitle("Race Car");
        primaryStage.setScene(scene);
        primaryStage.show();

        keyHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getText().equals(e.getText().toUpperCase()) && e.getCode().isLetterKey()) {
                    testCar1(e);
                } else if (e.getCode().isLetterKey()) {
                    testCar2(e);
                }

                if (car1.isOver) {
                    scene.removeEventHandler(KeyEvent.KEY_RELEASED, keyHandler);
                    if (car1.didWin) {
                        score.setText("Player 1 won! : )");
                    } else {
                        score.setText("Player 1 lost : (");
                    }
                    restart.setText("Press Space to Start a New Game");
                    scene.addEventHandler(KeyEvent.KEY_RELEASED, spaceHandler);
                } else if (car2.isOver) {
                    scene.removeEventHandler(KeyEvent.KEY_RELEASED, keyHandler);
                    if (car2.didWin) {
                        score.setText("Player 2 won! : )");
                    } else {
                        score.setText("Player 2 lost : (");
                    }
                    restart.setText("Press Space to Start a New Game");
                    scene.addEventHandler(KeyEvent.KEY_RELEASED, spaceHandler);
                }
            }
        };

        spaceHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                switch (e.getCode()) {
                    case SPACE:
                        shell.getChildren().clear();
                        startRace();
                        scene.removeEventHandler(KeyEvent.KEY_RELEASED, spaceHandler);
                        scene.addEventHandler(KeyEvent.KEY_RELEASED, keyHandler);
                }
            }
        };

        escHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                switch (e.getCode()) {
                    case ESCAPE:
                        shell.getChildren().clear();
                        startRace();
                        scene.addEventHandler(KeyEvent.KEY_RELEASED, keyHandler);
                }
            }
        };

        scene.addEventHandler(KeyEvent.KEY_RELEASED, keyHandler);
        scene.addEventHandler(KeyEvent.KEY_RELEASED, escHandler);

        shell.requestFocus();
    }

    public void startRace() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(25, 10, 25, 10));
        pane.setHgap(5);
        pane.setVgap(10);
        //pane.setGridLinesVisible(true);

        Label p1 = new Label("Player 1");
        Label p2 = new Label("Player 2");
        car1 = new RaceCarPane(750, 50, Color.TURQUOISE, Color.TEAL, Color.BLACK);
        Rectangle lane1 = new Rectangle(50, 25, 815, 50);
        Rectangle lane2 = new Rectangle(150, 25, 815, 50);
        lane1.setFill(Color.web("9F9BA5"));
        lane2.setFill(Color.web("9F9BA5"));
        car2 = new RaceCarPane(750, 50, Color.RED, Color.MAROON, Color.BLACK);
        car1.setMinSize(830, 50);
        car2.setMinSize(830, 50);
        l1 = new Label("");
        l2 = new Label("");
        score = new Label("");
        restart = new Label("");
        p1.setFont(Font.font("Futura", 20));
        p2.setFont(Font.font("Futura", 20));
        l1.setFont(Font.font("Futura", 50));
        l2.setFont(Font.font("Futura", 50));
        score.setFont(Font.font("Futura", 50));
        restart.setFont(Font.font("Futura", 30));

        pane.add(p1, 0, 0);
        pane.add(p2, 0, 4);
        pane.add(lane1, 0, 1);
        pane.add(car1, 0, 1);
        pane.add(lane2, 0, 5);
        pane.add(car2, 0, 5);
        pane.add(l1, 1, 1);
        pane.add(l2, 1, 5);
        pane.add(score, 0, 2);
        pane.add(restart, 0, 3);
        pane.setHalignment(p1, HPos.CENTER);
        pane.setHalignment(p2, HPos.CENTER);
        pane.setHalignment(l1, HPos.RIGHT);
        pane.setHalignment(l2, HPos.RIGHT);
        pane.setHalignment(score, HPos.CENTER);
        pane.setHalignment(restart, HPos.CENTER);

        newKey1(l1);
        newKey2(l2);

        shell.getChildren().add(pane);
    }

    public void testCar1(KeyEvent e) {
        if (e.getText().equals(curr1)) {
            car1.driveForward();
            newKey1(l1);
        } else {
            car1.driveBackward();
        }
    }

    public void testCar2(KeyEvent e) {
        if (e.getText().toLowerCase().equals(curr2)) {
            car2.driveForward();
            newKey2(l2);
        } else {
            car2.driveBackward();
        }
    }

    public void newKey1(Label l1) {
        int rand1 = (int) (Math.random() * 24);
        curr1 = p1Keys[rand1];
        if (!curr1.equals(curr2)) {
            l1.setText(curr1);
        } else {
            newKey1(l1);
        }
    }

    public void newKey2(Label l2) {
        int rand2 = (int) (Math.random() * 24);
        curr2 = p2Keys[rand2];
        if (!curr1.equals(curr2)) {
            l2.setText(curr2);
        } else {
            newKey2(l2);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
