import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        try {
        System.out.print("Enter Decimal Number: ");
        Scanner read = new Scanner(System.in);
        conversion instance = new conversion();
        int decimal = Integer.parseInt(instance.filterInput(read.nextLine()));
        System.out.printf("Input Cleaned: %d%n",decimal);
        System.out.println("====RESULTS====");
        instance.binary(decimal);
        instance.octal_Hexa_conversion(decimal, 8);
        instance.octal_Hexa_conversion(decimal,16);
        System.out.println("===============");
        
        } catch (Exception e) {
           System.out.printf("Error: %s",e.fillInStackTrace());
        }
    }
}

class conversion{
   public void binary(int decimal){
    int zero = 0;
    String output = "";
    int binary = 2;
    
    if(decimal ==0){
        System.out.println("BINARY: 0");
        return;
    }
        while(decimal!=zero){
            output+= (decimal%binary == zero) ? 0:1;
            decimal/=binary;
        }
          System.out.printf("BINARY: %s %n",new StringBuilder(output).reverse());
    }
    public void octal_Hexa_conversion(double decimal,int base){
        String hexadecimal ="0123456789ABCDEF";
        List<String> output = new ArrayList<>();
        String processedString ="";
        int octal =8;
        int hexa =16;

        if(base !=octal && base!=hexa ){
            System.out.println("Base 8 and 16 is Allowed.");
            return;
        } 
        if(decimal == 0){
            System.out.println((base==hexa) ? "HEXA: 0":"OCTAL: 0");
            return;
        }
        while(decimal>0){
            decimal/=base;
            String ternary = (decimal%base == 0)? "0":returnremainder(decimal,base);
            output.add(ternary);    
            decimal = (decimal<1)? 0:decimal;
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
    public String filterInput (String input){
        String filtered = "";
        for(char c : input.toCharArray()){
            if(Character.isDigit(c)){
                filtered+=c;
            }
        }
        return filtered.toString();
    }
    private String returnremainder(double decimal, int base){
        double remainder = base *(decimal -  Math.floor(decimal));
        return String.format("%.0f",Math.floor(remainder));
    }
}