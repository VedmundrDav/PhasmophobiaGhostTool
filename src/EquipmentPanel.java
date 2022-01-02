import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class EquipmentPanel extends JPanel {
    private ArrayList<Equipment> equipments;
    ArrayList<GhostCheckBox> equipmentCheckBoxes;
    EquipmentPanel(){
        equipments = getEquipment();
        equipmentCheckBoxes = getEquipmentAsCheckBoxes(equipments);
        sortEquipment(equipmentCheckBoxes);

        GridLayout equipmentLayout = new GridLayout(equipmentCheckBoxes.size()/5, equipmentCheckBoxes.size()/2);
        setLayout(equipmentLayout);
        setBorder(GUI.setTitledBorder("Equipment in Ghost Room"));
        setBackground(GUIConstants.backGroundColor);
        createComponents();

    }
    private ArrayList<Equipment> getEquipment(){return GhostProcessor.readEquipmentXMLIntoEquipment();}
    private ArrayList<GhostCheckBox> getEquipmentAsCheckBoxes(ArrayList<Equipment> equipments){
        ArrayList<GhostCheckBox> equipmentCheckBoxes= new ArrayList<>();
        for(Equipment equipment : equipments){
            GhostCheckBox checkBox = new GhostCheckBox(equipment.getName());
            equipmentCheckBoxes.add(checkBox);
        }
        return equipmentCheckBoxes;
    }
    private void sortEquipment(ArrayList<GhostCheckBox> list){
        for(int i = 0; i < list.size()-1; i++){
            for(int j = i + 1; j < list.size(); j++){
                if(list.get(i).getText().compareTo(list.get(j).getText()) > 0){
                    Collections.swap(list, i, j);
                }
            }
        }
    }
    private void createComponents(){
        for(GhostCheckBox checkBox : equipmentCheckBoxes){
            add(checkBox);
            checkBox.setBackground(GUIConstants.backGroundColor);
            checkBox.setForeground(Color.white);

        }
    }
    public void reset(){
        for(GhostCheckBox chkBox : equipmentCheckBoxes){
            if(chkBox.isSelected()){
                chkBox.setSelected(false);
            }
        }
    }
}
