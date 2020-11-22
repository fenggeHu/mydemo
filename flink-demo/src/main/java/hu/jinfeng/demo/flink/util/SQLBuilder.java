package hu.jinfeng.demo.flink.util;

import hu.jinfeng.demo.util.FileUtils;
import hu.jinfeng.demo.util.VelocityEngineUtils;

import java.util.Properties;

/**
 * @Author hujinfeng  @Date 2020/11/22
 **/
public class SQLBuilder {

    /**
     * 读resource目录下的文件内容
     * @param fileName
     * @return
     */
    public static String readLocalSQL(String fileName) {
        String templateFile = VelocityEngineUtils.LOCAL_RESOURCE_PATH + fileName;
        return FileUtils.readLocalFile(templateFile);
    }

    /**
     * 文件内容解析
     * @param template
     * @param properties
     * @return
     */
    public static String getSQL(String template, Properties properties) {
        String content = readLocalSQL(template);
        return VelocityEngineUtils.parseTemplate(content, properties);
    }
}
