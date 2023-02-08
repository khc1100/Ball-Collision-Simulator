import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BVH implements Iterable<Circle>{
    Box boundingBox;

    BVH child1;
    BVH child2;

    Circle containedCircle;

    // todo for students
    public BVH(ArrayList<Circle> circles) {
        Box btbox = buildTightBoundingBox(circles);
        this.boundingBox = buildTightBoundingBox(circles);
        if (circles.size()>1){
            ArrayList<Circle>[] bow= split(circles,btbox);
            this.child1 = new BVH(bow[0]);
            this.child2 = new BVH(bow[1]);
        }
        else {
            if(circles.size()==1) {
                containedCircle = circles.get(0);
            }

        }

    }

    public void draw(Graphics2D g2) {
        this.boundingBox.draw(g2);
        if (this.child1 != null) {
            this.child1.draw(g2);
        }
        if (this.child2 != null) {
            this.child2.draw(g2);
        }
    }

    // todo for students
    public static ArrayList<Circle>[] split(ArrayList<Circle> circles, Box boundingBox) {
        //check if the width is greater than the height
        ArrayList<Circle>[] beakfast = new ArrayList[2];
        beakfast[1]=new ArrayList<Circle>();
        beakfast[0]=new ArrayList<Circle>();

        if(boundingBox.getWidth() > boundingBox.getHeight() ){
            for (int i=0; i<circles.size();i++){
                if(circles.get(i).getPosition().x> boundingBox.getMidX()){
                    beakfast[1].add(circles.get(i));
                }
                else {
                    beakfast[0].add(circles.get(i));
                }
            }

        }
        else {
            for (int i=0; i<circles.size();i++){
                if(circles.get(i).getPosition().y>boundingBox.getMidY()){
                    beakfast[1].add(circles.get(i));
                }
                else{
                    beakfast[0].add(circles.get(i));
                }
            }

        }
        return beakfast;
    }

    // returns the smallest possible box which fully encloses every circle in circles
    public static Box buildTightBoundingBox(ArrayList<Circle> circles) {
        Vector2 bottomLeft = new Vector2(Float.POSITIVE_INFINITY);
        Vector2 topRight = new Vector2(Float.NEGATIVE_INFINITY);

        for (Circle c : circles) {
            bottomLeft = Vector2.min(bottomLeft, c.getBoundingBox().bottomLeft);
            topRight = Vector2.max(topRight, c.getBoundingBox().topRight);
        }

        return new Box(bottomLeft, topRight);
    }

    // METHODS BELOW RELATED TO ITERATOR

    // todo for students
    @Override
    public Iterator<Circle> iterator() {
        return null;
    }

    public class BVHIterator implements Iterator<Circle> {

        // todo for students
        public BVHIterator(BVH bvh) {

        }

        // todo for students
        @Override
        public boolean hasNext() {
            return false;
        }

        // todo for students
        @Override
        public Circle next() {
            return null;
        }
    }
}