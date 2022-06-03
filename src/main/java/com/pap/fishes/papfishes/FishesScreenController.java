package com.pap.fishes.papfishes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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

    @SuppressWarnings("exports")
	public Stage getStage() {
        return stage;
    }

    public FishesScreenController(){
        fishList = new FishList(QuerySender.getAllFishes());
        currentFish = null;
    }

    public void switchScene(@SuppressWarnings("exports") ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fxml/fish.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMinHeight(950);
        stage.setMinWidth(701);
        stage.show();
    }

    public void initialize() {
        ObservableList<String> all_categories = FXCollections.observableList(QuerySender.getAllCategories());
        if (all_categories.size() > 0) {
            quickCat1.setText(all_categories.get(0));
        }
        if (all_categories.size() > 1) {
            quickCat2.setText(all_categories.get(1));
        }
        if (all_categories.size() > 2) {
            quickCat3.setText(all_categories.get(2));
        }
        OnUczSieButtonClicked();
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
        term_label.setFont(Font.font("System", FontWeight.BOLD, 40));
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
        term_label.setFont(Font.font("System", FontWeight.NORMAL, 30));
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
    public void OnChangeButtonClicked()throws IOException {

        final Stage searchWindow = new Stage();
        searchWindow.initModality(Modality.APPLICATION_MODAL);
        searchWindow.initOwner(stage);
        ObservableList<String> all_categories = FXCollections.observableList(QuerySender.getAllCategories());

        Label cat1 = new Label("1. Kategoria");
        Label cat2 = new Label("2. Kategoria");
        Label cat3 = new Label("3. Kategoria");

        ChoiceBox<String> choice1 = new ChoiceBox<String>(all_categories);
        ChoiceBox<String> choice2 = new ChoiceBox<String>(all_categories);
        ChoiceBox<String> choice3 = new ChoiceBox<String>(all_categories);
        choice1.setValue(quickCat1.getText());
        choice2.setValue(quickCat2.getText());
        choice3.setValue(quickCat3.getText());

        choice1.setOnAction(event -> {
            quickCat1.setText(choice1.getValue());
        });
        choice2.setOnAction(event -> {
            quickCat2.setText(choice2.getValue());
        });
        choice3.setOnAction(event -> {
            quickCat3.setText(choice3.getValue());
        });


        HBox changeHBox = new HBox();
        VBox cat1VBox = new VBox();
        VBox cat2VBox = new VBox();
        VBox cat3VBox = new VBox();

        cat1VBox.getChildren().add(cat1);
        cat1VBox.getChildren().add(choice1);
        changeHBox.getChildren().add(cat1VBox);
        cat2VBox.getChildren().add(cat2);
        cat2VBox.getChildren().add(choice2);
        changeHBox.getChildren().add(cat2VBox);
        cat3VBox.getChildren().add(cat3);
        cat3VBox.getChildren().add(choice3);
        changeHBox.getChildren().add(cat3VBox);

        changeHBox.setAlignment(Pos.CENTER);
        Scene searchScene = new Scene(changeHBox, 400, 100);
        searchScene.getStylesheets().add(getClass().getResource("/fish.css").toExternalForm());
        searchWindow.setScene(searchScene);
        searchWindow.show();
    }
    public void OnQuick1() {
        if (!quickCat1.getText().equals("Kategoria 1")) {
            fishList = new FishList(QuerySender.getFishesFromCategory(quickCat1.getText()));
            currentFish = fishList.getCurrentFish();
            displayFishFront();
        }
    }
    public void OnQuick2() {
        if (!quickCat2.getText().equals("Kategoria 2")) {
            fishList = new FishList(QuerySender.getFishesFromCategory(quickCat2.getText()));
            currentFish = fishList.getCurrentFish();
            displayFishFront();
        }
    }
    public void OnQuick3() {
        if (!quickCat3.getText().equals("Kategoria 3")) {
            fishList = new FishList(QuerySender.getFishesFromCategory(quickCat3.getText()));
            currentFish = fishList.getCurrentFish();
            displayFishFront();
        }
    }
    public void OnAll() {
        fishList = new FishList(QuerySender.getAllFishes());
        currentFish = fishList.getCurrentFish();
        displayFishFront();
    }
}
