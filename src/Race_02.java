
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
 
public class Race_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //"George, Peter, Bill, Tom".split(", ") -> ["George", "Peter", "Bill", "Tom"]
        List<String> racers = Arrays.stream(scanner.nextLine().split(", ")).collect(Collectors.toList());
        Map<String, Integer> racersDistances = new LinkedHashMap<>();
        //име на състезателя -> дистанция
        racers.forEach(racer -> racersDistances.put(racer, 0));
        //Регекси
        String regexLetter = "[A-Za-z]+";
        Pattern patternLetter = Pattern.compile(regexLetter);
 
        String regexDigit = "[0-9]";
        Pattern patternDigit = Pattern.compile(regexDigit);
 
        String input = scanner.nextLine();
        while(!input.equals("end of race")) {
            //input = "G!32e%o7r#32g$235@!2e"
            //1. игнорирам специални символи различни от буква и цифра
            //2. буквите -> име на състезателя
            StringBuilder nameBuilder = new StringBuilder(); // конструирам името на състезателя
            Matcher matcherName = patternLetter.matcher(input); //всички букви -> ["G", "e", "o", "r", "g", "e"]
            while (matcherName.find()) {
                nameBuilder.append(matcherName.group());
            }
            //3. сума на цифри -> разстоянието
            int distance = 0;
            Matcher matcherDigits = patternDigit.matcher(input); //всички цифри -> ["3", "2", "7", "3", "2"]
            while (matcherDigits.find()) {
                distance += Integer.parseInt(matcherDigits.group());
            }
 
            //име на човек + дистанция
            String racerName = nameBuilder.toString(); //име на състезателя
            if (racersDistances.containsKey(racerName)) {
                int currentDistance = racersDistances.get(racerName);
                racersDistances.put(racerName, currentDistance + distance);
            }
 
            input = scanner.nextLine();
        }
        //начин 1:
        //връщаме мап с първите три елемента след сортирането
        /*Map<String,Integer> firstThreeRacers = racersDistances.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))//descending order (намаляващ ред)
                .limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a1, a2) -> a1, LinkedHashMap::new));*/
 
 
        //състезател -> избягана дистанция
        //1. сортираме по value (дистанция) в descending order
        //racersDistances.entrySet().stream().sorted(Map.Entry.comparingByValue()) -> ascending order (нарастващ ред)
        //начин 2:
        //списък с имената на първите трима
        List<String> firstThreeNames = racersDistances.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3) //получавам мап с 3 записа
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
 
        System.out.println("1st place: " + firstThreeNames.get(0));
        System.out.println("2nd place: " + firstThreeNames.get(1));
        System.out.println("3rd place: " + firstThreeNames.get(2));
 
    }
}