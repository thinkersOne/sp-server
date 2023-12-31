package com.pj.utils;

import cn.hutool.core.io.resource.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileGenerator {
    public static void generateFile(String directory, String fileName, String fileContent) {
        try{
            File writeName = new File(directory + fileName);
            if(!writeName.getParentFile().exists()){
                writeName.getParentFile().mkdirs();
            }
            if(!writeName.exists()){
                writeName.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(writeName));
            writer.write(fileContent);
            System.out.println("文件生成成功！");
            writer.close();
        } catch (IOException e) {
            System.out.println("文件生成失败：" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String directory = "src/main/resources/";
        String fileName = "aa.json";
        String jsonData = "{\"key\": \"value\"}"; // 替换为您需要生成的JSON数据
        generateFile(directory, fileName, jsonData);
    }

}
