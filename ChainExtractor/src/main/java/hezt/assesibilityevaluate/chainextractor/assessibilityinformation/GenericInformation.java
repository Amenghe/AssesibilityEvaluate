package hezt.assesibilityevaluate.chainextractor.assessibilityinformation;

import hezt.assesibilityevaluate.chainextractor.elementextractor.GenericExtractor;
import org.dom4j.Attribute;
import org.dom4j.Element;

public class GenericInformation {
    public Element element;

    public GenericInformation(String xmlFilePath,String id){
        this.element = GenericExtractor.getInstance().extractElementFromXmlFile(xmlFilePath,id);
    }

    public GenericInformation(Element element){
        this.element = element;
    }

    public GenericInformation(){};

    public  String getText(){
        Attribute textAtrr = element.attribute("text");
        if(textAtrr == null){
            return null;
        }else{
            return textAtrr.getValue();
        }
    }
    public  String getContentDescription(){
        Attribute contentDescriptionAtrr = element.attribute("contentDescription");
        if(contentDescriptionAtrr == null){
            return null;
        }else{
            return contentDescriptionAtrr.getValue();
        }
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    @Override
    public String toString(){
        return "{text:"+getText()+";\n"+"contenDescription:"+getContentDescription()+"}";
    }

}
