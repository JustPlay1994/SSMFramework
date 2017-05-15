package github.com.justplay1994.ssmframework.web.dao;

import github.com.justplay1994.ssmframework.web.model.DailyLog;

/**
 * Created by JustPlay1994 on 2017/4/16.
 * https://github.com/JustPlay1994/daily-log-manager
 */

public interface DailyLogMapper {
    /**
     *  查找整个日志所有属性
     * @return 日志类DailyLog
     */
    DailyLog getDailyLog();
    /**
     * 查找日志内容
     * @return 字符串
     */
    String getContent();
    /**
     * 将日志内容存入数据库
     * @param content
     */
    void setDailyLog(String content);

}
