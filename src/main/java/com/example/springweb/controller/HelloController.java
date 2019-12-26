package com.example.springweb.controller;

import com.example.springweb.dao.HelloApp;
import com.example.springweb.dao.HelloUser;
import com.example.springweb.service.AppService;
import com.example.springweb.service.HelloService;
import org.apache.ibatis.annotations.Result;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

@Controller
public class HelloController {
    @Autowired
    HelloService helloService;
    @Autowired
    AppService appService;

    public final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping(value="/")
    public String home(){
        return "redirect:home";
    }

    @RequestMapping(value="/home", method=RequestMethod.POST)
    public String homeClick(String action){
        logger.info("hdhs");
        if(action.equals("login"))
           return "redirect:login";
        else if(action.equals("register"))
            logger.info("hdhhsdhdss");
        return "redirect:register";
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String register(){return "register";}

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerClick(HelloUser user, String action){
        if(action.equals("return"))return "redirect:home";
        helloService.InsertUser(user);
        return "redirect:home";
    }
    @RequestMapping(value="/home", method = RequestMethod.GET)
    public String realHome(){
        return "home";
    }

    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value="/login",method=RequestMethod.POST)
    public String inputId(HelloUser user, Model model, String action){
        String id = user.getId();
        String password = user.getPassword();
        HelloUser sql = helloService.getOne(id);
        if(password.equals(sql.getPassword())) {
            return "redirect:userPage-id="+id;
        }
        else {
            model.addAttribute("wrongInfo", "wrong password");
            return "login";
        }
    }

    @RequestMapping(value="/userPage-id={id}", method=RequestMethod.POST)
    public String userPageClick(@PathVariable String id, String action){
        if(action.equals("add"))
            return "redirect:addAppRange-id="+id;
        return "redirect:visitTable-id="+id;
    }

    @RequestMapping(value="/userPage-id={id}", method=RequestMethod.GET)
    public String userPage(@PathVariable String id){
        return "userPage";
    }

    @RequestMapping(value="/addAppRange-id={id}", method = RequestMethod.GET)
    public String addApp(){
        return "addAppRange";
    }
    @RequestMapping(value="addAppRange-id={id}", method = RequestMethod.POST)
    public String addAppRangeClick (@PathVariable String id, String type, String action, Model model)
            throws UnsupportedEncodingException{
        type = new String(type.getBytes("utf8"),"ISO-8859-1");
        if(action.equals("next")) {
            return "redirect:addAppHuanjie-id=" + id + "-range=" + type;
        }
        else return "redirect:userPage-id="+id;
    }

    @RequestMapping(value = "/addAppHuanjie-id={id}-range={range}", method = RequestMethod.GET)
    public String addAppHuanjie(){
        return "addAppHuanjie";
    }

    @RequestMapping(value = "/addAppHuanjie-id={id}-range={range}", method = RequestMethod.POST)
    public String addAppHuanjieClick(@PathVariable String id, @PathVariable String range, String type, String action)
            throws UnsupportedEncodingException{
        range = new String(range.getBytes("utf8"),"ISO-8859-1");
        String type1 = new String(type.getBytes("utf8"),"ISO-8859-1");
        System.out.println("addhuanjie "+ id+" "+range+" "+type1+" "+type);
        if(action.equals("pre"))
            return "redirect:addAppRange-id="+id;
        else{
            if(type.equals("研发设计工业APP"))
                return "redirect:addAppHuanjie1-id="+id+"-range="+range+"-huanjie1="+type1;
            else if(type.equals("生产制造工业APP"))
                return "redirect:addAppHuanjie2-id="+id+"-range="+range+"-huanjie1="+type1;
            else if(type.equals("过程控制设计类"))
                return "redirect:addAppHuanjie3-id="+id+"-range="+range+"-huanjie1="+type1;
            else return "redirect:addAppHuanjie4-id="+id+"-range="+range+"-huanjie1="+type1;
        }
    }

    @RequestMapping(value = "/addAppHuanjie1-id={id}-range={range}-huanjie1={huanjie1}", method = RequestMethod.GET)
    public String addAppHuanjie1(){
        return "addAppHuanjie1";
    }

    @RequestMapping(value = "/addAppHuanjie2-id={id}-range={range}-huanjie1={huanjie1}", method = RequestMethod.GET)
    public String addAppHuanjie2(){
        return "addAppHuanjie2";
    }
    @RequestMapping(value = "/addAppHuanjie3-id={id}-range={range}-huanjie1={huanjie1}", method = RequestMethod.GET)
    public String addAppHuanjie3(){
        return "addAppHuanjie3";
    }

    @RequestMapping(value = "/addAppHuanjie4-id={id}-range={range}-huanjie1={huanjie1}", method = RequestMethod.GET)
    public String addAppHuanjie4(){
        return "addAppHuanjie4";
    }

    @RequestMapping(value={"/addAppHuanjie1-id={id}-range={range}-huanjie1={huanjie1}",
            "/addAppHuanjie2-id={id}-range={range}-huanjie1={huanjie1}",
            "/addAppHuanjie3-id={id}-range={range}-huanjie1={huanjie1}",
            "/addAppHuanjie4-id={id}-range={range}-huanjie1={huanjie1}"}, method = RequestMethod.POST)
    public String addAppHuanjie_Click(@PathVariable String id, @PathVariable String range,
                                      @PathVariable String huanjie1, String type, String action)
            throws UnsupportedEncodingException{
        id = new String(id.getBytes("utf8"),"ISO-8859-1");
        range = new String(range.getBytes("utf8"),"ISO-8859-1");
        huanjie1 = new String(huanjie1.getBytes("utf8"),"ISO-8859-1");
        type = new String(type.getBytes("utf8"),"ISO-8859-1");
        if(action.equals("next"))
            return "redirect:addAppKnowledge-id="+id+"-range="+range+"-huanjie1="+huanjie1+"-huanjie2="+type;
        else return "redirect:addAppHuanjie-id="+id+"-range="+range;
    }

