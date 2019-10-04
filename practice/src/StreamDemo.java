import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StreamDemo {

    public static void main(String[] args) {
        List<Integer> list = Collections.emptyList();
        System.out.println(list.stream().map(s -> s + 2).collect(Collectors.toList()));
    }
}
