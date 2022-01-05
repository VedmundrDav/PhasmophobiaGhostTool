import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EvidencePanel extends JPanel {
    private ArrayList<Evidence> evidences;
    private ArrayList<GhostCheckBox> ghostCheckBoxes;

    private ArrayList<Ghost> ghosts;
    EvidencePanel(){
        evidences = getEvidences();
        ghostCheckBoxes = getEvidenceAsCheckBoxes(evidences);
        setBackground(GUIConstants.backGroundColor);
        createComponents();

    }
    private ArrayList<Evidence> getEvidences(){
        return GhostProcessor.readEvidenceXMLIntoEvidence();
    }

    private ArrayList<GhostCheckBox> getEvidenceAsCheckBoxes(ArrayList<Evidence> evidences){
        ArrayList<GhostCheckBox> ghostCheckBoxes = new ArrayList<>();
        for(Evidence evidence : evidences){
            GhostCheckBox checkBox = new GhostCheckBox(evidence.getName());
            ghostCheckBoxes.add(checkBox);
        }
        return ghostCheckBoxes;
    }
    private void createComponents(){
        for(GhostCheckBox checkBox : ghostCheckBoxes){
            add(checkBox);
            checkBox.setBackground(GUIConstants.backGroundColor);
            checkBox.setForeground(Color.white);

        }

    }
    public void reset(){
        for(GhostCheckBox chkBox : ghostCheckBoxes){
            if(chkBox.isSelected()){
                chkBox.setSelected(false);
            }
        }
    }

    public ArrayList<GhostCheckBox> getEvidence(){
        return ghostCheckBoxes;
    }
    public void setGhosts(ArrayList<Ghost> ghosts) {
        this.ghosts = ghosts;
    }
}
