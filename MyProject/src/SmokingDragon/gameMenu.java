package SmokingDragon;

import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class gameMenu extends Application{
	private Image bg1=new Image(getClass().getResourceAsStream("resource/gameMenu1.png"));
	private BackgroundSize bgSize=new BackgroundSize(800,733, false, false, false, true);
	private BackgroundImage bgImg1=new BackgroundImage(bg1, BackgroundRepeat.NO_REPEAT,
			BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bgSize);
	private Background backg1=new Background(bgImg1);
	
	
	private Image menuBtnImg=new Image(getClass().getResourceAsStream("resource/menuBtn.png"));
	private ImageView startBtn= new ImageView();
	private ImageView quitBtn=new ImageView();
	private VBox btnGroup=new VBox();
	

	
	private Pane root=new Pane();
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage st) throws Exception {
		startBtn.setImage(menuBtnImg);
		quitBtn.setImage(menuBtnImg);
		
		startBtn.setViewport(new Rectangle2D(0,0,200,70));
		quitBtn.setViewport(new Rectangle2D(400,0,200,70));

		btnGroup.getChildren().addAll(startBtn,quitBtn);
		btnGroup.setStyle("-fx-spacing:50;");
		btnGroup.setLayoutX(290);
		btnGroup.setLayoutY(250);
		
		quitBtn.setOnMouseClicked(e->{
			st.close();
		});
		
		startBtn.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            
     		public void handle(MouseEvent t) {
     			st.hide();
     			InGame startGame=new InGame();
     			try {
					startGame.start(new Stage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		         	t.consume();
            }
        });
		
		
		
		
		root.setBackground(backg1);
		root.getChildren().addAll(btnGroup);
		Scene sc= new Scene(root,800,600);
		st.setTitle("Smoking Dragon");
		st.setScene(sc);
		st.setResizable(false);
		st.show();
		st.centerOnScreen();
		
	}
}
