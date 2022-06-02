package com.pap.fishes.papfishes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

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
    @FXML
    Button shuffle_button;
    @FXML
    Button wyszukaj_button;
    @FXML
    Label no_fishes_found_label;
    @FXML
    TextField search_text_field;
    @FXML
    Button quickCat1;
    @FXML
    Button quickCat2;
    @FXML
    Button quickCat3;


    private Stage stage;
    private Scene scene;
    private Parent root;
    Fish currentFish;
    FishList fishList;
    Vector<String> currentCategories;

    public Stage getStage() {
        return stage;
    }

    public FishesScreenController(){
        fishList = new FishList(QuerySender.getAllFishes());
        currentFish = null;
    }

    public void switchScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fxml/fish.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMinHeight(950);
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
        shuffle_button.setVisible(true);

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
    public void OnShuffleButtonClicked(){
        fishList.shuffle();
        currentFish = fishList.getCurrentFish();
        displayFishFront();
    }
    public void OnSearchButtonClicked() throws IOException {

        final Stage searchWindow = new Stage();
        searchWindow.initModality(Modality.APPLICATION_MODAL);
        searchWindow.initOwner(stage);
        TextField textField = new TextField();
        Label noFishesFoundLabel = new Label("Nie znaleziono żadnych fiszek");
        noFishesFoundLabel.setTextFill(Paint.valueOf("#f80000"));
        noFishesFoundLabel.setVisible(false);
        Button button = new Button("WYSZUKAJ");
        button.setId("menu");
        button.setOnAction(event -> {
            String text = textField.getText();
            if (text == ""){
                noFishesFoundLabel.setVisible(true);
                return;
            }
            Vector<Fish> allFishes = QuerySender.findFishByTerm(text);
            noFishesFoundLabel.setVisible(allFishes.isEmpty());
            if (!allFishes.isEmpty()){
                fishList = new FishList(allFishes);
                currentFish = fishList.getCurrentFish();
                displayFishFront();
                searchWindow.close();
            }
        });
        VBox searchVbox = new VBox();
        searchVbox.getChildren().add(textField);
        searchVbox.getChildren().add(noFishesFoundLabel);
        searchVbox.getChildren().add(button);
        searchVbox.setAlignment(Pos.CENTER);
        Scene searchScene = new Scene(searchVbox, 400, 100);
        searchScene.getStylesheets().add(getClass().getResource("/fish.css").toExternalForm());
        searchWindow.setScene(searchScene);
        searchWindow.show();
    }
    public void OnWyszukajButtonClicked() {


    }
    public void OnEditButtonClicked(){
        try {
            final Stage searchWindow = new Stage();
            searchWindow.initModality(Modality.APPLICATION_MODAL);
            searchWindow.initOwner(stage);
            VBox searchVbox = FXMLLoader.load(getClass().getResource("/fxml/fish_edit.fxml"));
            Scene dialogScene = new Scene(searchVbox, 300, 200);
            searchWindow.setScene(dialogScene);
            searchWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void OnAddButtonClicked(){
        try {
            final Stage searchWindow = new Stage();
            searchWindow.initModality(Modality.APPLICATION_MODAL);
            searchWindow.initOwner(stage);
            VBox searchVbox = FXMLLoader.load(getClass().getResource("/fxml/fish_add.fxml"));
            Scene dialogScene = new Scene(searchVbox, 300, 200);
            searchWindow.setScene(dialogScene);
            searchWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
