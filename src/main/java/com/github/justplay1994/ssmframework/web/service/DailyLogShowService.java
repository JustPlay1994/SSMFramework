package com.github.justplay1994.ssmframework.web.service;

import com.github.justplay1994.ssmframework.web.model.DailyLog;

/**
 * Created by JustPlay1994 on 2017/4/16.
 * https://github.com/JustPlay1994/daily-log-manager
 */

public interface DailyLogShowService {
    DailyLog getDailyLog();
    String getContent();
    void setDailyLog(String content);
}
