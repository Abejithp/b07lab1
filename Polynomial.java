import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Polynomial{

    double [] coefficients;
    int [] degree;

    public Polynomial(){
        coefficients = new double[0];
        degree = new int[0];
    }
    
    public Polynomial(double[] poly){

        int zero = 0;
        for(int i = 0; i < poly.length; i++){
            if(poly[i]==0){
                zero +=1;
            }
        }

        coefficients  = new double[poly.length-zero];
        degree = new int[poly.length-zero];

        int x = 0;
        for(int j = 0; j < poly.length; j++){
            if(poly[j]!=0){
                coefficients[x] = poly[j];
                degree[x] = j;
                x +=1;
            }
        }

    }

    public void sort(Polynomial poly){
        double co = 0;
        int expo=0;
        for(int i=0;i<poly.degree.length;i++){
            for(int j=i; j<poly.degree.length;j++){
               if(poly.degree[i]>poly.degree[j]){
                   expo = poly.degree[i];
                   co = poly.coefficients[i];

                   poly.degree[i] = poly.degree[j];
                   poly.coefficients[i] = poly.coefficients[j];

                   poly.degree[j] = expo;
                   poly.coefficients[j]=co;
               }
            }
        }
    }


    public Polynomial add(Polynomial other){
        if(coefficients.length + other.coefficients.length==0){
            return null;
        }
        else if(coefficients.length==0){
            return other;
        }
        else if(other.coefficients.length==0){
            Polynomial add = new Polynomial();
            add.coefficients = coefficients.clone();
            add.degree = degree.clone();
            return add;
        }

        int max = Math.max(degree.length,other.degree.length);
        int min = Math.min(degree.length, other.degree.length);

        double[] total = new double[Math.max(degree[degree.length-1],other.degree[other.degree.length-1])+1];

        sort(this);
        sort(other);

        for(int i=0; i<min;i++){
            if(degree[i]==other.degree[i]){
                total[i]= coefficients[i]+other.coefficients[i];
            }
            else{
                total[degree[i]] += coefficients[i];
                total[other.degree[i]] += other.coefficients[i];
            }
        }

        if(degree.length==max){
            for(int j=min;j<max;j++){
               total[degree[j]]+=coefficients[j];
            }
        }

        else {
            for (int j = min; j < max; j++) {
                total[other.degree[j]] +=coefficients[j];
            }
        }


        return new Polynomial(total);

    }
    public Polynomial multiply(Polynomial other){
        if(coefficients.length + other.coefficients.length==0){
            return null;
        }
        else if(coefficients.length==0){
            return other;
        }
        else if(other.coefficients.length==0){
            Polynomial m = new Polynomial();
            m.coefficients = coefficients.clone();
            m.degree = degree.clone();
            return m;
        }
        double[] multi = new double[degree[degree.length-1]+other.degree[other.degree.length-1]+1];

        for(int i=0;i<degree.length;i++){
            for(int j=0; j<other.degree.length; j++){
                multi[degree[i]+other.degree[j]] += coefficients[i]*other.coefficients[j];
            }
        }

        return new Polynomial(multi);

    }
    public double evaluate(double x){
        double total = 0.0;
        for(int i = 0; i < coefficients.length;i++){
            total += coefficients[i]*Math.pow(x,degree[i]);
        }
        return total;
    }

    public boolean hasRoot(double x){
        if(evaluate(x)==0){
            return true;
        }
        return false;
    }

    public Polynomial(File x) throws FileNotFoundException {
        Scanner scan = new Scanner(x);
        String line = scan.nextLine();
        String filtered = line.charAt(0)+line.substring(1).replaceAll("-","%-");

        String[] terms = filtered.split("[+ %]");
        double[] co = new double[terms.length];
        int[] expo = new int[terms.length];

        for(int i =0;i< terms.length;i++){
            if(terms[i].split("x").length==1){
                //constant
                co[i] = Double.parseDouble(terms[i]);
                expo[i] = 0;
            }
            else{
                //only polynomial
                co[i] = Double.parseDouble(terms[i].split("x")[0]);
                expo[i] = Integer.parseInt(terms[i].split("x")[1]);
            }
        }
        this.coefficients = co;
        this.degree=expo;
    }

    public void saveToFile(String fileName) throws IOException {
        String terms = "";

        for(int i=0;i<degree.length;i++){
            terms+= Double.toString(coefficients[i])+"x"+Integer.toString(degree[i]);

            if(i+1!=degree.length&&coefficients[i+1]>0){
                terms+="+";
            }
        }

        BufferedWriter output = new BufferedWriter(new FileWriter(fileName));
        output.write(terms);
        output.close();
    }
}