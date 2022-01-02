import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EvidencePanel extends JPanel {
    private ArrayList<Evidence> evidences;
    ArrayList<GhostCheckBox> evidenceCheckBoxes;

    EvidencePanel(){
        evidences = getEvidences();
        evidenceCheckBoxes = getEvidenceAsCheckBoxes(evidences);
        setBackground(GUIConstants.backGroundColor);
        createComponents();
    }
    private ArrayList<Evidence> getEvidences(){
        return GhostProcessor.readEvidenceXMLIntoEvidence();
    }

    private ArrayList<GhostCheckBox> getEvidenceAsCheckBoxes(ArrayList<Evidence> evidences){
        ArrayList<GhostCheckBox> evidenceCheckBoxes= new ArrayList<>();
        for(Evidence evidence : evidences){
            GhostCheckBox checkBox = new GhostCheckBox(evidence.getName());
            evidenceCheckBoxes.add(checkBox);
        }
        return evidenceCheckBoxes;
    }
    private void createComponents(){
        for(GhostCheckBox checkBox : evidenceCheckBoxes){

            add(checkBox);
            checkBox.setBackground(GUIConstants.backGroundColor);
            checkBox.setForeground(Color.white);

        }

    }
    public void reset(){
        for(GhostCheckBox chkBox : evidenceCheckBoxes){
            if(chkBox.isSelected()){
                chkBox.setSelected(false);
            }
        }
    }
}
