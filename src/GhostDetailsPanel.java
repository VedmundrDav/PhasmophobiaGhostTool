import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.Random;

public class GhostDetailsPanel extends JFrame{
    private Ghost ghost;
    private JLabel ghostName;
    private JLabel evidence_1;
    private JLabel evidence_2;
    private JLabel evidence_3;
    private JLabel strength;
    private JLabel weakness;

    private JPanel mainPanel;

    GhostDetailsPanel(){

    }

    public GhostDetailsPanel(Ghost ghost) {
        this.ghost = ghost;
        buildPanel();

    }

    private void buildPanel(){
        ghostName = new JLabel("Name: " + this.ghost.getName());
        evidence_1 = new JLabel("Evidence 1: " + this.ghost.getEvidence_1());
        evidence_2 = new JLabel("Evidence 2: " + this.ghost.getEvidence_2());
        evidence_3 = new JLabel("Evidence 3: " + this.ghost.getEvidence_3());
        strength = new JLabel("Strength: " + this.ghost.getStrength());
        weakness = new JLabel("Weakness: " + this.ghost.getWeakness());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(ghostName);
        mainPanel.add(evidence_1);
        mainPanel.add(evidence_2);
        mainPanel.add(evidence_3);
        mainPanel.add(strength);
        mainPanel.add(weakness);

        Random random = new Random();

        add(mainPanel);
        setVisible(true);
        setLayout(new FlowLayout());
        setSize(new Dimension(500,200));
        setResizable(false);
        setTitle(ghost.getName());
        setLocation(random.nextInt(1000), random.nextInt(500));
        setAlwaysOnTop(true);
    }

    public Ghost getGhost() {
        return ghost;
    }

    public void setGhost(Ghost ghost) {
        this.ghost = ghost;
    }

}
