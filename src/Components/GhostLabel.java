import javax.swing.*;
import java.awt.*;

public class GhostLabel  extends JLabel {
    private String labelText;
    private Color foreColor;
    private Color backColor;

    GhostLabel(){

    }

    public GhostLabel(String labelText, Color foreColor, Color backColor) {
        super();
        this.labelText = labelText;
        this.foreColor = foreColor;
        this.backColor = backColor;

        super.setText(this.labelText);
        super.setForeground(this.foreColor);
        super.setBackground(this.backColor);
        super.setVisible(true);
    }

    public Color getForeColor() {
        return foreColor;
    }

    public void setForeColor(Color foreColor) {
        this.foreColor = foreColor;
    }

    public Color getBackColor() {
        return backColor;
    }

    public void setBackColor(Color backColor) {
        this.backColor = backColor;
    }

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }
}
