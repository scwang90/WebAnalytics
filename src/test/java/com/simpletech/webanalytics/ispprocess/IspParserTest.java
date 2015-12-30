package com.simpletech.webanalytics.ispprocess;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * IspParserTest
 * Created by root on 15-12-30.
 */
public class IspParserTest extends TestCase {

    @Test
    public void testIspFile() {
        List<String[]> buffer = new ArrayList<>();//存储ip和相应ip信息
        try (InputStream input = IspParser.class.getClassLoader().getResourceAsStream("ip-isp.txt")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.add(line.split(" "));
            }
            Collections.sort(buffer, new Comparator<String[]>() {
                @Override
                public int compare(String[] o1, String[] o2) {
                    int compare = Long.compare(IspParser.ipToLong(o1[0]),IspParser.ipToLong(o2[0]));
                    if (compare == 0) {
                        compare = Long.compare(IspParser.ipToLong(o1[1]),IspParser.ipToLong(o2[1]));
                    }
                    return compare;
                }
            });
            try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("ip-isp.txt.out"),"UTF-8"))){
                for (String[] lines : buffer) {
                    for (String token : lines) {
                        writer.print(token);
                        writer.print(' ');
                    }
                    writer.println();
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testIspParser() throws Exception {
//        System.out.println(IspParser.ipToLong("223.255.252.0"));
        System.out.println(IspParser.ispParser("113.136.175.255"));
    }
}