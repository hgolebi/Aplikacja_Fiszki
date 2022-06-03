package com.pap.fishes.papfishes;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class Main extends Application {

	private static MediaPlayer  mp;


	public static void muteMusic()
	{
		mp.setMute(!mp.isMute());
	}

	MediaPlayer mediaPlayer;
	public MediaPlayer getMediaPlayer(String fileName) {

		Media media = new Media(Objects.requireNonNull(getClass().getResource(fileName)).toExternalForm());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		return mediaPlayer;
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
//		 Stage stage = new Stage();
//
		stage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue)
                stage.setMaximized(false);
        });
		
		String fileName = "/water.mp3";
		mp = getMediaPlayer(fileName);
		mp.play();
//		muzyka
//		 setting an icon of the program
		Image icon = new Image("FISH.png");
		stage.getIcons().add(icon);
		stage.setTitle("FISHES");
		
		
		AnchorPane root1 = new AnchorPane();
		StackPane stackPane = new StackPane();
		stackPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		Scene scene1 = new Scene(stackPane, 100, 100, Color.WHITE);
		
		scene1.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());
		
		//TO JEST TWOJA SCENA BARTEK, do tej sceny sie dostajemy przyciskiem z pierwszej sceny
//		GridPane playScene = FXMLLoader.load(getClass().getResource("/fxml/fish.fxml"));
//		Scene scene2 = new Scene(playScene);


		
		
// FILL IN THE OPENING SCENE (moja)
		
		stage.setResizable(true);
		stage.setHeight(800);
		stage.setWidth(800);
		
		stage.setMinWidth(500);
		stage.setMinHeight(600);
		
		
		Text Header = new Text("FISHES");
		Header.setStyle("-fx-font-family: arial; -fx-font-size: 50; -fx-fill: #124DCC;");

		Header.setFill(Color.BLACK);
		Header.setFont(Font.font("Times New Roman", 25));
		
//		Line line = new Line();
//		line.setStartX(200);
//		line.setStartY(200);
//		line.setEndX(500);
//		line.setEndY(200);
//		line.setStrokeWidth(5);
//		line.setStroke(Color.RED);
//		line.setOpacity(0.5);
//		line.setRotate(45);
//		root1.getChildren().add(line);
		
		Rectangle HeaderRectangle = new Rectangle();
		HeaderRectangle.setWidth(500);
		HeaderRectangle.setHeight(100);
		HeaderRectangle.setStrokeWidth(2);
		HeaderRectangle.setFill(Color.web("#cce5ff",1.0));
		HeaderRectangle.setStroke(Color.rgb(225, 147, 21));
		
		
		StackPane stack = new StackPane();
		
		ImageView imageView1 = new ImageView(icon);
		imageView1.setFitWidth(500);
		imageView1.setPreserveRatio(true);
		
		Image image = new Image("FISH2.png");
		ImageView imageView2 = new ImageView(image);
		imageView2.setFitWidth(100);
		imageView2.setPreserveRatio(true);
		
		
		stack.getChildren().addAll(HeaderRectangle, Header);
		
		Button btn1 = new Button("PLAY");
		btn1.setId("custombutton");
		btn1.setPrefWidth(250);
		// btn1.setOnAction(e -> stage.setScene(scene2)); // zmiana sceny przyciskiem!!!!!
		
		EventHandler<ActionEvent> event = e -> {
//                stage.setScene(scene2);
//
//                stage.setMaxWidth(900);
//        		stage.setMaxHeight(900);
//
//        		stage.setMinWidth(500);
//        		stage.setMinHeight(600);
			FishesScreenController fsc = new FishesScreenController();
			try {
				fsc.switchScene(e);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		};

        btn1.setOnAction(event);

        
		VBox TopBox = new VBox();
		HBox BottomBox = new HBox();
		
		TopBox.getChildren().add(stack);
		TopBox.getChildren().add(imageView1);
		
		BottomBox.getChildren().add(btn1);
		BottomBox.getChildren().add(imageView2);
		
		BottomBox.setSpacing(30);
		
		root1.getChildren().add(TopBox);
		AnchorPane.setTopAnchor(TopBox, 10.0);
		root1.getChildren().add(BottomBox);
		AnchorPane.setBottomAnchor(BottomBox, 100.0);
		
		stackPane.getChildren().add(root1);
		StackPane.setAlignment(root1, Pos.CENTER);
		
		TopBox.setAlignment(Pos.TOP_CENTER);
		TopBox.prefWidthProperty().bind(stackPane.widthProperty());
		BottomBox.setAlignment(Pos.BOTTOM_CENTER);
		BottomBox.prefWidthProperty().bind(stackPane.widthProperty());
		
	
// FILL IN THE GAME SCENE - tutaj pisz Bartek swoją scene, ja dałem przykładowo prostokąt, żeby
		// zobaczyć, czy działa
		
//		Rectangle rect = new Rectangle(50, 50);
//		root2.getChildren().add(rect);
		
		
		
		/////////////
		
		stage.setScene(scene1);
		stage.show();

	}

}
