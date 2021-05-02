package hezt.assesibilityevaluate.chainextractor.util;

public class PublicNode {
    public String type;
    public String name;
    public String id;
    public PublicNode(String record){
        record = record.substring(record.indexOf("<"),record.lastIndexOf(">")+1);
        String attrs[] = record.split(" ");
        this.type = attrs[1].substring(6,attrs[1].length()-1);
        this.name = attrs[2].substring(6,attrs[2].length()-1);
        this.id = attrs[3].substring(4,attrs[3].length()-1);
    }
}
