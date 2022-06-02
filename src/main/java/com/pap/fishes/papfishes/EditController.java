package com.pap.fishes.papfishes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditController {

    @FXML
    Label info_text;
    @FXML
    TextField field_term;
    @FXML
    TextField field_cat;
    @FXML
    TextField field_new;
    @FXML
    ChoiceBox newColSelect;

    ObservableList<String> choices = FXCollections.observableArrayList("Słowo", "Znaczenie", "Kategoria");

    @FXML
    private void initialize() {
        newColSelect.setValue("Słowo");
        newColSelect.setItems(choices);
    }

    public void OnEditButtonClicked() {
        info_text.setVisible(true);
        if (field_term.getText() == null || field_term.getText().trim().isEmpty()) {
            info_text.setText("Pole słowa puste");
        } else if (field_cat.getText() == null || field_cat.getText().trim().isEmpty()) {
            info_text.setText("Pole kategorii puste");
        } else if (field_new.getText() == null || field_new.getText().trim().isEmpty()) {
            info_text.setText("Pole zmiany puste");
        } else {
            switch (newColSelect.getValue().toString()) {
                case "Słowo":
                    QuerySender.editFishTerm(field_term.getText(), field_cat.getText(), field_new.getText());
                    break;
                case "Znaczenie":
                    QuerySender.editFishDef(field_term.getText(), field_cat.getText(), field_new.getText());
                    break;
                case "Kategoria":
                    QuerySender.editFishCat(field_term.getText(), field_cat.getText(), field_new.getText());
                    break;
            }
            info_text.setText("Zmieniono");
        }
    }
}
