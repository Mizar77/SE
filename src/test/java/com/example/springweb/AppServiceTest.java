package com.example.springweb;

import com.example.springweb.dao.HelloApp;
import com.example.springweb.service.AppService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppServiceTest {
    @Autowired
    AppService appService;

    @Test
    public void getApps() {
        List<HelloApp> apps = appService.getAppList();
        assertFalse("App not null", apps == null);
        assertNotNull(apps);
        assertNotEquals(apps.size(), 0);
        assertEquals(apps.get(0).getName(), "工业App1");
        assertEquals(apps.get(0).getUserId(), "100");
    }

    @Test
    public void testInsert() throws Exception{

        Map<String,String> params=new HashMap<>();
        params.put("userId","101");
        params.put("name","name101");
        params.put("range", "基础APP");
        params.put("huanJie", "研发设计工业APP");
        params.put("knowledge", "业务信息化类");
        appService.InsertApp(params);
        assertEquals(appService.getAppList().size(),3);
        assertEquals(appService.getOne("101").get(0).getName(),"name101");
        assertEquals(appService.getOne("101").get(0).getRange(),"基础APP");
        assertEquals(appService.getOne("101").get(0).getHuanJie(), "研发设计工业APP");
        assertEquals(appService.getOne("101").get(0).getKnowledge(), "业务信息化类");
    }

    @Test
    public void testGetOne() throws Exception{
        List<HelloApp> helloApps = appService.getOne("100");
        assertEquals(helloApps.get(0).getName(),"工业App1");
        assertEquals(helloApps.get(0).getRange(), "基础共性工业APP");
        assertEquals(helloApps.get(0).getHuanJie(), "研发设计工业APP-产品设计类");
        assertEquals(helloApps.get(0).getKnowledge(), "业务信息化类");

    }

   /* @Test
    public void testUpdate() throws Exception{
        // helloService.UpdateByID(new HelloUser("3","ooo","bbbb"));
        // assertEquals(helloService.getOne("3").getName(),"ooo");
        Map<String,String> params=new HashMap<>();
        params.put("id","4");
        params.put("name","yyy");
        //helloService.UpdateByID(params);
        assertEquals(helloService.getOne("4").getName(),"yyy");
    }

    @Test
    public void testDelete() throws Exception{
        //helloService.DeleteByID("4");
        assertEquals(helloService.getOne("4").getName(),null);
    }*/
}
