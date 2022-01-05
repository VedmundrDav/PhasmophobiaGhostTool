import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

public class GhostPanel extends JPanel {
    private ArrayList<Ghost> ghosts;
    private ArrayList<GhostButton> ghostButtons;

    GhostPanel(){
        ghosts = getGhosts();
        ghostButtons = getGhostsAsButtons(ghosts);
        GridLayout ghostBtnLayout = new GridLayout(ghostButtons.size()/2, ghostButtons.size()/2);
        ghostBtnLayout.setHgap(5);
        ghostBtnLayout.setVgap(5);
        setLayout(ghostBtnLayout);
        setBackground(GUIConstants.backGroundColor);

        createComponents();
    }

    private ArrayList<Ghost> getGhosts() {
        return GhostProcessor.readGhostXMLIntoGhost();
    }

    private ArrayList<GhostButton> getGhostsAsButtons(ArrayList<Ghost> ghosts) {
        ArrayList<GhostButton> ghostButtons = new ArrayList<>();
        for(Ghost ghost : ghosts){
            GhostButton button = new GhostButton(ghost.getName());
            button.setToolTipText("Name: " + ghost.getName() +
                                  " Evidence 1: " + ghost.getEvidence_1() +
                                  " Evidence 2: " + ghost.getEvidence_2() +
                                  " Evidence 3: " + ghost.getEvidence_3());
            ghostButtons.add(button);
        }
        return ghostButtons;
    }
    private void createComponents(){
        for(JButton btn : ghostButtons){
            btn.setBackground(GUIConstants.btnColor);
            add(btn);
            btn.addActionListener(e -> {
                //display a pop up window with the ghost info
                for(Ghost ghost : ghosts){
                    if(ghost.getName().equals(btn.getText())){
                        GhostDetailsPanel ghostDetailsPanel = new GhostDetailsPanel(ghost);
                    }
                }
            });
        }
    }
    public void determineAvailableGhosts(EvidencePanel evidencePanel){
        Component[] evidencePanelComponents = evidencePanel.getComponents();
        for (Component comp : evidencePanelComponents) {
            if (comp instanceof GhostCheckBox) {
                ((GhostCheckBox) comp).addItemListener(e -> {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        enableDisableBtns(false, ghostButtons, ghosts, (GhostCheckBox) comp);
                        enableDisableCheckBoxes(evidencePanelComponents);
                    } else {
                        enableDisableBtns(true, ghostButtons, ghosts, (GhostCheckBox) comp);
                        for (Component dcomp : evidencePanelComponents) {
                            if (dcomp instanceof JCheckBox && ((JCheckBox) dcomp).isSelected()) {
                                enableDisableBtns(false, ghostButtons, ghosts, (GhostCheckBox) dcomp);
                                enableDisableCheckBoxes(evidencePanelComponents);
                            }
                        }
                    }
                });

            }
        }
    }

    private void enableDisableCheckBoxes(Component[] evidenceCheckBoxes) {
        for (Component checkBox : evidenceCheckBoxes) {
            if (checkBox instanceof GhostCheckBox) {
                if (!((GhostCheckBox) checkBox).isCheckBoxAPossibility(ghosts, ghostButtons)) {
                    checkBox.setEnabled(false);
                } else {
                    checkBox.setEnabled(true);
                }
            }
        }
    }

    private void enableDisableBtns(boolean b, ArrayList<GhostButton> btns, ArrayList<Ghost> ghosts, GhostCheckBox comp) {
        for (Ghost ghost : ghosts) {
            if (!ghost.containsEvidence(comp.getText())) {
                for (JButton btn : btns) {
                    if (ghost.getName().equals(btn.getText())) {
                        btn.setEnabled(b);
                        if (b) {
                            btn.setBackground(GUIConstants.btnColor);
                        } else {
                            btn.setBackground(GUIConstants.disabledBtnColor);
                        }
                    }
                }
            }
        }

    }

    public void setGhosts(ArrayList<Ghost> ghosts) {
        this.ghosts = ghosts;
    }

    public ArrayList<GhostButton> getGhostButtons() {
        return ghostButtons;
    }

    public void setGhostButtons(ArrayList<GhostButton> ghostButtons) {
        this.ghostButtons = ghostButtons;
    }

    public void reset() {
        for (JButton btn : ghostButtons) {
            if (!btn.isEnabled()) {
                btn.setEnabled(false);
            }
        }
    }

    public ArrayList<Ghost> getGhostList() {
        return ghosts;
    }
}
