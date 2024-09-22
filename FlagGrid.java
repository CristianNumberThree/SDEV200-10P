import java.awt.*;
import javax.swing.*;

public class FlagGrid extends JFrame {

    public FlagGrid() {
        // Set the title of the window
        setTitle("Flag Grid");

        // Set the layout to a 2x2 grid
        setLayout(new GridLayout(2, 2));

        // Load and add the images to the grid
        add(new JLabel(new ImageIcon("flag1.gif")));
        add(new JLabel(new ImageIcon("flag2.gif")));
        ImageIcon flag6Icon = new ImageIcon("flag6.gif");
        Image scaledFlag6 = flag6Icon.getImage().getScaledInstance(412, 217, Image.SCALE_SMOOTH);
        add(new JLabel(new ImageIcon(scaledFlag6)));
        ImageIcon flag7Icon = new ImageIcon("flag7.gif");
        Image scaledFlag7 = flag7Icon.getImage().getScaledInstance(417, 217, Image.SCALE_SMOOTH);
        add(new JLabel(new ImageIcon(scaledFlag7)));

        // Set default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the window
        setSize(855, 480);

        // Make the window visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Invoke the constructor to create the GUI
        new FlagGrid();
    }
}

