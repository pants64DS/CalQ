package calq.calq;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        var resultLabel = new Label();
        var textField = new TextField();
        textField.setPrefWidth(500);
        textField.setOnAction((event) -> {
            resultLabel.setText(Evaluator.evaluate(textField.getText()));
        });
        
        var bottomPane = new BorderPane();
        bottomPane.setLeft(textField);
        bottomPane.setRight(resultLabel);
        var mainPane = new BorderPane();
        mainPane.setBottom(bottomPane);
        
        stage.setScene(new Scene(mainPane, 640, 480));
        stage.setTitle("CalQ");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}