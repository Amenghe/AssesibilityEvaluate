package hezt.assesibilityevaluate.chainextractor.assessibilityinformation;

import hezt.assesibilityevaluate.chainextractor.elementextractor.GenericExtractor;
import hezt.assesibilityevaluate.chainextractor.util.App;
import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.apache.log4j.Logger;

import java.util.*;

public class GenericInformation {
    public Element element;
    public String id;
    public App app;
    public GenericInformation(App app, String id) throws DocumentException {
        if(id != null && !id.equals("")){
            this.element = GenericExtractor.extractElementFromXmlFile(app,id);
            //this.id = app.getIdFromPublicXml(id);
            this.id = id;
            this.app = app;
        }
    }

    public GenericInformation(Element element){
        this.element = element;
    }

    public GenericInformation(){};

//    public  String getText(){
//        Logger logger = Logger.getLogger(this.getClass());
//        try{
//            Attribute textAtrr = element.attribute("text");
//            if(textAtrr == null){
//                return null;
//            }else{
//                return textAtrr.getValue();
//            }
//        }catch(Exception e){
//            logger.error("未在xml文件中找到节点");
//        }
//        return null;
//    }
//    public  String getContentDescription(){
//        Logger logger = Logger.getLogger(this.getClass());
//        try{
//            Attribute contentDescriptionAtrr = element.attribute("contentDescription");
//            if(contentDescriptionAtrr == null){
//                return null;
//            }else{
//                return contentDescriptionAtrr.getValue();
//            }
//        }catch(Exception e){
//            logger.error("未在xml文件中找到节点");
//        }
//        return null;
//    }
    public  String getContentDescription(Element node){
        Logger logger = Logger.getLogger(this.getClass());
        try{
            Attribute contentDescriptionAtrr = node.attribute("contentDescription");
            if(contentDescriptionAtrr == null){
                return null;
            }else{
                String contentDescription =contentDescriptionAtrr.getValue();
                if(contentDescription.startsWith("@string/")){
                    contentDescription = this.app.strings.get(contentDescription.substring(8,contentDescription.length()));
                }
                return contentDescription;
            }
        }catch(Exception e){
            logger.error("无contentDescription");
        }
        return null;
    }

    public  String getText(Element node){
        Logger logger = Logger.getLogger(this.getClass());
        try{
            Attribute textAtrr = node.attribute("text");
            if(textAtrr == null){
                return null;
            }else{
                String text =textAtrr.getValue();
                if(text.startsWith("@string/")){
                    text = this.app.strings.get(text.substring(8,text.length()));
                }
                return text;
            }
        }catch(Exception e){
            logger.error("无Text");
        }
        return null;
    }

    public String getHint(Element node){
        Logger logger = Logger.getLogger(this.getClass());
        try{
            Attribute hintAtrr = node.attribute("hint");
            if(hintAtrr == null){
                return null;
            }else{
                String hint =hintAtrr.getValue();
                if(hint.startsWith("@string/")){
                    hint = this.app.strings.get(hint.substring(8,hint.length()));
                }
                return hint;
            }
        }catch(Exception e){
            logger.error("无hint");
        }
        return null;
    }

    public Map<String,List<String>> getSpeakableText(){
        Map<String, List<String>> speakableText= new HashMap<String,List<String>>();
        speakableText.put("allText",new LinkedList<String>());
        speakableText.put("transitionText",new LinkedList<String>());
        if(this.element == null){
            return speakableText;
        }
        /*最外层节点*/
        String contentDescription = getContentDescription(this.element);
        if(contentDescription != null && !contentDescription.contains("null")){
            speakableText.get("allText").add(contentDescription);
        }
        for(Iterator<Element> it = this.element.elementIterator(); it.hasNext();){
            Element child = it.next();
            String type = child.getQName().getName();
            if(type.contains("EditText")){
                String append = getHint(child);
                speakableText.get("allText").add(append);
                if(child.attribute("id")!=null&&child.attribute("id").getValue()!=null&&("@id/"+this.id).contains(child.attribute("id").getValue())){
                    speakableText.get("transitionText").add(append);
                }
            }else if(type.contains("TextView")){
                String append = getText(child);
                speakableText.get("allText").add(append);
                if(child.attribute("id")!=null&&child.attribute("id").getValue()!=null&&("@id/"+this.id).contains(child.attribute("id").getValue())){
                    speakableText.get("transitionText").add(append);
                }
            }else{
                String append = getContentDescription(child);
                speakableText.get("allText").add(append);
                if(child.attribute("id")!=null&&child.attribute("id").getValue()!=null&&("@id/"+this.id).contains(child.attribute("id").getValue())){
                    speakableText.get("transitionText").add(append);
                }
            }
        }
        return speakableText;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    @Override
    public String toString(){
        Map<String,List<String>> speakableText = getSpeakableText();
        String allText = "";
        String transitionText = "";
        for(String s:speakableText.get("allText")){
            allText += s + ",";
        }
        if(!allText.equals("")){
            allText = allText.substring(0,allText.length()-1);
        }
        for(String s:speakableText.get("transitionText")){
            transitionText += s ;
        }
        return "{allText:["+allText+"],"+"transitionText:["+transitionText+"]}";
    }

}
