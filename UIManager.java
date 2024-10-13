import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import java.util.List;

public class UIManager {

    public static void openSecondWindow(GameController gameController) {
        Stage secondWindow = new Stage();

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Button btnSelectRandom = new Button("Search the room");
        Button btnFeedFriend = new Button("Feed your friend");
        Button btnChangeLocation = new Button("Go somewhere else");
        Button btnShowStatus = new Button("Inventory");
        Button btnClose = new Button("Go back to sleep");

        btnSelectRandom.setOnAction(e -> gameController.addRandomItem());
        btnFeedFriend.setOnAction(e -> openThirdWindow(gameController));
        btnChangeLocation.setOnAction(e -> openLocationWindow(gameController));
        btnShowStatus.setOnAction(e -> showStatusWindow(gameController));

        layout.getChildren().addAll(gameController.getMessage(), btnSelectRandom, 
                                    btnFeedFriend, btnChangeLocation, btnShowStatus, btnClose);

        secondWindow.setScene(new Scene(layout, 750, 250));
        secondWindow.setTitle("My Friend");
        secondWindow.show();
    }

    public static void openThirdWindow(GameController gameController) {
        Stage thirdWindow = new Stage();
        ComboBox<String> dropdown = new ComboBox<>();
        dropdown.getItems().addAll(gameController.getFoodList());

        Button btnConfirm = new Button("Confirm");
        btnConfirm.setOnAction(e -> {
            String selectedFood = dropdown.getValue();
            if (selectedFood != null) {
                gameController.feedFriend(selectedFood);
                dropdown.getItems().setAll(gameController.getFoodList());
            }
            thirdWindow.close();
        });

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(dropdown, btnConfirm);

        thirdWindow.setScene(new Scene(layout, 250, 150));
        thirdWindow.setTitle("Feed Friend");
        thirdWindow.show();
    }

    public static void openLocationWindow(GameController gameController) {
        Stage locationWindow = new Stage();
        ComboBox<String> locationDropdown = new ComboBox<>();
        locationDropdown.getItems().addAll("Bedroom", "Bathroom", "Kitchen", "Livingroom", "Basement");

        Button btnConfirm = new Button("Confirm");
        btnConfirm.setOnAction(e -> {
            String selectedLocation = locationDropdown.getValue();
            if (selectedLocation != null) {
                gameController.setLocation(selectedLocation);
            }
            locationWindow.close();
        });

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(locationDropdown, btnConfirm);

        locationWindow.setScene(new Scene(layout, 250, 150));
        locationWindow.setTitle("Select Location");
        locationWindow.show();
    }

    public static void showStatusWindow(GameController gameController) {
        Stage statusWindow = new Stage();
        Text statusText = new Text("Inventory: " + gameController.getFoodList());

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(statusText, new Button("Close"));

        statusWindow.setScene(new Scene(layout, 300, 150));
        statusWindow.setTitle("Inventory");
        statusWindow.show();
    }

    public static void showErrorMessage(String message, String imageFile) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
