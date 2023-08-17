package vttp2023.batch3.csf.day37.orderserver.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2023.batch3.csf.day37.orderserver.models.LineItem;
import vttp2023.batch3.csf.day37.orderserver.Utils;

@Repository
public class LineItemRepository {

	@Autowired
	private JdbcTemplate template;

	public void insertLineItem(String orderId, LineItem lineItem) {

		template.update(Utils.SQL_INSERT_LINEITEM, orderId, lineItem.name()
				, lineItem.quantity(), lineItem.unitPrice());
	}

}
