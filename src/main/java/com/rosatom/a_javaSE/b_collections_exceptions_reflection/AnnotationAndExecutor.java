package com.rosatom.a_javaSE.b_collections_exceptions_reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AnnotationAndExecutor {
    public static void main(String[] args) {
        ThreadPoolExecutor pool = new AdvancedThreadPoolExecutor(3);
        pool.execute(new TestRunnable());
//        we used `allowCoreThreadTimeOut(true)`
//        pool.shutdown();
    }

    @Repeat(4)
    private static class TestRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello");
        }
    }
}

class AdvancedThreadPoolExecutor extends ThreadPoolExecutor {
    public AdvancedThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize, corePoolSize, 1, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    }

    @Override
    public void execute(Runnable command) {
        allowCoreThreadTimeOut(true);
        Repeat repeat = Objects.requireNonNull(command).getClass().getAnnotation(Repeat.class);
        for (int i = 0; i < repeat.value(); i++) {
            super.execute(command);
        }
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Repeat {
    int value() default 1;
}
