package com.pap.fishes.papfishes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Vector;

public class FishesScreenController {

    @FXML
    Label term_label;
    @FXML
    Label category_label;
    @FXML
    Button ucz_sie_button;
    @FXML
    ToggleButton fish_button;
    private Stage stage;
    private Scene scene;
    private Parent root;
    Fish currentFish;
    Vector<Fish> fishList;

    public FishesScreenController(){
        fishList = QuerySender.getAllFishes();

    }

    public void switchScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fxml/fish.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void OnMusicButtonClicked(){
        Main.muteMusic();
    }
    public void OnUczSieButtonClicked(){
        String term;
        String category;
        if (!fishList.isEmpty()) {
            currentFish = fishList.get(0);
        }
        else
            currentFish = null;

        if (currentFish == null){
            term = "BRAK FISZEK";
            category = "Dodaj fiszki uzywajac przycisku DODAJ";
        }
        else {
            term = currentFish.getTerm();
            category = currentFish.getCategory();
        }
        term_label.setText(term);
        category_label.setText(category);
//        ucz_sie_button.cancelButtonProperty();
        ucz_sie_button.setVisible(false);
    }
    public void OnFishButtonClicked(){
        if (!(currentFish == null)){
            if(fish_button.isSelected()){
                String term = currentFish.getTerm();
                term_label.setText(term);
            }
            else {
                String definition = currentFish.getDefinition();
                term_label.setText(definition);
            }
        }
    }
}
