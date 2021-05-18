package hezt.assesibilityevaluate.chainextractor;

import hezt.assesibilityevaluate.chainextractor.assessibilityinformation.GenericInformation;
import hezt.assesibilityevaluate.chainextractor.elementextractor.GenericExtractor;
import hezt.assesibilityevaluate.chainextractor.model.stg.input.InSTG;
import hezt.assesibilityevaluate.chainextractor.model.stg.input.InTransitionEdge;
import hezt.assesibilityevaluate.chainextractor.util.App;
import hezt.assesibilityevaluate.chainextractor.util.CmdLineParser;
import hezt.assesibilityevaluate.chainextractor.util.FileUtil;
import org.dom4j.Element;
import java.util.List;

public class Main {
    public static void main(String args[]) throws Exception {
        CmdLineParser parser = new CmdLineParser(args);
        InSTG stg = InSTG.getInstance(parser.getStgPath());
        FileUtil.decompressionApk(parser.getApkPath(),parser.getAppPath());
        Thread.currentThread().sleep(3000);
        List<List<InTransitionEdge>> chains = stg.getChains();
        App app = new App(parser.getAppPath());
        app.menu2Layout();
        Thread.currentThread().sleep(3000);
        for(List<InTransitionEdge> chain:chains){
            for(InTransitionEdge edge:chain){
                GenericInformation information = Converter.edge2information(edge,app);
                System.out.println(information.toString());
            }
            System.out.println("\n");
        }
    }
}
