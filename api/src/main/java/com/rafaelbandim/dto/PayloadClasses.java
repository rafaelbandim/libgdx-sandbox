package com.rafaelbandim.dto;

import com.rafaelbandim.network.BodyType;
import com.rafaelbandim.network.GameRequest;
import com.rafaelbandim.network.GameResponse;
import com.rafaelbandim.network.Status;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PayloadClasses {

    public PayloadClasses() {
    }

    public static List<Class<?>> getClasses() {
        /*InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream("com.rafaelbandim.dto".replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, "com.rafaelbandim.dto"))
                .forEach(System.out::println);*/
        List<Class<?>> list = new LinkedList<>();
        list.add(GameRequest.class);
        list.add(GameResponse.class);
        list.add(ArrayList.class);
        list.add(BodyType.class);
        list.add(PlayersDTO.class);
        list.add(PlayerDTO.class);
        list.add(Vector2DTO.class);
        list.add(Status.class);
        list.add(MoveInputDTO.class);
        return list;
    }

    private static Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }
}
