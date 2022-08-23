package HW11;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
public class Homework11 {
    public String getOddNames(List<String> input) {

        return IntStream.range(0, input.size())
                .filter(index -> index % 2 == 1)
                .mapToObj(index1 -> index1 + " " + input.get(index1))
                .collect(Collectors.joining(", "));
    }

    public List<String> getUpperSortedReverseNames(List<String> input) {

        return input.stream()
                .map(String::toUpperCase)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
    }

    public String getParseIntStr(String[] input) {
        return Arrays.stream(input)
                .flatMap(s -> Stream.of(s.split(", ")))
                .sorted()
                .collect(Collectors.joining(", "));
    }

    public Stream<Long> task4(Long seed, Long a, Long c, Long m) {
        return Stream.iterate(new Long[]{seed, a, c, m}, n -> new Long[]{(n[1] * n[0] + n[2]) % n[3], n[1], n[2], n[3]})
                .map(n -> n[0]);
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Stream<T> result = Stream.empty();
        Iterator<T>[] iterators = new Iterator[2];
        iterators[0] = first.iterator();
        iterators[1] = second.iterator();
        Iterator<T> a = iterators[0];
        Iterator<T> b = iterators[1];
        while (a.hasNext() && b.hasNext()) {
            result = Stream.concat(result, Stream.of(a.next(), b.next()));
        }
        return result;
    }
}
