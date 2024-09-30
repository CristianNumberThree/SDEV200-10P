import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {
    private TextField tfId = new TextField();
    private TextField tfLastName = new TextField();
    private TextField tfFirstName = new TextField();
    private TextField tfMi = new TextField();
    private TextField tfAddress = new TextField();
    private TextField tfCity = new TextField();
    private TextField tfState = new TextField();
    private TextField tfTelephone = new TextField();
    private TextField tfEmail = new TextField();

    private Button btnView = new Button("View");
    private Button btnInsert = new Button("Insert");
    private Button btnUpdate = new Button("Update");

    private final String DB_URL = "jdbc:mysql://localhost:3306/your_database"; // Change this
    private final String USER = "your_username"; // Change this
    private final String PASS = "your_password"; // Change this

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(5);
        pane.setHgap(5);

        pane.add(new Label("ID:"), 0, 0);
        pane.add(tfId, 1, 0);
        pane.add(new Label("Last Name:"), 0, 1);
        pane.add(tfLastName, 1, 1);
        pane.add(new Label("First Name:"), 0, 2);
        pane.add(tfFirstName, 1, 2);
        pane.add(new Label("MI:"), 0, 3);
        pane.add(tfMi, 1, 3);
        pane.add(new Label("Address:"), 0, 4);
        pane.add(tfAddress, 1, 4);
        pane.add(new Label("City:"), 0, 5);
        pane.add(tfCity, 1, 5);
        pane.add(new Label("State:"), 0, 6);
        pane.add(tfState, 1, 6);
        pane.add(new Label("Telephone:"), 0, 7);
        pane.add(tfTelephone, 1, 7);
        pane.add(new Label("Email:"), 0, 8);
        pane.add(tfEmail, 1, 8);

        pane.add(btnView, 0, 9);
        pane.add(btnInsert, 1, 9);
        pane.add(btnUpdate, 2, 9);

        btnView.setOnAction(e -> viewStaff());
        btnInsert.setOnAction(e -> insertStaff());
        btnUpdate.setOnAction(e -> updateStaff());

        Scene scene = new Scene(pane, 400, 400);
        primaryStage.setTitle("Staff Information");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void viewStaff() {
        String id = tfId.getText();
        String query = "SELECT * FROM Staff WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                tfLastName.setText(rs.getString("lastName"));
                tfFirstName.setText(rs.getString("firstName"));
                tfMi.setText(rs.getString("mi"));
                tfAddress.setText(rs.getString("address"));
                tfCity.setText(rs.getString("city"));
                tfState.setText(rs.getString("state"));
                tfTelephone.setText(rs.getString("telephone"));
                tfEmail.setText(rs.getString("email"));
            } else {
                showAlert(Alert.AlertType.INFORMATION, "No record found with ID " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error accessing database: " + e.getMessage());
        }
    }

    private void insertStaff() {
        String query = "INSERT INTO Staff (id, lastName, firstName, mi, address, city, state, telephone, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, tfId.getText());
            pstmt.setString(2, tfLastName.getText());
            pstmt.setString(3, tfFirstName.getText());
            pstmt.setString(4, tfMi.getText());
            pstmt.setString(5, tfAddress.getText());
            pstmt.setString(6, tfCity.getText());
            pstmt.setString(7, tfState.getText());
            pstmt.setString(8, tfTelephone.getText());
            pstmt.setString(9, tfEmail.getText());
            
            int result = pstmt.executeUpdate();
            if (result > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Record inserted successfully");
            } else {
                showAlert(Alert.AlertType.WARNING, "Failed to insert record");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error inserting record: " + e.getMessage());
        }
    }

    private void updateStaff() {
        String query = "UPDATE Staff SET lastName = ?, firstName = ?, mi = ?, address = ?, city = ?, state = ?, telephone = ?, email = ? WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, tfLastName.getText());
            pstmt.setString(2, tfFirstName.getText());
            pstmt.setString(3, tfMi.getText());
            pstmt.setString(4, tfAddress.getText());
            pstmt.setString(5, tfCity.getText());
            pstmt.setString(6, tfState.getText());
            pstmt.setString(7, tfTelephone.getText());
            pstmt.setString(8, tfEmail.getText());
            pstmt.setString(9, tfId.getText());
            
            int result = pstmt.executeUpdate();
            if (result > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Record updated successfully");
            } else {
                showAlert(Alert.AlertType.WARNING, "No record found with ID " + tfId.getText());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error updating record: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.show();
    }
}
