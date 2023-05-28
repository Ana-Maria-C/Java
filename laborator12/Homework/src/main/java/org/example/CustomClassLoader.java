package org.example;

import java.io.File;
import java.io.FileInputStream;

public class CustomClassLoader extends ClassLoader {
    private File baseDir;

    public CustomClassLoader(File baseDir) {
        this.baseDir = baseDir;
    }

    public Class<?> loadClass(String className) throws ClassNotFoundException {
        String fileName = className.replace('.', File.separatorChar) + ".class";
        File classFile = new File(baseDir, fileName);

        try (FileInputStream fis = new FileInputStream(classFile)) {
            byte[] classBytes = new byte[(int) classFile.length()];
            fis.read(classBytes);
            return defineClass(className, classBytes, 0, classBytes.length);
        } catch (Exception e) {
            throw new ClassNotFoundException("Failed to load class: " + className, e);
        }
    }
}
