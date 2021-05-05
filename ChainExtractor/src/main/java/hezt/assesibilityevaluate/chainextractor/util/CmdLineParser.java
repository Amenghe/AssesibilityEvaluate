package hezt.assesibilityevaluate.chainextractor.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CmdLineParser {
    String apkName;
    Properties properties;
    public CmdLineParser(String args[]) throws Exception {
        for(String s:args){
            if(s.endsWith(".apk")){
                this.apkName = s;
            }
        }
        if(this.apkName == null){
            throw new Exception("没有输入ap文件名");
        }
        this.properties = new Properties();
        File propertiesFile = new File(System.getProperty("user.dir")+"\\ChainExtractor\\src\\main\\resources\\androidapp.properties");
        InputStream in = new FileInputStream(propertiesFile);
        properties.load(in);

    }
    public CmdLineParser(String apkName) throws IOException {
        this.apkName = apkName;
        this.properties = new Properties();
        this.properties = new Properties();
        File propertiesFile = new File(System.getProperty("user.dir")+"\\ChainExtractor\\src\\main\\resources\\androidapp.properties");
        InputStream in = new FileInputStream(propertiesFile);
        properties.load(in);
    }
    public String getApkPath(){
        return System.getProperty("user.dir")+"\\ChainExtractor\\"+
                properties.getProperty("apkDir") + apkName;
    }
    public String getStgPath(){
        return System.getProperty("user.dir")+"\\ChainExtractor\\"+
                properties.getProperty("stgDir") + apkName.substring(0,apkName.lastIndexOf(".apk"))+"_stg.xml";
    }
    public String getAppPath(){
        return System.getProperty("user.dir")+"\\ChainExtractor\\"+
                properties.getProperty("outDir") + apkName.substring(0,apkName.lastIndexOf(".apk"));
    }

}
