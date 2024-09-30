import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyFriendTextBasedRPG extends Application {

    // List to store selected random numbers
    private List<Integer> food = new ArrayList<>();
    private Text messageInSecondWindow = new Text("Good morning friend (says the unseen thing in the shadowy corner of the room) I was waiting for you to wake up shall we get going?");
    private String locationVariable = "Not Selected"; // Variable to store the location choice

    @Override
    public void start(Stage primaryStage) {
        // Main window elements
        Text message = new Text("You are currently sleeping");
        Button btnOpenWindow = new Button("Wake up");
        Button btnClose = new Button("Keep Sleeping");

        // Button actions
        btnOpenWindow.setOnAction(e -> openSecondWindow());
        btnClose.setOnAction(e -> primaryStage.close());

        // Layout for the main window
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER); // Centering all elements
        layout.getChildren().addAll(message, btnOpenWindow, btnClose);

        // Main window scene
        Scene scene = new Scene(layout, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dreaming");
        primaryStage.show();
    }

    // Method to open the second window
    private void openSecondWindow() {
        Stage secondWindow = new Stage();
        Button btnSelectRandomNumber = new Button("Search the room");
        Button btnOpenThirdWindow = new Button("Feed your friend");
        Button btnOpenLocationWindow = new Button("Go somewhere else");
        Button btnShowStatus = new Button("Inventory");
        Button btnClose = new Button("Go back to sleep");

        // Button 1: Select a random number and display it
        btnSelectRandomNumber.setOnAction(e -> selectRandomNumber());

        // Button 2: Open the third window
        btnOpenThirdWindow.setOnAction(e -> openThirdWindow(secondWindow));

        // Button 4: Open the location window
        btnOpenLocationWindow.setOnAction(e -> openLocationWindow());

        // Button 5: Show the status window
        btnShowStatus.setOnAction(e -> showStatusWindow());

        // Button 3: Close the second window
        btnClose.setOnAction(e -> secondWindow.close());

        // Layout for the second window
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER); // Centering content
        layout.getChildren().addAll(messageInSecondWindow, btnSelectRandomNumber, btnOpenThirdWindow, btnOpenLocationWindow, btnShowStatus, btnClose);

        Scene secondScene = new Scene(layout, 750, 250);
        secondWindow.setScene(secondScene);
        secondWindow.setTitle("My Friend");
        secondWindow.show();
    }

    // Method to select a random number and store it in the food list
    private void selectRandomNumber() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(10) + 1; // Random number between 1 and 10
        food.add(randomNumber);
        messageInSecondWindow.setText("You found " + randomNumber);
    }

    // Method to open the third window with dropdown and buttons
    private void openThirdWindow(Stage secondWindow) {
        Stage thirdWindow = new Stage();
        ComboBox<Integer> dropdown = new ComboBox<>();
        dropdown.getItems().addAll(food); // Populate dropdown with numbers from the food list

        Button btnCloseThirdWindow = new Button("Nevermind");
        Button btnConfirmSelection = new Button("Confirm Selection");

        // Button 1: Close third window
        btnCloseThirdWindow.setOnAction(e -> thirdWindow.close());

        // Button 2: Confirm the dropdown selection, update second window, and remove selected number from list
        btnConfirmSelection.setOnAction(e -> {
            Integer selected = dropdown.getValue();
            if (selected != null) {
                // Update the second window's message
                messageInSecondWindow.setText("Friend has been fed");

                // Remove the selected number from the food list
                food.remove(selected);

                // Update the dropdown list
                dropdown.getItems().clear();
                dropdown.getItems().addAll(food);
            }
            thirdWindow.close();
        });

        // Layout for the third window
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER); // Centering content
        layout.getChildren().addAll(dropdown, btnConfirmSelection, btnCloseThirdWindow);

        Scene thirdScene = new Scene(layout, 250, 150);
        thirdWindow.setScene(thirdScene);
        thirdWindow.setTitle("Available food");
        thirdWindow.show();
    }

    // Method to open the location window
    private void openLocationWindow() {
        Stage locationWindow = new Stage();
        ComboBox<String> locationDropdown = new ComboBox<>();
        locationDropdown.getItems().addAll("Bedroom", "Bathroom", "Livingroom", "Kitchen", "Basement");

        Button btnConfirmLocation = new Button("Lets go");

        // Button: Confirm the selected location and close the window
        btnConfirmLocation.setOnAction(e -> {
            String selectedLocation = locationDropdown.getValue();
            if (selectedLocation != null) {
                locationVariable = selectedLocation; // Store the selected location
                locationWindow.close();
                // Update the message in the second window
                messageInSecondWindow.setText("You make your way to the " + locationVariable);

                locationWindow.close();
            }
        });

        // Layout for the location window
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(locationDropdown, btnConfirmLocation);

        Scene locationScene = new Scene(layout, 200, 150);
        locationWindow.setScene(locationScene);
        locationWindow.setTitle("Location Select");
        locationWindow.show();
    }

    // Method to show the status window
    private void showStatusWindow() {
        Stage statusWindow = new Stage();
        String foodList = food.isEmpty() ? "No food selected" : food.toString();
        String statusMessage = "Available Food: " + foodList + "\nLocation: " + locationVariable;

        Text statusText = new Text(statusMessage);

        Button btnCloseStatus = new Button("Close Status Window");
        btnCloseStatus.setOnAction(e -> statusWindow.close());

        // Layout for the status window
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(statusText, btnCloseStatus);

        Scene statusScene = new Scene(layout, 300, 200);
        statusWindow.setScene(statusScene);
        statusWindow.setTitle("Status Window");
        statusWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
