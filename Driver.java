import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Driver {
    public static void main(String [] args) throws IOException {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double [] c1 = {2,0,3};
        Polynomial p1 = new Polynomial(c1);
        double [] c2 = {2,5};
        Polynomial p2 = new Polynomial(c2);
        Polynomial s = p1.add(p2);
        Polynomial m = p1.multiply(p2);

        File file = new File("/Users/abejith/Downloads/Second Year/b07lab1/info");
        String poly = "test";
        p1.saveToFile(poly);
       //System.out.println("coefficients"+n.coefficients[0]);
        /*
         System.out.println("multi:"+m.coefficients[0]);
        System.out.println("s(0.1) = " + s.evaluate(0.1));
        if(s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");
        */

    }
}
