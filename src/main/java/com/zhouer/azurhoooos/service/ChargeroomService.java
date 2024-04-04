package com.zhouer.azurhoooos.service;

import com.zhouer.azurhoooos.beans.RegiPageResult;
import com.zhouer.azurhoooos.beans.RegistrationResult;
import com.zhouer.azurhoooos.beans.SelectContainer;
import com.zhouer.azurhoooos.entity.RegistrationRecord;
import com.zhouer.azurhoooos.mapper.RegistrationRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChargeroomService {
    @Autowired
    private RegistrationRecordMapper registrationRecordMapper;

//查询所有挂号记录
    public RegistrationResult selectAll(){
        RegistrationResult registrationResult=new RegistrationResult();
        List<RegistrationRecord> registrationRecords= registrationRecordMapper.selectRegistrationRecord();
        if(registrationRecords==null){
            registrationResult.setSuccess(false);
            registrationResult.setStatusCode("40000");
            registrationResult.setRegistrationRecords(null);
        }else{
            registrationResult.setSuccess(true);
            registrationResult.setStatusCode("20000");
            registrationResult.setRegistrationRecords(registrationRecords);
        }
        return registrationResult;
    }

    //添加一条挂号记录
    public RegistrationResult addRecord(RegistrationRecord registrationRecord){
        RegistrationResult registrationResult=new RegistrationResult();
        Integer line= registrationRecordMapper.addRecord(registrationRecord);

        //addBeingDiagnosed(registrationRecord)

        if(line>=1){
            registrationResult.setMsg("挂号成功");
            registrationResult.setSuccess(true);
            registrationResult.setStatusCode("20000");
        }else{
            registrationResult.setSuccess(false);
            registrationResult.setStatusCode("40000");
        }
        return registrationResult;
    }

    //删除一条挂号记录
    public String deleteRecord(Integer id){
        Integer line= registrationRecordMapper.deleteRecord(id);
        if(line>=1){
            registrationRecordMapper.dropTempTable();
            registrationRecordMapper.createTempTable();
            return "删除成功";
        }else{
            return "删除失败";
        }
    }

    //编辑一条挂号记录
    public RegistrationResult editRecord(RegistrationRecord registrationRecord){
        RegistrationResult registrationResult=new RegistrationResult();
        Integer line= registrationRecordMapper.updateRecord(registrationRecord);

        if(line>=1){
            registrationResult.setMsg("修改成功");
            registrationResult.setSuccess(true);
            registrationResult.setStatusCode("20000");
        }else{
            registrationResult.setSuccess(false);
            registrationResult.setStatusCode("40000");
        }
        return registrationResult;
    }

    //-----------------------查-------------------------------------------------

    //分页查找
    public RegiPageResult selectByPageInTemp(Integer startPage,Integer pageSize){
        RegiPageResult regiPageResult=new RegiPageResult();
        List<RegistrationRecord> registrationRecords=registrationRecordMapper.selectByPageInTemp(startPage,pageSize);
        Integer total=registrationRecordMapper.selectNumInTemp();
        regiPageResult.setTotal(total);
        regiPageResult.setRegistrationRecordList(registrationRecords);
        return regiPageResult;
    }


    //开局产生展示表
    public void createTempTable(){
        registrationRecordMapper.dropTempTable();
        registrationRecordMapper.createTempTable();
    }

    //使用选择器产生展示表
    public void createTempTableBySelector(SelectContainer selectContainer){
        registrationRecordMapper.dropTempTable();
        registrationRecordMapper.createTempTableBySelector(selectContainer.getCategory(),selectContainer.getCamp());
    }

    //使用名字模糊查找产生展示表
    public void  createTempTableByName(String name){
        registrationRecordMapper.dropTempTable();
        registrationRecordMapper.createTempTableByName(name);
    }
  }
