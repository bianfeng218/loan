package com.qp.common.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 文件处理类
 * 
 * @author GaoXiang Date: 2015-4-24
 */
public class QpFileOperator {
    
    private static QpFileOperator instance = new QpFileOperator();
    
    private QpFileOperator() {
    }
    
    /**
     * method to get FileOperator instance,thread safety
     * 
     * @return
     */
    public static QpFileOperator getInstance() {
        return instance;
    }
    
    /**
     * save the content to file
     * 
     * @param content
     * : file content
     * @param filePath
     * : absolute path
     * @return
     */
    public boolean saveToFile(String content, String filePath) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(filePath);
            bw = new BufferedWriter(fw);
            bw.write(content);
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            closeResources(bw, fw);
        }
        
        return true;
    }
    
    /**
     * is file exist
     * 
     * @param fileName
     * absolute path
     * @return
     */
    public boolean isFileExist(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * whether file empty
     * 
     * @param filePath
     * @return
     */
    public boolean isEmpty(String filePath) {
        File file = new File(filePath);
        if (file.exists() && file.length() > 0) {
            return false;
        }
        else {
            return true;
        }
    }
    
    /**
     * rename file
     * 
     * @param sourceName
     * @param destName
     * @return
     */
    public boolean renameFile(String sourceName, String destName) {
        File sourceFile = new File(sourceName);
        File destFile = new File(destName);
        return sourceFile.renameTo(destFile);
    }
    
    /**
     * force rename a file, if dest name exist, delete it first
     * 
     * @param sourceName
     * @param destName
     * @return
     */
    public boolean forceRenameFile(String sourceName, String destName) {
        if (isFileExist(destName)) {
            deleteFile(destName);
        }
        
        return renameFile(sourceName, destName);
    }
    
    /**
     * delete file, either file or directory, it should be the absolute path
     * 
     * @param fileName
     * @return
     */
    public boolean deleteFile(String fileName) {
        boolean isDelete = false;
        File file = new File(fileName);
        if (file.exists()) {
            if (file.isFile()) {
                isDelete = deleteSingleFile(fileName);
            }
            else {
                isDelete = deleteDirectory(fileName);
            }
        }
        
        return isDelete;
    }
    
    /**
     * return all the file names in the directory
     * 
     * @param path
     * @return
     */
    public List<String> fileNamesInPath(String path) {
        File directoryFile = new File(path);
        if (!directoryFile.exists())// if directory is not exist
        {
            return null;
        }
        else if (!directoryFile.isDirectory())// if directory is not a directory
        {
            return null;
        }
        
        List<String> fileNamesList = new ArrayList<String>();
        File[] files = directoryFile.listFiles();
        for (File file : files) {
            fileNamesList.add(file.getName());
        }
        
        return fileNamesList;
    }
    
    /**
     * delete single file
     * 
     * @param fileName
     * @return
     */
    private boolean deleteSingleFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            return false;
        }
        
        if (file.isFile()) {
            if (file.delete()) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
    
    /**
     * delete directory
     * 
     * @param directoryName
     * @return
     */
    private boolean deleteDirectory(String directoryName) {
        File dirFile = new File(directoryName);
        
        if (!dirFile.exists())// if directoryName is not exist
        {
            return false;
        }
        else if (!dirFile.isDirectory())// if directoryName is not a directory
        {
            return false;
        }
        
        // delete all the files in the directory(include sub directory)
        boolean flag = true;
        File[] files = dirFile.listFiles();
        for (File file : files) {
            if (file.isFile())// delete files in the directory
            {
                flag = deleteSingleFile(file.getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
            else
            // delete sub directory
            {
                flag = deleteDirectory(file.getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        
        if (!flag) {
            return false;
        }
        
        // delete current directoryName
        if (dirFile.delete()) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * copy file or dirctory
     * 
     * @param sourceFile
     * @param targetFile
     * @return
     */
    public boolean copyFile(String sourceFile, String targetFile) {
        boolean rs = false;
        
        File srcFile = new File(sourceFile);
        if (!srcFile.exists()) {
            return false;
        }
        
        if (srcFile.isFile()) {
            rs = copySingleFile(sourceFile, targetFile);
        }
        else if (srcFile.isDirectory()) {
            rs = copyDirectory(sourceFile, targetFile);
        }
        
        return rs;
    }
    
    /**
     * copy file, if targetFile exist, delete targetFile first
     * 
     * @param sourceFile
     * @param targetFile
     * @return
     */
    public boolean copyFileMandatory(String sourceFile, String targetFile) {
        File tarFile = new File(targetFile);
        if (tarFile.exists()) {
            if (!deleteFile(targetFile)) {
                return false;
            }
        }
        
        return copyFile(sourceFile, targetFile);
    }
    
    /**
     * copy one file
     * 
     * @param sourceFile
     * absolute path of sourceFile
     * @param targetFile
     * absolute path of targetFile
     * @return
     */
    private boolean copySingleFile(String sourceFile, String targetFile) {
        FileInputStream input = null;
        BufferedInputStream inBuff = null;
        FileOutputStream output = null;
        BufferedOutputStream outBuff = null;
        
        File source = new File(sourceFile);
        if (!source.exists()) {
            return false;
        }
        else if (!source.isFile()) {
            return false;
        }
        
        File target = new File(targetFile);
        if (target.exists() && target.isDirectory()) {
            return false;
        }
        
        try {
            input = new FileInputStream(source);
            inBuff = new BufferedInputStream(input);
            
            output = new FileOutputStream(target);
            outBuff = new BufferedOutputStream(output);
            
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            outBuff.flush();
            
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally {
            closeResources(inBuff, outBuff, output, input);
        }
    }
    
    /**
     * copy directory
     * 
     * @param sourceDir
     * @param targetDir
     */
    private boolean copyDirectory(String sourceDir, String targetDir) {
        boolean rs = true;
        
        File sourceDirFile = new File(sourceDir);
        if (!sourceDirFile.exists()) {
            return false;
        }
        else if (sourceDirFile.isFile()) {
            return false;
        }
        
        File targetDirFile = new File(targetDir);
        if (targetDirFile.exists()) {
            if (targetDirFile.isFile()) {
                return false;
            }
        }
        else {
            targetDirFile.mkdir();
        }
        
        // get all files in sourceDir
        File[] files = sourceDirFile.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                rs = copySingleFile(file.getAbsolutePath(), targetDir + File.separator + file.getName());
                if (!rs) {
                    return false;
                }
            }
            
            if (file.isDirectory()) {
                rs = copyDirectory(file.getAbsolutePath(), targetDir + File.separator + file.getName());
                if (!rs) {
                    return false;
                }
            }
        }
        
        return rs;
    }
    
    public boolean deleteEmptyFilesInDirectory(String dir) {
        File dirFile = new File(dir);
        if (isDirectoryExist(dirFile)) {
            File[] files = dirFile.listFiles();
            for (File file : files) {
                try {
                    if (0 == file.length()) {
                        file.delete();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            return true;
        }
        else {
            return false;
        }
    }
    
    public void createFolder(String folder) {
        File folderFile = new File(folder);
        if (!folderFile.exists()) {
            folderFile.mkdir();
        }
    }
    
    private boolean isDirectoryExist(File dirFile) {
        return (dirFile.exists() && dirFile.isDirectory()) ? true : false;
    }
    
    public long getFileLength(String setsimLogFilePath) {
        File file = new File(setsimLogFilePath);
        return file.length();
    }
    
    /**
     * get the String contents
     * 
     * @param filePath
     * @return
     */
    public List<String> getFileContents(String filePath) {
        List<String> fileList = new LinkedList<String>();
        File file = new File(filePath);
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                fileList.add(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            closeResources(fr, br);
        }
        
        return fileList;
    }
    
    /**
     * close resources
     * 
     * @param objects
     */
    public void closeResources(Object... objects) {
        for (Object o : objects) {
            if (null != o) {
                Method method = null;
                try {
                    method = o.getClass().getMethod("close", new Class[] {});
                    method.setAccessible(true);
                }
                catch (SecurityException e) {
                    e.printStackTrace();
                }
                catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                
                try {
                    method.invoke(o, new Object[] {});
                }
                catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
}
