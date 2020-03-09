package com.li.LoginTest;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet( "/checkCodeServlet")
public class checkCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int hight = 50;
        int width = 100;
        //1、创建一个对象
        BufferedImage bufferedImage = new BufferedImage(width, hight, BufferedImage.TYPE_INT_RGB);
        //2 美化图片
        //2.1 填充背景色
        Graphics graphics = bufferedImage.getGraphics();//画笔对象
        graphics.setColor(Color.PINK);//设置画笔颜色
        graphics.fillRect(0,0,width,hight);
        //2.2 画边框
        graphics.setColor(Color.blue);
        graphics.drawRect(0,0,width-1,hight-1);
        //2.3 写验证码，验证码使使用随机数显示
        String str = "QWERTYUIOPASDFGHJKLMNBVCXZqwertyuioplkjhgfdsazxcvbnm1234567890";
        Random ran = new Random();
        //2.4new一个StringBuilder，存储验证码信息
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=1;i<=4;i++){
            int num = ran.nextInt(str.length());
            char c = str.charAt(num);
           stringBuilder.append(c);
            graphics.drawString(c+"",width/5*i,hight/2);
        }
        //2.5、将验证码存入session中，以便之后的验证
        String checkCode = stringBuilder.toString();
        request.getSession().setAttribute("checkCode",checkCode);
        //2.6 随机画一些线
        graphics.setColor(Color.GREEN);
        for(int i=0;i<10;i++){
            int x1 = ran.nextInt(width);
            int x2 = ran.nextInt(width);
            int y1 = ran.nextInt(hight);
            int y2 = ran.nextInt(hight);
            graphics.drawLine(x1,y1,x2,y2);
        }

        //3 输出
        ImageIO.write(bufferedImage,"jpg",response.getOutputStream());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
