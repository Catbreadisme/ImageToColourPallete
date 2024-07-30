import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SavePalette {
    public SavePalette(ColorPalette colorPalette){
        ArrayList<String> c = colorPalette.getColorPalette();
        ArrayList<String> finalStrings = new ArrayList<>();
        JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int a = jFileChooser.showSaveDialog(null);

        System.out.println("hi");

        if (!c.isEmpty()){
            for (String s: c) {
                Color col = Color.decode(s);
                finalStrings.add(col.getRed()+" "+col.getBlue()+" "+col.getGreen()+" ");
            }
        }

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
