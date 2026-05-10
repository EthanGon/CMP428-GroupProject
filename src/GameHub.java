import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameHub extends JPanel implements ActionListener {

    private long startTime;
    private Timer timer;
    private Game game;

    public GameHub(Game game) {
    	this.game = game;
        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.BLACK);
        startTime = System.currentTimeMillis();
        timer = new Timer(100, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));

        long survivalTime = (System.currentTimeMillis() - startTime) / 1000;
        long minutes = survivalTime / 60;
        long seconds = survivalTime % 60;
        
        g.drawString(String.format("Time: %02d:%02d", minutes, seconds), 20, 40);
        g.drawString("Enemies Killed: " + game.getKillCount() , 20, 80);
        g.drawString("Level: " + Player.getPlayer().level, 20, 120);
        g.drawString("HP: " + Player.getPlayer().currentHealth, 20 , 160);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

    }
}