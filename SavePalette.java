import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SavePalette {
    public SavePalette(ColorPalette colorPalette){
        // Import palette data
        ArrayList<String> c = colorPalette.getColorPalette();
        ArrayList<String> finalStrings = new ArrayList<>();

        // Setup file Chooser to save file
        JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int a = jFileChooser.showSaveDialog(null);

        // Fill final strings array with color data formatted to gpl file requirements
        if (!c.isEmpty()){
            for (String s: c) {
                Color col = Color.decode(s);
                finalStrings.add(col.getRed()+" "+col.getBlue()+" "+col.getGreen()+" ");
            }
        }

        // Write final strings to gpl file
        if (a == JFileChooser.APPROVE_OPTION) {
            try(FileWriter fw = new FileWriter(jFileChooser.getSelectedFile()+".gpl")) {
                fw.write("GIMP Palette\n");
                for (String s: finalStrings) {
                    fw.write(s+"\n");
                }
            }
            catch (IOException e){
                System.out.println(e);
            }
        }

    }
}
