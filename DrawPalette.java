import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawPalette extends JPanel {
    ArrayList<String> palette;

    DrawPalette(ArrayList<String> colorPalette) {
        setPreferredSize(new Dimension(800,200));
        palette = colorPalette;
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        int x = 50;
        for (String s: palette) {
            g.setColor(Color.decode(s));
            g.fillRect(x, 50, 50, 50);
            x+=50;
        }
    }
}
