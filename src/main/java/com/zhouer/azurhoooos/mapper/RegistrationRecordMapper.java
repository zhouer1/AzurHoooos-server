package com.zhouer.azurhoooos.mapper;
import com.zhouer.azurhoooos.entity.RegistrationRecord;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface RegistrationRecordMapper {

    //新增一条
    @Insert("insert into registration_record(name,age,category,camp,handler,fare,date) values(#{name},#{age},#{category},#{camp},#{handler},#{fare},CURDATE())")
    Integer addRecord(RegistrationRecord registrationRecord);

    //删除一条
    @Delete("delete from registration_record where id=#{id}")
    Integer deleteRecord(Integer id);

    //删除展示表（以便于创建一个新的）
    @Update("drop table if exists temp_registration_record")
    void dropTempTable();

    //更新一条
    @Update("update registration_record set name=#{name},age=#{age},category=#{category},camp=#{camp},handler=#{handler},fare=#{fare} where id=#{id}")
    Integer updateRecord(RegistrationRecord registrationRecord);

    //查询所有
    @Select("select * from registration_record")
    List<RegistrationRecord> selectRegistrationRecord();

    //查询展示表中数据的条数
    @Select("select count(*) from temp_registration_record")
    Integer selectNumInTemp();

    //选择器选择后创建展示表
    void createTempTableBySelector(@Param("category")List<String> category,@Param("camp")List<String> camp);

    //开局创建展示表
    @Update("create table if not exists temp_registration_record as select * from registration_record")
    void createTempTable();

    //使用名字进行模糊查询并创建新的展示表
    @Update("create table if not exists temp_registration_record as select * from registration_record where name like CONCAT('%',#{name},'%')")
    void createTempTableByName(String name);

    //分页查询统一查展示表，分页查询不需要考虑展示表的内容，因为内容已经提前更新好了
    @Select("select * from temp_registration_record order by id desc limit #{startPage},#{pageSize}")
    List<RegistrationRecord> selectByPageInTemp(@Param("startPage") Integer page,@Param("pageSize") Integer pageSize);
}
