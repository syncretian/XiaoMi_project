package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.junit.Test;

public class ImgCode {
	static Random r = new Random();
	 public static String code = "";
	
	public static String getImg() {
		int width = 150;
		int height = 50;
		//创建图片缓存对象
		BufferedImage bm = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//得到画笔  对象
		Graphics2D g = bm.createGraphics();
		
		
		g.setColor(getColor());
		g.setFont(new Font("新宋体", Font.BOLD, 40));
		
		//填充矩形(  坐标 x , y 矩形宽高 w ,h )
		g.fillRect(0, 0, width, height);
		
		//画出4个 验证码
		int x = 0;
		int y = 0;
		
		code="";
		for(int i =0 ;i < 4 ;i++) {
			char ch = getChar();
			x+=20+r.nextInt(10);
			y=20+r.nextInt(30);
			
			g.setColor(getColor());
			g.drawString(ch+"", x, y);
			
			code = code+ch;
		}
		
		System.out.println("请求验证码："+code);
		//画10条干扰线
		int x1 = 0 ;
		int y1 = 0 ;
		int x2 = 0 ;
		int y2 = 0 ;
		for(int i = 0 ;i < 5; i++) {
			
			x1 = 5+r.nextInt(width/2);
			y1= 40- r.nextInt(height);
			
			x1 = width/2 + r.nextInt(width/2);
			y2= r.nextInt(height);
			
			g.setColor(getColor());
			g.drawLine(x1, y1, x2, y2);
		}
		
		
		String imgPath = UUID.randomUUID().toString()+".jpg";
		
		//生成图片
		try {
			
			ImageIO.write(bm, "jpg", new FileOutputStream("E:\\XiaoMi_project\\xm_static\\codeImg\\"+imgPath));
			
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		g.dispose();
		
		return imgPath;
		
	}
	
	public static Color getColor() {
		
		int R = r.nextInt(255);
		int G = r.nextInt(255);
		int B = r.nextInt(255);
		
//		System.out.println("RGB( "+R+", "+G+", "+B);
//		int R = (int)Math.random()*255;
//		int G =(int)Math.random()*255;
//		int B = (int)Math.random()*255;
		
		return new Color(R,G,B);
	}
	
	public static char getChar() {
		String str = "0123456789abcdefghijklmnopqrstuvwxyz"+"abcdefghijklmnopqrstuvwxyz".toUpperCase();
		
		return str.charAt(r.nextInt(str.length()));
	}
	
	
	@Test
	public void test(){
		String a = "";
		String b ="s";
		String c = "2";
		
//		System.out.println(new Integer(a));
		System.out.println(new Integer(b));
		System.out.println(new Integer(c));
	}
	
}
