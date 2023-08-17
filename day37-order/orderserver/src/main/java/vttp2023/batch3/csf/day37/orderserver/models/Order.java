package vttp2023.batch3.csf.day37.orderserver.models;

import java.util.List;

public record Order(String name, String email, Boolean express, List<LineItem> lineItems) {
}
