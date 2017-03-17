package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Engine {

	public static void main(String[] args) {
		TrieNode root = new TrieNode("", false);
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String line = br.readLine();
			while(!line.equals("S")){
				root.addKid(line);
			}
			line = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(root);
	}
}
