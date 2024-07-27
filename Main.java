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

        // Button setup for open image button
        JPanel buttons = new JPanel();
        JButton b1 = new JButton("Open Image");
        b1.setBounds(buttonSize);

        // General info and setting labels
        JLabel l = new JLabel("No file selected");
        JLabel numberOfColoursLabel = new JLabel("Number Of Colours In Palette");
        JTextField numberOfColours = new JTextField("Insert Number");


        /* Listens for action from open image button and does colour and image manipulations from there*/

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // System for choosing image files
                JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                j.setAcceptAllFileFilterUsed((false));
                // Restricts files to required types
                j.setDialogTitle(".JPG, .JPEG, .PNG, .GIF");
                FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .JPG, .JPEG, .PNG, or .GIF files", "jpeg", "jpg", "png", "gif");
                j.addChoosableFileFilter(restrict);

                //Resets colours array everytime a new image is input
                colors = new ArrayList<Color>();

                // Check to make sure user has selected file and not cancelled operation
                int r = j.showOpenDialog(null);
                if (r == JFileChooser.APPROVE_OPTION) {
                    // Display selected file as text on screen
                    l.setText(j.getSelectedFile().getAbsolutePath());

                    try {
                        // Load image in
                        BufferedImage image = ImageIO.read(j.getSelectedFile());
                        JPanel imagePane = displayImage(image);
                        f.add(imagePane);

                        // Extract image data to array of colours
                        for (int x = 0; x < image.getWidth(); x++) {
                            for (int y = 0; y < image.getHeight(); y++) {
                                Color c = new Color(image.getRGB(x,y));
                                colors.add(c);
                            }
                        }

                        // Generate colour palette from array of colours
                        generateColorPalette(colors, Integer.parseInt(numberOfColours.getText()));

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else {
                    l.setText("User cancelled the operation");
                }
            }
        });

        // Add all items to panels
        buttons.add(numberOfColoursLabel);
        buttons.add(numberOfColours);
        buttons.add(b1);
        buttons.add(l);

        f.add(buttons);

        f.show();
    }

    // Simple image panel creation
    private static JPanel displayImage(BufferedImage i){
        JPanel p = new JPanel();
        JLabel image = new JLabel(new ImageIcon(i));
        p.add(image);
        return p;
    }

    // Generates a colour palette, currently outputs to console only
    private static void generateColorPalette(ArrayList<Color> colors, int numCol){
        HashMap<Color, Integer> colorOccurrences = new HashMap<Color, Integer>();

        for (Color c : colors) {
            if(colorOccurrences.get(c) == null){
                colorOccurrences.put(c, 1);
            }
            if (colorOccurrences.get(c) > 0) {
                colorOccurrences.put(c, colorOccurrences.get(c) + 1);
            } else {
                colorOccurrences.put(c, 1);
            }


        }
        List<Integer> list = new ArrayList<Integer>(colorOccurrences.values());
        Collections.sort(list, Collections.reverseOrder());
        List<Integer> top5 = list.subList(0, numCol);

        ArrayList<Color> finalPalette = new ArrayList<Color>();

        for (Map.Entry<Color, Integer> colorIntegerEntry : colorOccurrences.entrySet()) {
            for (int i = 0; i < numCol; i++) {
                if (colorIntegerEntry.getValue() == top5.get(i)) {
                    finalPalette.add(colorIntegerEntry.getKey());
                }
            }
        }
        System.out.println("Colour Palette Is");
        for (Color c : finalPalette) {
            System.out.println("#" + Integer.toHexString(c.getRGB()).substring(2));
        }
    }
}
