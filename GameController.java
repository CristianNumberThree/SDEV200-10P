import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController {
    private List<String> food = new ArrayList<>();
    private List<String> eaten = new ArrayList<>();
    private String location = "Bedroom";
    private Text message = new Text(
        "Good morning friend (says the unseen thing in the shadowy corner). Shall we get going?"
    );

    public List<String> getFoodList() {
        return food;
    }

    public List<String> getEatenList() {
        return eaten;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String newLocation) {
        location = newLocation;
        message.setText("You moved to: " + location);
    }

    public Text getMessage() {
        return message;
    }

    public void addRandomItem() {
        Random rand = new Random();
        String item = switch (location) {
            case "Bedroom" -> rand.nextBoolean() ? "Rotten Food" : "Trash";
            case "Bathroom" -> rand.nextBoolean() ? "Golden Egg" : "Trash";
            case "Kitchen" -> rand.nextBoolean() ? "Fresh Food" : "Rotten Food";
            case "Livingroom" -> rand.nextBoolean() ? "Strange Meat" : "Trash";
            case "Basement" -> "Strange Meat";
            default -> "Unknown";
        };
        food.add(item);
        message.setText("You found: " + item);
    }

    public void feedFriend(String selectedFood) {
        food.remove(selectedFood);
        eaten.add(selectedFood);
        message.setText("Friend has been fed");

        if (eaten.size() == 3) {
            handleEatenItems();
        }
    }

    private void handleEatenItems() {
        long trashCount = eaten.stream().filter(item -> item.equals("Trash")).count();
        long rottenFoodCount = eaten.stream().filter(item -> item.equals("Rotten Food")).count();

        if (trashCount >= 2) {
            UIManager.showErrorMessage("Hello friend I am the trash man", "trashman.gif");
        } else if (rottenFoodCount >= 2) {
            UIManager.showErrorMessage("Run its the appetizer", "appetizer.gif");
        }
        System.exit(0);
    }
}
