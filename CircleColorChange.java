import org.w3c.dom.events.MouseEvent;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CircleColorChange extends Application {

    @Override
    public void start(Stage primaryStage) {
        Circle circle = new Circle(50); // Circle with radius 50
        circle.setFill(Color.WHITE); // Initial color is white

        // Change the circle color to black when mouse is pressed
        circle.setOnMousePressed((MouseEvent e) -> circle.setFill(Color.BLACK));

        // Change the circle color to white when mouse is released
        circle.setOnMouseReleased((MouseEvent e) -> circle.setFill(Color.WHITE));

        StackPane root = new StackPane(circle); // StackPane to center the circle

        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("Circle Color Change");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
