public class Ghost {

    private String name;
    private String evidence_1;
    private String evidence_2;
    private String evidence_3;
    private String Strength;
    private String Weakness;

    public Ghost(){

    }
    public Ghost(String name, String evidence_1, String evidence_2, String evidence_3, String strength, String weakness) {
        this.name = name;
        this.evidence_1 = evidence_1;
        this.evidence_2 = evidence_2;
        this.evidence_3 = evidence_3;
        Strength = strength;
        Weakness = weakness;
    }

    public boolean containsEvidence(String ev){
        if(!ev.equals(this.evidence_1) &&
           !ev.equals(this.evidence_2) &&
           !ev.equals(this.evidence_3)){
            return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEvidence_1() {
        return evidence_1;
    }

    public void setEvidence_1(String evidence_1) {
        this.evidence_1 = evidence_1;
    }

    public String getEvidence_2() {
        return evidence_2;
    }

    public void setEvidence_2(String evidence_2) {
        this.evidence_2 = evidence_2;
    }

    public String getEvidence_3() {
        return evidence_3;
    }

    public void setEvidence_3(String evidence_3) {
        this.evidence_3 = evidence_3;
    }

    public String getStrength() {
        return Strength;
    }

    public void setStrength(String strength) {
        Strength = strength;
    }

    public String getWeakness() {
        return Weakness;
    }

    public void setWeakness(String weakness) {
        Weakness = weakness;
    }
}
