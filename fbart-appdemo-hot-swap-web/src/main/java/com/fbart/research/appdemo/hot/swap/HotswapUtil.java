package com.fbart.research.appdemo.hot.swap;

import org.eclipse.jetty.webapp.WebAppClassLoader;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class HotswapUtil {
    private static URLClassLoader urlClassLoader = null;
    private static final String SWAP_CLASSES_URL = "/data/swap/classes";

    public static void swap() {
        try {
            URL url = new File(SWAP_CLASSES_URL).toURI().toURL();
            urlClassLoader = WebAppClassLoader.newInstance(new URL[]{url}, Thread.currentThread().getContextClassLoader());
            Class<?> loadedClass = urlClassLoader.loadClass("com.fbart.research.Employee");
            Object instance = loadedClass.newInstance();
            Method declaredMethod = loadedClass.getDeclaredMethod("say", String.class);

            String str = (String) declaredMethod.invoke(instance, "Tom");
            System.out.println(str);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void swap2() {
        try {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            ClassLoader originClassLoader = contextClassLoader;
            URL url = new File(SWAP_CLASSES_URL).toURI().toURL();
            contextClassLoader = URLClassLoader.newInstance(new URL[]{url});
            Class<?> loadedClass = contextClassLoader.loadClass("com.fbart.research.Employee");
            Thread.currentThread().setContextClassLoader(originClassLoader);
            Object instance = loadedClass.newInstance();
            Method declaredMethod = loadedClass.getDeclaredMethod("say", String.class);

            String str = (String) declaredMethod.invoke(instance, "Tom");
            System.out.println(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
