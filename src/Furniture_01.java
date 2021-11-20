
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class Furniture_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<String> furniture = new ArrayList<>(); //имената на мебелите
        double totalSum = 0; //обща изхарчена сума
 
        //">>{furniture name}<<{price}!{quantity}"
        //String regex = ">>(?<furnitureName>[A-Za-z]+)<<(?<price>[0-9]+.?[0-9]*)!(?<quantity>[0-9]+)";
        String regex = ">>(?<furnitureName>\\w+)<<(?<price>\\d+.?\\d*)!(?<quantity>\\d+)";
        Pattern pattern = Pattern.compile(regex);
        while(!input.equals("Purchase")) {
            //">>Sofa<<312.23!3"
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                //true -> input отговаря на regex-a в pattern-a
                //false -> input НЕ отговаря на regex-a в pattern-a
                String furnitureName = matcher.group("furnitureName");
                double price = Double.parseDouble(matcher.group("price"));
                int quantity = Integer.parseInt(matcher.group("quantity"));
 
                furniture.add(furnitureName);
                totalSum += price * quantity;
            }
            input = scanner.nextLine();
        }
 
        System.out.println("Bought furniture:");
        furniture.forEach(System.out::println);
        System.out.printf("Total money spend: %.2f", totalSum);
    }
}