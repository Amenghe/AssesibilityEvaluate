package hezt.assesibilityevaluate.chainextractor.util;

import java.io.File;

public class App {
    public String AppDir;
    public App(String AppDir){
        this.AppDir = AppDir;
    }

    public String getPublicPath(){
        return AppDir + File.separator + "res" + File.separator + "values" + File.separator + "public.xml";
    }
    public String getStringsPath(){
        return AppDir + File.separator + "res" + File.separator + "values" + File.separator + "strings.xml";
    }
    public String getActivityLayoutPath(String layoutFile){
        return AppDir + File.separator + "res" + File.separator + "layout" + File.separator + layoutFile +".xml";
    }
    public String getMenuLayoutPath(String layoutFile){
        return AppDir + File.separator + "res" + File.separator + "menu" + File.separator + layoutFile +".xml";
    }
    public String getMenuLayoutDir(){
        return AppDir + File.separator + "res" + File.separator + "menu" + File.separator;
    }
    public String getSmaliPath(String activityName){
        return AppDir + File.separator + "smali" + File.separator + activityName.replace(".",File.separator) +".smali";
    }
}
