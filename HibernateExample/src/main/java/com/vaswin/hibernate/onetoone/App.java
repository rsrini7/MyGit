package com.vaswin.hibernate.onetoone;

import java.util.Date;


import org.hibernate.Session;

import com.vaswin.hibernate.util.HibernateUtil;

public class App {
	public static void main(String[] args) {
		System.out.println("Hibernate one to one (Annotation)");
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		Stock stock = new Stock();

		stock.setStockCode("7052");
		stock.setStockName("PADINI");

		StockDetail stockDetail = new StockDetail();
		stockDetail.setCompName("PADINI Holding Malaysia");
		stockDetail.setCompDesc("one stop shopping");
		stockDetail.setRemark("vinci vinci");
		stockDetail.setListedDate(new Date());

		stock.setStockDetail(stockDetail);
		stockDetail.setStock(stock);

		session.save(stock);
		//session.save(stockDetail);
		//stock.setStockName("POWDER");
		
		session.getTransaction().commit();
		session.close();
		
		System.out.println("Done");
	}
}
