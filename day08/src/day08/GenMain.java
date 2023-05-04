package day08;

import java.util.LinkedList;
import java.util.List;

public class GenMain {

    public static void main(String[] args) {

        Cart<String, Integer> strCart = new Cart<>();
        Cart<Integer, Float> intCart = new Cart<>();

        List<Integer> intList = new LinkedList<>();
        intList.add(1);

        List<Cart<String, Integer>> strCartList = new LinkedList<>();
        strCartList.add(strCart);
        //strCartList.add(intCart);
    }
    
}
