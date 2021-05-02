package hezt.assesibilityevaluate.chainextractor.util;

import java.io.*;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

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
    public static PublicNode getNodeByIdFromPublic(String id,String path){
        Logger logger = Logger.getLogger(FileUtil.class);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File((path))));
            String record = "";
            while((record = reader.readLine())!=null){
                if(record.contains(id)){
                    logger.info("找到ID:"+id+"对应节点");
                    break;
                }
            }
            reader.close();
            if(record.contains(id)){
                return new PublicNode(record);
            }
            logger.error("未找到"+id+"对应节点");
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

}
