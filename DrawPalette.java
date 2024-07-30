import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawPalette extends JPanel {
    ArrayList<String> palette;

    // Sets up basic panel info
    DrawPalette(ArrayList<String> colorPalette) {
        setPreferredSize(new Dimension((Main.size.width / 100) * 30,(Main.size.height / 100) * 20));
        palette = colorPalette;
        this.repaint();
    }

    // Paint palette data onto panel
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
