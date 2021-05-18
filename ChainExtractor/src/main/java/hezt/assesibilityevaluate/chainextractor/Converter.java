package hezt.assesibilityevaluate.chainextractor;

import hezt.assesibilityevaluate.chainextractor.assessibilityinformation.ButtonInformation;
import hezt.assesibilityevaluate.chainextractor.assessibilityinformation.GenericInformation;
import hezt.assesibilityevaluate.chainextractor.assessibilityinformation.MenuInformation;
import hezt.assesibilityevaluate.chainextractor.assessibilityinformation.ViewGroupInformation;
import hezt.assesibilityevaluate.chainextractor.model.stg.input.InTransitionEdge;
import hezt.assesibilityevaluate.chainextractor.util.App;
import hezt.assesibilityevaluate.chainextractor.util.FileUtil;
import org.dom4j.DocumentException;
import org.dom4j.Element;

public class Converter {
    public static GenericInformation edge2information(InTransitionEdge edge, App app) throws DocumentException {
//        String smaliPath = app.getSmaliPath(edge.getSrcNode().getName());
//        String activityId = FileUtil.getLayoutIdFromSmali(smaliPath);
//        Element activityNode = FileUtil.getNodeByIdFromPublic(activityId,app.getPublicPath());
//        String activityLayoutFile = app.getActivityLayoutPath(activityNode.attribute("name").getValue());
//        Element child = (Element) FileUtil.getNodeByIdFromPublic(edge.getEdgeTag().getResId().toString(),app.getPublicPath());

        GenericInformation information;
        String resId = edge.getEdgeTag().getResId();
        String typeOfUiElement = edge.getEdgeTag().getTypeOfUiElement();
        if(typeOfUiElement.equals("item")){
            information = new MenuInformation(app,resId);
        }else if(typeOfUiElement.equals("Button")){
            information = new ButtonInformation(app,resId);
        }else if(typeOfUiElement.contains("ViewGroup")||typeOfUiElement.contains("Layout")){
            information = new ViewGroupInformation(app,resId);
        }
        else{
            information = new GenericInformation(app,resId);
        }

        return information;
    }
}
