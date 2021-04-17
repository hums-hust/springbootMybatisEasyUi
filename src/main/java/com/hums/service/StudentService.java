package com.hums.service;

import com.hums.entity.Student;

import java.util.List;

public interface StudentService {

    public String getSno(String sno,String password);

    public String login(String sno, String password);

    public List<Student> userInfo(String sno);

    public int getStuNumber( );

    public List<Student> stuInfo(int startRecord,int pageSize);

    public void addUsers(String sno,String sname,String password,String tno,String tname,String tgrade);

    public void updateUsers(Integer id,String sno,String sname,String password,String tno,String tname,String tgrade);

    public void removeUsers(Integer id);
}
