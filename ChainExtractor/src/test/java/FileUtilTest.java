import hezt.assesibilityevaluate.chainextractor.util.FileUtil;
import hezt.assesibilityevaluate.chainextractor.util.PublicNode;
import org.dom4j.Element;
import org.junit.Test;


public class FileUtilTest {
    @Test
    public void testGetLayoutIdFromSmali(){
        String ID = FileUtil.getLayoutIdFromSmali("C:\\Users\\heztw\\Downloads\\out\\" +
                "smali\\com\\smartwho\\SmartQuickSettings\\SettingActivity.smali");
        System.out.println(Integer.getInteger("0x10")==Integer.getInteger("16"));
    }
    @Test
    public void testGetNodeById(){
        PublicNode node = FileUtil.getNodeByIdFromPublic("0x7f030005","C:\\Users\\heztw\\Downloads\\out\\res\\values\\public.xml");
        System.out.println(node.type+"  "+node.id+"  "+node.name);
    }

    @Test
    public void testFindNodeFromActivity(){
        String activityPath = "C:\\Users\\heztw\\Downloads\\out\\res\\layout\\setting_activity.xml";
        //String activityPath = "C:\\Users\\heztw\\Desktop\\test.xml";
        String id = "textTitle";
        Element element = FileUtil.findNodeFromActivity(activityPath,id);
        System.out.println(element);
    }
}
