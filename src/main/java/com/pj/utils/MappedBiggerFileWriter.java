package com.pj.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description 大文件写入类
 * @Author wqin
 * @Date 2022/12/19
 */
@Slf4j
public class MappedBiggerFileWriter {

    /**
     * 对外接口：以指定编码字符串对应的编码，向目标文件写入内容（字符串），如果没有则创建
     * @param to 目标文件全路径
     * @param content 待写入内容
     * @param charsetString 编码字符串
     * @throws IOException UnsupportedEncodingException异常
     */
    public static void write(String to, String content, String charsetString){
        byte[] bs;
        try {
            if (charsetString != null && charsetString.length() > 0){
                bs = content.getBytes(charsetString);
            }else {
                bs = content.getBytes();
            }
            write(to, bs);
        }catch (Exception e){
            log.error("write file error", e.getMessage());
        }
    }

    /**
     * 对外接口：以指定编码，向目标文件写入内容（字符串），如果没有则创建
     * @param to 目标文件全路径
     * @param content 待写入内容
     * @param charset 编码
     * @throws IOException IO异常
     */
    public static void write(String to, String content, Charset charset) throws IOException {
        byte[] bs;
        if (charset != null){
            bs = content.getBytes(charset);
        }else {
            bs = content.getBytes();
        }
        write(to, bs);
    }

    /**
     * 对外接口：以默认编码向目标文件写入内容（字符串），如果没有则创建
     * @param to 目标文件全路径
     * @param content 待写入内容
     * @throws IOException IO异常
     */
    public static void write(String to, String content) throws IOException {
        write(to, content, "");
    }

    /**
     * 对外接口：向目标文件写入内容（字节数组），如果没有则创建
     * @param to 目标文件全路径
     * @param bs 待写入内容字节数组
     * @throws IOException 异常
     */
    public static void write(String to, byte[] bs){
        File f = new File(to);
        // 通过RandomAccessFile拿到channel，然后利用MappedByteBuffer写文件
        RandomAccessFile acf = null;
        FileChannel fc;
        try {
            // 文件/目录不存在则将文件/目录创建
            if (!f.exists()){
                if (!f.getParentFile().exists()){
                    f.getParentFile().mkdirs();
                }
                f.createNewFile();
            }
            acf = new RandomAccessFile(to, "rw");
            fc = acf.getChannel();
            long offset = f.length();
            MappedByteBuffer mbuf = fc.map(FileChannel.MapMode.READ_WRITE, offset, bs.length);
            mbuf.put(bs);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (acf != null){
                try {
                    acf.close();
                } catch (IOException e) {
                    log.error("关闭文件流异常", e.getMessage());
                }
            }
        }
    }

}

