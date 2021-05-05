package hezt.assesibilityevaluate.chainextractor.assessibilityinformation;

import hezt.assesibilityevaluate.chainextractor.elementextractor.GenericExtractor;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.apache.log4j.Logger;

public class GenericInformation {
    public Element element;

    public GenericInformation(String xmlFilePath,String id){
        this.element = GenericExtractor.extractElementFromXmlFile(xmlFilePath,id);
    }

    public GenericInformation(Element element){
        this.element = element;
    }

    public GenericInformation(){};

    public  String getText(){
        Logger logger = Logger.getLogger(String.valueOf(GenericInformation.class));
        try{
            Attribute textAtrr = element.attribute("text");
            if(textAtrr == null){
                return null;
            }else{
                return textAtrr.getValue();
            }
        }catch(Exception e){
            logger.error("未在xml文件中找到节点");
        }
        return null;
    }
    public  String getContentDescription(){
        Logger logger = Logger.getLogger(String.valueOf(GenericInformation.class));
        try{
            Attribute contentDescriptionAtrr = element.attribute("contentDescription");
            if(contentDescriptionAtrr == null){
                return null;
            }else{
                return contentDescriptionAtrr.getValue();
            }
        }catch(Exception e){
            logger.error("未在xml文件中找到节点");
        }
        return null;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    @Override
    public String toString(){
        return "{text:"+getText()+";"+"contenDescription:"+getContentDescription()+"}";
    }

}
