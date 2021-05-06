package hezt.assesibilityevaluate.chainextractor;

import hezt.assesibilityevaluate.chainextractor.assessibilityinformation.ButtonInformation;
import hezt.assesibilityevaluate.chainextractor.assessibilityinformation.GenericInformation;
import hezt.assesibilityevaluate.chainextractor.assessibilityinformation.MenuInformation;
import hezt.assesibilityevaluate.chainextractor.model.stg.input.InTransitionEdge;
import hezt.assesibilityevaluate.chainextractor.util.App;
import hezt.assesibilityevaluate.chainextractor.util.FileUtil;
import org.dom4j.Element;

public class Converter {
    public static GenericInformation edge2information(InTransitionEdge edge, App app){
        String smaliPath = app.getSmaliPath(edge.getSrcNode().getName());
        String activityId = FileUtil.getLayoutIdFromSmali(smaliPath);
        Element activityNode = FileUtil.getNodeByIdFromPublic(activityId,app.getPublicPath());
        String activityLayoutFile = app.getActivityLayoutPath(activityNode.attribute("name").getValue());
        Element child = (Element) FileUtil.getNodeByIdFromPublic(edge.getEdgeTag().getResId().toString(),app.getPublicPath());
        GenericInformation information;
        if(edge.getEdgeTag().getTypeOfUiElement().equals("item")){
            information = new MenuInformation(app.getMenuLayoutDir(),child.attribute("name").getValue());
        }else if(edge.getEdgeTag().getTypeOfUiElement().equals("Button")){
            information = new ButtonInformation(activityLayoutFile,child.attribute("name").getValue());
        }else{
            information = new GenericInformation(activityLayoutFile,child.attribute("name").getValue());
        }
        return information;
    }
}
