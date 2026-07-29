import java.awt.BorderLayout;
import java.awt.Color;
import java.nio.file.Path;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class PlayerWindow {
    private final JFrame frame;
    private final JPanel videoPanel;
    private final JButton openButton;
    private final JButton playButton;
    private final JButton pauseButton;
    private final JButton stopButton;
    private Path selectedMediaPath;
    private PlayerState playerState = PlayerState.NO_MEDIA;


    public PlayerWindow() {
        frame = new JFrame("Video Player");
        frame.setLayout(new BorderLayout());

        videoPanel = new JPanel();
        videoPanel.setBackground(Color.BLACK);

        JPanel controlPanel = new JPanel();

        openButton = new JButton("open");
        openButton.addActionListener(event -> chooseMediaFile());

        playButton = new JButton("play");
        playButton.addActionListener(event -> startPlayback());

        pauseButton = new JButton("pause");
        pauseButton.addActionListener(event -> startPlayback());

        stopButton = new JButton("stop");
        stopButton.addActionListener(event -> startPlayback());

        updateControls();

        controlPanel.add(playButton);
        controlPanel.add(pauseButton);
        controlPanel.add(stopButton);
        controlPanel.add(openButton);

        frame.add(videoPanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);

        frame.setSize(960, 540);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

    }

    public void show() {
        frame.setVisible(true);
    }

    private void chooseMediaFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a Video");

        int result = fileChooser.showOpenDialog(frame);

        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        selectedMediaPath = fileChooser.getSelectedFile().toPath();

        frame.setTitle("Video Player - " + selectedMediaPath.getFileName());
        setPlayerState(PlayerState.READY);
    }

    private void setPlayerState(PlayerState newState) {
        playerState = newState;
        System.out.println("Player state: " + playerState);
        updateControls();
    }

    private void updateControls() {
        boolean canPlay = 
            playerState == PlayerState.READY
            || playerState == PlayerState.PAUSED;

        boolean canPause = playerState == PlayerState.PLAYING;

        boolean canStop = 
            playerState == PlayerState.PLAYING
            || playerState == PlayerState.PAUSED;

        playButton.setEnabled(canPlay);
        pauseButton.setEnabled(canPause);
        stopButton.setEnabled(canStop);

    }

    private void startPlayback() {
        if (playerState == PlayerState.READY || playerState == PlayerState.PAUSED) {
            setPlayerState(PlayerState.PLAYING);
        }
    }

    private void pausePlayback() {
        if (playerState == PlayerState.PLAYING) {
            setPlayerState(PlayerState.PAUSED);
        }
    }

    private void stopPlayback() {
        if (playerState == PlayerState.PLAYING || playerState == PlayerState.PAUSED) {
            setPlayerState(PlayerState.READY);
        }
    }
}
