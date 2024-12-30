package com.hehe.fbalx.utils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class LogUtil {

    private static Logger logger = Logger.getLogger(LogUtil.class.getName());
    public static FileHandler fileHandler = null;

    // 初始化日志配置
    public static void initLog(){
        try {
            // 获取当前项目目录
            String projectDir = System.getProperty("user.dir");
            // 定义日志文件夹路径
            String logsDirPath = projectDir + File.separator + "FBALingXingLogs";
            File logsDir = new File(logsDirPath);

            // 如果日志文件夹不存在，则创建
            if (!logsDir.exists()) {
                boolean created = logsDir.mkdirs();
                if (created) {
                    System.out.println("Log folder created：" + logsDirPath);
                } else {
                    System.out.println("Log folder creation failed：" + logsDirPath);
                }
            }

            // 定义日志文件名的格式，每月一个文件
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
            String fileName = logsDirPath + File.separator + "log-" + dateFormat.format(new Date()) + ".log"; // 文件名按年月分配
            // 如果已有 FileHandler 则先关闭它
            if (fileHandler != null) {
                fileHandler.close();
            }
            // 创建 FileHandler（文件处理器），并设置按日期生成新日志文件
            fileHandler = new FileHandler(fileName, true); // 'true' 表示追加到现有日志文件
            // 设置日志输出格式
            fileHandler.setFormatter(new CustomFormatter());
            // 移除所有现有的 Handler
            /*Handler[] handlers = logger.getHandlers();
            for (Handler handler : handlers) {
                if (handler instanceof ConsoleHandler) {
                    logger.removeHandler(handler); // 删除 ConsoleHandler
                }
            }*/

            // 将 FileHandler 添加到 Logger
            logger.addHandler(fileHandler);

            // 禁用父日志处理器（确保不再控制台输出日志）
            logger.setUseParentHandlers(false);
            // 也将日志输出到控制台
            //ConsoleHandler consoleHandler = new ConsoleHandler();
            //consoleHandler.setFormatter(new SimpleFormatter());
            //logger.addHandler(consoleHandler);

            // 设置日志级别 INFO、WARNING、SEVERE级别的日志会被输出
            logger.setLevel(Level.INFO);

        } catch (IOException e) {
            System.err.println("Logging Configuration Failure：" + e.getMessage());
        }
    }

    // 自定义日志格式
    static class CustomFormatter extends Formatter {
        @Override
        public String format(LogRecord record) {
            // 获取当前时间并格式化为指定格式
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(record.getMillis()));
            // 获取日志源类名
            String className = getCallerClassName();
            // 获取日志级别
            String level = record.getLevel().toString();
            // 获取日志内容
            String message = record.getMessage();

            // 拼接日志输出格式：日期 类名 信息
            return String.format("[%s] [%s] [%s] %s\n", timestamp, className, level, message);
        }
        // 获取调用日志工具类方法的类名
        private String getCallerClassName() {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

            // stackTrace[0] 是当前方法 (getCallerClassName)，stackTrace[1] 是调用getCallerClassName的方法
            // stackTrace[2] 就是我们需要的调用日志记录方法的类名
            int i = 3;  // 从 stackTrace[3] 开始，跳过 LogUtil 和其他框架的调用

            // 继续向上查找，直到找到调用 LogUtil 的真实类
            while (i < stackTrace.length) {
                String className = stackTrace[i].getClassName();

                // 跳过与日志框架相关的栈帧，直到找到方法名
                if (!className.startsWith("java.util.logging") && !className.equals(LogUtil.class.getName())) {
                    return stackTrace[i].getClassName() + "." + stackTrace[i].getMethodName();  // 返回调用日志方法的当前方法名
                }
                i++;
            }
            return "Unknown";
        }
    }

    // 输出信息级别的日志
    public static void info(String message) {
        logger.info(message);
    }

    // 输出警告级别的日志
    public static void warning(String message) {
        logger.warning(message);
    }

    // 输出严重错误级别的日志
    public static void severe(String message) {
        logger.severe(message);
    }

    // 输出调试级别的日志
    public static void fine(String message) {
        logger.fine(message);
    }

    // 输出日志，并附带异常
    public static void logException(Exception e) {
        logger.log(Level.SEVERE, e.getMessage(), e);
    }

}