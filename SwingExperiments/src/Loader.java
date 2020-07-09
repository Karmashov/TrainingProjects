import javax.swing.*;

public class Loader {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(400, 200);

        frame.add(new MainForm().getMainPanel());

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
