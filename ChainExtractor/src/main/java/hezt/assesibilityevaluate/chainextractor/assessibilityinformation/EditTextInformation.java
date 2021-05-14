package hezt.assesibilityevaluate.chainextractor.assessibilityinformation;

import hezt.assesibilityevaluate.chainextractor.elementextractor.EditTextExtractor;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Element;

public class EditTextInformation extends GenericInformation {
    public EditTextInformation(String xmlFilePath,String id){
        super();
        this.element = EditTextExtractor.extractElementFromXmlFile(xmlFilePath,id);
    }
    public EditTextInformation(Element element){
        super(element);
    }

    public String getHint(){
        Logger logger = Logger.getLogger(this.getClass());
        try{
            Attribute hintAtrr = element.attribute("hint");
            if(hintAtrr == null){
                return null;
            }else{
                return hintAtrr.getValue();
            }
        }catch(Exception e){
            logger.error("未在xml文件中找到节点");
        }
        return null;
    }

    @Override
    public String toString(){
        return "{hint:"+getHint()+"}";
    }
}
