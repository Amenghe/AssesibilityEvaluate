package hezt.assesibilityevaluate.chainextractor.util;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
public class App {
    public String AppDir;
    public Element public_xml;
    public Map<String,String> strings;
    public File[] layoutFiles;
    public App(String AppDir){
        this.AppDir = AppDir;
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(new File(getPublicPath()));
            this.public_xml = document.getRootElement();
            document = reader.read(new File(getStrings()));
            Element stringsRoot = document.getRootElement();
            this.strings = new HashMap<String,String>();
            for(Iterator it = stringsRoot.elementIterator();it.hasNext();){
                Element e = (Element) it.next();
                strings.put(e.attribute("name").getValue(),e.getText());
            }
            File layout = new File(getLayoutDir());
            this.layoutFiles = layout.listFiles();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
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
    public String getStrings(){
        return AppDir + File.separator + "res" + File.separator + "values" + File.separator + "strings" +".xml";
    }
    public String getLayoutDir(){
        return AppDir + File.separator + "res" + File.separator + "layout" + File.separator ;
    }

    public String getIdFromPublicXml(String id){
        Logger logger = Logger.getLogger(this.getClass());
        String tag = "id";
        if(id.charAt(0) > '9' || id.charAt(0) < '0' ){
            tag = "name";
            logger.info("找到id为"+id+"的节点");
            return id;
        }else{
            id = hezt.assesibilityevaluate.chainextractor.util.FileUtil.convert2Hex(id);
        }
        for(Iterator it = public_xml.elementIterator(); it.hasNext();){
            Element node = (Element) it.next();
            Attribute attribute = node.attribute(tag);
            if(attribute.getValue().equals(id)){
                logger.info("在public.xml中找到id为"+attribute.getValue()+"的节点");
                return node.attribute("name").getValue();
            }
        }
        logger.error("未在public.xml中找到id为"+id+"的节点");
        return null;
    }

    public void copyFile(File src,File des) throws IOException {
        FileInputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(des);
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();

    }
    public void menu2Layout() throws IOException {
        File files = new File(getMenuLayoutDir());
        for(File f:files.listFiles()){
            File out = new File(getLayoutDir() + f.getName());
            copyFile(f,out);
        }
    }
//    public Element findNodeFromMenu(String id){
//        id = getIdFromPublicXml(id);
//        if(id == null)return null;
//
//        try {
//            return FileUtil.findNodeFromMenu(getMenuLayoutDir(),id);
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public Element findeNodeFromLayout(String id) throws DocumentException {
//        id = getIdFromPublicXml(id);
//        for(File file:layoutFiles){
//            Element node = FileUtil.findNodeHelper2(file,id);
//            if(node != null){
//                return node;
//            }
//        }
//        return null;
//    }
//
//    public Element findViewGroupFromLayout(String id) throws DocumentException {
//        Logger logger = Logger.getLogger(this.getClass());
//        SAXReader reader = new SAXReader();
//        id = getIdFromPublicXml(id);
//        for(File file:layoutFiles){
//            Document document = reader.read(file);
//            Element node = FileUtil.findNodeHelper(document.getRootElement(),id);
//            if(node != null){
//                logger.info("找到id为"+id+"的布局元素");
//                return node;
//            }
//        }
//        logger.info("没有找到id为"+id+"的布局元素");
//        return null;
//    }

}
