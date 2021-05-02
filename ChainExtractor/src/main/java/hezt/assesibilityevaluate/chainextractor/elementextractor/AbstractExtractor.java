package hezt.assesibilityevaluate.chainextractor.elementextractor;

import hezt.assesibilityevaluate.chainextractor.util.FileUtil;
import org.dom4j.Element;

import java.util.logging.Logger;

public abstract class AbstractExtractor {
    public   Element extractTextFromActivity(String activityPath,String elementId){
        Logger logger = Logger.getLogger(String.valueOf(this.getClass()));
        Element element = FileUtil.findNodeFromActivity(activityPath,elementId);
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
        return element;
    }
}
