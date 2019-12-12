package net.company.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SettingsAnalyzer {
    private final static String DEFAULT_ADDRESS = "233.55.221.16";
    private final static String DEFAULT_PORT = "7654";
    private final static String DEFAULT_TTL = "2";
    private final static String DEFAULT_MODE = "client";
    private final static String IP_PATTERN = "(2[2-3]\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}";
    private final static String MODE_PATTERN = "client|server";
    private final static String PORT_PATTERN = "\\d{4}";
    private final static String TTL_PATTERN = "\\d{1,3}";

    /**
     * Анализ параметров коммандной строки
     * @param args - входные параметры коммандной строки
     * @return [0]-mode, [1]-address, [2]-port, [3]-ttl
     */
    public static String[] analyzeSettings(String[] args) {
        String[] settings = new String[4];
        settings[0] = DEFAULT_MODE;
        settings[1] = DEFAULT_ADDRESS;
        settings[2] = DEFAULT_PORT;
        settings[3] = DEFAULT_TTL;

        if (args.length > 0) {
            // Проверяем параметры на соответсвие шаблонам.
            // Если есть соответствие, устанавливаем соответствующий параметр
            for(String str : args) {
                Pattern pattern = Pattern.compile(MODE_PATTERN);
                Matcher matcher = pattern.matcher(str);
                if (matcher.find()) {
                    settings[0] = matcher.group();
                    continue;
                }
                pattern = Pattern.compile(IP_PATTERN);
                matcher = pattern.matcher(str);
                if (matcher.find()) {
                    settings[1] = matcher.group();
                    continue;
                }
                pattern = Pattern.compile(PORT_PATTERN);
                matcher = pattern.matcher(str);
                if (matcher.find()) {
                    String ip = matcher.group();
                    if (Integer.parseInt(ip.substring(1,2)) >= 24 ) {
                        settings[2] = ip;
                    }
                    continue;
                }
                pattern = Pattern.compile(TTL_PATTERN);
                matcher = pattern.matcher(str);
                if (str.length() < 4 && matcher.find()) {
                    settings[3] = matcher.group();
                }
            }
        }
        return settings;
    }
}
