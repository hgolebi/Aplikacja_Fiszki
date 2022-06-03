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
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AddController {

    @FXML Label info_text;
    @FXML TextField field_term;
    @FXML TextField field_def;
    @FXML TextField field_cat;


//    public AddController(){
//        fishList = new FishList(QuerySender.getAllFishes());
//        currentFish = null;
//    }
    public void OnAddButtonClicked() {
        info_text.setVisible(true);
        if (field_term.getText() == null || field_term.getText().trim().isEmpty()) {
            info_text.setText("Pole słowa puste");
        } else if (field_def.getText() == null || field_def.getText().trim().isEmpty()) {
            info_text.setText("Pole znaczenia puste");
        } else if (field_cat.getText() == null || field_cat.getText().trim().isEmpty()) {
            info_text.setText("Pole kategorii puste");
        } else {
            QuerySender.addFish(field_term.getText(), field_def.getText(), field_cat.getText());
            info_text.setText("Dodano");
        }
    }

}
