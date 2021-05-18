package hezt.assesibilityevaluate.chainextractor.elementextractor;

import hezt.assesibilityevaluate.chainextractor.util.App;
import hezt.assesibilityevaluate.chainextractor.util.FileUtil;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.apache.log4j.Logger;
import java.io.File;

public class GenericExtractor {

    public  static Element extractElementFromXmlFile(App app, String elementId) throws DocumentException {
        Logger logger = Logger.getLogger(String.valueOf(GenericExtractor.class));
        elementId = app.getIdFromPublicXml(elementId);
        for(File file:app.layoutFiles){
            Element node = FileUtil.findNodeHelper2(file,elementId);
            if(node != null){
                logger.info("找到id为"+elementId+"的元素");
                return node;
            }
        }
        logger.error("没有找到id为"+elementId+"的元素");
        return null;
//        if(element == null){
//            return null;
//        }else {
//            Attribute text = element.attribute("text");
//            Attribute cont_dec = element.attribute("contentDescription");
//            String ans = "";
//            if (text != null) {
//                ans = "text:" + text.getValue();
//            }
//            if (cont_dec != null) {
//                ans += "contentDescription:" + cont_dec.getValue();
//            }
//            logger.info("找到节点无障碍信息：" + ans);
//            return ans;
//        }
    }
}
