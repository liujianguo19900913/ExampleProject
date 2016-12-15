package com.nengxin.example.exampleproject.model.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipException;

/**
 * 作者: alan on 2016/7/27 14:54.
 */
public class FileUtils {

    /**
     * 获取根目录(应用cache 或者 SD卡)
     * <br>
     * 优先获取SD卡根目录[/storage/sdcard0]
     * <br>
     * 应用缓存目录[/data/data/应用包名/cache]
     *
     * @param context 上下文
     * @return
     */
    public static String getDiskCacheDir(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Gisformopping";
            createdir(path);
            // 优先获取SD卡根目录[/storage/sdcard0]
            return path;
        } else {
            String path = context.getCacheDir().getAbsolutePath() + File.separator + "Gisformopping";
            createdir(path);
            // 应用缓存目录[/data/data/应用包名/cache]
            return path;
        }
    }

    public static File createSDDir(Context myContext, String dirName) {
        File dir = new File(getDiskCacheDir(myContext) + File.separator + dirName);
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }

    public static boolean isFileExist(Context myContext, String fileName) {
        File file = new File(getDiskCacheDir(myContext) + File.separator + fileName);
        return file.exists();
    }

    public static void createdir(String str) {
        File dirFirstFile = new File(str);//新建一级主目录

        if (!dirFirstFile.exists()) {//判断文件夹目录是否存在

            dirFirstFile.mkdir();//如果不存在则创建

        }
    }

    /**
     * 递归删除文件和文件夹
     *
     * @param file 要删除的根目录
     */
    public static void RecursionDeleteFile(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0) {
                file.delete();
                return;
            }
            for (File f : childFile) {
                RecursionDeleteFile(f);
            }
            file.delete();
        }
    }

    /**
     * 读取文件内容
     *
     * @param strFilePath
     * @return
     */
    public static String ReadTxtFile(String strFilePath) {
        String path = strFilePath;
        String content = ""; //文件内容字符串
        //打开文件
        File file = new File(path);
        //如果path是传递过来的参数，可以做一个非目录的判断
        if (file.isDirectory()) {
            LogUtil.d("TestFile", "The File doesn't not exist.");
        } else {
            try {
                InputStream instream = new FileInputStream(file);
                if (instream != null) {
                    InputStreamReader inputreader = new InputStreamReader(instream);
                    BufferedReader buffreader = new BufferedReader(inputreader);
                    String line;
                    //分行读取
                    while ((line = buffreader.readLine()) != null) {
                        content += line + "\n";
                    }
                    instream.close();
                }
            } catch (FileNotFoundException e) {
                LogUtil.d("TestFile", "The File doesn't not exist.");
            } catch (IOException e) {
                LogUtil.d("TestFile", e.getMessage());
            }
        }
        return content;
    }

    /**
     * 从路径中提取文件名
     *
     * @param pathandname
     * @return
     */
    public String getFileName(String pathandname) {

        int start = pathandname.lastIndexOf("/");
        int end = pathandname.lastIndexOf(".");
        if (start != -1 && end != -1) {
            return pathandname.substring(start + 1, end);
        } else {
            return null;
        }

    }

    /**
     * 遍历文件夹下的文件
     * 获取路径中文件夹名字
     *
     * @param filePath
     */
    public static ArrayList<String> traversewjj(String filePath) {
        ArrayList<String> fileName = new ArrayList<>();
        File file = new File(filePath);
        if (file.isDirectory() == false) {
            //// TODO: 2016/8/5
        } else {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory() == true) {
                    fileName.add(files[i].getName());
                }
            }
        }
        return fileName;
    }

    /**
     * 获取路径中文件名字
     *
     * @param filePath
     */
    public static ArrayList<String> traversewj(String filePath) {
        ArrayList<String> fileName = new ArrayList<>();
        File file = new File(filePath);
        if (file.isDirectory() == false) {
            //// TODO: 2016/8/5
        } else {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory() == false) {
                    Log.e("info", "files[i].getName()" + files[i].getName());
                    String str[] = files[i].getName().split("[.]");
                    fileName.add(str[0]);
                }
            }
        }
        return fileName;
    }

    /**
     * 获取路径中文件名字(带后缀名)
     *
     * @param filePath
     */
    public static ArrayList<String> traverse(String filePath) {
        ArrayList<String> fileName = new ArrayList<>();
        File file = new File(filePath);
        if (file.isDirectory() == false) {
        } else {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory() == false) {
                    Log.e("info", "files[i].getName()" + files[i].getName());
                    String str = files[i].getName();
                    fileName.add(str);
                }
            }
        }
        return fileName;
    }

    /**
     *  解压文件
     * @param archive   压缩文件路径
     * @param decompressDir  解压路径
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ZipException
     */
    public static void unZipFile(String archive, String decompressDir) throws IOException, FileNotFoundException, ZipException {
        BufferedInputStream bi;
        ZipFile zf = new ZipFile(archive, "GBK");
        Enumeration e = zf.getEntries();
        while (e.hasMoreElements()) {
            ZipEntry ze2 = (ZipEntry) e.nextElement();
            String entryName = ze2.getName();
            String path = decompressDir + "/" + entryName;
            if (ze2.isDirectory()) {
                System.out.println("正在创建解压目录 - " + entryName);
                File decompressDirFile = new File(path);
                if (!decompressDirFile.exists()) {
                    decompressDirFile.mkdirs();
                }
            } else {
                System.out.println("正在创建解压文件 - " + entryName);
                String fileDir = path.substring(0, path.lastIndexOf("/"));
                File fileDirFile = new File(fileDir);
                if (!fileDirFile.exists()) {
                    fileDirFile.mkdirs();
                }
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(decompressDir + "/" + entryName));
                bi = new BufferedInputStream(zf.getInputStream(ze2));
                byte[] readContent = new byte[1024];
                int readCount = bi.read(readContent);
                while (readCount != -1) {
                    bos.write(readContent, 0, readCount);
                    readCount = bi.read(readContent);
                }
                bos.close();
            }
        }
        zf.close();
    }

    /**
     * 复制单个文件
     *
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (!oldfile.exists()) { //文件不存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }

    }

    /**
     * 复制整个文件夹内容
     *
     * @param oldPath String 原文件路径 如：c:/fqf
     * @param newPath String 复制后路径 如：f:/fqf/ff
     * @return boolean
     */
    public static void copyFolder(String oldPath, String newPath) {

        try {
            (new File(newPath)).mkdirs(); //如果文件夹不存在 则建立新文件夹
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }

                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath + "/" +
                            (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {//如果是子文件夹
                    copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("复制整个文件夹内容操作出错");
            e.printStackTrace();

        }
    }
}
