package main;

import java.util.Arrays;
import java.util.HashMap;

public class TrieNode {

	private String term;

	private boolean isValid;

	private HashMap<String, TrieNode> kids = new HashMap<String, TrieNode>();

	public TrieNode(String term, boolean isValid) {
		this.term = term;
		this.isValid = isValid;
	}

	public void addKid(String rawNgram) {
		String ngram = rawNgram.trim();
		int spaceIndex = ngram.indexOf(" ");
		if (spaceIndex == -1) {
			TrieNode node = kids.get(ngram);
			if (node == null) {
				kids.put(ngram, new TrieNode(ngram, true));
			} else {
				node.isValid = true;
			}
		} else {
			String first = ngram.substring(0, spaceIndex);
			String rest = ngram.substring(spaceIndex + 1);
			TrieNode node = kids.get(ngram);
			if (node == null) {
				node = new TrieNode(first, false);
			}
			node.addKid(rest);
		}
	}
	
	@Override
	public String toString() {
		String rep = "term: " + term + "\n" + kids.toString();
		return rep;
	}
}
