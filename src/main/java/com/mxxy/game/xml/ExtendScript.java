package com.mxxy.game.xml;

import com.mxxy.game.listener.IPanelListener;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * 扩展脚本
 *
 * @author dell
 */
public class ExtendScript {

    /**
     * 将java 编译后的.class文件 放在该目录
     */
    private String classesDir = "script";

    public static void main(String[] args) {
        ExtendScript extendScript = new ExtendScript();
        try {
            extendScript.compile("script/SelectRole.java");
            System.out.println(extendScript.loadClass("com.mxxy.extendpackage.Text") instanceof IPanelListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用JavaCompiler 编译java文件
     *
     * @param filename
     * @return
     */
    public boolean compile(String filename) {
        File dir = new File(classesDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        System.out.println("编译" + filename + " ..");
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        System.out.println(compiler);
        DiagnosticCollector diagnostics = new DiagnosticCollector();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
        Iterable compilationUnits = fileManager.getJavaFileObjectsFromStrings(Arrays.asList(filename));
        String[] options = new String[]{"-d", classesDir};
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, Arrays.asList(options),
                null, compilationUnits);
        boolean success = task.call();
        try {
            fileManager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println((success)
                ? "编译成功."
                : "编译失败!");
        return success;
    }

    public Object loadClass(String name) throws Exception {
        URLClassLoader cLoader = null;
        try {
            cLoader = new URLClassLoader(new URL[]{new File(classesDir).toURI().toURL()});
            return cLoader.loadClass(name).newInstance();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            cLoader.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            cLoader.close();
        } catch (IOException e) {
            e.printStackTrace();
            cLoader.close();
        }
        return null;
    }

    public IPanelListener loadUIScript(String panelname) {
        try {
            compile("script/" + panelname + ".java");
            return (IPanelListener) loadClass("panel." + panelname);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
