import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class MyFriendTextBasedRPG extends Application {

    private final GameController gameController = new GameController();

    @Override
    public void start(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Button btnOpenWindow = new Button("Wake up");
        Button btnClose = new Button("Keep Sleeping");

        btnOpenWindow.setOnAction(e -> UIManager.openSecondWindow(gameController));
        btnClose.setOnAction(e -> primaryStage.close());

        layout.getChildren().addAll(btnOpenWindow, btnClose);
        primaryStage.setScene(new Scene(layout, 300, 150));
        primaryStage.setTitle("Dreaming");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
