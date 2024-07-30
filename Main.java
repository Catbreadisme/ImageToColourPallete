import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Main {

    // Public list of colours to be passed between methods
    public static ArrayList<Color> colors = new ArrayList<Color>();
    // Final color palette
    static ColorPalette colorPalette = new ColorPalette();
    // Main window that can be added to
    public static JFrame f = new JFrame("ImageToColourPalette");
    // For clearing prior palette from screen
    public static JPanel lastPalette = new JPanel();
    // Screen size for usability feature
    public static Dimension size = Toolkit.getDefaultToolkit(). getScreenSize();

    public static void main(String[] args){
        // Default Button Size
        Rectangle buttonSize = new Rectangle(100,50);

        // Frame and window setup
        f.setSize((size.width / 100) * 30,(size.height / 100) * 60);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new FlowLayout());
        f.setLocationRelativeTo(null);

        // Button setup for open image button and save palette button
        JPanel buttons = new JPanel();
        JButton b1 = new JButton("Open Image");
        b1.setBounds(buttonSize);
        JButton b2 = new JButton("Save Palette");
        b2.setBounds(buttonSize);

        // General info and setting labels
        JLabel numberOfColoursLabel = new JLabel("Number Of Colours In Palette");
        JTextField numberOfColours = new JTextField("Insert Number");

        // Used for clearing prior image from display
        final JPanel[] lastImage = {new JPanel()};


        /* Listens for action from open image button and does colour and image manipulations from there*/

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    colorPalette = new ColorPalette();
                    new OpenImage(colors, numberOfColours, lastImage);
            }
        });
        /* Listens for action from save palette button and run palette saving code*/
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SavePalette(colorPalette);
            }
        });

        // Add all simple items to panels
        buttons.add(b1);
        buttons.add(b2);
        buttons.add(numberOfColoursLabel);
        buttons.add(numberOfColours);
        f.add(buttons);
        f.show();
    }
}
