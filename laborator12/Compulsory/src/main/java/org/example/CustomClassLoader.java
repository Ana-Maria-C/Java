package org.example;

class CustomClassLoader extends ClassLoader {
    public Class<?> defineClass(byte[] classBytes) {
        return defineClass(null, classBytes, 0, classBytes.length);
    }
}
