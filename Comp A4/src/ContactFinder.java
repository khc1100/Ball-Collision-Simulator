import java.util.ArrayList;
import java.util.HashSet;

public class ContactFinder {
    // todo for students
    // Returns a HashSet of ContactResult objects representing all the contacts between circles in the scene.
    // The runtime of this method should be O(n^2) where n is the number of circles.
    public static HashSet<ContactResult> getContactsNaive(ArrayList<Circle> circles) {
        HashSet<ContactResult> results= new HashSet<ContactResult>();
        for(int i=0; i<circles.size();i++){
            for(int j=0; j<circles.size();j++){
                if(circles.get(i).id!=circles.get(j).id){
                if(circles.get(i).isContacting(circles.get(j))!=null) {
                    results.add(circles.get(i).isContacting(circles.get(j)));
                }
                }
            }
        }

        return results;
    }

    // todo for students
    // Returns a HashSet of ContactResult objects representing all the contacts between circles in the scene.
    // The runtime of this method should be O(n*log(n)) where n is the number of circles.
    public static HashSet<ContactResult> getContactsBVH(ArrayList<Circle> circles, BVH bvh) {
        HashSet<ContactResult> results = new HashSet<ContactResult>();
        for (int i=0; i<circles.size();i++){
            HashSet<ContactResult> temp = getContactBVH(circles.get(i),bvh);

            for(ContactResult d: temp){
                results.add(d);

            }

        }
        return results;
    }

    // todo for students
    // Takes a single circle c and a BVH bvh.
    // Returns a HashSet of ContactResult objects representing contacts between c
    // and the circles contained in the leaves of the bvh.
    public static HashSet<ContactResult> getContactBVH(Circle c, BVH bvh) {
        HashSet<ContactResult> boon = new HashSet<ContactResult>();

        if (c.getBoundingBox().intersectBox(bvh.boundingBox)){

            if(bvh.containedCircle!=null){
                if(c.isContacting(bvh.containedCircle)!=null);

                if(c.id!=bvh.containedCircle.id) {
                    boon.add(c.isContacting(bvh.containedCircle));
                    return boon;
                }
            }
            if (bvh.child1!= null){

                return getContactBVH(c,bvh.child1);
            }
            if(bvh.child2!=null) {

               return getContactBVH(c, bvh.child2);

            }
        }
        if(bvh.child1!=null&bvh.child2!=null) {

                if (bvh.child1.containedCircle.isContacting(bvh.child2.containedCircle) != null) {
                    boon.add(bvh.child1.containedCircle.isContacting(bvh.child2.containedCircle));
                }

        }
        else {
            return boon;
        }

        return boon;
    }
}
