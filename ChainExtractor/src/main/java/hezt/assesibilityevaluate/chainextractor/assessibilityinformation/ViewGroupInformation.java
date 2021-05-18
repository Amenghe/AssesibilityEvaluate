package hezt.assesibilityevaluate.chainextractor.assessibilityinformation;

import hezt.assesibilityevaluate.chainextractor.elementextractor.ViewGroupExtractor;
import hezt.assesibilityevaluate.chainextractor.util.App;
import org.dom4j.DocumentException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewGroupInformation extends GenericInformation{
    public ViewGroupInformation(App app, String elementId) throws DocumentException {
        super(app,elementId);
        //this.element = ViewGroupExtractor.extractElementFromXmlFile(app,elementId);
    }
    @Override
    public Map<String, List<String>> getSpeakableText(){
        Map<String, List<String>> speakableText= super.getSpeakableText();
        //speakableText.put("transitionText",speakableText.get("allText"));
        return speakableText;
    }
}
