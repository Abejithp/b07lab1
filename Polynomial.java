class Polynomial{

    double [] coefficients;

    public Polynomial(){
        coefficients = new double[0];
    }
    
    public Polynomial(double[] poly){
        coefficients = new double[poly.length];
        System.arraycopy(poly, 0, coefficients, 0, poly.length);
    }

    public Polynomial add(Polynomial other){
        int max = Math.max(coefficients.length,other.coefficients.length);
        int min = Math.min(coefficients.length,other.coefficients.length);
        double[] add = new double[max];
        for(int i = 0; i<min; i++){
            add[i]= coefficients[i] + other.coefficients[i];
        }
        if(coefficients.length == max){
            for(int j = min;j<max;j++){
                add[j]= coefficients[j];
            }
        }

        else{
            for(int j = min;j<max;j++) {
                add[j] = other.coefficients[j];
            }
        }
        Polynomial total = new Polynomial(add);
        return total;

    }

    public double evaluate(double x){
        double total = 0.0;
        for(int i = 0; i < coefficients.length;i++){
            total += coefficients[i]*Math.pow(x,i);
        }
        return total;
    }

    public boolean hasRoot(double x){
        if(evaluate(x)==0){
            return true;
        }
        return false;
    }
}