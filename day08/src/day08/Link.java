package day08;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Link {

    LinkedList<Hittable> ofThingsToHit = new LinkedList<>();
    ArrayList<Hittable> arrList = new ArrayList<>();

    List<Hittable> hitList = new LinkedList<>();

    public void hit() {

        Box box = new Box();
        ofThingsToHit.add(box);
        Monster monster = new Monster();
        ofThingsToHit.add(monster);
        Cart<String, Integer> cart = new Cart<>();
        ofThingsToHit.add(cart);

        Hittable h = new Box();
        Box b;

        if (h instanceof Box)
            b = (Box)h;

        h = new Cart<String, Float>();

        hitEverything(ofThingsToHit);
        hitEverything(arrList);

    }

    public void hitEverything(List<Hittable> hitList) {
        for (Hittable h: hitList)
            h.hit(4);
    }
    
}
