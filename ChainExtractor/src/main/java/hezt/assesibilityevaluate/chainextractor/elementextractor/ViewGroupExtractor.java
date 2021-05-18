package hezt.assesibilityevaluate.chainextractor.elementextractor;

import hezt.assesibilityevaluate.chainextractor.util.App;
import hezt.assesibilityevaluate.chainextractor.util.FileUtil;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;

public class ViewGroupExtractor {
    public static Element extractElementFromXmlFile(App app, String elementId) throws DocumentException {
        Logger logger = Logger.getLogger(ViewGroupExtractor.class);
        SAXReader reader = new SAXReader();
        elementId = app.getIdFromPublicXml(elementId);
        for(File file:app.layoutFiles){
            Document document = reader.read(file);
            Element node = FileUtil.findNodeHelper(document.getRootElement(),elementId);
            if(node != null){
                logger.info("找到id为"+elementId+"的布局元素");
                return node;
            }
        }
        logger.info("没有找到id为"+elementId+"的布局元素");
        return null;
    }
}
