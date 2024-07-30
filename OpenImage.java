import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class OpenImage {
    public OpenImage(ArrayList<Color> colors, JTextField numberOfColours, JPanel[] lastImage){
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
            try {

                Main.f.remove(lastImage[0]);
                // Load image in
                BufferedImage image = ImageIO.read(j.getSelectedFile());

                JPanel imagePane = displayImage(image, Main.f);
                Main.f.add(imagePane);
                lastImage[0] = imagePane;

                Main.f.show();

                // Extract image data to array of colours
                for (int x = 0; x < image.getWidth(); x++) {
                    for (int y = 0; y < image.getHeight(); y++) {
                        Color c = new Color(image.getRGB(x,y));
                        colors.add(c);
                    }
                }

                // Generate colour palette from array of colours
                if (numberOfColours.getText().equals("Insert Number")){
                    generateColorPalette(colors, 5);
                }else {
                    generateColorPalette(colors, Integer.parseInt(numberOfColours.getText()));
                }

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    // Simple image panel creation
    private static JPanel displayImage(BufferedImage i, JFrame f){
        JPanel p = new JPanel();
        float scaleFactor = 0.5f;


        Image newImg = i.getScaledInstance((int) (i.getWidth() * scaleFactor), (int) (i.getHeight()*scaleFactor), i.SCALE_SMOOTH);
        JLabel image = new JLabel(new ImageIcon(newImg));
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
        java.util.List<Integer> list = new ArrayList<Integer>(colorOccurrences.values());
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
        for (Color c : finalPalette) {
            System.out.println(c.toString());
            Main.colorPalette.addColor("#" + Integer.toHexString(c.getRGB()).substring(2));
        }
        Main.colorPalette.drawPalette();
    }
}
