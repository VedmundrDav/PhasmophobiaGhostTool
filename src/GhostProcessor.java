import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GhostProcessor {
    private final static String ghostPath = "ghosts.xml";
    private final static String equipmentPath = "equipment.xml";
    private final static String evidencePath = "evidence.xml";


    public static ArrayList<Ghost> readGhostXMLIntoGhost(){
        //File f = new File(ghostPath);
        InputStream file = GhostProcessor.class.getResourceAsStream(ghostPath);
        ArrayList<Ghost> ghosts = new ArrayList<>();
        try{
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.parse(file);
            NodeList list = doc.getElementsByTagName("ghost");
            for(int i = 0; i < list.getLength(); i++){
                Ghost ghost = new Ghost();
                Node ghostNode = list.item(i);
                if(ghostNode.getNodeType() == Node.ELEMENT_NODE) {
                    if(ghostNode.getNodeName().equals("ghost")){
                        NodeList content = ghostNode.getChildNodes();
                        for(int j = 0; j < content.getLength(); j++){
                            Node contentNode = content.item(j);
                            if(contentNode.getNodeName().equals("type")){
                                //System.out.println(contentNode.getTextContent());
                                ghost.setName(contentNode.getTextContent());
                            }else if(contentNode.getNodeName().equals("evidence_1")){
                                ghost.setEvidence_1(contentNode.getTextContent());
                            }else if(contentNode.getNodeName().equals("evidence_2")){
                                ghost.setEvidence_2(contentNode.getTextContent());
                            }else if(contentNode.getNodeName().equals("evidence_3")){
                                ghost.setEvidence_3(contentNode.getTextContent());
                            }else if(contentNode.getNodeName().equals("strength")){
                                ghost.setStrength(contentNode.getTextContent());
                            }else if(contentNode.getNodeName().equals("weakness")){
                                ghost.setWeakness(contentNode.getTextContent());
                            }
                        }
                    }
                    ghosts.add(ghost);
                }
            }

        }catch (IOException | SAXException | ParserConfigurationException e){
            e.printStackTrace();
        }

        return ghosts;
    }

    public static ArrayList<Equipment> readEquipmentXMLIntoEquipment(){
        //File file = new File(equipmentPath);
        InputStream file = GhostProcessor.class.getResourceAsStream(equipmentPath);
        ArrayList<Equipment> equipments = new ArrayList<>();
        try{
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.parse(file);
            NodeList list = doc.getElementsByTagName("item");
            for(int i = 0; i < list.getLength(); i++){
                Equipment equipment = new Equipment();
                Node ghostNode = list.item(i);
                if(ghostNode.getNodeType() == Node.ELEMENT_NODE) {
                    if(ghostNode.getNodeName().equals("item")){
                        NodeList content = ghostNode.getChildNodes();
                        for(int j = 0; j < content.getLength(); j++){
                            Node contentNode = content.item(j);
                            if(contentNode.getNodeName().equals("name")){
                                //System.out.println(contentNode.getTextContent());
                                equipment.setName(contentNode.getTextContent());
                            }else if(contentNode.getNodeName().equals("required")){
                                if(contentNode.getTextContent().equals("Yes")){
                                    equipment.setRequired(true);
                                }else{
                                    equipment.setRequired(false);
                                }
                            }
                        }
                    }
                    equipments.add(equipment);
                }
            }

        }catch (IOException | SAXException | ParserConfigurationException e){
            e.printStackTrace();
        }

        return equipments;

    }

    public static ArrayList<Evidence> readEvidenceXMLIntoEvidence(){
        //File file = new File(evidencePath);
        InputStream file = GhostProcessor.class.getResourceAsStream(evidencePath);
        ArrayList<Evidence> evidences = new ArrayList<>();
        try{
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.parse(file);
            NodeList list = doc.getElementsByTagName("evidence");
            for(int i = 0; i < list.getLength(); i++){
                Evidence evidence = new Evidence();
                Node ghostNode = list.item(i);
                if(ghostNode.getNodeType() == Node.ELEMENT_NODE) {
                    if(ghostNode.getNodeName().equals("evidence")){
                        NodeList content = ghostNode.getChildNodes();
                        for(int j = 0; j < content.getLength(); j++){
                            Node contentNode = content.item(j);
                            if(contentNode.getNodeName().equals("name")){
                                //System.out.println(contentNode.getTextContent());
                                evidence.setName(contentNode.getTextContent());
                            }
                        }
                    }
                    evidences.add(evidence);
                }
            }

        }catch (IOException | SAXException | ParserConfigurationException e){
            e.printStackTrace();
        }

        return evidences;
    }
}
