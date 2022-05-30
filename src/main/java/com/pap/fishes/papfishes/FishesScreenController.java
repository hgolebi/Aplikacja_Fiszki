package com.pap.fishes.papfishes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
    @FXML
    Button right_swipe_button;
    @FXML
    Button left_swipe_button;
    @FXML
    CheckBox need_repeat_checkbox;
    @FXML
    Button repeat_button;
    @FXML
    Button back_button;

    private Stage stage;
    private Scene scene;
    private Parent root;
    Fish currentFish;
    FishList fishList;

    public FishesScreenController(){
        fishList = new FishList(QuerySender.getAllFishes());
        fishList.shuffle();
        currentFish = null;
    }

    public void switchScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fxml/fish.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void displayFishFront(){
        String term;
        String category;
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
        if (fish_button.isSelected())
            fish_button.setSelected(false);
        need_repeat_checkbox.setSelected(currentFish.isRepeat());
    }
    public void displayFishBack(){
        String definition;
        String category;
        if (currentFish == null){
            definition = "BRAK FISZEK";
            category = "Dodaj fiszki uzywajac przycisku DODAJ";
        }
        else {
            definition = currentFish.getDefinition();
            category = currentFish.getCategory();
        }
        term_label.setText(definition);
        category_label.setText(category);
        if (!fish_button.isSelected())
            fish_button.setSelected(true);
        need_repeat_checkbox.setSelected(currentFish.isRepeat());
    }


    public void OnMusicButtonClicked(){
        Main.muteMusic();
    }
    public void OnUczSieButtonClicked(){

        if (!fishList.isEmpty()) {
            currentFish = fishList.getCurrentFish();

        }
        displayFishFront();
        ucz_sie_button.setVisible(false);
        left_swipe_button.setVisible(true);
        right_swipe_button.setVisible(true);
        need_repeat_checkbox.setVisible(true);
        repeat_button.setVisible(true);

    }
    public void OnFishButtonClicked(){
        if (!(currentFish == null)){
            if(!fish_button.isSelected()){
                displayFishFront();
            }
            else {
                displayFishBack();
            }
        }
    }
    public void OnLeftSwipeClicked(){
        currentFish =  fishList.getPreviousFish();
        displayFishFront();
    }
    public void OnRightSwipeClicked(){
        currentFish = fishList.getNextFish();
        displayFishFront();
    }
    public void OnRepeatCheckboxClicked(){

        currentFish.changeRepeat();
        repeat_button.setDisable(!fishList.isAnyRepeat());
    }
    public void OnRepeatButtonClicked(){
        FishList repeatList = fishList.getRepeatList();
        if (repeatList.isEmpty())
            return;

        fishList = fishList.getRepeatList();
        currentFish = fishList.getCurrentFish();
        displayFishFront();
        fishList.setAllRepeat(false);
        need_repeat_checkbox.setSelected(false);
        back_button.setVisible(fishList.hasSourceList());
    }
    public void OnBackButtonClicked(){
        fishList = fishList.getSourceList();
        currentFish = fishList.getCurrentFish();
        displayFishFront();
        fishList.setAllRepeat(false);
        need_repeat_checkbox.setSelected(false);
        back_button.setVisible(fishList.hasSourceList());
    }
}
