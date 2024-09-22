import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class TextColorSlider extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create label to display the text
        Label textLabel = new Label("Dynamic Color Text");
        textLabel.setStyle("-fx-font-size: 24px;");

        // Create sliders for red, green, blue, and opacity
        Slider redSlider = createColorSlider();
        Slider greenSlider = createColorSlider();
        Slider blueSlider = createColorSlider();
        Slider opacitySlider = createColorSlider();
        
        opacitySlider.setValue(100); // Default to fully opaque
        
        // Label sliders
        Label redLabel = new Label("Red");
        Label greenLabel = new Label("Green");
        Label blueLabel = new Label("Blue");
        Label opacityLabel = new Label("Opacity");

        // GridPane to organize the sliders and labels
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        // Add labels and sliders to the grid
        gridPane.add(redLabel, 0, 0);
        gridPane.add(redSlider, 1, 0);
        gridPane.add(greenLabel, 0, 1);
        gridPane.add(greenSlider, 1, 1);
        gridPane.add(blueLabel, 0, 2);
        gridPane.add(blueSlider, 1, 2);
        gridPane.add(opacityLabel, 0, 3);
        gridPane.add(opacitySlider, 1, 3);

        // VBox to organize the text and the sliders
        VBox root = new VBox(20, textLabel, gridPane);
        root.setAlignment(Pos.CENTER);

        // Event listener for color change
        redSlider.valueProperty().addListener((obs, oldVal, newVal) -> updateTextColor(textLabel, redSlider, greenSlider, blueSlider, opacitySlider));
        greenSlider.valueProperty().addListener((obs, oldVal, newVal) -> updateTextColor(textLabel, redSlider, greenSlider, blueSlider, opacitySlider));
        blueSlider.valueProperty().addListener((obs, oldVal, newVal) -> updateTextColor(textLabel, redSlider, greenSlider, blueSlider, opacitySlider));
        opacitySlider.valueProperty().addListener((obs, oldVal, newVal) -> updateTextColor(textLabel, redSlider, greenSlider, blueSlider, opacitySlider));

        // Set up the scene
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Text Color Slider");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper method to create sliders
    private Slider createColorSlider() {
        Slider slider = new Slider(0, 255, 0); // Range 0-255
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        return slider;
    }

    // Method to update the text color based on slider values
    private void updateTextColor(Label textLabel, Slider red, Slider green, Slider blue, Slider opacity) {
        Color color = Color.rgb(
            (int) red.getValue(),
            (int) green.getValue(),
            (int) blue.getValue(),
            opacity.getValue() / 100.0 // Opacity is a percentage
        );
        textLabel.setTextFill(color);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
