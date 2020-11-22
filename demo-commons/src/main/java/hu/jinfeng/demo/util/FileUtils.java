package hu.jinfeng.demo.util;

import java.io.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author hujinfeng  @Date 2020/11/19
 */
public class FileUtils {

    /**
     * 覆盖写入文件内容
     *
     * @param fileName
     * @param content
     */
    public static boolean writeFile(String fileName, String content) {
        return writeFile(new File(fileName), content);
    }

    public static boolean writeFile(File file, String content) {
        try {
            if (!file.exists()) {
                File dir = new File(file.getParent());
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(content);
            writer.close();
            return true;
        } catch (IOException e) {
            throw new RuntimeException("writeFile: " + file.getName(), e);
        } finally {
        }
    }

    /**
     * 把文件按行读出
     *
     * @param fileName
     * @return
     */
    public static List<String> readLines(String fileName) {
        List<String> ret = new LinkedList<>();
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line != "") {
                    ret.add(line);
                }
            }
            fr.close();
            br.close();
        } catch (Exception e) {
            throw new RuntimeException("readLines: " + fileName, e);
        } finally {

        }
        return ret;
    }

    /**
     * 扩展文件行
     *
     * @param file
     * @param lines
     */
    public static void appendLines(String file, Collection<String> lines) {
        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String line : lines) {
                bw.append(line).append("\r\n");
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            throw new RuntimeException("appendLines: " + file, e);
        } finally {

        }
    }

    /**
     * @param path
     * @return
     */
    public static String readLocalFile(String path) {
        return readLocalFile(new File(path));
    }

    public static String readLocalFile(File file) {
        try {
            if (!file.exists() || !file.isFile() || !file.canRead()) {
                return null;
            }
            BufferedReader in = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String str;
            while ((str = in.readLine()) != null) {
                if (sb.length() > 0) {
                    sb.append("\n");
                }
                sb.append(str);
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException("readLocalFile_error: " + file.getPath(), e);
        }
    }

    public static void copyDir(String oldPath, String newPath) throws IOException {
        File file = new File(oldPath);
        // 文件名称列表
        String[] filePath = file.list();
        if (!(new File(newPath)).exists()) {
            (new File(newPath)).mkdir();
        }
        for (int i = 0; i < filePath.length; i++) {
            if ((new File(oldPath + file.separator + filePath[i])).isDirectory()) {
                copyDir(oldPath + file.separator + filePath[i], newPath + file.separator + filePath[i]);
            }
            if (new File(oldPath + file.separator + filePath[i]).isFile()) {
                copyFile(oldPath + file.separator + filePath[i], newPath + file.separator + filePath[i]);
            }
        }
    }

    public static void copyDirWithoutGit(String oldPath, String newPath) throws IOException {
        File file = new File(oldPath);
        // 文件名称列表
        String[] filePath = file.list();
        if (!(new File(newPath)).exists()) {
            (new File(newPath)).mkdir();
        }
        for (int i = 0; i < filePath.length; i++) {
            if (!".git".equals(filePath[i])) {
                if ((new File(oldPath + file.separator + filePath[i])).isDirectory()) {
                    copyDir(oldPath + file.separator + filePath[i], newPath + file.separator + filePath[i]);
                }
                if (new File(oldPath + file.separator + filePath[i]).isFile()) {
                    copyFile(oldPath + file.separator + filePath[i], newPath + file.separator + filePath[i]);
                }
            }
        }
    }

    public static void copyFile(File sourceFile, File targetFile) {
        try {
            // 新建文件输入流并对它进行缓冲
            FileInputStream input = new FileInputStream(sourceFile);
            BufferedInputStream inBuff = new BufferedInputStream(input);

            // 新建文件输出流并对它进行缓冲
            FileOutputStream output = new FileOutputStream(targetFile);
            BufferedOutputStream outBuff = new BufferedOutputStream(output);

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();

            // 关闭流
            inBuff.close();
            outBuff.close();
            output.close();
            input.close();
        } catch (Exception e) {
            throw new RuntimeException("copyFile", e);
        } finally {

        }
    }

    public static void copyFile(String oldPath, String newPath) {
        File sourceFile = new File(oldPath);
        File targetFile = new File(newPath);
        copyFile(sourceFile, targetFile);
    }

}
