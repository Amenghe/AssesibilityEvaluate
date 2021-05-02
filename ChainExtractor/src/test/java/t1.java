import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.junit.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import hezt.assesibilityevaluate.chainextractor.model.stg.input.*;

public class t1 {
    @Test
    public void xml(){
        person p1 = new person("liujiadong","21",null);
        person p2 = new person("fangchao","21",null);
        person p3 = new person("hezhentao","21",null);
        p3.addFriend(p1);
        p3.addFriend(p2);
//        p1.addFriend(p2);
//        p1.addFriend(p3);
//        p2.addFriend(p1);
//        p2.addFriend(p3);
        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
        System.out.println(xStream.toXML(p3));
    }

    @Test
    public void toBean() throws MalformedURLException {

        XStream xStream = new XStream();
        xStream.setClassLoader(InSTG.class.getClassLoader());
        //XStream.setupDefaultSecurity(xStream);
        xStream.setMode(XStream.NO_REFERENCES);
        xStream.autodetectAnnotations(true);
        xStream.addPermission(AnyTypePermission.ANY);
        help(xStream);
        InSTG stg = (InSTG) xStream.fromXML(new URL("file:/E:\\GoalExplorerDir\\sootOutput\\new1.xml"));
        System.out.println("ok");

    }
    @Test
    public void testSTG(){
        XStream xStream = new XStream();
        xStream.setClassLoader(InSTG.class.getClassLoader());
        //XStream.setupDefaultSecurity(xStream);
        xStream.setMode(XStream.NO_REFERENCES);
        xStream.autodetectAnnotations(true);
        xStream.addPermission(AnyTypePermission.ANY);
//        Set<InScreenNode> screenNodes = new HashSet<InScreenNode>();
//        InScreenNode screenNode = new InScreenNode();
//        screenNode.setName("s1");
//        screenNodes.add(screenNode);
//        InSTG stg = new InSTG();
//        stg.setScreenNodeSet(screenNodes);
//        System.out.println(xStream.toXML(stg));
        help(xStream);
        String xml = "<ScreenTransitionGraph>\n" +
                "\t<screenNodeSet>\n" +
                "<ScreenNode>\n" +
                "      <name>com.smartwho.SmartQuickSettings.SystemInfo</name>\n" +
                "      <fragments/>\n" +
                "      <dialogs/>\n" +
                "    </ScreenNode>\n" +
                "    <ScreenNode>\n" +
                "      <name>com.smartwho.SmartQuickSettings.AppSettings</name>\n" +
                "      <fragments/>\n" +
                "      <dialogs/>\n" +
                "    </ScreenNode>"+
                "  </screenNodeSet>\n" +
                "</ScreenTransitionGraph>";
        InSTG stg = (InSTG) xStream.fromXML(xml);
        System.out.println(stg);
    }

    public static void help(XStream xStream){
        Set<InScreenNode> screenNodes = new HashSet<InScreenNode>();
        InScreenNode screenNode = new InScreenNode();
        screenNode.setName("s1");
        screenNodes.add(screenNode);
        InSTG stg = new InSTG();
        stg.setScreenNodeSet(screenNodes);
        System.out.println(xStream.toXML(stg));
    }
    @Test
    public void convert2Hex(){
        String a = "10";
        if(a.startsWith("0x")){
            System.out.println(a);
        }else{
            System.out.println("0x"+Integer.toHexString(Integer.valueOf(a)));
        }
    }

}
class person{
    String name;
    String age;
    @XStreamAlias("friends")
    Set<person> friend;
    public person(String name,String age,Set<person> friend){
        this.name = name;
        this.age = age;
        this.friend = friend;
    }
    public void addFriend(person friend){
        if(this.friend == null){
            this.friend = new HashSet<person>();
        }
        this.friend.add(friend);
    }

}
