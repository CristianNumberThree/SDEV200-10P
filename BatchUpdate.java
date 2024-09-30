import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;

public class BatchUpdate extends Application {
    private Connection connection;

    @Override
    public void start(Stage primaryStage) {
        // Create Connect button
        Button btnConnect = new Button("Connect to Database");
        Button btnInsertWithoutBatch = new Button("Insert 1000 Records (Without Batch)");
        Button btnInsertWithBatch = new Button("Insert 1000 Records (With Batch)");

        // Disable insert buttons until connection is established
        btnInsertWithoutBatch.setDisable(true);
        btnInsertWithBatch.setDisable(true);

        // Connect button action to open DBConnectionPane in a dialog
        btnConnect.setOnAction(e -> showDBConnectionDialog(primaryStage, btnInsertWithoutBatch, btnInsertWithBatch));

        // Insert records without batch
        btnInsertWithoutBatch.setOnAction(e -> insertRecords(false));

        // Insert records with batch
        btnInsertWithBatch.setOnAction(e -> insertRecords(true));

        // Layout
        VBox vBox = new VBox(10, btnConnect, btnInsertWithoutBatch, btnInsertWithBatch);
        vBox.setPadding(new javafx.geometry.Insets(10));

        // Scene and stage setup
        Scene scene = new Scene(vBox, 300, 150);
        primaryStage.setTitle("Insert Performance Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showDBConnectionDialog(Stage primaryStage, Button btnInsertWithoutBatch, Button btnInsertWithBatch) {
        // Create dialog window for DB connection
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);

        // Create DBConnectionPane
        DBConnectionPane dbConnectionPane = new DBConnectionPane();
        Button btnConnect = new Button("Connect");
        btnConnect.setOnAction(e -> {
            connection = dbConnectionPane.getConnection();
            if (connection != null) {
                showAlert(AlertType.INFORMATION, "Connection Successful", "Connected to the database.");
                btnInsertWithoutBatch.setDisable(false);
                btnInsertWithBatch.setDisable(false);
                dialog.close();
            } else {
                showAlert(AlertType.ERROR, "Connection Failed", "Could not connect to the database.");
            }
        });

        VBox vbox = new VBox(10, dbConnectionPane, btnConnect);
        Scene dialogScene = new Scene(vbox, 400, 250);
        dialog.setScene(dialogScene);
        dialog.setTitle("Database Connection");
        dialog.show();
    }

    private void insertRecords(boolean useBatch) {
        String sql = "INSERT INTO Temp (num1, num2, num3) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            Instant start = Instant.now();

            for (int i = 0; i < 1000; i++) {
                pstmt.setDouble(1, Math.random());
                pstmt.setDouble(2, Math.random());
                pstmt.setDouble(3, Math.random());

                if (useBatch) {
                    pstmt.addBatch();
                    if (i % 100 == 0) { // Execute batch in chunks of 100
                        pstmt.executeBatch();
                    }
                } else {
                    pstmt.executeUpdate();
                }
            }

            // Execute remaining batch if using batch updates
            if (useBatch) {
                pstmt.executeBatch();
            }

            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);

            String mode = useBatch ? "with batch" : "without batch";
            showAlert(AlertType.INFORMATION, "Success", "Inserted 1000 records " + mode + " in " + timeElapsed.toMillis() + " ms.");
        } catch (SQLException e) {
            showAlert(AlertType.ERROR, "Error", "Error inserting records: " + e.getMessage());
        }
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
