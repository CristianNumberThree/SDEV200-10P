import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FlagGrid extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a GridPane layout
        GridPane gridPane = new GridPane();

        // Load and add the images to the grid
        ImageView flag1 = new ImageView(new Image("flag1.gif"));
        ImageView flag2 = new ImageView(new Image("flag2.gif"));
        
        // Load flag6 and scale it
        Image flag6Image = new Image("flag6.gif");
        ImageView flag6 = new ImageView(flag6Image);
        flag6.setFitWidth(412);  // Set width
        flag6.setFitHeight(217); // Set height
        flag6.setPreserveRatio(true);

        // Load flag7 and scale it
        Image flag7Image = new Image("flag7.gif");
        ImageView flag7 = new ImageView(flag7Image);
        flag7.setFitWidth(417);  // Set width
        flag7.setFitHeight(217); // Set height
        flag7.setPreserveRatio(true);

        // Add the images to the grid
        gridPane.add(flag1, 0, 0);  // First image in row 0, column 0
        gridPane.add(flag2, 1, 0);  // Second image in row 0, column 1
        gridPane.add(flag6, 0, 1);  // Third image in row 1, column 0
        gridPane.add(flag7, 1, 1);  // Fourth image in row 1, column 1

        // Create a Scene and set it on the primary stage
        Scene scene = new Scene(gridPane, 855, 480);  // Scene size matches the window size
        primaryStage.setScene(scene);

        // Set the title of the window
        primaryStage.setTitle("Flag Grid");

        // Show the stage (window)
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}
