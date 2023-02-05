import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;



public class Key<K,V> {
	public K key;
	public int count;
	public V text;
	
	private static final int DEFAULT_SIZE = 1000;
	public List<String> textList = new ArrayList<>();
	Key(K key,  V text,int count) {		
		this.key = key;
		this.count = count;
		this.text = text;		
		}
	
	public int frequency() {
		count++;
		return count;
	}

	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public V getText() {
		return text;
	}
	public void setText(V text) {
		textList.add(text.toString());
	}
	public List<String> addText(String text) {
		textList.add(text);
		return textList;
	}
	public List<String> Txt(){
	
		System.out.println(Arrays.deepToString(textList.toArray()));
		return textList;
		
	}
	   @Override
	    public String toString() {
	        return "Key{" +
	                "key='" + key + '\'' +
	                ", text='" + text+ '\'' +
	                ", count=" + count +
	                '}';
	    }
	
}
