package org.example;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class Analyzer {
    private String path;
    private List<Class<?>> testedClasses;

    public Analyzer(String path) {
        this.path = path;
        this.testedClasses = new ArrayList<>();
        analyzeInput(path);
        invokeTestMethods();
        printStatistics();
    }

    private void analyzeInput(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            analyzeClassesInDirectory(file);
        } else if (file.isFile()) {
            String fileName = file.getName();
            if (fileName.endsWith(".jar")) {
                analyzeClassesInJar(file);
            } else if (fileName.endsWith(".class")) {
                analyzeSingleClass(file);
            }
        }
    }

    private void analyzeClassesInDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                analyzeInput(file.getAbsolutePath());
            }
        }
    }

    private void analyzeClassesInJar(File jarFile) {
        try {
            CustomClassLoader classLoader = new CustomClassLoader(jarFile);
            analyzeClassesInDirectory(jarFile.getParentFile(), classLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void analyzeSingleClass(File classFile) {
        try {
            CustomClassLoader classLoader = new CustomClassLoader(classFile.getParentFile());
            String className = getClassNameFromFile(classFile);
            Class<?> clazz = classLoader.loadClass(className);
            analyzeClass(clazz);
            if (clazz.isAnnotationPresent(Test.class)) {
                testedClasses.add(clazz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getClassNameFromFile(File classFile) {
        String filePath = classFile.getAbsolutePath();
        String className = filePath.substring(filePath.indexOf("classes") + 8, filePath.lastIndexOf('.'))
                .replace(File.separator, ".");
        return className;
    }

    private void analyzeClass(Class<?> clazz) {
        System.out.println("Class: " + clazz.getName());

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(" - " + method.getName());
        }

        System.out.println();
    }

    private void invokeTestMethods() {
        for (Class<?> clazz : testedClasses) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Test.class) && Modifier.isStatic(method.getModifiers()) && method.getReturnType().equals(void.class) && method.getParameterCount() == 0) {
                    System.out.println("Invoking test method: " + method.getName());
                    invokeTestMethod(clazz, method);
                }
            }
        }
    }

    private void invokeTestMethod(Class<?> clazz, Method method) {
        try {
            Object instance = null;

            if (!Modifier.isStatic(method.getModifiers())) {
                instance = clazz.getDeclaredConstructor().newInstance();
            }

            Class<?>[] parameterTypes = method.getParameterTypes();
            Object[] arguments = new Object[parameterTypes.length];
            for (int i = 0; i < parameterTypes.length; i++) {
                Class<?> parameterType = parameterTypes[i];
                arguments[i] = generateMockValue(parameterType);
            }

            method.invoke(instance, arguments);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object generateMockValue(Class<?> parameterType) {
        if (parameterType.equals(int.class)) {
            return 0;
        } else if (parameterType.equals(String.class)) {
            return "mock";
        }
        return null;
    }

    private void printStatistics() {
        int totalClasses = testedClasses.size();
        int totalTestMethods = 0;

        for (Class<?> clazz : testedClasses) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Test.class) && method.getReturnType().equals(void.class) && method.getParameterCount() == 0) {
                    totalTestMethods++;
                }
            }
        }

        System.out.println("Statistics:");
        System.out.println("Total classes with @Test annotation: " + totalClasses);
        System.out.println("Total test methods: " + totalTestMethods);
    }
}