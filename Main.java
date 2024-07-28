import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Main {

    // Public access list of colours to be passed between methods
    public static ArrayList<Color> colors = new ArrayList<Color>();
    public static ArrayList<String> colorPalette = new ArrayList<String>();

    public static void main(String[] args){

        // Accessibility feature, all calculations are based off of screen size to make the program more compatible with different systems
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int[] screenDims = {(int) size.getWidth(), (int)size.getHeight()};




        Rectangle buttonSize = new Rectangle(100,50);

        // Frame and window setup
        JFrame f = new JFrame("ImageToColourPalette");
        f.setSize(screenDims[0] / 100 * 40,screenDims[1] / 100 * 70);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new FlowLayout());

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

                if (e.getActionCommand()=="Open Image"){
                    new OpenImage(colors, colorPalette,  f, lastImage, numberOfColours);

                    if (!colorPalette.isEmpty()){
                        for (String s: colorPalette) {
                        }
                    }
                }
                if(e.getActionCommand()=="Save"){
                    new SavePalette(colorPalette);
                }
            }
        });

        // Add all items to panels
        buttons.add(b1);
        buttons.add(numberOfColoursLabel);
        buttons.add(numberOfColours);

        f.add(buttons);

        f.show();
    }
}
