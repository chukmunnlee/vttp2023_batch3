package day08;

//public class Cart<T, U> {
public class Cart<T, U extends Number> implements Hittable {

    private T item;
    private U quantity;

    public T getItem() {
        return item;
    }
    public void setItem(T item) {
        this.item = item;
    }

    public U getQuantity() {
        return quantity;
    }
    public void setQuantity(U quantity) {
        this.quantity = quantity;
    }

    public void hit(int damage) { }

}
