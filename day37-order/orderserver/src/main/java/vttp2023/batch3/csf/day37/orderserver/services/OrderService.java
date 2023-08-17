package vttp2023.batch3.csf.day37.orderserver.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp2023.batch3.csf.day37.orderserver.repositories.LineItemRepository;
import vttp2023.batch3.csf.day37.orderserver.repositories.OrdersRepository;
import vttp2023.batch3.csf.day37.orderserver.models.LineItem;
import vttp2023.batch3.csf.day37.orderserver.models.Order;

@Service
public class OrderService {

	@Autowired
	private OrdersRepository orderRepo;

	@Autowired
	private LineItemRepository lineItemRepo;

	@Transactional(rollbackFor = OrderException.class)
	public String insertNewOrder(Order order) throws OrderException {

		String orderId = UUID.randomUUID().toString().substring(0, 8);

		System.out.printf(">>> generated orderId: %s\n", orderId);

		try {
			orderRepo.createOrder(orderId, order);

			if (order.lineItems().size() == 0) 
				throw new OrderException("No line items");
				//return orderId;

			for(LineItem li: order.lineItems())
				lineItemRepo.insertLineItem(orderId, li);
		} catch (DataAccessException ex) {
			throw new OrderException(ex.getMessage());
		}

		return orderId;
	}
}
