package hezt.assesibilityevaluate.chainextractor;

import hezt.assesibilityevaluate.chainextractor.model.stg.input.InSTG;
import hezt.assesibilityevaluate.chainextractor.model.stg.input.InTransitionEdge;
import hezt.assesibilityevaluate.chainextractor.util.CmdLineParser;
import hezt.assesibilityevaluate.chainextractor.util.FileUtil;

import java.util.List;

public class Main {
    public static void main(String args[]) throws Exception {
        CmdLineParser parser = new CmdLineParser(args);
        InSTG stg = InSTG.getInstance(parser.getStgPath());
        FileUtil.decompressionApk(parser.getApkPath(),parser.getAppPath());
        List<List<InTransitionEdge>> chains = stg.getChains();
        for(List<InTransitionEdge> chain:chains){
            for(InTransitionEdge edge:chain){
                System.out.print(edge);
            }
            System.out.println("_______________");
        }
    }
}
