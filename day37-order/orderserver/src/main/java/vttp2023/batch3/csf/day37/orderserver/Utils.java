package vttp2023.batch3.csf.day37.orderserver;

import java.io.StringReader;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2023.batch3.csf.day37.orderserver.models.LineItem;
import vttp2023.batch3.csf.day37.orderserver.models.Order;

public class Utils {

	public static final String SQL_INSERT_ORDER = """
			insert into orders(order_id, name, email, express) values (?, ?, ?, ?) 
		""";
	public static final String SQL_INSERT_LINEITEM = """
			insert into lineitems(order_id, item_name, quantity, unit_price) values (?, ?, ?, ?)
		""";

	public static JsonObject toJsonObject(String data) {
		JsonReader reader = Json.createReader(new StringReader(data));
		return reader.readObject();
	}

	public static Order toOrder(JsonObject json) {
		List<LineItem> lineItems = json.getJsonArray("lineItems").stream()
				.map(j -> j.asJsonObject())
				.map(j -> new LineItem(j.getString("name", "")
							, j.getInt("quantity", 0)
							, (float)j.getJsonNumber("unitPrice").doubleValue()))
				.toList();
		return new Order(json.getString("name"), json.getString("email")
				, json.getBoolean("express", false), lineItems);
	}
}
