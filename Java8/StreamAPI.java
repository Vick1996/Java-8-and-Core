package Java8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class StreamAPI
{
    public static void function(List<Emp> listEmp)
    {
        //Find emp with max sal
        Emp highestSal = listEmp.stream().max(Comparator.comparingDouble(Emp::getSal)).get();
        System.out.println("Max sal: "+ highestSal);

        //collect list of names grouped by gender
        Map<Character, List<String>> genderMap = listEmp.stream().collect(Collectors.groupingBy(Emp::getGender, Collectors.mapping(Emp::getName, Collectors.toList())));
        System.out.println(genderMap);

        //print names of all dept
        List<String> deptList = listEmp.stream().map(Emp::getDepartment).distinct().toList();
        System.out.println(deptList);

        //Avg age of male and female emp
        Map<Character, Double> avgAgeMap = listEmp.stream().collect(Collectors.groupingBy(Emp::getGender, Collectors.averagingInt(Emp::getAge)));
        System.out.println(avgAgeMap);

        //Emp with sal > 95000
        List<String> empSalList = listEmp.stream().filter(E -> E.getSal() > 95000).map(Emp::getName).toList();
        System.out.println(empSalList);

        //count no of emp in each dept
        Map<String, Long> countDept = listEmp.stream().collect(Collectors.groupingBy(Emp::getDepartment, Collectors.counting()));
        System.out.println("\n"+countDept);

        //avg sal of each dept
        Map<String, Double> avgDeptSal = listEmp.stream().collect(Collectors.groupingBy(Emp::getDepartment, Collectors.averagingDouble(Emp::getSal)));
        System.out.println(avgDeptSal);

        //Get details of youngest male emp in IT dept
        Emp youngest = listEmp.stream().filter(e->("IT").equals(e.getDepartment()) && 'M' == e.getGender()).min(Comparator.comparingInt(Emp::getAge)).get();
        System.out.println(youngest);

        //count of m and f in IT dept
        Map<Character, Long> mapCountIt = listEmp.stream().filter(e->("IT").equals(e.getDepartment())).collect(Collectors.groupingBy(Emp::getGender, Collectors.counting()));
        System.out.println("\n"+mapCountIt);

        //List of names of all emp in each dept
        Map<String, List<String>> mapDept = listEmp.stream().collect(Collectors.groupingBy(Emp::getDepartment, Collectors.mapping(Emp::getName, Collectors.toList())));
        System.out.println("\n"+mapDept);

        //Separate emp with age<30 and >30
        Map<String, List<String>> ageEmp = listEmp.stream().collect(Collectors.groupingBy(e->e.getAge() >= 25 ? "Above or Equal 25" : "Less than 25", Collectors.mapping(Emp::getName, Collectors.toList())));
        System.out.println(ageEmp);

        //Highest sal of each dept
//        Map<String, Double> highSalDept = listEmp.stream()
//                .map(Emp::getSal)
//                .collect(Collectors.groupingBy(Emp::getDepartment,
//                        Collectors.maxBy(Comparator.comparingDouble(Emp::getSal))
//                                .orElseGet(OptionalDouble::getAsDouble)));  // orElse(Optional.of(0.0d))));
//        System.out.println(highSalDept);

        List<Integer> intli = Arrays.asList(1,2,3,4,1,2,3,4,1,4,1);

        //sum all elements
        System.out.println(intli.stream().mapToInt(x->x).sum());

        //Sort based on count and then sort lexicographically if same count

        List<String> fruits = List.of("apple", "mango", "banana", "cherry" ,"apple", "mango", "banana", "cherry", "apple", "mango", "banana", "cherry", "apple", "mango");

        Function<Map.Entry<String, Long>, Long> getVal = entry -> entry.getValue();

        Map<String, Long> fruitMap = fruits.stream()
                .collect(Collectors.groupingBy(f -> f, Collectors.counting()))  //Count of all elements in list
                .entrySet().stream()
                // Sort by count descending
                .sorted(Comparator.comparing(getVal, Comparator.reverseOrder())
                        .thenComparing(Map.Entry::getKey))
                // Collect entries into a LinkedHashMap to preserve order
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));


        System.out.println(fruitMap);

        //Sort list lexicographically
        System.out.println(fruits.stream().sorted(Comparator.comparing(e->e)).toList());

        //Sum of no of letters in list
        System.out.println((fruits.stream().mapToInt(e->e.length()).sum()));

        //freq of each string in list
        Map<String, Long> countMap = fruits.stream().collect(Collectors.groupingBy(f->f, Collectors.counting()));
        System.out.println(countMap);

        //Find first non-repeating char in string
        String str = "aaabbbcdddefg";
        Optional<Character> ch = str.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(e->e, LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().filter(e->e.getValue()==1).map(e->e.getKey()).findFirst();
        System.out.println(ch.get());
    }

    public static void main(String[] args)
    {
        List<Emp> listEmp = List.of(new Emp(100000, "Vick", "IT", 27, 'M'),
                new Emp(96000, "Pam", "Accounts", 44, 'F'),
                new Emp(140000, "Jim", "Accounts", 34, 'M'),
                new Emp(90000, "Cory", "IT", 20, 'M'),
                new Emp(95000, "Blake", "IT", 22, 'M'),
                new Emp(97000, "Bianca", "IT", 25, 'F'),
                new Emp(86000, "Lory", "Sales", 41, 'F'),
                new Emp(92500, "Matt", "Sales", 29, 'M'),
                new Emp(87500, "Becky", "Sales", 35, 'F'));

        function(listEmp);
    }
}
