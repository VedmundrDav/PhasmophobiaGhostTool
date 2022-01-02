import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class NotesPanel extends JPanel{
    private JTextArea notesTextArea;

    NotesPanel(){
        Border border = BorderFactory.createLineBorder(Color.black);


        notesTextArea = new JTextArea(20,65);
        notesTextArea.setBackground(GUIConstants.JTextAreaColor);
        notesTextArea.setLineWrap(true);
        JScrollPane scroller = new JScrollPane(notesTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        notesTextArea.setBorder(border);
        add(scroller);

        setBorder(GUI.setTitledBorder("Notes:"));
        setBackground(GUIConstants.backGroundColor);
    }

    public void reset(){
        notesTextArea.setText("");
    }


}
