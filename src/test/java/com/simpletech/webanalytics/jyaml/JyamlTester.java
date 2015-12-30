package com.simpletech.webanalytics.jyaml;

import com.simpletech.webanalytics.util.AfReflecter;
//import org.yaml.snakeyaml.Yaml;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Jyaml 包测试类
 * Created by Administrator on 2015/10/22.
 */
public class JyamlTester {

    public static class Browsers{
        public String regex;
        public String name;
        public String version;
        public HashMap engine;
    }

//    @Test
//    public void testYaml() throws FileNotFoundException {
//        Browsers[] load = new Yaml().loadAs(ClassLoader.getSystemResourceAsStream("browsers.yml"),Browsers[].class);
//        System.out.println(JacksonUtil.toJson(load));
//    }
//
//    @Test
//    public void testYamlWrite() throws IOException, IllegalAccessException {
//        List<HashMap<String, Object>> hashMaps = testToMap(Browser.values());
//        System.out.println(JacksonUtil.toJson(hashMaps));
//        FileWriter writer = new FileWriter("browsers.yml");
//        new Yaml().dumpAll(hashMaps.iterator(),writer);
//        writer.close();
//        System.out.println("转换成功");
//    }
//
//    @Test
//    public void testYamlReader() throws IOException, IllegalAccessException {
//        List<HashMap<String, Object>> hashMaps = testToMap(Browser.values());
//        System.out.println(JacksonUtil.toJson(hashMaps));
//        FileWriter writer = new FileWriter("browsers.yml");
//        new Yaml().dumpAll(hashMaps.iterator(),writer);
//        writer.close();
//        System.out.println("转换成功");
//    }

    public List<HashMap<String, Object>> testToMap(Object[] models) throws IllegalAccessException {
        List<HashMap<String,Object>> maps = new ArrayList<>();
        for (Object model : models) {
            HashMap<String,Object> map = new LinkedHashMap<>();
            for (Field field : AfReflecter.getField(model.getClass())) {
                field.setAccessible(true);
                Object val = field.get(model);
                if (val != null) {
                    if (val instanceof Pattern) {
                        val = ((Pattern) val).pattern();
                    }
                    if (!(val instanceof String)) continue;
                    map.put(field.getName(), val);
                }
            }
            maps.add(map);
        }
        return maps;
    }
}
