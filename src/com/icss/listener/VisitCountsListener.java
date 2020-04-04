/**
  * <p>Title: VisitCountsListener.java</p>
　 * <p>Description: </p>
　 * <p>Copyright: Copyright (c) 2020</p>
　 * <p>Company: </p>
　 * @author liuxin
　 * @date 2020年3月23日
　 * @version 1.0
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
　 * <p>Description: </p>
　 * @author liuxin
　 * @date 2020年3月23日
 */
//实现上下文监听
public class VisitCountsListener implements ServletContextListener {

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	//上下文销毁之前，先从上下文中获取登录次数，然后将登录次数存储到文本文件中
	public void contextDestroyed(ServletContextEvent arg0) {
		//1.获得上下文对象
		ServletContext sc = arg0.getServletContext();
		//2.从上下文中取出登录次数
		Integer count =(Integer)sc.getAttribute("counts");
		//3.获得文件真实目录
		String path = sc.getRealPath("/WEB-INF/classes");
		//4.将获得的次数写到文件中
		File file = new java.io.File(path+"/com/icss/listener/visitcounts.txt");
		try {
			//写
			FileWriter fw = new FileWriter(file);
			//缓存
			BufferedWriter bw = new BufferedWriter(fw);
			//开始写入了
			fw.write(count.toString());
			//关闭之前先清空缓存，防止存不进去
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
	//初始上下文之前，先获取文本文件中登录的次数，然后登录次数加一
	public void contextInitialized(ServletContextEvent arg0) {
		//1.获得上下文对象
		ServletContext sc = arg0.getServletContext();
		//2.获得文件真实目录
		String path = sc.getRealPath("/WEB-INF/classes");
		//3.将获得的次数取出
		File file = new java.io.File(path+"/com/icss/listener/visitcounts.txt");
		try {
			//读
			FileReader fr = new FileReader(file);
			//缓存
			BufferedReader br = new BufferedReader(fr);
			//获取读取内容，文件中都是String类型的
			String str =br.readLine();
			int count =Integer.parseInt(str);
			//将值放到上下文中
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
