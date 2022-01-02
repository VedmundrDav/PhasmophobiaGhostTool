import javax.swing.*;

public class GhostCheckBox extends JCheckBox {

    GhostCheckBox(String chkBoxText){
        setText(chkBoxText);
        setBackground(GUIConstants.textAndBorderColor);
    }
}
