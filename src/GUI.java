import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;
import java.util.ArrayList;

public class GUI extends JFrame {
    //for all vars that can be converted to local vars: LEAVE AS IS incase there are future updates.
    private JPanel ghostMainPanel;
    private JPanel evidencePanel;
    private JPanel ghostPanel;

    private JPanel notesPanel;
    private JPanel notesTextPanel;
    private JPanel equipmentPanel;

    private JTextArea notesTextArea;

    private JButton clearBtn;

    private ArrayList<Ghost> ghosts;
    private ArrayList<Evidence> evidences;
    private ArrayList<Equipment> equipments;

    public GUI(){
        buildGUI();
    }
    private void buildGUI(){
        ghosts = getGhosts();
        evidences = getEvidences();
        equipments = getEquipment();

        ArrayList<JCheckBox> evidenceCheckBoxes = getEvidenceAsCheckBoxes(evidences);
        ArrayList<JButton> ghostButtons = getGhostsAsButtons(ghosts);
        ArrayList<JCheckBox> equipmentCheckBoxes = getEquipmentAsCheckBoxes(equipments);

        Border border = BorderFactory.createLineBorder(Color.black);
        Border emptyBorder = BorderFactory.createEmptyBorder(5,5,5,5);

        //layouts for the ghostbuttons and equipment checkboxes
        GridLayout ghostBtnLayout = new GridLayout(ghostButtons.size()/2, ghostButtons.size()/2);
        ghostBtnLayout.setHgap(5);
        ghostBtnLayout.setVgap(5);
        GridLayout equipmentLayout = new GridLayout(equipmentCheckBoxes.size()/5, equipmentCheckBoxes.size()/2);

        ghostMainPanel = new JPanel();
        ghostMainPanel.setLayout(new BoxLayout(ghostMainPanel, BoxLayout.PAGE_AXIS));
        evidencePanel = new JPanel();

        ghostPanel = new JPanel();
        ghostPanel.setLayout(ghostBtnLayout);
        ghostPanel.setBorder(emptyBorder);

        ghostMainPanel.add(evidencePanel);
        ghostMainPanel.add(ghostPanel);

        notesPanel = new JPanel();

        notesTextPanel = new JPanel();
        notesTextArea = new JTextArea(15,65);
        notesTextArea.setLineWrap(true);
        JScrollPane scroller = new JScrollPane(notesTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        notesTextArea.setBorder(border);
        notesTextPanel.add(scroller);
        notesTextPanel.setBorder(setTitledBorder("Notes:"));

        equipmentPanel = new JPanel();
        equipmentPanel.setLayout(equipmentLayout);
        equipmentPanel.setBorder(setTitledBorder("Equipment in Ghost Room"));

        notesPanel.setLayout(new GridLayout(1,2));
        notesPanel.add(notesTextPanel);
        notesPanel.add(equipmentPanel);

        //creates all buttons and checkboxes
        createComponents(ghostButtons, evidenceCheckBoxes, equipmentCheckBoxes);

        add(ghostMainPanel);
        add(notesPanel);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        setSize(new Dimension(1500, 750));
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Phasmophobia Ghost Tool");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        determineAvailableGhosts(ghosts, ghostButtons);

        clearBtn.addActionListener(e -> clearWorkspace(ghosts, ghostButtons, notesTextArea));

    }

    private ArrayList<Ghost> getGhosts(){
        return GhostProcessor.readGhostXMLIntoGhost();
    }
    private ArrayList<Evidence> getEvidences(){
        return GhostProcessor.readEvidenceXMLIntoEvidence();
    }
    private ArrayList<Equipment> getEquipment(){return GhostProcessor.readEquipmentXMLIntoEquipment();}
    private ArrayList<JCheckBox> getEvidenceAsCheckBoxes(ArrayList<Evidence> evidences){
        //ArrayList<Evidence> evidences = FileProcessor.readEvidenceXMLIntoEvidence();
        ArrayList<JCheckBox> evidenceCheckBoxes= new ArrayList<>();
        for(Evidence evidence : evidences){
            JCheckBox checkBox = new JCheckBox(evidence.getName());
            evidenceCheckBoxes.add(checkBox);
        }
        return evidenceCheckBoxes;
    }
    private ArrayList<JButton> getGhostsAsButtons(ArrayList<Ghost> ghosts){
        //ArrayList<Ghost> ghosts = FileProcessor.readGhostXMLIntoGhost();
        ArrayList<JButton> ghostButtons = new ArrayList<>();
        for(Ghost ghost : ghosts){
            JButton button = new JButton(ghost.getName());
            ghostButtons.add(button);
        }
        return ghostButtons;
    }
    private ArrayList<JCheckBox> getEquipmentAsCheckBoxes(ArrayList<Equipment> equipments){
        ArrayList<JCheckBox> equipmentCheckBoxes= new ArrayList<>();
        for(Equipment equipment : equipments){
            JCheckBox checkBox = new JCheckBox(equipment.getName());
            equipmentCheckBoxes.add(checkBox);
        }
        return equipmentCheckBoxes;
    }
    private void determineAvailableGhosts(ArrayList<Ghost> ghosts, ArrayList<JButton> ghostButtons){
        Component[] evidencePanelComponents = evidencePanel.getComponents();
        for (Component comp : evidencePanelComponents) {
            if (comp instanceof JCheckBox) {
                ((JCheckBox) comp).addItemListener(e -> {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        if (comp instanceof JCheckBox) {
                            enableDisableBtns(false, ghostButtons, ghosts, (JCheckBox) comp);
                        }
                    } else {
                        if(comp instanceof JCheckBox) {
                            enableDisableBtns(true, ghostButtons, ghosts, (JCheckBox) comp);
                            //comb the list for checkboxes that are checked and disable buttons accordingly
                            for(Component dcomp : evidencePanelComponents){
                                if(dcomp instanceof JCheckBox && ((JCheckBox) dcomp).isSelected()){
                                    enableDisableBtns(false, ghostButtons, ghosts, (JCheckBox) dcomp);
                                }
                            }
                        }

                    }
                });

            }
        }
    }

