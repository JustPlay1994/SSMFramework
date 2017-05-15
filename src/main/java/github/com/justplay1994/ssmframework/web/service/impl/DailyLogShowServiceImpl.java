package github.com.justplay1994.ssmframework.web.service.impl;

import github.com.justplay1994.ssmframework.web.dao.DailyLogMapper;
import github.com.justplay1994.ssmframework.web.model.DailyLog;
import github.com.justplay1994.ssmframework.web.service.DailyLogShowService;
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
     * 构建sqlSession，使用后必须close，否则内存泄漏
     * @return sql连接
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
     *  查找整个日志所有属性
     * @return 日志类DailyLog
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
     * 查找日志内容
     * @return 字符串
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
     * 将日志内容存入数据库
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
