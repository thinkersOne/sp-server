package com.pj.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileGenerator {
    public static void generateFile(String directory, String fileName, String fileContent) {
        String filePath = directory + fileName;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(fileContent);
            System.out.println("文件生成成功！");
        } catch (IOException e) {
            System.out.println("文件生成失败：" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String directory = "/src/resources/json/";
        String fileName = "文件名.json";
        String jsonData = "{\"key\": \"value\"}"; // 替换为您需要生成的JSON数据
        generateFile(directory, fileName, jsonData);
    }

}
