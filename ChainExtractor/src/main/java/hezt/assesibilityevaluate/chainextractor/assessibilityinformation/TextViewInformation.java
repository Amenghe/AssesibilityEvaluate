package hezt.assesibilityevaluate.chainextractor.assessibilityinformation;

import hezt.assesibilityevaluate.chainextractor.elementextractor.TextViewExtractor;
import org.dom4j.Element;

public class TextViewInformation extends GenericInformation{
    public TextViewInformation(String xmlFilePath, String id){
        super();
        this.element = TextViewExtractor.extractElementFromXmlFile(xmlFilePath,id);
    }
    public TextViewInformation(Element element){
        super(element);
    }

    @Override
    public String toString(){
        return "{text:"+getText()+","+"contenDescription:"+getContentDescription()+"}";
    }
}
