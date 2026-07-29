import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args){

        System.out.println("Video Player Starting");
        SwingUtilities.invokeLater(() -> {
            PlayerWindow window = new PlayerWindow();
            window.show();
        });
        
    }
}
