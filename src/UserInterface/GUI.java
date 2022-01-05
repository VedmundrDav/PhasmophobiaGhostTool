import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;


public class GUI extends JFrame {
    //for all vars that can be converted to local vars: LEAVE AS IS incase there are future updates.
    private final String title = "Phasmophobia Ghost Tool";
    private EvidencePanel evidencePanel;
    private GhostPanel ghostPanel;

    //private NotesPanel notesPanel;
    private NotesPanel notesTextPanel;
    private EquipmentPanel equipmentPanel;


    private GhostButton resetBtn;

    public GUI(){
        buildGUI();
    }
    private void buildGUI(){

        resetBtn = new GhostButton("Reset");

        evidencePanel = new EvidencePanel();
        evidencePanel.add(resetBtn);
        ghostPanel = new GhostPanel();
        ghostPanel.determineAvailableGhosts(evidencePanel);
        evidencePanel.setGhosts(ghostPanel.getGhostList());
        GhostMainPanel ghostMainPanel = new GhostMainPanel(evidencePanel, ghostPanel);
        ghostMainPanel.add(evidencePanel);
        ghostMainPanel.add(ghostPanel);

        notesTextPanel = new NotesPanel();
        equipmentPanel = new EquipmentPanel();
        NotesMainPanel notesMainPanel = new NotesMainPanel(notesTextPanel, equipmentPanel);

        //this Frame methods
        addActionListeners();
        add(ghostMainPanel);
        add(notesMainPanel);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        setSize(new Dimension(1500, 750));
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private void clearWorkspace(EvidencePanel evidence, GhostPanel ghosts, EquipmentPanel equipment, NotesPanel notes){
        equipment.reset();
        ghosts.reset();
        evidence.reset();
        notes.reset();
        closeGhostFrames();
    }
    private void closeGhostFrames(){
        Frame[] frames = Frame.getFrames();
        for(Frame f : frames){
            if(!f.getTitle().equals(title)){
                f.dispose();
            }
        }
    }
    public static Border setTitledBorder(String title){
        TitledBorder titledBorder = BorderFactory.createTitledBorder(title);
        titledBorder.setTitleColor(GUIConstants.textAndBorderColor);
        return titledBorder;
    }
    private void addActionListeners(){
        //condense into a "listeners function"
        resetBtn.addActionListener(e -> clearWorkspace(evidencePanel, ghostPanel, equipmentPanel, notesTextPanel));
    }


}
