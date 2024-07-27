import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args){

        // Accessibility feature, all calculations are based off of screen size to make the program more compatible with different systems
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int[] screenDims = {(int) size.getWidth(), (int)size.getHeight()};

        Rectangle buttonSize = new Rectangle(100,50);

        JFrame f = new JFrame("Hello World");
        f.setSize(screenDims[0] / 100 * 40,screenDims[1] / 100 * 70);
        f.setVisible(true);

        JPanel buttons = new JPanel();
        JButton b1 = new JButton("Open Image");
        b1.setBounds(buttonSize);

        JLabel l = new JLabel("No file selected");

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                j.setAcceptAllFileFilterUsed((false));
                j.setDialogTitle(".JPG, .JPEG, .PNG, .GIF");
                FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .JPG, .JPEG, .PNG, or .GIF files", "jpeg", "jpg", "png", "gif");
                j.addChoosableFileFilter(restrict);

                int r = j.showOpenDialog(null);
                if (r == JFileChooser.APPROVE_OPTION) {
                    l.setText(j.getSelectedFile().getAbsolutePath());
                }
                else {
                    l.setText("the user cancelled the operation");
                }
            }
        });

        buttons.add(b1);
        buttons.add(l);

        f.add(buttons);
        f.show();


    }
}
