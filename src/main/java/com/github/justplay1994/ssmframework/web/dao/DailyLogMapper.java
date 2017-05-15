package com.github.justplay1994.ssmframework.web.dao;

import com.github.justplay1994.ssmframework.web.model.DailyLog;

/**
 * Created by JustPlay1994 on 2017/4/16.
 * https://github.com/JustPlay1994/daily-log-manager
 */

public interface DailyLogMapper {
    /**
     *  ����������־��������
     * @return ��־��DailyLog
     */
    DailyLog getDailyLog();
    /**
     * ������־����
     * @return �ַ���
     */
    String getContent();
    /**
     * ����־���ݴ������ݿ�
     * @param content
     */
    void setDailyLog(String content);

}
