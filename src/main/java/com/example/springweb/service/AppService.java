package com.example.springweb.service;

import com.example.springweb.dao.HelloUser;
import com.example.springweb.mapper.AppMapper;
import com.example.springweb.mapper.HelloMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.example.springweb.dao.HelloApp;
@Service
public class AppService {
    @Resource
    private AppMapper appMapper;

    public List<HelloApp> getAppList() {
        List<HelloApp> list = appMapper.findAll();
        return list;
    }

    /*public void InsertUser(HelloUser helloUser){
        helloMapper.insert(helloUser);
        System.out.println("Afterinsert:"+helloMapper.findAll());
    }*/
    public void InsertApp(Map<String, String>params){
        ObjectMapper objectMapper = new ObjectMapper();
        HelloApp helloApp = objectMapper.convertValue(params, HelloApp.class);
        appMapper.insert(helloApp);
    }


    public List<HelloApp> getOne(String id){
        //HelloUser result = new HelloUser();
        List<HelloApp> result = appMapper.getOne(id);
        System.out.println("getOne:"+result);
        if (result==null)
        {
            result= Arrays.asList(new HelloApp());//索引为空的时候，返回null，需要这时候对其getId,getName就会出错。
        }
        System.out.println(result.toString());
        return result;
    }

    /*public void UpdateByID(HelloUser helloUser){
        helloMapper.updateByID(helloUser);
        System.out.println("AfterUpdate:"+ helloMapper.getOne(helloUser.getId()));
    }*/
    /*public void UpdateByID(Map<String, String> params){
        String id = params.get("id");
        //Long recordId = Long.parseLong(params.get("recordId"));
        //ObjectMapper objectMapper = new ObjectMapper();
        //HelloUser helloUser = objectMapper.convertValue(params, HelloUser.class);
        //helloMapper.updateByID(helloUser);
        HelloUser temp = helloMapper.getOne(id);
        if(params.get("name")!=null)
            temp.setName(params.get("name"));
        if(params.get("password")!=null)
            temp.setPassword((params.get("password")));
        helloMapper.updateByID(temp);
    }


    public void DeleteByID(String id){
        helloMapper.deleteByID(id);
        System.out.println("AfterDelete:"+helloMapper.getOne(id));
    }*/
}
