package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class TrieNode {

	private String term;

	private boolean isValid;

	private HashMap<String, TrieNode> kids = new HashMap<String, TrieNode>();

	public TrieNode(String term, boolean isValid) {
		this.term = term;
		this.isValid = isValid;
	}

	public void addKidRec(String rawNgram) {
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
			kids.put(first, node);
			node.addKidRec(rest);
		}
	}
	
	public void addKid(String rawNgram){
		StringTokenizer st = new StringTokenizer(rawNgram);
		TrieNode node = this;
		while (st.hasMoreTokens()){
			String token = st.nextToken();
			if (node.kids.containsKey(token)){
				node = node.kids.get(token);
			} else {
				TrieNode newNode = new TrieNode(token, false);
				node.kids.put(token, newNode);
				node = newNode;
			}
		}
		node.isValid = true;
	}
	
	public void removeKid(String rawNgram){
		StringTokenizer st = new StringTokenizer(rawNgram);
		TrieNode node = this;
		while(st.hasMoreTokens()){
			String token = st.nextToken();
			if (node.kids.containsKey(token)){
				node = node.kids.get(token); 
			} else {
				break;
			}
		}
		if (!st.hasMoreTokens())
			node.isValid = false;
	}
	
	public static String searchStat(String doc, TrieNode root) {
		return root.search(doc);
	}
	
	public String search(String doc) {
		StringBuilder resultBuilder = new StringBuilder();
		HashSet<String> resultSet = new HashSet<String>();
		int ndx = doc.indexOf(' ');
		while (ndx != -1){
			String r = singleSearch(doc, resultSet);
			resultBuilder.append(r);
			doc = doc.substring(ndx + 1);
			ndx = doc.indexOf(' ');
		}
		resultBuilder.append(singleSearch(doc, resultSet));
		try{
			resultBuilder.deleteCharAt(resultBuilder.length() - 1);
		} catch (StringIndexOutOfBoundsException e){
			
		}
		
		return resultBuilder.length() > 0 ? resultBuilder.toString() : "-1";
	}
	
	public String singleSearch(String doc, HashSet<String> resultSet){
		TrieNode node = this;
		StringTokenizer st = new StringTokenizer(doc);
		StringBuilder result = new StringBuilder();
		String ngram = "";
		while(st.hasMoreTokens()){
			String token = st.nextToken();
			node = node.kids.get(token);
			if (node == null) break;
			ngram = ngram + " " + token;
			ngram = ngram.trim();
			if (node.isValid && !resultSet.contains(ngram)){ 
				result.append(ngram + "|");
				resultSet.add(ngram);
			}
		}
		return result.toString();
		
	}
	
	public List<String> singleSearch(String doc){
		List<String> resultList = new ArrayList<String>();
		if (doc.charAt(0) == ' ' || doc.length() == 0) return resultList;
		TrieNode node = this;
		StringTokenizer st = new StringTokenizer(doc);
		String ngram = "";
		while(st.hasMoreTokens()){
			String token = st.nextToken();
			node = node.kids.get(token);
			if (node == null) break;
			ngram = ngram + " " + token;
			ngram = ngram.trim();
			if (node.isValid){ 
				resultList.add(ngram);
			}
		}
		return resultList;
		
	}
	@Override
	public String toString() {
		String rep = "term: " + term + " " + isValid + "\n\t" + kids.toString();
		return rep;
	}
}
