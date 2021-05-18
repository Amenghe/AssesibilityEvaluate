import hezt.assesibilityevaluate.chainextractor.elementextractor.ButtonExtractor;
import hezt.assesibilityevaluate.chainextractor.util.FileUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;


public class FileUtilTest {
    @Test
    public void testGetLayoutIdFromSmali(){
        String ID = FileUtil.getLayoutIdFromSmali("C:\\Users\\heztw\\Downloads\\out\\" +
                "smali\\com\\smartwho\\SmartQuickSettings\\SettingActivity.smali");
        System.out.println(Integer.getInteger("0x10")==Integer.getInteger("16"));
    }
    @Test
    public void testGetNodeById(){
        Element node = FileUtil.getNodeByIdFromPublic("0x7f030005","E:\\GoalExplorerDir\\sootOutput\\SmartQuickSettings_v2.3.3_apkpure.com\\res\\values\\public.xml");
        System.out.println(node.attribute("type").getValue()+"  "+node.attribute("id").getValue()+"  ");
    }

    @Test
    public void testFindNodeFromActivity(){
        String activityPath = "E:\\GoalExplorerDir\\sootOutput\\SmartQuickSettings_v2.3.3_apkpure.com\\res\\layout\\setting_activity.xml";
        //String activityPath = "C:\\Users\\heztw\\Desktop\\test.xml";
        String id = "textTitle";
        Element element = FileUtil.findNodeFromActivity(activityPath,id);
        System.out.println(element);
    }
//    @Test
//    public void testExtractTextFromActivity() throws DocumentException {
//        String activityPath = "E:\\GoalExplorerDir\\sootOutput\\SmartQuickSettings_v2.3.3_apkpure.com\\res\\layout\\setting_activity.xml";
//        //String activityPath = "C:\\Users\\heztw\\Desktop\\test.xml";
//        String id = "textTitle";
//        Element element = ButtonExtractor.extractElementFromXmlFile(activityPath,id);
//        System.out.println(element.getQualifiedName());
//    }
    @Test
    public void testGetTextByNameFromString(){
       String path = "E:\\GoalExplorerDir\\sootOutput\\SmartQuickSettings_v2.3.3_apkpure.com\\res\\values\\strings.xml";
       String name = "list_detail_bright_mode_a";
       System.out.println(FileUtil.getTextByNameFromString(path,name));
    }
    @Test
    public void testFindNodeFromMenu() throws DocumentException {
        String path = "D:\\IdeaProjects\\AssesibilityEvaluate\\ChainExtractor\\outDir\\QuranforAndroid_v2.7.5-p1_apkpure.com\\res\\menu";
        String id = "about";
        Element element = FileUtil.findNodeFromMenu(path,id);
        System.out.println(element);
    }
    @Test
    public void testQName() throws DocumentException {
//        SAXReader reader = new SAXReader();
//        Document document = reader.read(new File("C:\\Users\\heztw\\Desktop\\QName.xml"));
//        Element root = document.getRootElement();
//        System.out.println(root.getQName().getName());
        String a = "@string/xxx";
        System.out.println(a.substring(8,a.length()));
    }

}
