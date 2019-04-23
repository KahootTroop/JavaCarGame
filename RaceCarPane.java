/*
Author: Rocky Mazorow
Date: 2/13/2018

This class build the pane that creates the lanes, the cars, and drives them forward or backwards
*/
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class RaceCarPane extends Pane {
    private double x = 15;
    private double y = 15;
    private double width = 0.0;
    private double height = 0.0;
    private Polygon top;
    private Rectangle body;
    private Circle w1;
    private Circle w2;
    private Color topColor = Color.RED;
    private Color bodyColor = Color.rgb(250, 128, 144);
    private Color wheelColor = Color.BLUE;
    public boolean isOver = false;
    public boolean didWin = false;

    public RaceCarPane(int w, int h) {
        this.y = h;
        this.width = w;
        this.height = h;
        this.draw();
    }

    public RaceCarPane(int w, int h, Color t, Color b, Color ws) {
        this.y = h;
        this.width = w;
        this.height = h;
        this.topColor = t;
        this.bodyColor = b;
        this.wheelColor = ws;
        this.draw();
    }

    private void draw() {
        this.getChildren().clear();
        
        top = new Polygon();
        top.getPoints().addAll(new Double[]{
            x + 10, y - 20,
            x + 20, y - 30,
            x + 30, y - 30,
            x + 40, y - 20});
        top.setFill(topColor);
        body = new Rectangle(x, y - 20, 50, 10);
        body.setFill(bodyColor);
        w1 = new Circle(x + 15, y - 5, 5);
        w1.setFill(wheelColor);
        w2 = new Circle(x + 35, y - 5, 5);
        w2.setFill(wheelColor);
        this.getChildren().addAll(top, body, w1, w2);
    }

    public void driveForward() {
        x += 75;
        if (x > this.width) {
            x = this.width;
            isOver = true;
            didWin = true;
        }
        this.draw();
    }

    public void driveBackward() {
        x -= 75;
        if (x < 0) {
            x = 0;
            isOver = true;
            didWin = false;
        }
        this.draw();
    }
}
