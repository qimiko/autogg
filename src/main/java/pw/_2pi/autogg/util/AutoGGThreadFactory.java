/*
 * Decompiled with CFR 0.139.
 */
package pw._2pi.autogg.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class AutoGGThreadFactory
implements ThreadFactory {
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, "AutoGG" + this.threadNumber.getAndIncrement());
    }
}

