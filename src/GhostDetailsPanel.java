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
        ghostName = new GhostLabel("Name: " + this.ghost.getName(), Color.white, GUIConstants.backGroundColor);
        evidence_1 = new GhostLabel("Evidence 1: " + this.ghost.getEvidence_1(), Color.white, GUIConstants.backGroundColor);
        evidence_2 = new GhostLabel("Evidence 2: " + this.ghost.getEvidence_2(),  Color.white, GUIConstants.backGroundColor);
        evidence_3 = new GhostLabel("Evidence 3: " + this.ghost.getEvidence_3(), Color.white, GUIConstants.backGroundColor);
        strength = new GhostLabel("Strength: " + this.ghost.getStrength(), Color.white, GUIConstants.backGroundColor);
        weakness = new GhostLabel("Weakness: " + this.ghost.getWeakness(), Color.white, GUIConstants.backGroundColor);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(ghostName);
        mainPanel.add(evidence_1);
        mainPanel.add(evidence_2);
        mainPanel.add(evidence_3);
        mainPanel.add(strength);
        mainPanel.add(weakness);

        mainPanel.setBackground(GUIConstants.backGroundColor);

        Random random = new Random();

        add(mainPanel);
        getContentPane().setBackground(GUIConstants.backGroundColor);
        setVisible(true);
        this.setBackground(GUIConstants.backGroundColor);
        setLayout(new FlowLayout());
        setSize(new Dimension(500,150));
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
