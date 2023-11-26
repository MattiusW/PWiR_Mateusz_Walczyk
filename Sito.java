class Sito extends Thread{
    
    private static int count = 1;
    private Buffor bufforIn;
    private Buffor bufforOut;
    private int prime;

    public Sito(int prime, Buffor bufforIn){
        this.prime = prime;
        this.bufforIn = bufforIn;
    }
    
    public void run(){
        Sito sito = null;
        
        while(true){
            int value = bufforIn.getProduct();
            if(value % prime != 0){
                if(bufforOut == null){
                    bufforOut = new Buffor();
                    sito = new Sito(value, bufforOut);
                    System.out.println(count + " . " + sito);
                }
            }
            else{
                bufforOut.setProduct(value);
            }
        }
    }
    
    @Override
    public String toString(){
        return " " + prime;
    }
    
}