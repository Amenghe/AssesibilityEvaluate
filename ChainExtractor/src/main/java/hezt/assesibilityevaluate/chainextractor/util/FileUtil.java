package hezt.assesibilityevaluate.chainextractor.util;

import java.io.*;
import java.lang.management.RuntimeMXBean;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.thoughtworks.xstream.XStream;
import hezt.assesibilityevaluate.chainextractor.model.stg.input.InSTG;
import hezt.assesibilityevaluate.chainextractor.model.stg.input.InScreenNode;
import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.swing.*;

public class FileUtil {

    /*
     *从activity对应的smali文件中读出activity对应的布局文件
     *依据规律：
     *     const v0, 0x7f030005
     *
     *     invoke-virtual {p0, v0}, Lcom/smartwho/SmartQuickSettings/SettingActivity;->setContentView(I)V
     */
    public static String getLayoutIdFromSmali(String path){
        Logger logger = Logger.getLogger(FileUtil.class);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File((path))));
            String slow = "";
            String fast = "";
            while((fast = reader.readLine())!=null){
                if(fast.contains("setContentView(I)V")){
                    logger.info("找到setContentView()方法");
                    break;
                }
                if(! fast.equals("")){
                    slow = fast;
                }
            }
            reader.close();
            if(fast.contains("setContentView(I)V")){
                return slow.substring(slow.lastIndexOf(" ")+1);
            }
            return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     *@param path:public.xml的路径
     *从public文件中找与ID对应的节点
     */
    public static Element getNodeByIdFromPublic(String id,String path){
        Logger logger = Logger.getLogger(FileUtil.class);
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(new File(path));
            Element root = document.getRootElement();
            id = convert2Hex(id);
            for(Iterator it = root.elementIterator();it.hasNext();){
                Element node = (Element) it.next();
                Attribute attribute = node.attribute("id");
                if(attribute.getValue().equals(id)){
                    logger.info("在public.xml中找到id为"+id+"的节点");
                    return node;
                }
            }
        } catch (DocumentException e) {
            logger.error("创建Document对象失败");
            e.printStackTrace();
        }
        logger.error("未在public.xml中找到id为"+id+"的节点");
        return null;
    }

    /*
     *在指定activity的xml文件中找到拥有特定ID的特定类型节点
     */
    public static Element findNodeFromActivity(String activityPath,String id){
        SAXReader reader = new SAXReader();
        Logger logger = Logger.getLogger(FileUtil.class);
        try {
            Document document = reader.read(new File(activityPath));
            Element root = document.getRootElement();
            Element ans = findNodeHelper(root,id);
            if(ans != null){
                logger.info("在"+activityPath+"中找到id为"+id+"的节点");
            }else{
                logger.warn("未在"+activityPath+"中找到id为"+id+"的节点");
            }
            return ans;
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Element findNodeHelper(Element root,String id){
        for(Iterator<Element> it = root.elementIterator();it.hasNext();){
            Element child = it.next();

            Attribute attribute = child.attribute("id");
//            if(attribute!=null){
//                System.out.println(attribute.getValue());
//            }
            if(attribute!=null&&attribute.getValue().contains(id)){
                return child;
            } else{
                Element ans = findNodeHelper(child,id);
                if(ans!=null){
                    return ans;
                }
            }
        }
        return null;
    }

    /*
     *在menu的xml中找到包含指定id的item的menu
     */
    public static Element findNodeFromMenu(String menuLayoutDir,String id) throws DocumentException {
        Logger logger = Logger.getLogger(FileUtil.class);
        File file = new File(menuLayoutDir);
        if(!file.isDirectory()){
            logger.error(menuLayoutDir+"不是一个文件夹");
            return null;
        }
        for(File f:file.listFiles()){
            Element ans = findNodeHelper2(f,id);
            if(ans!=null){
                logger.info("在文件"+f.getName()+"中找到含有id为"+id+"的菜单项的menu");
                return ans;
            }
        }
        logger.error("未在所有menu布局文件中找到id为"+id+"的菜单项");
        return null;
    }
    public static Element findNodeHelper2(File file,String id) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element root = document.getRootElement();
        Element ans = findNodeHelper3(root,id);
        return ans;
    }
    public static Element findNodeHelper3(Element root,String id){
        for(Iterator<Element> it = root.elementIterator();it.hasNext();){
            Element child = it.next();

            Attribute attribute = child.attribute("id");
//            if(attribute!=null){
//                System.out.println(attribute.getValue());
//            }
            if(attribute!=null&&attribute.getValue().contains(id)){
                return root;
            } else{
                Element ans = findNodeHelper3(child,id);
                if(ans!=null){
                    return ans;
                }
            }
        }
        return null;
    }
    /*
     *将数字字符串转换为以0x开头的16进制字符串
     */
    public static  String convert2Hex(String num){
        if(num.startsWith("0x")){
            return num;
        }else{
            return "0x" + Integer.toHexString(Integer.valueOf(num));
        }
    }

    /*
     *在strings.xml文件中查找指定条目
     */
    public static String getTextByNameFromString(String path,String name){
        Logger logger = Logger.getLogger(FileUtil.class);
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(new File(path));
            Element root = document.getRootElement();
            for(Iterator it = root.elementIterator();it.hasNext();){
                Element node = (Element) it.next();
                Attribute attribute = node.attribute("name");
                if(attribute.getValue().equals(name)){
                    logger.info("在strings.xml中找到name为"+name+"的节点");
                    return node.getText();
                }
            }
        } catch (DocumentException e) {
            logger.error("创建Document对象失败");
            e.printStackTrace();
        }
        logger.error("未在strings.xml中找到name为"+name+"的节点");
        return null;
    }

    /*
     *使用XStream前必需执行的步骤，原因不详
     */
    public static void helpBuilderStg(XStream xStream){
        Set<InScreenNode> screenNodes = new HashSet<InScreenNode>();
        InScreenNode screenNode = new InScreenNode();
        screenNode.setName("s1");
        screenNodes.add(screenNode);
        InSTG stg = new InSTG();
        stg.setScreenNodeSet(screenNodes);
        xStream.toXML(stg);
        //System.out.println(xStream.toXML(stg));
    }

    /*
     *解压apk文件
     */
    public static void decompressionApk(String apkFilePath,String outDir) throws IOException {
        Logger logger = Logger.getLogger(FileUtil.class);
        File file = new File(outDir);
        if(file.exists()){
            logger.info("文件夹: "+outDir+" 已存在");
            return;
        }
        Runtime run = Runtime.getRuntime();
        Process p = run.exec("java -jar " + System.getProperty("user.dir") + "\\ChainExtractor\\lib\\apktool_2.1.1.jar d " +
                apkFilePath + " -o " + outDir);
        logger.info("文件夹: "+outDir+" 已被创建");
    }
}
