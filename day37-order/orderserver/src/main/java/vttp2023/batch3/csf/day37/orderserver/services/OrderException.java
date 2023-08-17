package vttp2023.batch3.csf.day37.orderserver.services;

public class OrderException extends Exception {
	public OrderException() { }
	public OrderException(String msg) {
		super(msg);
	}
}
