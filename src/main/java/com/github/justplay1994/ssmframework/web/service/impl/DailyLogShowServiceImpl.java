package com.github.justplay1994.ssmframework.web.service.impl;

import com.github.justplay1994.ssmframework.web.dao.DailyLogMapper;
import com.github.justplay1994.ssmframework.web.model.DailyLog;
import com.github.justplay1994.ssmframework.web.service.DailyLogShowService;
import org.apache.ibatis.io.Resources;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.io.InputStream;

/**
 * Created by JustPlay1994 on 2017/4/16.
 * https://github.com/JustPlay1994/daily-log-manager
 */
@Service()
public class DailyLogShowServiceImpl implements DailyLogShowService {
    /**
     * ����sqlSession��ʹ�ú����close�������ڴ�й©
     * @return sql����
     */
    private SqlSession createSqlSession(){
        String resource= "mybatisConfig.xml";
        InputStream configuration= null;
        try {
            configuration = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory.openSession();
    }

    /**
     *  ����������־��������
     * @return ��־��DailyLog
     */
    @Autowired
    public DailyLog getDailyLog() {
        SqlSession sqlSession=createSqlSession();
        try {

            DailyLogMapper dailyLogMapper = sqlSession.getMapper(DailyLogMapper.class);
            DailyLog dailyLog = dailyLogMapper.getDailyLog();
//            System.out.println(dailyLog);
            return dailyLog;
        }finally {
            sqlSession.close();
        }
    }

    /**
     * ������־����
     * @return �ַ���
     */
    @Autowired
    public String getContent() {
        SqlSession sqlSession=createSqlSession();
        try {
            DailyLogMapper dailyLogMapper = sqlSession.getMapper(DailyLogMapper.class);
            return dailyLogMapper.getContent();
        }catch (Exception e){
            System.out.println(e);
            return "error";
        }finally {
            sqlSession.close();
        }
    }

    /**
     * ����־���ݴ������ݿ�
     * @param content
     */

    public void setDailyLog(String content){
        SqlSession sqlSession=createSqlSession();
        try {
            DailyLogMapper dailyLogMapper = sqlSession.getMapper(DailyLogMapper.class);
            dailyLogMapper.getContent();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            sqlSession.close();
        }
    }
}
