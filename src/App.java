import java.util.*;
public class App {
    public static void main(String[] args) throws Exception {
        try {
        System.out.print("Enter Decimal Number: ");
        Scanner read = new Scanner(System.in);
        int decimal = Math.abs(read.nextInt());
        conversion instance = new conversion();

        System.out.println("====RESULTS====");
        instance.binary(decimal);
        instance.octal_Hexa_conversion(decimal, 8);
        instance.octal_Hexa_conversion(decimal,16);
        System.out.println("===============");
        
        } catch (Exception e) {
           System.out.printf("Enter a valid integer number %n%s",e.fillInStackTrace());
        }
    }
}

class conversion{
   
   public void binary(int decimal){
    int zero =0;
    String output = "";
    int binary = 2;
        while(decimal!=zero){
            output+= (decimal%binary == zero) ? 0:1;
            decimal/=binary;
        }
          System.out.printf("BINARY: %s %n",new StringBuilder(output).reverse());
    }
    
    public void octal_Hexa_conversion(double decimal,int base){
        String hexadecimal ="0123456789ABCDEF";
        List<String> output = new ArrayList<>();
        double threshold = 1;
        String processedString ="";
        int octal =8;
        int hexa =16;

        if(base !=octal && base!=hexa ){
            System.out.println("Base 8 and 16 is Allowed.");
            return;
        }
        
        while(decimal>threshold){
            decimal/=base;
            String ternary = (decimal%base ==0)? "0":returnremainder(decimal,base);
            output.add(ternary);    
        }
        for(int a =0;a<output.size();a++){
                if(base==hexa){
                    int num = Integer.parseInt(output.get(a));
                    processedString+=hexadecimal.charAt(num);
                }
                else{
                   processedString+= output.get(a);
                }       
        }
        System.out.printf((base==hexa) ? "HEXA: %s %n":"OCTAL: %s %n", new StringBuilder(processedString).reverse());
    }
    private String returnremainder(double decimal, int base){
        double remainder = base *(decimal -  Math.floor(decimal));
        return String.format("%.0f",Math.floor(remainder));
    }
}