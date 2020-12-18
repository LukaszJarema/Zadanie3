package kolkoiKrzyzyk;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button button00;

    @FXML
    private Button button10;

    @FXML
    private Button button20;

    @FXML
    private Button button01;

    @FXML
    private Button button11;

    @FXML
    private Button button21;

    @FXML
    private Button button02;

    @FXML
    private Button button12;

    @FXML
    private Button button22;

    @FXML
    private Button mainButton;

    @FXML
    private Label label;

    @FXML
    void button00Action(ActionEvent event) {

    }

    @FXML
    void button01Action(ActionEvent event) {

    }

    @FXML
    void button02Action(ActionEvent event) {

    }

    @FXML
    void button10Action(ActionEvent event) {

    }

    @FXML
    void button11Action(ActionEvent event) {

    }

    @FXML
    void button12Action(ActionEvent event) {

    }

    @FXML
    void button20Action(ActionEvent event) {

    }

    @FXML
    void button21Action(ActionEvent event) {

    }

    @FXML
    void button22Action(ActionEvent event) {

    }

    @FXML
    void mainButtonAction(ActionEvent event) {

    }

    private Gameplay gameplay;
    private Button [] [] buttons;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        gameplay = new Gameplay();
        buttons = new Button[][] { {button00, button10, button20}, {button01, button11, button21}, {button02, button12, button22}};
        addButtonAction();
    }

    private void newGame(){
        gameplay = new Gameplay();
        for(int i = 0; i < buttons.length; i++) {
            for(int j = 0; j < buttons[i].length; j++) {
                buttons[i] [j].setText("");
            }
        }
    }

    private void addButtonAction(){
        for(int i = 0; i < buttons.length; i++){
            for(int j = 0; j < buttons[i].length; j++){
                final int x = i, y = j;
                buttons[i] [j].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String buttonText = buttons[x] [y].getText();
                        if("".equals(buttonText)){
                            gameplay.setValue(x, y, gameplay.getActivePlayer());
                            updateView();
                            gameplay.switchPlayer();
                        } if(gameplay.getWinner() != Gameplay.BLANK){
                            showWinnerPopup();
                        }
                    }
                });
            }
        }
    }

    private void updateView(){
        for(int i = 0; i < Gameplay.SIZE; i++){
            for(int j = 0; j < Gameplay.SIZE; j++){
                if(gameplay.getValue(i, j) == Gameplay.X){
                    buttons[i] [j].setText("X");
                } else if(gameplay.getValue(i, j) == Gameplay.O){
                    buttons[i] [j].setText("O");
                }
            }
        }
        mainButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newGame();
            }
        });
    }

    private void showWinnerPopup(){
        String winner = null;
        if(gameplay.getWinner() == Gameplay.X){
            winner = "X";
        } else if(gameplay.getWinner() == Gameplay.O){
            winner = "O";
        }
        String winnerText = "Wygrywa: " + winner;
        Popup popup = createPopup(winnerText);
        popup.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newGame();
            }
        });
    }

    private Popup createPopup(String text){
        TextArea popupText = new TextArea(text);
        popupText.setPrefWidth(200);
        popupText.setPrefHeight(100);
        popupText.setEditable(false);

        Popup popup = new Popup();
        popup.setAutoFix(true);
        popup.getContent().addAll(popupText);

        popup.show(mainButton.getScene().getWindow());
        popup.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newGame();
                popup.hide();
            }
        });
        return popup;
    }
}
