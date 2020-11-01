package com.Rolin.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@WebServlet("/Verification")
public class Verification extends HttpServlet {
    public static final int WIDTH = 60; //验证码宽度
    public static final int HEIGHT = 20; //验证码高度
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        HttpSession session = request.getSession();
        response.setContentType("image/jpeg");
        ServletOutputStream sos = response.getOutputStream();

        //设置不要缓存
        response.setHeader("Prama","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires",0);

        //创建图像
        BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        //产生随机验证码
        char[] rands = generateCheckCode();

        //产生图像
        drawBackground(g);
        drawRands(g,rands);

        //结束绘画过程
        g.dispose();

        //将图片输出到客户端
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image,"JPEG",baos);
        byte[] buf = baos.toByteArray();
        response.setContentLength(buf.length);
        sos.write(buf);
        //关闭流
        baos.close();
        sos.close();

        //把正确的验证码存储到Session中
        session.setAttribute("VerCode",new String(rands));
        System.out.println(session.getAttribute("VerCode"));
    }

    private void drawBackground(Graphics g) {
        //画背景
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0,0,WIDTH,HEIGHT);

        //随机产生干扰点
        for (int i = 0; i <Math.random()*200; i++) {
            int x = (int)(Math.random()*WIDTH);
            int y = (int)(Math.random()*HEIGHT);
            int red = (int)(Math.random()*255);
            int green = (int)(Math.random()*255);
            int blue = (int)(Math.random()*255);
            g.setColor(new Color(red,green,blue));
            g.drawOval(x,y,1,0);
        }
    }

    private void drawRands(Graphics g, char[] rands) {
        g.setColor(Color.black);
        g.setFont(new Font(null,Font.ITALIC| Font.BOLD,18));

        //在不同高度上输出每个字符
        int[] Xposition = {1,16,31,46};
        int[] Yposition = {17,15,18,16};
        for (int i = 0; i <4 ; i++) {
            g.drawString(""+rands[i],Xposition[i],Yposition[i]);
        }
    }

    private char[] generateCheckCode() {
        //定义验证码字符表
        String chars = "0123456789abcdefghijklmnopqrstuvwxyz";
        char[] rands = new char[4];
        for (int i = 0; i <4 ; i++) {
            int rand = (int)(Math.random()*36);
            rands[i] = chars.charAt(rand);
        }
        return rands;
    }


}
