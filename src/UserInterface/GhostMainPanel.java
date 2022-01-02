import javax.swing.*;

public class GhostMainPanel extends JPanel {

    GhostMainPanel(EvidencePanel evidence, GhostPanel ghosts){
        if(evidence != null){
            add(evidence);
        }else{
            System.out.println("EVIDENCE IS NULL!");
        }
        if(ghosts != null){
            add(ghosts);
        }else{
            System.out.println("GHOSTS ARE NULL");
        }
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }
}
