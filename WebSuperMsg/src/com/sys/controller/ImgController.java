package com.sys.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sys.common.ConfigKeys;
import com.sys.common.util.ConfigUtil;
import com.sys.common.util.ImgUtil;
import com.sys.common.util.StringUtil;

/**
 * 
 * @author chenchuan
 * @date 2016��1��22�� 
 * ͼƬ���������
 */
@Controller
@RequestMapping("/img")
public class ImgController {
	/**
	 * ��֤���ַ���
	 */
	char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'X', 'Y', 'Z',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	@RequestMapping("/code")
	public void verifyCode(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// ����ҳ�治����
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		// ���ڴ��д���ͼ��
		int width = 80, height = 30;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// ��ȡͼ��������
		Graphics g = image.getGraphics();

		// ���������
		Random random = new Random();

		// �趨����ɫ
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);

		// ���߿�
		// g.setColor(new Color());
		// g.drawRect(0,0,width-1,height-1);

		// �������155�������ߣ�ʹͼ���е���֤�벻�ױ���������̽�⵽
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 5; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		// ȡ�����������֤��(4λ�ַ�)
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(codeSequence[random.nextInt(59)]);
			sRand += rand;
			// �趨����
			g.setFont(getRandomFont());
			// ����֤����ʾ��ͼ����
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			// ���ú�����������ɫ��ͬ����������Ϊ����̫�ӽ�������ֻ��ֱ������
			g.drawString(rand, 18 * i + 6, 20);
		}

		// ����֤�����session
		request.getSession().setAttribute("code", sRand);

		// ͼ����Ч
		g.dispose();
		OutputStream out = response.getOutputStream();
		// ���ͼ��ҳ��
		ImageIO.write(image, "JPEG", out);
		out.close();
	}

	private Color getRandColor(int fc, int bc) { // ������Χ��������ɫ
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	@RequestMapping("/photo")
	public void photo(String path, HttpServletResponse response)throws ServletException, IOException {
		if(StringUtil.isNotNull(path)){
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			String filePath = ConfigUtil.getStrVal(ConfigKeys.IMG_SAVEPATH);
			filePath = ImgUtil.replaceFileSplit(filePath+path).toString();
			File file = new File(filePath);
			FileInputStream fips = new FileInputStream(file);
			bis = new BufferedInputStream(fips);
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			if (bis != null){
				bis.close();
			}
				
			if (bos != null){
				bos.close();
			}
				
		}
	}
	 /**
	  * <b>function:</b> ����������塢���ִ�С
	  * @createDate 2010-8-23 ����10:44:22
	  * @author hoojo
	  * @return
	  */ 
	 public static Font getRandomFont() { 
	     String[] fonts = {"Georgia", "Segoe Script","Verdana", "Arial", "Tahoma", "Time News Roman", "Courier New", "Arial Black", "Quantzite"}; 
	     int fontIndex = (int)Math.round(Math.random() * (fonts.length - 1)); 
	     int fontSize = (int) Math.round(Math.random() * 4 + 16); 
	     return new Font(fonts[fontIndex], Font.PLAIN, fontSize); 
	 } 
}
