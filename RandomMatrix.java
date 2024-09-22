import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.stage.Stage;

import java.util.Random;

public class RandomMatrix extends Application {

    private static final int SIZE = 10;

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        Random random = new Random();

        // Loop to create the 10x10 grid of TextFields
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                TextField textField = new TextField();
                textField.setPrefColumnCount(1);
                textField.setText(String.valueOf(random.nextInt(2))); // Random 0 or 1
                textField.setAlignment(Pos.CENTER); // Center the text
                textField.setEditable(false); // Make the TextField non-editable
                gridPane.add(textField, j, i);
            }
        }

        Scene scene = new Scene(gridPane, 300, 300);
        primaryStage.setTitle("Random 10x10 Matrix");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
