package hezt.assesibilityevaluate.chainextractor.textextractor;

import com.thoughtworks.xstream.io.xml.Dom4JReader;
import hezt.assesibilityevaluate.chainextractor.util.FileUtil;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.BufferedReader;
import java.util.logging.Logger;

public class ButtonExtractor extends AbstractExtractor{
    public String extractTextFromActivity(String activityPath, String elementId) {
        Logger logger = Logger.getLogger(String.valueOf(ButtonExtractor.class));
        Element element = FileUtil.findNodeFromActivity(activityPath,elementId);
        if(element == null){
            return null;
        }else{
            Attribute text = element.attribute("android:text");
            Attribute cont_dec = element.attribute("android:contentDescription");
            String ans = "";
            if(text!=null){
                ans = "text:"+text.getValue();
            }
            if(cont_dec!=null){
                ans += "contentDescription:"+cont_dec.getValue();
            }
            logger.info("找到节点无障碍信息："+ans);
            return ans;
        }
    }
}
