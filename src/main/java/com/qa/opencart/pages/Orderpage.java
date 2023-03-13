package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class Orderpage {
	By order = By.id("order");
	By price = By.id("price");
	public void getOrder(){
		System.out.println("get order ");
	}
	
	public void getPrice(){
		System.out.println("get Price  ");
	}
}
