package vttp2023.batch3.csf.day37.orderserver.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2023.batch3.csf.day37.orderserver.models.Order;
import vttp2023.batch3.csf.day37.orderserver.Utils;

@Repository
public class OrdersRepository {

	@Autowired
	private JdbcTemplate template;

	public void createOrder(String orderId, Order order) {

		// Throws unchecked exception - DataAccessException
		template.update(Utils.SQL_INSERT_ORDER
				, orderId, order.name(), order.email(), order.express());

	}
}
