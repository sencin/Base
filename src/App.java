import java.util.*;
public class App {
    public static void main(String[] args) throws Exception {

        System.out.print("Enter Decimal Number: ");
        Scanner read = new Scanner(System.in);
        int decimal = Math.abs(Integer.parseInt(read.nextLine()));
        conversion instance = new conversion();
        instance.binary(decimal);
        instance.octal_Hexa_conversion(decimal, 8);
        instance.octal_Hexa_conversion(decimal,16);
        
    }
}

class conversion{
   int zero =0;
   double tolerance = 1e-0;
   public void binary(int decimal){
    String output = "";
    int binary = 2;
        while(decimal!=zero){
            if(decimal%binary == zero){
                decimal/=binary;
                output+=0;
            }else{
                decimal/=binary;
                output+= 1;
            } 
        }
          System.out.printf("BINARY VALUE: %s %n",new StringBuilder(output).reverse());
    }
    
    public void octal_Hexa_conversion(double decimal,int base){

        String hexadecimal ="0123456789ABCDEF";
        List<String> output = new ArrayList<>();

        String convertedString ="";
        int value = base;

        if(value !=8 && value!=16 ){
            System.out.println("Base 8 and 16 is Allowed.");
            return;
        }
        
        while(decimal>=tolerance){
            if(decimal%value==0){
                decimal/=value;
                output.add("0");
            }else{
                decimal/=value;
                double remainder = value *(decimal -  Math.floor(decimal));
                output.add(String.format("%.0f",Math.floor(remainder)));
            }
        }
    
        if( base ==16){
            for(int a =0;a<output.size();a++){
                int num = Integer.parseInt(output.get(a));
                convertedString+=hexadecimal.charAt(num);
             }
        }
        else{
            for (String string : output) {
                convertedString+=string;
            }     
        }
          System.out.printf((base==16) ? "HEXADECIMAL: %s %n":"OCTAL: %s %n", new StringBuilder(convertedString).reverse());
    }
}