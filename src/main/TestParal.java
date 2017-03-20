package main;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestParal {
	
	public static void main(String[] args) {
		List<String> list = Arrays.asList("hanhan", "olde?");
		String s = list.parallelStream().map(e -> e).collect(Collectors.joining("|"));
		System.out.println(s);
	}
}
