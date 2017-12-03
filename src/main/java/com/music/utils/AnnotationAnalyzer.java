package com.music.utils;

import com.music.entity.AbstractModel;
import com.music.utils.Annotations.Getter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnnotationAnalyzer {
    public static List<Method> findGetters(AbstractModel model) throws Exception {
        List<Method> result = new ArrayList<Method>(10);
        Method res[] = new Method[model.getClass().getDeclaredFields().length-1];
        Method methods[] = model.getClass().getMethods();
        for (Method method: methods){
            if(method.isAnnotationPresent(Getter.class)){

                res[method.getAnnotation(Getter.class).num()-1] = method;
            }
        }
        return new ArrayList<>(Arrays.asList(res));
    }
}
