/**
  * <p>Title: VisitCountsListener.java</p>
�� * <p>Description: </p>
�� * <p>Copyright: Copyright (c) 2020</p>
�� * <p>Company: </p>
�� * @author liuxin
�� * @date 2020��3��23��
�� * @version 1.0
 */
package com.icss.listener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * <p>Title: VisitCountsListener</p>
�� * <p>Description: </p>
�� * @author liuxin
�� * @date 2020��3��23��
 */
//ʵ�������ļ���
public class VisitCountsListener implements ServletContextListener {

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	//����������֮ǰ���ȴ��������л�ȡ��¼������Ȼ�󽫵�¼�����洢���ı��ļ���
	public void contextDestroyed(ServletContextEvent arg0) {
		//1.��������Ķ���
		ServletContext sc = arg0.getServletContext();
		//2.����������ȡ����¼����
		Integer count =(Integer)sc.getAttribute("counts");
		//3.����ļ���ʵĿ¼
		String path = sc.getRealPath("/WEB-INF/classes");
		//4.����õĴ���д���ļ���
		File file = new java.io.File(path+"/com/icss/listener/visitcounts.txt");
		try {
			//д
			FileWriter fw = new FileWriter(file);
			//����
			BufferedWriter bw = new BufferedWriter(fw);
			//��ʼд����
			fw.write(count.toString());
			//�ر�֮ǰ����ջ��棬��ֹ�治��ȥ
			fw.flush();
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	//��ʼ������֮ǰ���Ȼ�ȡ�ı��ļ��е�¼�Ĵ�����Ȼ���¼������һ
	public void contextInitialized(ServletContextEvent arg0) {
		//1.��������Ķ���
		ServletContext sc = arg0.getServletContext();
		//2.����ļ���ʵĿ¼
		String path = sc.getRealPath("/WEB-INF/classes");
		//3.����õĴ���ȡ��
		File file = new java.io.File(path+"/com/icss/listener/visitcounts.txt");
		try {
			//��
			FileReader fr = new FileReader(file);
			//����
			BufferedReader br = new BufferedReader(fr);
			//��ȡ��ȡ���ݣ��ļ��ж���String���͵�
			String str =br.readLine();
			int count =Integer.parseInt(str);
			//��ֵ�ŵ���������
			sc.setAttribute("counts", count);
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
