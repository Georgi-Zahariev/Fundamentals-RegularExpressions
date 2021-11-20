
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class StarEnigma_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countMessages = Integer.parseInt(scanner.nextLine());
        String regex = "@(?<planetName>[A-Za-z]+)[^@!:>-]*:(?<population>[0-9]+)[^@!:>-]*!(?<attack>[AD])![^@!:>-]*->(?<soldierCount>[0-9]+)";
        Pattern pattern = Pattern.compile(regex);
 
        List<String> attackersPlanets = new ArrayList<>(); //списък с планетите с тип на атака A
        List<String> destroyedPlanets = new ArrayList<>(); //списък с планетите с тип на атака D
        for (int messageCount = 1; messageCount <= countMessages; messageCount++) {
            String encryptedMessage = scanner.nextLine(); //криптирано съобщение
            String decryptedMessage = getDecryptedMessage(encryptedMessage); //декриптирано
            //декриптирано: PQ@Alderaa1:30000!A!->20000
            Matcher matcher = pattern.matcher(decryptedMessage);
            if (matcher.find()) {
                String planetName = matcher.group("planetName");
                //int population = Integer.parseInt(matcher.group("population"));
                String attackType = matcher.group("attack");  ; //A -> attacker; D - > destroyed
                //int soldierCount = Integer.parseInt(matcher.group("soldierCount"));
                if (attackType.equals("A")) {
                    attackersPlanets.add(planetName);
                } else if (attackType.equals("D")) {
                    destroyedPlanets.add(planetName);
                }
            }
        }
 
        System.out.println("Attacked planets: " + attackersPlanets.size());
        Collections.sort(attackersPlanets);
        attackersPlanets.forEach(planet -> System.out.println("-> " + planet));
 
        System.out.println("Destroyed planets: " + destroyedPlanets.size());
        Collections.sort(destroyedPlanets);
        destroyedPlanets.forEach(planet -> System.out.println("-> " + planet));
    }
 
    private static String getDecryptedMessage(String encryptedMessage) {
        //криптирано съобщение: STCDoghudd4=63333$D$0A53333
        //брой на спец. букви: 3
        //декриптирано съобщение:
        //1. броя на [s, S, T, t, A, a, R, r] в криптираното съобщение
        int countLetters = getSpecialLettersCount(encryptedMessage);
        StringBuilder decryptedMessage = new StringBuilder();
        //взимаме всяка буква от криптираното съобщение -> ascii
        //ascii - countLetters -> ascii кода на нова буква
        //нова буква -> добавяме към декриптираното съобщение
        for (char symbol : encryptedMessage.toCharArray()) {
            char newSymbol = (char)(symbol - countLetters);
            decryptedMessage.append(newSymbol);
        }
        return decryptedMessage.toString();
    }
 
    private static int getSpecialLettersCount(String encryptedMessage) {
        int counter = 0;
        for (char symbol : encryptedMessage.toLowerCase().toCharArray()) {
            if (symbol == 's' || symbol == 'a' || symbol == 't' || symbol == 'r') {
                counter++;
            }
        }
        return counter;
    }
}