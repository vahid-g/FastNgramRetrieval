package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Engine {

	public static void main(String[] args) throws Exception {
		TrieNode root = new TrieNode("", false);
		// PrintWriter log = new PrintWriter("log.txt");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(
				System.in))) {
			String line = br.readLine();
			while (!line.equals("S")) {
				root.addKid(line);
				line = br.readLine();
			}
			// Path path = FileSystems.getDefault().getPath("small.result");
			// List<String> inputs = Files.readAllLines(path);
			// int i = 0;
			System.out.println("R");
			line = br.readLine();
			StringBuilder sb = new StringBuilder();
			while (true) {
				if (line == null || line.length() == 0) {
					break;
				} else if (line.charAt(0) == 'Q') {
					// System.out.println(root.search(line.substring(2)));
//					 sb.append(root.search(line.substring(2)) + "\n");
					String doc = line.substring(2);
					ArrayList<Integer> spaceIndices = new ArrayList<Integer>();
					spaceIndices.add(-1);
//					int index = doc.indexOf(' ');
//					while (index != -1) {
//						spaceIndices.add(index);
//						index = doc.indexOf(' ', index + 1);
//					}
					spaceIndices.add(doc.indexOf(' ', doc.length() / 2));
					
					String res = spaceIndices.parallelStream()
							.map(e -> root.searchAsList(doc.substring(e + 1)))
							.flatMap(f -> f.stream()).distinct()
							.collect(Collectors.joining("|"));
					if (res.isEmpty())
						sb.append("-1");
					else
						sb.append(res);
					sb.append("\n");
					
				} else if (line.charAt(0) == 'A') {
					root.addKid(line.substring(2));
				} else if (line.charAt(0) == 'D') {
					root.removeKid(line.substring(2));
				} else if (line.charAt(0) == 'F') {
					System.out.print(sb.toString());
					sb = new StringBuilder();
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			// e.printStackTrace();
			// log.println("pokh");
		}
		// log.println("end");
		// log.close();
		// System.out.println(root);
	}
}
