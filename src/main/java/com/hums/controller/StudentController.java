package com.hums.controller;

import com.hums.entity.Student;
import com.hums.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName StudentController
 * @Description TODO
 * @Author hums
 * @Date 2021/3/24 19:30:19
 **/

/**
 * 不要使用spring boot的@RestController注解，直接使用spring原来的注解@Controller就可以了
 * @RestController注解，相当于@Controller+@ResponseBody两个注解的结合，
 * @Responsebody后，返回结果直接写入HTTP response body中，不会被解析为跳转路径，所以你总是看到是打印字符串的效果，不是跳转效果。
 */
//@RestController
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    public String login() {
        return "/login/login";
    }

    //用户登陆
    @RequestMapping(value = "/loginPage", method = {RequestMethod.POST, RequestMethod.GET})
    public String login(HttpServletRequest request, HttpSession session) {
        String sno = request.getParameter("sno");
        String password = request.getParameter("password");
        String sname = studentService.login(sno, password);//查询姓名
        String tno=studentService.getSno(sno, password);//查询课程编号
        List<Student> stuInfo=studentService.userInfo(sno);
        int id=stuInfo.get(0).getId();
        session.setAttribute("id",id);
        session.setAttribute("tno",tno);
        session.setAttribute("sname", sname);

        if (sname == null) {
            return "redirect:/";
        } else {
            return "redirect:/index";
        }
    }
    //用户登陆成功主页面
    @RequestMapping(value = "/index", method = {RequestMethod.POST, RequestMethod.GET})
    public String loginIndex(HttpSession session) {
        return "/login/index";
    }
    //用户信息管理
    @RequestMapping(value = "/stuinfomation", method = {RequestMethod.POST, RequestMethod.GET})
    public String stuInfo(HttpSession session) {

        return "/common/information";
    }
    /*
       用户信息列表
    */
    @PostMapping(value = "/stuinforlist")
    @ResponseBody
    public Map getStuinforList(HttpServletRequest request){
        int page=Integer.parseInt(request.getParameter("page"));
        int pageSzie=Integer.parseInt(request.getParameter("rows"));//pageSzie
        int startRecord=(page-1)*pageSzie+1;
        int total=studentService.getStuNumber();
        List<Student>  stuinforlist=studentService.stuInfo(startRecord,pageSzie);
        Map resultMap=new HashMap();
        resultMap.put("total",total-1);
        resultMap.put("rows",stuinforlist);
        return resultMap;
    }
    /*
    进入用户信息界面
    */
    @GetMapping(value="/info")
    public String stuinfor(){
        return "/common/information";
    }
    @PostMapping(value="/save_users")//保存新增用户和修改的用户信息
    @ResponseBody
    public Map<String,String> saveUsers(@RequestParam(value="id",required = false, defaultValue = "0") Integer id,
                                        @RequestParam("sno") String sno,
                                        @RequestParam("sname") String sname,
                                        @RequestParam("password") String password,
                                        @RequestParam("tno") String tno,
                                        @RequestParam("tname") String tname,
                                        @RequestParam("tgrade") String tgrade,HttpSession session) {

        Map<String, String> map = new HashMap<String, String>();
        if (session.getAttribute("id").equals(id)) {
            map.put("msg", "违法操作！不能修改自己的信息！");
            return map;
        }
        try {
            if (id == 0) {
                studentService.addUsers(sno, sname, password, tno, tname, tgrade);
            } else {
                studentService.updateUsers(id, sno, sname, password, tno, tname, tgrade);
            }
            map.put("success", "true");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "请核对信息确保登录名唯一");
        }

        return map;
    }
    @PostMapping(value = "/remove_users")//删除用户
    @ResponseBody
    public Map<String,String> removeUsers(@RequestParam("id") Integer id,HttpSession session){


        Map<String,String> result = new HashMap<String,String>();
        if(session.getAttribute("id").equals(id)){
            result.put("msg","违法操作！不能删除自己！");
            return result;
        }
        try{
            studentService.removeUsers(id);
            result.put("success","true");
            System.out.println("删除Id: "+id);
        }catch(Exception e) {
            e.printStackTrace();
            result.put("msg","Some errors occured.");
        }
        return result;
    }

}
