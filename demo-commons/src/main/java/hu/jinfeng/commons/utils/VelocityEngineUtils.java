package hu.jinfeng.commons.utils;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

/**
 * @Author hujinfeng  @Date 2020/11/22
 **/
public class VelocityEngineUtils {

    private final static VelocityEngine velocityEngine;
    public final static String LOCAL_RESOURCE_PATH;

    static {
        velocityEngine = new VelocityEngine();
//        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
//        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.str2Txt());
        velocityEngine.setProperty(RuntimeConstants.INPUT_ENCODING, "UTF-8");
        velocityEngine.setProperty(RuntimeConstants.OUTPUT_ENCODING, "UTF-8");
        velocityEngine.init();
        LOCAL_RESOURCE_PATH = VelocityEngineUtils.class.getResource("/").getPath();
    }

    /**
     * velocity logTag
     */
    private final static String VELOCITY_LOGTAG = "velocity/logTag";
    private final static String VELOCITY_LOG_TAG_PATH = LOCAL_RESOURCE_PATH + VELOCITY_LOGTAG;

    /**
     * 模板解析
     *
     * @param templateContent
     * @param ctx
     */
    public static String parseTemplate(String templateContent, VelocityContext ctx) {
        try {
            StringWriter sw = new StringWriter();
            boolean success = velocityEngine.evaluate(ctx, sw, VELOCITY_LOG_TAG_PATH, templateContent);
            if (success) {
                return sw.toString();
            } else {
                throw new RuntimeException("velocity engine evaluate failed");
            }
        } catch (Exception e) {
            throw new RuntimeException("velocity engine parse failed: " + e.getMessage(), e);
        }
    }

    /**
     * 解析模板
     *
     * @param templateContent
     * @param context
     * @return
     */
    public static String parseTemplate(String templateContent, Map<String, Object> context) {
        VelocityContext ctx = getVelocityContext(context);
        return parseTemplate(templateContent, ctx);
    }
    public static String parseTemplate(String templateContent, Properties properties) {
        VelocityContext ctx = getVelocityContext(properties);
        return parseTemplate(templateContent, ctx);
    }

    /**
     * 模板参数
     *
     * @param context
     * @return
     */
    public static VelocityContext getVelocityContext(Map<String, Object> context) {
        VelocityContext ctx = new VelocityContext();
        if (null != context) {
            context.forEach((k, v) -> ctx.put(k, v));
        }
        return ctx;
    }

    public static VelocityContext getVelocityContext(Properties properties) {
        VelocityContext ctx = new VelocityContext();
        if (null != properties) {
            properties.forEach((k, v) -> ctx.put(k.toString(), v));
        }
        return ctx;
    }

}