    @RequestMapping(value="/addAppKnowledge-id={id}-range={range}-huanjie1={huanjie1}-huanjie2={huanjie2}",
            method = RequestMethod.GET)
    public String addAppKnowledge(){return "addAppKnowledge";}

    @RequestMapping(value="/addAppKnowledge-id={id}-range={range}-huanjie1={huanjie1}-huanjie2={huanjie2}",
            method = RequestMethod.POST)
    public String addAppKnowledgeClick(@PathVariable String id, @PathVariable String range,
                                       @PathVariable String huanjie1, @PathVariable String huanjie2,
                                       String type, String action)
            throws UnsupportedEncodingException{
        //helloApp.setKnowledge(type);
        id = new String(id.getBytes("utf8"),"ISO-8859-1");
        range = new String(range.getBytes("utf8"),"ISO-8859-1");
        huanjie2 = new String(huanjie2.getBytes("utf8"),"ISO-8859-1");
        huanjie1 = new String(huanjie1.getBytes("utf8"),"ISO-8859-1");
        type = new String(type.getBytes("utf8"),"ISO-8859-1");
        System.out.println(id+" "+range+" "+huanjie1+" "+huanjie2+" "+type);
        if(action.equals("next"))
            return "redirect:addAppName-id="+id+"-range="+range+"-huanjie1="+huanjie1+"-huanjie2="
                    +huanjie2+"-knowledge="+type;
        else return "redirect:addAppHuanjie-id="+id+"-range="+range;
    }
    @RequestMapping(value="/addAppName-id={id}-range={range}-huanjie1={huanjie1}-huanjie2={huanjie2}-knowledge={knowledge}",
            method = RequestMethod.GET)
    public String addAppName(){
        return "addAppName";
    }

    @RequestMapping(value="/addAppName-id={id}-range={range}-huanjie1={huanjie1}-huanjie2={huanjie2}-knowledge={knowledge}", method = RequestMethod.POST)
    public String addAppNameClick(@PathVariable String id,@PathVariable String range,
                                  @PathVariable String huanjie1,@PathVariable String huanjie2,
                                  @PathVariable String knowledge, String name, String action, Model model)
            throws UnsupportedEncodingException{
        if(action.equals("ok")){
            if(name!="") {
                Map<String, String>params = new HashMap<>();
                params.put("userId",id);
                params.put("name", name);
                params.put("range",range);
                params.put("huanJie",huanjie1+"-"+huanjie2);
                params.put("knowledge", knowledge);
                appService.InsertApp(params);
                model.addAttribute("rightInfo", "添加成功");
                return "redirect:userPage-id="+id;
            }else{
                id = new String(id.getBytes("utf8"),"ISO-8859-1");
                range = new String(range.getBytes("utf8"),"ISO-8859-1");
                huanjie2 = new String(huanjie2.getBytes("utf8"),"ISO-8859-1");
                huanjie1 = new String(huanjie1.getBytes("utf8"),"ISO-8859-1");
                knowledge = new String(knowledge.getBytes("utf8"),"ISO-8859-1");
                model.addAttribute("wrongInfo", "请输入命名");
                return "addAppName";
            }
        }
        else {
            id = new String(id.getBytes("utf8"),"ISO-8859-1");
            range = new String(range.getBytes("utf8"),"ISO-8859-1");
            huanjie2 = new String(huanjie2.getBytes("utf8"),"ISO-8859-1");
            huanjie1 = new String(huanjie1.getBytes("utf8"),"ISO-8859-1");
            //knowledge = new String(knowledge.getBytes("utf8"),"ISO-8859-1");
            return "redirect:addAppKnowledge-id=" + id + "-range=" + range + "-huanjie1=" + huanjie1 + "-huanjie2=" + huanjie2;
        }

    }

    @RequestMapping(value="/visitTable-id={id}", method = RequestMethod.GET)
    public String visitTable(@PathVariable String id, Model model){
        List<HelloApp> list = appService.getOne(id);
        System.out.println(list);
        if(list.size()>=1) {
            model.addAttribute("name1", list.get(0).getName());
            model.addAttribute("range1", list.get(0).getRange());
            model.addAttribute("huanjie1", list.get(0).getHuanJie());
            model.addAttribute("knowledge1", list.get(0).getKnowledge());
        }
        if(list.size()>=2) {
            model.addAttribute("name2", list.get(1).getName());
            model.addAttribute("range2", list.get(1).getRange());
            model.addAttribute("huanjie2", list.get(1).getHuanJie());
            model.addAttribute("knowledge2", list.get(1).getKnowledge());
        }
        if(list.size()>=3) {
            model.addAttribute("name2", list.get(2).getName());
            model.addAttribute("range2", list.get(2).getRange());
            model.addAttribute("huanjie2", list.get(2).getHuanJie());
            model.addAttribute("knowledge2", list.get(2).getKnowledge());
        }
        return "visitTable";
    }

    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String hello(){
        //logger.info("hello logging" + helloService.getUserList());
        return "hello";
        //return "index";
    }

    @RequestMapping(value="/index", method=RequestMethod.GET)
    public  String index(){
        return "index";
    }
}
