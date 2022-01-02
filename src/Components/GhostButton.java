import javax.swing.*;

public class GhostButton extends JButton {

    GhostButton(String btnText){
        setText(btnText);
        setBackground(GUIConstants.btnColor);
    }

}
