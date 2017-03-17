package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine {

	public static void main(String[] args) {
		TrieNode root = new TrieNode("", false);
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String line = br.readLine();
			while(!line.equals("S")){
				root.addKid(line);		
				line = br.readLine();
			}
			line = br.readLine();
			while(!(line == null)){
				if (line.charAt(0) == 'Q'){
					System.out.println(root.search(line.substring(2)));
				} else if (line.charAt(0) == 'A'){
					root.addKid(line.substring(2));
				} else if (line.charAt(0) == 'D') {
					root.removeKid(line.substring(2));
				} else if (line.charAt(0) == 'F') {
					break;
				} else {
					System.err.println("wrong input format");
					break;
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println(root);
	}
}
