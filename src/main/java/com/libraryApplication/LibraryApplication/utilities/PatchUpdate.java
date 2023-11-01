package com.libraryApplication.LibraryApplication.utilities;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;

public class PatchUpdate {
    public static <T> T update(T oldObject, Object newObject) {
        Field[] fields = newObject.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach((key) -> {
            var field = ReflectionUtils.findField(oldObject.getClass(), key.getName());
            Object value = null ;
            try {
                key.setAccessible(true);
                value = key.get(newObject);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            if (field != null && value != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, oldObject, value);
            }
        });
        return oldObject;
    }
}
