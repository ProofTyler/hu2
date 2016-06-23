package hu2;

import static hu2.model.Labyrinth.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LabyrinthController {
	@FXML TextField field_x_matrix;
	@FXML TextField field_y_matrix;
	@FXML TextField field_start;
	
	private static BorderPane rootLayout;	
	private static Rectangle[][] rectangles;
	private static int pos_x = 0;
	private static int pos_y = 0;
	private static int x_matrix;
	private static int y_matrix;
	
	@FXML private void handleCreate(){
		y_matrix = Integer.parseInt(field_y_matrix.getText());
		x_matrix = Integer.parseInt(field_x_matrix.getText());
		rectangles = new Rectangle[y_matrix][x_matrix];
		create(y_matrix,x_matrix);
	}
	
	@FXML private void handleStepByStep(){
		findNextStep();
	}
	
	@FXML private void handleRun(){
		while(findNextStep() != true);
	}
	
	@FXML private void handleClearAll(){
		pos_x = 0;
		pos_y = 0;
		for(Rectangle[] rec : rectangles)
			for(Rectangle rec2 : rec)
				rootLayout.getChildren().remove(rec2);
	}
	
	@FXML private void handleClear(){
		pos_x = 0;
		pos_y = 0;
		clear(y_matrix, x_matrix);
	}
	
	public void init() {
		field_x_matrix.setText("28");
		field_y_matrix.setText("24");
	}
	
	public static void createRectangle(int y, int x){
		final Rectangle rec = new Rectangle(pos_x,pos_y,20,20);		
		rec.setFill(Color.GREY);
	    rec.setStroke(Color.BLACK);
	    rec.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.isShiftDown()) setStatus(GESPERRT,y,x);
				if(event.isAltDown()) setStatus(WEG_NEU,y,x);			
			}
		});
		rec.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton() == MouseButton.SECONDARY) 	setStatus(START,y,x);
				if(event.getButton() == MouseButton.PRIMARY) 	setStatus(ZIEL,y,x);				
			}
		});
		pos_x += 20;
		if( (x_matrix * 20) == pos_x ){
			pos_y += 20;
			pos_x = 0;
		}
		rectangles[y][x] = rec;
		rootLayout.getChildren().add(rec);
	}
	
	public static void setFillColor(int status, int y, int x) {
		switch (status) {
		case GESPERRT: 		rectangles[y][x].setFill(Color.GREY);
			break;
		case WEG_NEU: 		rectangles[y][x].setFill(Color.WHITE);
			break;
		case WEG_GELAUFEN: 	rectangles[y][x].setFill(Color.ORANGE);
			break;
		case START: 		rectangles[y][x].setFill(Color.BLUE);
			break;
		case ZIEL: 			rectangles[y][x].setFill(Color.GREEN);
			break;
		}
	}
	
	public void setRootLayout(BorderPane root) {
		rootLayout = root;
	}

	

	

	
}
