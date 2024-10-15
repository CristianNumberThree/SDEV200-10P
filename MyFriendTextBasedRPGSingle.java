package javafxapplication1;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Modality;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static javafx.application.Application.launch;

public class MyFriendTextBasedRPGSingle extends Application {

    // List to store selected items
    private List<String> food = new ArrayList<>();
    private List<String> eaten = new ArrayList<>();
    private Text messageInSecondWindow = new Text("Good morning friend (says the unseen thing in the shadowy corner of the room) I was waiting for you to wake up shall we get going?");
    private String locationVariable = "Bedroom"; // Default location is set to "Bedroom"

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

        // Button 1: Add items based on location
        btnSelectRandomNumber.setOnAction(e -> {
            selectRandomNumber();
            checkFoodListSize(btnSelectRandomNumber, btnOpenLocationWindow, btnShowStatus, btnClose); // Check list size after adding item
        });

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

    // Method to add items based on the current location
    private void selectRandomNumber() {
        Random rand = new Random();
        String item = "";

        switch (locationVariable) {
            case "Bedroom":
                item = rand.nextBoolean() ? "Rotten Food" : "Trash";
                break;
            case "Bathroom":
                item = rand.nextBoolean() ? "Golden Egg" : "Trash";
                break;
            case "Kitchen":
                item = rand.nextBoolean() ? "Fresh Food" : "Rotten Food";
                break;
            case "Livingroom":
                item = rand.nextBoolean() ? "Strange Meat" : "Trash";
                break;
            case "Basement":
                item = "Strange Meat";
                break;
            default:
                item = "Unknown"; // Optional handling for any other locations
        }

        // Add the item to the food list
        food.add(item);

        // Update the message in the second window
        messageInSecondWindow.setText("You found: " + item);
    }

    // Method to check the size of the food list and disable buttons if the list has 10 items
    private void checkFoodListSize(Button btnSelectRandomNumber, Button btnOpenLocationWindow, Button btnShowStatus, Button btnClose) {
        if (food.size() >= 10) {
            btnSelectRandomNumber.setDisable(true);
            btnOpenLocationWindow.setDisable(true);
            btnShowStatus.setDisable(true);
            btnClose.setDisable(true);
        }
    }

    // Method to open the third window with dropdown and buttons
    private void openThirdWindow(Stage secondWindow) {
        Stage thirdWindow = new Stage();
        ComboBox<String> dropdown = new ComboBox<>();
        dropdown.getItems().addAll(food); // Populate dropdown with items from the food list

        Button btnCloseThirdWindow = new Button("Nevermind");
        Button btnConfirmSelection = new Button("Confirm Selection");

        // Button 1: Close third window
        btnCloseThirdWindow.setOnAction(e -> thirdWindow.close());

        // Button 2: Confirm the dropdown selection, update second window, and remove selected item from list
        btnConfirmSelection.setOnAction(e -> {
            String selected = dropdown.getValue();
            if (selected != null) {
                // Update the second window's message
                messageInSecondWindow.setText("Friend has been fed");

                // Remove the selected item from the food list and add to the eaten list
                food.remove(selected);
                eaten.add(selected);

                // Check if eaten has 3 items
                if (eaten.size() == 3) {
                    handleEatenItems();
                }

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

    // Method to handle the eaten items and display appropriate error message and image
    private void handleEatenItems() {
        long trashCount = eaten.stream().filter(item -> item.equals("Trash")).count();
        long rottenFoodCount = eaten.stream().filter(item -> item.equals("Rotten Food")).count();
        long goldenEggCount = eaten.stream().filter(item -> item.equals("Golden Egg")).count();
        long freshFoodCount = eaten.stream().filter(item -> item.equals("Fresh Food")).count();
        long strangeMeatCount = eaten.stream().filter(item -> item.equals("Strange Meat")).count();

        if (trashCount >= 2) {
            showErrorMessage("Hello friend I am the trash man");
        } else if (rottenFoodCount >= 2) {
            showErrorMessage("Run its the appetizer");
        } else if (goldenEggCount >= 2) {
            openLink("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        } else if (freshFoodCount >= 2) {
            showErrorMessage("So full so demure");
        } else if (strangeMeatCount >= 2) {
            showErrorMessage("iM hErE FoR YoU");
        }

        // Close the program after displaying the error message
        System.exit(0);
    }

    // Method to display an error message with an image
    private void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.APPLICATION_MODAL); // Prevent interaction with other windows
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait(); // Block until the alert is closed
    }

    // Method to open a link in the default browser
    private void openLink(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    // Method to open the location window
    private void openLocationWindow() {
        Stage locationWindow = new Stage();
        ComboBox<String> locationDropdown = new ComboBox<>();
        locationDropdown.getItems().addAll("Bedroom", "Bathroom", "Kitchen", "Livingroom", "Basement");

        Button btnConfirmLocation = new Button("Confirm Location");
        btnConfirmLocation.setOnAction(e -> {
            locationVariable = locationDropdown.getValue();
            messageInSecondWindow.setText("You moved to: " + locationVariable);
            locationWindow.close();
        });

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(locationDropdown, btnConfirmLocation);

        Scene locationScene = new Scene(layout, 250, 150);
        locationWindow.setScene(locationScene);
        locationWindow.setTitle("Select Location");
        locationWindow.show();
    }

    // Method to show the inventory (food list)
    private void showStatusWindow() {
        Stage statusWindow = new Stage();
        Text statusText = new Text("Inventory: " + food.toString());
        Button btnCloseStatus = new Button("Close");

        btnCloseStatus.setOnAction(e -> statusWindow.close());

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(statusText, btnCloseStatus);

        Scene statusScene = new Scene(layout, 300, 150);
        statusWindow.setScene(statusScene);
        statusWindow.setTitle("Status");
        statusWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
