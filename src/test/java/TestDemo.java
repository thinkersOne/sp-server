import com.pj.utils.IntegerUtils;
import com.pj.utils.ListUtils;

import java.util.*;

public class TestDemo {

    public static void main(String[] args) {
//        Map<String,Integer> redMap = new HashMap<>(100);
//        redMap.put("a", IntegerUtils.convertToInt(redMap.get("a")) +1);
//        System.out.println(redMap.get("a"));
//
//        redMap.put("大",1);
//        System.out.println(redMap.get("大"));
//        StringBuffer sb = new StringBuffer();
//        sb.append("123");
//        System.out.println(sb.toString());
//        sb.delete(0, sb.length());
//        System.out.println(sb.toString());

        String[] arrayA = new String[] { "1", "2", "3", "4","5"};
        String[] arrayB = new String[] { "3", "4", "5", "6" };
        List<String> listA = Arrays.asList(arrayA);
        List<String> listB = Arrays.asList();

        List<?> objects = ListUtils.intersectionForList_3(listA, listB);
        System.out.println(objects);

    }


}
