
	// Upgrade Menu will be a pop up GUI that will appear when player levels up.
	// The game will pause when the GUI appears.
	// Three choices available for the player to choose from
	// Increase health by +10 to overall health
	// Increase strength by +1 to overall strength
	// Increase defense by +1 to overall defense
	
	// Three stack data structures health, strength and defense
	// Append algorithm of adding integer by the amount of upgrade + 10 or + 1
	// Max health 250, max strength + 15 , max defense + 15.
	// Overall upgrade levels will be 15.
	// Starting status of player 100 health, 5 strength, 5 defense.



import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class UpgradeMenu extends Dialog implements ActionListener, KeyListener {

    private HealthUpgrade health;
    private StrengthUpgrade attack;
    private DefenseUpgrade defense;

    private Button healthBtn;
    private Button strengthBtn;
    private Button defenseBtn;
    private Button resumeBtn;

    private Runnable onClose;

    public UpgradeMenu(Frame owner,
                       HealthUpgrade health,
                       StrengthUpgrade attack,
                       DefenseUpgrade defense,
                       Runnable onClose) {

        super(owner, "Level Up!", true);
       
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.onClose = onClose;

        setLayout(new GridLayout(4, 1));

        Label title = new Label("Choose an Upgrade", Label.CENTER);

        healthBtn = new Button("+10 Health");
        strengthBtn = new Button("+1 Strength");
        defenseBtn = new Button("+1 Defense");
        resumeBtn = new Button ("Resume");

        healthBtn.addActionListener(this);
        strengthBtn.addActionListener(this);
        defenseBtn.addActionListener(this);
        resumeBtn.addActionListener(this);


        add(title);
        add(healthBtn);
        add(strengthBtn);
        add(defenseBtn);
        add(resumeBtn);
        addKeyListener(this);
        
        setFocusable(true);
        requestFocus();
        requestFocusInWindow();
       

        setSize(250, 200);
        setLocationRelativeTo(owner);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	
        if (e.getSource() == healthBtn) {
            health.pushHealthUpgrade();
        }

        if (e.getSource() == strengthBtn) {
            attack.pushStrengthUpgrade();
        }

        if (e.getSource() == defenseBtn) {
            defense.pushDefenseUpgrade();
        }
        if (e.getSource() == resumeBtn) {
        	dispose();
        }

        dispose();

        if (onClose != null) {
            onClose.run();
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    
    }
    
    @Override
    public void keyPressed(KeyEvent e) { 
	int code = e.getExtendedKeyCode();
    	
    	if(code == KeyEvent.VK_ESCAPE) {
    		this.dispose();
    		onClose.run();
    	}
    }
    @Override
    public void keyReleased(KeyEvent e) {}
   
}
