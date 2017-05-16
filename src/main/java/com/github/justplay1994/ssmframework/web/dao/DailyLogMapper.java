package com.github.justplay1994.ssmframework.web.dao;

import com.github.justplay1994.ssmframework.web.model.DailyLog;

/**
 * Created by JustPlay1994 on 2017/4/16.
 * https://github.com/JustPlay1994/daily-log-manager
 */

public interface DailyLogMapper {

    DailyLog getDailyLog();

    String getContent();

    void setDailyLog(String content);

}
