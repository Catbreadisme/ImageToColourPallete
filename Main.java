import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Main {

    // Public access list of colours to be passed between methods
    public static ArrayList<Color> colors = new ArrayList<Color>();
    static ColorPalette colorPalette = new ColorPalette();
    public static JFrame f = new JFrame("ImageToColourPalette");
    public static JPanel lastPalette = new JPanel();
    public static Dimension size = Toolkit.getDefaultToolkit(). getScreenSize();

    public static void main(String[] args){

        Rectangle buttonSize = new Rectangle(100,50);
        // Frame and window setup

        f.setSize((size.width / 100) * 30,(size.height / 100) * 60);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new FlowLayout());
        f.setLocationRelativeTo(null);

        // Button setup for open image button
        JPanel buttons = new JPanel();
        JButton b1 = new JButton("Open Image");
        b1.setBounds(buttonSize);

        JButton b2 = new JButton("Save");
        b2.setBounds(buttonSize);

        // General info and setting labels
        JLabel numberOfColoursLabel = new JLabel("Number Of Colours In Palette");
        JTextField numberOfColours = new JTextField("Insert Number");

        final JPanel[] lastImage = {new JPanel()};


        /* Listens for action from open image button and does colour and image manipulations from there*/

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    colorPalette = new ColorPalette();
                    new OpenImage(colors, numberOfColours, lastImage);
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SavePalette(colorPalette);
            }
        });

        // Add all items to panels
        buttons.add(b1);
        buttons.add(b2);
        buttons.add(numberOfColoursLabel);
        buttons.add(numberOfColours);

        f.add(buttons);

        while (f.isActive()) {
            f.show();
        }
    }
}
