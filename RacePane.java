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

public class RacePane extends GridPane {
	private EventHandler<KeyEvent> keyHandler;
	String[] p1Keys = {"A", "S", "D", "F"};
	String[] p2Keys = {"H", "J", "K", "L"};
	String curr1 = "";
	String curr2 = "";
	
	public RacePane() {
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(25, 10, 25, 10));
		pane.setHgap(5);
		pane.setVgap(10);
		//pane.setGridLinesVisible(true);
		
		Label p1 = new Label("Player 1");
		Label p2 = new Label("Player 2");
		RaceCarPane car1 = new RaceCarPane(750, 50, Color.TURQUOISE, Color.TEAL, Color.BLACK);
		Rectangle lane1 = new Rectangle(50, 25, 815, 50);
		Rectangle lane2 = new Rectangle(150, 25, 815, 50);
		lane1.setFill(Color.web("9F9BA5"));
		lane2.setFill(Color.web("9F9BA5"));
		RaceCarPane car2 = new RaceCarPane(750, 50, Color.RED, Color.MAROON, Color.BLACK);
		car1.setMinSize(830, 50);
		car2.setMinSize(830, 50);
		Label l1 = new Label("");
		Label l2 = new Label("");
		Label score = new Label("");
		p1.setFont(Font.font("Futura", 25));
		p2.setFont(Font.font("Futura", 25));
		l1.setFont(Font.font("Futura", 50));
		l2.setFont(Font.font("Futura", 50));
		score.setFont(Font.font("Futura", 50));
		
		pane.add(p1, 0, 0);
		pane.add(p2, 0, 3);
		pane.add(lane1, 0, 1);
		pane.add(car1, 0, 1);
		pane.add(lane2, 0, 4);
		pane.add(car2, 0, 4);
		pane.add(l1, 1, 1);
		pane.add(l2, 1, 4);
		pane.add(score, 0, 2);
		pane.setHalignment(p1, HPos.CENTER);
		pane.setHalignment(p2, HPos.CENTER);
		pane.setHalignment(l1, HPos.RIGHT);
		pane.setHalignment(l2, HPos.RIGHT);
		pane.setHalignment(score, HPos.CENTER);
		
		newKey1(l1);
		newKey2(l2);
		
		keyHandler = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
			//pane.setOnKeyReleased(e -> {
				switch(e.getCode()) {
					case A:
					case S:
					case D:
					case F:
						if (e.getText().equals("a") || e.getText().equals("s") || e.getText().equals("d") || e.getText().equals("f")) {
							break;
						}
						else if(e.getText().equals("A") && curr1.equals("A")) {
							car1.driveForward();
							newKey1(l1);
						}
						else if(e.getText().equals("S") && curr1.equals("S")) {
							car1.driveForward();
							newKey1(l1);
						} else if(e.getText().equals("D") && curr1.equals("D")) {
							car1.driveForward();
							newKey1(l1);
						} else if(e.getText().equals("F") && curr1.equals("F")) {
							car1.driveForward();
							newKey1(l1);
						}
						else {
							car1.driveBackward();
						}
						break;
				case H:
				case J:
				case K:
				case L:
						if (e.getText().equals("H") || e.getText().equals("J") || e.getText().equals("K") || e.getText().equals("L")) {
							break;
						}else if(e.getText().equals("h") && curr2.equals("H")) {
							car2.driveForward();
							newKey2(l2);
						}
						else if(e.getText().equals("j") && curr2.equals("J")) {
							car2.driveForward();
							newKey2(l2);
						} else if(e.getText().equals("k") && curr2.equals("K")) {
							car2.driveForward();
							newKey2(l2);
						} else if(e.getText().equals("l") && curr2.equals("L")) {
							car2.driveForward();
							newKey2(l2);
						}
						else {
							car2.driveBackward();
						}
				}
				
				if (car1.isOver){
					scene.removeEventHandler(KeyEvent.KEY_RELEASED, keyHandler);
					if (car1.didWin) {
						score.setText("Player 1 won! : )");
					}
					else {
						score.setText("Player 1 lost : (");
					}
				}
				else if (car2.isOver) {
					scene.removeEventHandler(KeyEvent.KEY_RELEASED, keyHandler);
					if (car2.didWin) {
						score.setText("Player 2 won! : )");
					}
					else {
						score.setText("Player 2 lost : (");
					}
				}
			}};
			
		scene.addEventHandler(KeyEvent.KEY_RELEASED, keyHandler);
	}
	
	public void newKey1(Label l1) {
		int rand1 = (int)(Math.random() * 4);
		curr1 = p1Keys[rand1];
		l1.setText(curr1);
	}
	
	public void newKey2(Label l2) {
		int rand2 = (int)(Math.random() * 4);
		curr2 = p2Keys[rand2];
		l2.setText(curr2);
	}
	
	public RaceCarPane getCar1() {
		return car1;
	}
	
	public RaceCarPane getCar2() {
		return car2;
	}
}