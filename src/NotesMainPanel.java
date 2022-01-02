import javax.swing.*;
import java.awt.*;

public class NotesMainPanel extends JPanel {
    NotesMainPanel(NotesPanel notes, EquipmentPanel equipment){
        if(notes != null){
            add(notes);
        }else{
            System.out.println("NOTES IS NULL");
        }

        if(equipment != null){
            add(equipment);
        }else{
            System.out.println("EQUIPMENT IS NULL");
        }
        setLayout(new GridLayout(1,2)); //condense into another custom panel?
    }

}
