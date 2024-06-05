import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        try {
        System.out.print("Enter Decimal Number: ");
        Scanner read = new Scanner(System.in);
        conversion instance = new conversion();
        double decimal = Double.parseDouble(instance.filterInput(read.nextLine()));
        System.out.printf("Data Cleaned: %.2f %n",decimal);
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
        String processedString ="";
        List<String> processedDecimal = new ArrayList<>();
        double fractionpart = 0.0;
        StringBuilder decimals = new StringBuilder();
        int hexa = 16;
        int octal = 8;
        int binary =2;

        if(base !=octal && base!=hexa && base !=binary ){
            System.out.println("Base 2, 8 and 16 is Allowed.");
            return;
        } 

        if(decimal - Math.floor(decimal) != 0){
            String[] split = String.valueOf(decimal).split("\\.");
            fractionpart = decimal - Integer.parseInt(split[0]);
            decimal = Integer.parseInt(split[0]);
        }
        while(decimal>0){
            decimal/=base;
            String ternary = (decimal%base == 0)? "0":returnremainder(decimal,base);
            integercontainer.add(ternary);    
            decimal = (decimal<1)? 0:decimal;
        }

        if(fractionpart != 0.0){
          boolean level = true;
            while (level) {
            double value = fractionpart*base; 
            processedDecimal.add(String.format("%.0f",Math.floor(value)));
            
            fractionpart = 0 + value % 1;
               if(processedDecimal.size()==7){
                break;
               }
            }
        }

        for(int a =0;a<integercontainer.size();a++){
            if(base==hexa){
                int num = Integer.parseInt(integercontainer.get(a));
                processedString+=getHexadecimalvalues(num);
                if(!processedDecimal.isEmpty()){
                    for(int z =0;z<processedDecimal.size();z++){
                        int num2 = Integer.parseInt(processedDecimal.get(z));
                        decimals.append(getHexadecimalvalues(num2));
                    }                    
                }
            }
            else{
               processedString+= integercontainer.get(a).toString();
            }       
        }
  
       StringBuilder reversedProcessedString = new StringBuilder(processedString).reverse();    
       
        if(!processedDecimal.isEmpty()){
            for (String string : processedDecimal) {
                decimals.append(string);
            } 
            String filteredtext = decimals.toString().substring(0,decimals.length()/2);
            System.out.printf("OUTPUT: %s.%s \n",reversedProcessedString.toString(),filteredtext);       
        }
        else{
            System.out.printf("OUTPUT: %s \n", reversedProcessedString);
        }
    }
 }