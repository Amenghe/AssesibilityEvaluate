import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import hezt.assesibilityevaluate.chainextractor.model.stg.input.InSTG;
import hezt.assesibilityevaluate.chainextractor.model.stg.input.InTransitionEdge;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class InSTGTest {

    @Test
    public void testGetChains() throws MalformedURLException {
        XStream xStream = new XStream();
        xStream.setClassLoader(InSTG.class.getClassLoader());
        //XStream.setupDefaultSecurity(xStream);
        xStream.setMode(XStream.NO_REFERENCES);
        xStream.autodetectAnnotations(true);
        xStream.addPermission(AnyTypePermission.ANY);
        t1.help(xStream);
        InSTG stg = (InSTG) xStream.fromXML(new URL("file:/D:\\IdeaProjects\\AssesibilityEvaluate" +
                "\\ChainExtractor\\stgDir\\QuranforAndroid_v2.7.5-p1_apkpure.com_stg.xml"));
        for(List<InTransitionEdge> chain:stg.getChains()){
            for(InTransitionEdge edge:chain){
                System.out.print(edge);
            }
            System.out.println("_______________");
        }
    }
    @Test
    public void testGetInstance(){
        String stgPath = "D:\\IdeaProjects\\AssesibilityEvaluate\\"+
                "ChainExtractor\\stgDir\\QuranforAndroid_v2.7.5-p1_apkpure.com_stg.xml";

        InSTG stg = InSTG.getInstance(stgPath);
        for(List<InTransitionEdge> chain:stg.getChains()){
            for(InTransitionEdge edge:chain){
                System.out.print(edge);
            }
            System.out.println("_______________");
        }
    }
}
