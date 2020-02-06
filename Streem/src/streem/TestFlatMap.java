package streem;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class TestFlatMap {

	public static void main(String[] args) {
		
  List<Integer>list1=Arrays.asList(1,2,3,4,5);
  List<Integer>list2=Arrays.asList(6,7,8);
  List<Integer>list3=Arrays.asList(3,4,5);

  List<List<Integer>> list = Arrays.asList(list1,list2,list3);
  
  System.out.println(list);
  
  Function<List<?>,Integer> size = List::size;
  Function<List<Integer>,Stream<Integer>> fl = l -> l.stream();
  
  list.stream()
                  .flatMap(fl)
                  .forEach(System.out::println);
	

				}

}
