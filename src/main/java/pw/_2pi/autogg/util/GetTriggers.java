/*
 * Decompiled with CFR 0.139.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.IOUtils
 */
package pw._2pi.autogg.util;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.io.IOUtils;
import pw._2pi.autogg.gg.AutoGG;

public class GetTriggers
implements Runnable {
    @Override
    public void run() {
        try {
            String rawTriggers = IOUtils.toString((URL)new URL("https://gist.githubusercontent.com/minemanpi/72c38b0023f5062a5f3eba02a5132603/raw/triggers.txt"));
            AutoGG.getInstance().setTriggers(new ArrayList<String>(Arrays.asList(rawTriggers.split("\n"))));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

