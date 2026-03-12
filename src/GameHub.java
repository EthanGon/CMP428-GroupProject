import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameHub extends JPanel implements ActionListener {

    private int enemiesKilled = 0;
    private long startTime;
    private Timer timer;

    public GameHub() {
        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.BLACK);

        startTime = System.currentTimeMillis();

        timer = new Timer(100, this);
        timer.start();

        new Timer(3000, e -> enemiesKilled++).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));

        long survivalTime = (System.currentTimeMillis() - startTime) / 1000;

        g.drawString("Survival Time: " + survivalTime + " sec", 20, 40);
        g.drawString("Enemies Killed: " + enemiesKilled, 20, 80);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("GameHub Survival Tracker");

        GameHub game = new GameHub();

        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}