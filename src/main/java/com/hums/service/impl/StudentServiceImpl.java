package com.hums.service.impl;

import com.hums.dao.StudentDao;
import com.hums.entity.Student;
import com.hums.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName StudentServiceImpl
 * @Description TODO
 * @Author hums
 * @Date 2021/3/24 19:32:24
 **/
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    public String getSno(String sno, String password) {
        return studentDao.getSno(sno,password);
    }

    public String login(String sno, String password) {
        return studentDao.login(sno,password);
    }

    public List<Student> userInfo(String sno) {
        return studentDao.userInfo(sno);
    }

    public int getStuNumber() {
        return studentDao.getStuNumber();
    }

    public List<Student> stuInfo(int startRecord, int pageSize) {
        return studentDao.stuInfo(startRecord,pageSize);
    }

    public void addUsers(String sno, String sname, String password, String tno, String tname, String tgrade) {
        studentDao.addUsers(sno,sname,password,tno,tname,tgrade);
    }

    public void updateUsers(Integer id, String sno, String sname, String password, String tno, String tname, String tgrade) {
        studentDao.updateUsers(id,sno,sname,password,tno,tname,tgrade);
    }

    public void removeUsers(Integer id) {
        studentDao.removeUsers(id);
    }
}
