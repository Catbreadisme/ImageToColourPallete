import javax.swing.*;
import java.util.ArrayList;

public class ColorPalette {
    ArrayList<String> colorPalette;

    public ColorPalette(){
        colorPalette = new ArrayList<>();
    }

    public void addColor(String s){
        colorPalette.add(s);
    }
    public ArrayList<String> getColorPalette(){
        return colorPalette;
    }
    public void drawPalette(){
        Main.f.remove(Main.lastPalette);
        Main.lastPalette.revalidate();
        JPanel p = new DrawPalette(colorPalette);
        Main.f.add(p);
        p.revalidate();
        Main.lastPalette = p;
    }
}
