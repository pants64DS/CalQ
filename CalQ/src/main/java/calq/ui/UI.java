package calq.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;


import calq.evaluator.Evaluator;

public class UI extends Application {
    @Override
    public void start(Stage stage) {
        var resultLabel = new Label();
        var textField = new TextField();
        
        resultLabel.setFont(Font.font("Consolas", 28));
        textField.setFont(Font.font("Consolas", 24));
        
        textField.setPrefWidth(500);
        textField.setOnAction((event) -> {
            resultLabel.setText(Evaluator.evaluate(textField.getText()));
        });
        
        var bottomPane = new BorderPane();
        bottomPane.setCenter(resultLabel);
        
        var mainPane = new BorderPane();
        mainPane.setTop(textField);
        mainPane.setLeft(bottomPane);
        
        stage.setScene(new Scene(mainPane, 640, 200));
        stage.setTitle("CalQ");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}