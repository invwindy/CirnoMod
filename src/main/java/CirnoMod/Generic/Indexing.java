package CirnoMod.Generic;

import org.apache.commons.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Indexing {
    private static final Logger logging = LogManager.getLogger(Indexing.class);

    private static String getID(String region, String name) { return "CirnoMod." + region + "." + name; }
    public static String cardID(String name) { return getID("Card", name); }
    public static String powerID(String name) { return getID("Power", name); }
    public static String relicID(String name) { return getID("Relic", name); }

    private static String getResource(String region, String name) {
        //return "resources/" + region + "/" + name + ".png";
        logging.info("Getting Resource: region [" + region + "]; name: [" + name + "].");
        return  region + "/" + name + ".png";
    }
    public static String cardPath(String name) { return getResource("Cards", name); }
    public static String powerPath(String name) { return getResource("Powers", name); }
    public static String relicPath(String name) { return getResource("Relics", name); }
    public static String relicOutlinePath(String name) { return getResource("Relics", name + "Outline"); }

    public static String format(String f, Object ...params) {
        String res = f;
        int index = 0;
        for(Object obj : params)
        {
            res = res.replaceAll(String.format("\\{%d\\}", index), obj.toString());
            index++;
        }
        return res;
    }
}
