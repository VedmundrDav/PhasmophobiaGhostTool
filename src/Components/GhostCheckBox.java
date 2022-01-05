import javax.swing.*;
import java.util.ArrayList;

public class GhostCheckBox extends JCheckBox {

    private String checkBoxText;

    public GhostCheckBox(String checkBoxText) {
        this.checkBoxText = checkBoxText;
        setText(this.checkBoxText);
        setBackground(GUIConstants.textAndBorderColor);

    }

    public boolean isCheckBoxAPossibility(ArrayList<Ghost> ghosts, ArrayList<GhostButton> btns){
        for(Ghost ghost : ghosts){
            if(ghost.containsEvidence(checkBoxText) && ghost.getCorrespondingButton(btns).isEnabled()){
                return true;
            }
        }
        return false;
    }


    public String getCheckBoxText() {
        return checkBoxText;
    }

    public void setCheckBoxText(String checkBoxText) {
        this.checkBoxText = checkBoxText;
    }

}
