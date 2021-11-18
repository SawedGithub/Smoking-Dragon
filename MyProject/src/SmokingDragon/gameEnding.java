package SmokingDragon;

import javafx.application.Application;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class gameEnding extends Application{
	private Image princessImg=new Image(getClass().getResourceAsStream("resource/princess/dragonPrincess01.png"));
	private ImageView princess=new ImageView();
	
	private Text lbl=new Text("Click Here to return\n to Menu");
	private Pane root=new Pane();
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage st) throws Exception {
		lbl.setOnMouseEntered(e->{
			lbl.setStyle("-fx-font-size:25;");
		});
		lbl.setOnMouseExited(e->{
			lbl.setStyle("-fx-font-size:20;");
		});
		
		lbl.setOnMouseClicked(e->{
			st.close();
			gameMenu returntoMenu=new gameMenu();
			try {
				returntoMenu.start(new Stage());
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		});
		
		lbl.setLayoutX(30);
		lbl.setLayoutY(420);
		lbl.setTextAlignment(TextAlignment.JUSTIFY);
		lbl.setFont(Font.loadFont(getClass().getResourceAsStream("resource/SIEBB.TTF"),20));
		lbl.setFill(Color.WHITE);
		
		princess.setImage(princessImg);
		princess.setViewport(new Rectangle2D(0,0,512,512));
		princess.setLayoutX(0);
		princess.setLayoutY(0);
		root.getChildren().add(princess);
		root.getChildren().add(lbl);
		Scene sc=new Scene(root,512,512);
		sc.setFill(Color.BLACK);
		
		st.setTitle("Smoking Dragon");
		st.setScene(sc);
		st.centerOnScreen();
		st.setResizable(false);
		st.show();
		
	}

}
