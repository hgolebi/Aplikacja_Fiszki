package com.pap.fishes.papfishes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FishesScreenController {
    @FXML
    private Label Title;

    @FXML
    protected void OnMusicButtonClicked() {
        Title.setText("Dupa");
    }
}
