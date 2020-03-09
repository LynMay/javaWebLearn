package com.li.LoginTest;

import com.li.dao.UserDao;
import com.li.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.JavaBean;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserDao userDao = new UserDao();
        User loginUser = userDao.login(user);
        //1、获取请求参数的值
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");
        System.out.println(checkCode);
        //2、验证码是否正确
        //2.1 使用session获取checkCode的值
        HttpSession session = request.getSession();
        String checkCode1 = (String) session.getAttribute("checkCode");
        session.removeAttribute("checkCode");
        System.out.println(checkCode1);
        //2.2 判断
        if(checkCode1 != null && checkCode1.equalsIgnoreCase(checkCode)){
            //判断用户名与密码
            if(loginUser!=null){
                //用户名与密码正确
                //使用request域存储用户名
                request.setAttribute("username",loginUser.getUsername());
                //转发
                request.getRequestDispatcher("/successServlet").forward(request,response);
            }else{
                //用户名与密码不正确
                request.setAttribute("user_error","用户名或密码输入错误");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
                System.out.println("用户名或密码输入错误");
            }

        }else{
            //验证码不正确，
            request.setAttribute("cc_error","验证码输入错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            System.out.println("验证码输入错误");

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
