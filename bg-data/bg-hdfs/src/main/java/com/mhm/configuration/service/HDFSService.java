package com.mhm.configuration.service;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MHm on 2019/7/5.
 */
@Service
public class HDFSService {

    private Configuration conf = null;
    private static FileSystem fileSystem = null;

    @Value("${hdfs.path}")
    private String path;
    @Value("${hdfs.username}")
    private String username;

    /**
     * 默认的HDFS路径，比如：hdfs://192.168.197.130:9000
     */
    private static String defaultHdfsUri;
    private static String hdfsPath;
    private static String hdfsName;
    private static final int bufferSize = 1024 * 1024 * 64;

    public HDFSService(Configuration conf,String defaultHdfsUri) {
        this.conf = conf;
        this.defaultHdfsUri = defaultHdfsUri;
    }

    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", defaultHdfsUri);
        return configuration;
    }

    @PostConstruct
    public void getPath() {
        hdfsPath = this.path;
    }

    @PostConstruct
    public void getName() {
        hdfsName = this.username;
    }

    public static String getHdfsPath() {
        return hdfsPath;
    }

    public String getUsername() {
        return username;
    }


    /**
     * 获取hdfs文件系统对象
     * @return
     */
    public static FileSystem getFileSystem(){
        try {
            if(null == fileSystem){
                fileSystem = FileSystem.get(new URI(hdfsPath),getConfiguration(),hdfsPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return fileSystem;
    }

    /**
     * 判断文件是否存在
     * @param path
     * @return
     */
    public static boolean existFile(String path){
        if(StringUtils.isEmpty(path)){
            return false;
        }
        FileSystem fileSystem = getFileSystem();
        Path srcPath = new Path(path);
        boolean isExists = false;
        try {
            isExists = fileSystem.exists(srcPath);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileSystem.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isExists;
    }

    /**
     * 创建文件夹
     * @param path
     * @return
     */
    public static boolean mkdir(String path){
        if(StringUtils.isEmpty(path)){
            return false;
        }
        if(existFile(path)){
            return true;
        }
        boolean isOk = false;
        FileSystem fileSystem = getFileSystem();
        Path srcPath = new Path(path);
        try {
            isOk = fileSystem.mkdirs(srcPath);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileSystem.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isOk;
    }

    /**
     * 查询目录下文件
     * @param path
     * @return
     */
    public static List<Map<Object,Object>> readPathInfo(String path){
        if(StringUtils.isEmpty(path)){
            return  null;
        }
        if(!existFile(path)){
            return null;
        }
        FileSystem fileSystem = getFileSystem();
        Path srcPath = new Path(path);
        List<Map<Object,Object>> returnList = new ArrayList<>();
        try {
            FileStatus[] statusArray = fileSystem.listStatus(srcPath);
            if(statusArray!=null && statusArray.length>0){
                for(FileStatus fileStatus:statusArray){
                    Map<Object,Object> map = new HashMap<Object, Object>();
                    map.put("filePath",fileStatus.getPath());
                    map.put("fileStatus",fileStatus.toString());
                    returnList.add(map);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnList;
    }

    /**
     * 创建文件
     * @param fileName 文件名及路径
     * @param content 内容
     */
    public static void createFile(String fileName,String content){
        FileSystem fileSystem = getFileSystem();
        Path newPath = new Path(fileName);
        FSDataOutputStream fos = null;
        try {
            fos =fileSystem.create(newPath);
            fos.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建文件
     * @param path
     * @param file
     */
    public static void createFile(String path,MultipartFile file){
        String fileName = file.getOriginalFilename();//原始文件名
        FileSystem fileSystem = getFileSystem();
        Path newPath = new Path(path+"/"+fileName);
        FSDataOutputStream fos = null;
        try {
            fos =fileSystem.create(newPath);
            fos.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 上传文件
     * @param srcPath
     * @param dstPath
     */
    public static void uploadFile(String srcPath,String dstPath){
        Path oldPath = new Path(srcPath);
        Path newPath = new Path(dstPath);
        FileSystem fileSystem = getFileSystem();
        try {
            fileSystem.copyFromLocalFile(oldPath,newPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(String path){
        FileSystem fileSystem = getFileSystem();
        Path srcPath = new Path(path);
        FSDataInputStream fis = null;
        try {
            fis = fileSystem.open(srcPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String lineText= "";
        StringBuffer sb = new StringBuffer();
        try {
            while((lineText = br.readLine())!=null){
                sb.append(lineText);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * 删除文件
     * @param path
     * @return
     */
    public static boolean deleteFile(String path){
        if(StringUtils.isEmpty(path)){
            return false;
        }
        if(!existFile(path)){
            return false;
        }

        FileSystem fileSystem = getFileSystem();
        Path srcPath = new Path(path);
        boolean isOk = false;
        try {
            isOk = fileSystem.deleteOnExit(srcPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isOk;
    }
}
