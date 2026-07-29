import javax.swing.JFrame;

public class PlayerWindow {
    private final JFrame frame;

    public PlayerWindow() {
        frame = new JFrame("Video Player");
        frame.setSize(960, 540);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);

    }

    public void show() {
        frame.setVisible(true);
    }
}
