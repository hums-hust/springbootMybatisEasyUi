package com.hums.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.hums.entity.Student;

import java.util.List;

/**
 * @ClassName StudentDao
 * @Description TODO
 * @Author hums
 * @Date 2021/3/24 19:32:50
 **/
@Mapper
public interface StudentDao {
    //登陆成功后查询用户的学号
    public String getSno(@Param("sno") String sno,@Param("password") String password);

    //登陆成功后查询用户的姓名
    public String login(@Param("sno") String sno,@Param("password") String password);

    //登陆成功后查询用户的所有信息
    public List<Student> userInfo(@Param("sno") String sno);

    //用户表中数据记录数
    public int getStuNumber( );

    //分页展示
    public List<Student> stuInfo(@Param("startRecord")int startRecord,@Param("pageSize")int pageSize);

    public void addUsers(@Param("sno") String sno,@Param("sname") String sname,@Param("password") String password,@Param("tno") String tno,@Param("tname") String tname,@Param("tgrade") String tgrade);

    public void updateUsers(@Param("id") Integer id,@Param("sno") String sno,@Param("sname") String sname,@Param("password") String password,@Param("tno") String tno,@Param("tname") String tname,@Param("tgrade") String tgrade);

    public void removeUsers(@Param("id") Integer id);
}
