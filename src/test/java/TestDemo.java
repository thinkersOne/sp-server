import com.pj.utils.IntegerUtils;

import java.util.HashMap;
import java.util.Map;

public class TestDemo {

    public static void main(String[] args) {
//        Map<String,Integer> redMap = new HashMap<>(100);
//        redMap.put("a", IntegerUtils.convertToInt(redMap.get("a")) +1);
//        System.out.println(redMap.get("a"));
//
//        redMap.put("大",1);
//        System.out.println(redMap.get("大"));
        StringBuffer sb = new StringBuffer();
        sb.append("123");
        System.out.println(sb.toString());
        sb.delete(0, sb.length());
        System.out.println(sb.toString());
    }


}
