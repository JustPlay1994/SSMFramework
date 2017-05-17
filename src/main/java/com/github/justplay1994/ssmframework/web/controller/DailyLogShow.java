package com.github.justplay1994.ssmframework.web.controller;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
        import com.github.justplay1994.ssmframework.web.service.DailyLogShowService;
        import com.github.justplay1994.ssmframework.web.model.DailyLog;

        import org.apache.log4j.LogManager;
        import org.apache.log4j.Logger;
        import org.junit.Test;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Qualifier;
        import org.springframework.stereotype.Component;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.bind.annotation.ResponseBody;

        import java.nio.charset.Charset;

/**
 * Created by JustPlay1994 on 2017/4/15.
 * https://github.com/JustPlay1994/daily-log-manager
 */

@Controller
@Component
public class DailyLogShow {
    Logger logger = LogManager.getLogger(DailyLogShow.class);
//    DailyLogShowService dailyLogShowService = new DailyLogShowServiceImpl();


    @Autowired @Qualifier("dailyLogShowServiceImpl")
    DailyLogShowService dailyLogShowService;
    @RequestMapping(value = "/get", method = RequestMethod.POST,consumes="application/json;charset=UTF-8", produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getDailyLog(@RequestBody String message){

        System.out.println("编码格式为: "+System.getProperty("file.encoding"));
        System.out.println("message: "+message);
        logger.info(dailyLogShowService.getContent());
        System.out.println("System.out + "+"中文："+dailyLogShowService.getContent());
        DailyLog dailyLog = dailyLogShowService.getDailyLog();
        logger.info(dailyLog);
        return "{\"a\":\"返回中文\"}";
    }
    @RequestMapping("/set")
    @Test
    public void setDailyLog(){
        String content = "今天天气不错";
        String encoding = System.getProperty("file.encoding");
        System.out.println(content+" 目前编码格式为:" + encoding);
        System.out.println(content.getBytes(Charset.forName("GBK")).length);
        System.out.println(content.getBytes(Charset.forName("UTF-8")).length);
        System.out.println(content.getBytes().length);
        dailyLogShowService.setDailyLog(content);
    }
}