    private void enableDisableBtns(boolean b, ArrayList<JButton> btns, ArrayList<Ghost> ghosts, JCheckBox comp){
        for (Ghost ghost : ghosts) {
            if (!ghost.containsEvidence(comp.getText())) {
                for (JButton btn : btns) {
                    if (ghost.getName().equals(btn.getText())) {
                        btn.setEnabled(b);
                    }
                }
            }
        }

    }

    private void clearWorkspace(ArrayList<Ghost> ghosts, ArrayList<JButton> ghostButtons, JTextArea textArea){
        //reset checkboxes and button filtering
        Component[] checkBoxes = evidencePanel.getComponents();
        for(Component comp : checkBoxes){
            if(comp instanceof JCheckBox){
                ((JCheckBox) comp).setSelected(false);
                determineAvailableGhosts(ghosts, ghostButtons);
            }
        }
        //clear any open windows
        Frame[] openGhosts = JFrame.getFrames();
        for(Frame frame : openGhosts){
            if(!frame.getTitle().equals("Phasmophobia Ghost Tool")){
                frame.dispose();
            }
        }

        //clear text area
        textArea.setText("");

        //clear the equipment panel
        Component[] equipmentBoxes = equipmentPanel.getComponents();
        for(Component comp : equipmentBoxes){
            if(comp instanceof JCheckBox){
                ((JCheckBox) comp).setSelected(false);
            }
        }

    }
    private void createComponents(ArrayList<JButton> ghostButtons,
                                  ArrayList<JCheckBox> evidenceCheckBoxes,
                                  ArrayList<JCheckBox> equipmentCheckBoxes){

        for(JButton btn : ghostButtons){
            ghostPanel.add(btn);
            btn.addActionListener(e -> {
                //display a pop up window with the ghost info
                for(Ghost ghost : ghosts){
                    if(ghost.getName().equals(btn.getText())){
                        //i need this to instantiate a ghostdetailspanel when the btn is clicked.
                        //intellij keeps showing a warning.
                        GhostDetailsPanel ghostDetailsPanel = new GhostDetailsPanel(ghost);
                    }
                }

            });
        }
        for(JCheckBox chkBox : equipmentCheckBoxes){
            equipmentPanel.add(chkBox);
        }
        for(JCheckBox checkBox : evidenceCheckBoxes){
            evidencePanel.add(new JCheckBox(checkBox.getText()));

        }
        clearBtn = new JButton("Clear");
        evidencePanel.add(clearBtn);
    }
    private Border setTitledBorder(String title){
        return BorderFactory.createTitledBorder(title);
    }
}
