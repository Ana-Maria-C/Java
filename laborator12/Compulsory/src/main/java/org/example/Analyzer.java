package org.example;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Analyzer {
    private String path;
    private Class<?> openedClass;

    public Analyzer(String path) {
        this.path = path;
        openFile(path);
        showClassInfo();
    }

    private void openFile(String path){
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            byte[] classBytes = new byte[fileInputStream.available()];
            fileInputStream.read(classBytes);
            fileInputStream.close();

            this.openedClass = new CustomClassLoader().defineClass(classBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showClassInfo(){
        String className = getClassName();
        System.out.println("Numele clasei este: " + className);

        String packageName = getPackageName();
        System.out.println("PackageName: " + packageName);


        List<Method> methodsList = getMethods();
        System.out.println("Methods: ");
        for (Method method : methodsList) {
            System.out.println(method.getName());
        }
    }

    private String getPackageName() {
        return openedClass.getPackage().getName();
    }

    private String getClassName(){
        return openedClass.getName();
    }

    private List<Method> getMethods(){
        Method[] methods = openedClass.getDeclaredMethods();

        List<Method> methodsList = new ArrayList<>();

        for (Method method : methods) {
            methodsList.add(method);
        }

        return methodsList;
    }
}