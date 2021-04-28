import com.thoughtworks.xstream.XStream;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class t1 {
    @Test
    public void xml(){
        person p1 = new person("liujiadong","21",null);
        person p2 = new person("fangchao","21",null);
        person p3 = new person("hezhentao","21",null);
        p3.addFriend(p1);
        p3.addFriend(p2);
        p1.addFriend(p2);
        p1.addFriend(p3);
        p2.addFriend(p1);
        p2.addFriend(p3);
        XStream xStream = new XStream();
        xStream.setMode(XStream.);
        System.out.println(xStream.toXML(p3));
    }

}
class person{
    String name;
    String age;
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
