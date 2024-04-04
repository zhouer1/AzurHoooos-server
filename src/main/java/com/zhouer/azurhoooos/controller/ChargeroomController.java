package com.zhouer.azurhoooos.controller;

import com.zhouer.azurhoooos.beans.SelectContainer;
import com.zhouer.azurhoooos.entity.RegistrationRecord;
import com.zhouer.azurhoooos.service.ChargeroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChargeroomController {
    @Autowired
    public ChargeroomService chargeroomService;

    //查询所有
    @GetMapping("/chargeroom/registration/selectall")
    public Object selectAll(){
        return chargeroomService.selectAll();
    }

    //开局初始化展示表
    @GetMapping("/chargeroom/registration/selectbypageInit")
    public Object selectByPageInit(@RequestParam Integer page,@RequestParam Integer pageSize){
        Integer startPage=(page-1)*pageSize;
        chargeroomService.createTempTable();
        return chargeroomService.selectByPageInTemp(startPage,pageSize);
    }

    //分页查询
    @GetMapping("/chargeroom/registration/selectbypage")
    public Object selectByPage(@RequestParam Integer page,@RequestParam Integer pageSize){
        Integer startPage=(page-1)*pageSize;
        return chargeroomService.selectByPageInTemp(startPage,pageSize);
    }

    //挂号表填写完毕，点击提交按钮，请求该接口，向挂号记录表里面新增一条数据，与此同时，提醒医生的接诊面板拉取数据
    @PostMapping("/chargeroom/registration/add")
    public Object add(@RequestBody RegistrationRecord registrationForm){
        return chargeroomService.addRecord(registrationForm);
    }

    //删除一条指定的数据
    @GetMapping("/chargeroom/registration/delete")
    public String delete(@RequestParam Integer id){
        return chargeroomService.deleteRecord(id);
    }

    //更新一条数据
    @PostMapping("/chargeroom/registration/edit")
    public Object edit(@RequestBody RegistrationRecord registrationRecord){
        return chargeroomService.editRecord(registrationRecord);
    }

    //按选择器查找
    @PostMapping("/chargeroom/registration/selectbyselector")
    public void selectBySelector(@RequestBody SelectContainer selectContainer){
        chargeroomService.createTempTableBySelector(selectContainer);
    }

    //按舰船名模糊查找
    @GetMapping("/chargeroom/registration/selectbyname")
    public void selectByName(@RequestParam String name){
        chargeroomService.createTempTableByName(name);
    }
}
