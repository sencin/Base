import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        try {
        Scanner read = new Scanner(System.in);
        conversion instance = new conversion();
        System.out.print("Enter Decimal Number: ");
        double decimal = Double.parseDouble(instance.filterInput(read.nextLine()));
        System.out.printf("Data Cleaned: %.1f %n",decimal);
        System.out.println("===BINARY===");
        instance.convert(decimal, 2);
        System.out.println("====OCTAL===");
        instance.convert(decimal, 8);
        System.out.println("====HEXA====");
        instance.convert(decimal, 16);
        System.out.println("============");
        } catch (Exception e) {
        System.out.printf("Error: %s",e.fillInStackTrace());
        }
    }
}

class conversion{
    public String filterInput (String input){
        String filtered = "";
        for(char c : input.toCharArray()){
            if(Character.isDigit(c) || c =='.'){
                filtered+=c;
            }
        }
        return filtered.toString();
    }
    private String returnremainder(double decimal, int base){
        double remainder = base *(decimal -  Math.floor(decimal));
        return String.format("%.0f",Math.floor(remainder));
    }
    private char getHexadecimalvalues(int index){
        String hexadecimal ="0123456789ABCDEF";
        return hexadecimal.charAt(index);
    }

    public void convert(double decimal, int base){
       
        List<String> integercontainer = new ArrayList<>();
        String processedInteger ="";

        double floatingPointContainer = 0.0;
        List<String> decimalContainer = new ArrayList<>();
        StringBuilder processedDecimal = new StringBuilder();
        
        int hexa = 16;
        int octal = 8;
        int binary =2;

        if(base !=octal && base!=hexa && base !=binary ){
            System.out.println("Base 2, 8 and 16 is Allowed.");
            return;
        } 

        if(decimal - Math.floor(decimal) != 0){
            String[] split = String.valueOf(decimal).split("\\.");
            floatingPointContainer = decimal - Integer.parseInt(split[0]);
            decimal = Integer.parseInt(split[0]);
        }
        if(decimal == 0) processedInteger+="0";
        
        while(decimal>0){
            decimal/=base;
            String ternary = (decimal%base == 0)? "0":returnremainder(decimal,base);
            integercontainer.add(ternary);    
            decimal = (decimal<1)? 0:decimal;
        }

        if(floatingPointContainer != 0.0){
          boolean isTrue = true;
            while (isTrue) {
            double value = floatingPointContainer*base; 
            decimalContainer.add(String.format("%.0f",Math.floor(value)));
            floatingPointContainer = 0 + value % 1;
               if(decimalContainer.size()==7){
                break;
               }
            }
        }

        for(int a =0;a<integercontainer.size();a++){
            if(base==hexa){
                int num = Integer.parseInt(integercontainer.get(a));
                processedInteger+=getHexadecimalvalues(num);     
                    if(!decimalContainer.isEmpty()){
                        for(int z =0;z<decimalContainer.size();z++){
                            int num2 = Integer.parseInt(decimalContainer.get(z));
                            processedDecimal.append(getHexadecimalvalues(num2));
                        }                    
                    }
            }
            else{
                processedInteger+= integercontainer.get(a).toString();
            }       
        }
   
       StringBuilder reversedProcessedString = new StringBuilder(processedInteger).reverse();    
       
        if(!decimalContainer.isEmpty()){
            for (String string : decimalContainer){
                processedDecimal.append(string);
            } 
            String decimalformat = processedDecimal.substring(0,processedDecimal.length()/2);
            System.out.printf("OUTPUT: %s.%s \n",reversedProcessedString.toString(),decimalformat.toString());       
        }
        else{
            System.out.printf("OUTPUT: %s \n", reversedProcessedString);
        }
    }
 }
