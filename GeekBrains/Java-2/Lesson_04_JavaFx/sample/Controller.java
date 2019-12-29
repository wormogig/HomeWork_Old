package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.HashSet;

public class Controller {

    private boolean inChat = false;
    private String name;

    @FXML
    Button btn1;

    @FXML
    Button btnEnt;

    @FXML
    javafx.scene.control.TextArea textArea;

    @FXML
    javafx.scene.control.TextArea textAreaNames;

    @FXML
    TextField textField;


    private HashSet<String> names = new HashSet<>();

    public void sendMsg() {
        if (inChat) {
            textArea.appendText(name + ": " + textField.getText() + "\n");
            textField.clear();
            textField.requestFocus();

        } else {
            entExit();
        }

    }

    public void entExit() {

        btn1.setDisable(inChat);
        btnEnt.setText(inChat ? "Войти" : "Выйти");
        textField.setPromptText(inChat ? "Введите имя..." : "Введите сообщение...");
        if (!inChat) {
            name = textField.getText();
            textArea.appendText("<<<В чат пришел " + name + ">>>\n");
            names.add(name);
            printNames();
        } else {
            names.remove(name);
            textArea.appendText("<<<"+name +" вышел из чата>>> \n");
            printNames();
        }
        inChat = !inChat;
        textField.clear();

    }

    public void printNames () {
        textAreaNames.clear();
        if (names.isEmpty()) {
            textAreaNames.appendText("В чате никого нет" + "\n");
        } else {
            textAreaNames.appendText("Сейчас в чате:" + "\n");
            for (String name : names) {
                textAreaNames.appendText(name + "\n");
            }
        }
    }
}
