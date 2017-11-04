package com.music.model;

import org.springframework.data.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractModel {

    @Override
    public String toString() {
        return this.getClass().getSimpleName().replaceAll("Entity", "");
    }

    public List<Field> getStringFields() {
        Field[] fields = this.getClass().getDeclaredFields();
        List<Field> stringFields = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getType().getSimpleName().equals("String")) {
                stringFields.add(fields[i]);
            }
        }
        return stringFields;
    }


    public void setStringFieldsValue(List<String> values) {

        List<Field> fields = getStringFields();
        System.out.println(fields.size());
        for (String s : values) {
            try {
                fields.get(values.indexOf(s)).setAccessible(true);
                fields.get(values.indexOf(s)).set(this, s);
                fields.get(values.indexOf(s)).setAccessible(false);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


}
