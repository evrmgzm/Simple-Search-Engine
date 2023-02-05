import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import java.util.Collections;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Stack;

public class SearchEngine {
	static List<String> list = new ArrayList<String>();
	 public static hashTable<String, String> hash = new hashTable<>();	
	 static List<String> words = new ArrayList<String>();		 
	 static List<Long> nanosecs = new ArrayList<>();
	public static int txtNo = 1;


		static Key<String,  String>[] table1=null;
	static	Key<String, String>[] table2=null;
     static	Key<String, String>[] table3=null;
  
     static Stack<Integer> highestCounts = new Stack<>();
     static Stack<String> highestTxts = new Stack<>();
	public static void main(String[] args) {

	    
		input();
	    scanFile();
	    bestText();
		//scanSearch();
		//a.printCollision();
	   // test();
	  
	}
	public static void test() {
		
		int wordSize = words.size();		
		long start = System.currentTimeMillis();
		
		for(int i = 0; i < wordSize; i++) {
			//a.insert(words.get(i), (String)null );
		}
		
		long end = System.currentTimeMillis();
		NumberFormat formatter = new DecimalFormat("#0.00000");
		
		System.out.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
		
	}
	@SuppressWarnings({ "null", "unchecked" })
		//scan file and fill hash table	
public static void scanFile() {
			
		
	    File file = new File("sport");
	 
	    int z = 0;
	    int x = 0;
	    int y = 0;	  
	  
	    if(file!=null && file.exists()){
	      File[] listOfFiles = file.listFiles();
	    
	     //file read
	        if(listOfFiles!=null){
	        
	        	table1 = new Key[listOfFiles.length];
	        	table2 = new Key[listOfFiles.length];
	        	table3 = new Key[listOfFiles.length];
	          for(int i = 0; i<listOfFiles.length; i++) {
	        	 
	          }
	            for (int i = 0; i < listOfFiles.length; i++) {
	            		            		              
	                if (listOfFiles[i].isFile()) {
	                	   Scanner sc2 = null;
	               	    try {
	               	        sc2 = new Scanner(new File(listOfFiles[i].toString()));
	               	    } catch (FileNotFoundException e) {
	               	        e.printStackTrace();  
	               	    }
	               	    //text read
	               	    while (sc2.hasNextLine()) {
	               	        Scanner s2 = new Scanner(sc2.nextLine());
	               	        while (s2.hasNext()) {
	               	            String s = s2.next().toLowerCase(Locale.forLanguageTag("en-US")).replaceAll("[^A-Za-z0-9]","");	   
	               	           	               	                  
	               	            words.add(s);
			   	               hash.insert(s,listOfFiles[i].toString());
			   	             			   	             			   	              		   	                
			   	              
	               	            } 
	               	 
	               	        }	
	               	    
	            
	               	    }	
	              	                               
	                
	           	   // table[x++] = new Key<String, String>( list.get(0),listOfFiles[i].toString(), a.getCount(list.get(0)));
	                table1[x++] = new Key<String, String>( list.get(0),listOfFiles[i].toString(), hash.getCount(list.get(0))); 
	                table2[y++] = new Key<String, String>( list.get(1),listOfFiles[i].toString(), hash.getCount(list.get(1)));
	                table3[z++] = new Key<String, String>( list.get(2),listOfFiles[i].toString(), hash.getCount(list.get(2)));
	              
	               
		               
	           	         hash.setCountZero(list.get(0));
	           	         hash.setCountZero(list.get(1));
	           	         hash.setCountZero(list.get(2));
	               
	           	    
	           	    
	           	         txtNo++;
	                
	                }
	            
	            }
	      
	        
	        }
	   // System.out.println(Arrays.deepToString(table1));
	   // System.out.println(Arrays.deepToString(table2));
	   // System.out.println(Arrays.deepToString(table3));
	   
	    System.out.println("--------------");
	    printFirst();
	    System.out.println("--------------");
	    printSecond();
	    System.out.println("--------------");
	    printThird();
	    System.out.println("--------------");
	   
	 
	}
	
	public static void bestText() {
		//find max frequency.
		 Stack<Integer> countTemp = new Stack<>();
		   Stack<String> txtTemp = new Stack<>();
		   while(!(highestCounts.isEmpty())) {
				//System.out.print(S4.peek());		
			   countTemp.push(highestCounts.pop());
			}
			while(!(countTemp.isEmpty())) {
						
				highestCounts.push(countTemp.pop());
			}
			
			while(!(highestTxts .isEmpty())) {
				//System.out.print(S3.peek());		
				txtTemp.push(highestTxts .pop());
			}
			while(!(txtTemp.isEmpty())) {
						
				highestTxts .push(txtTemp.pop());
			}
			while(!highestCounts.isEmpty())
	        {
				int temp=highestCounts.pop();
				String temp2=(String) highestTxts.pop();   
				
	            while(!countTemp.isEmpty() && (countTemp.peek() < temp))
	            {             
	            	highestCounts.push(countTemp.pop());
	            	highestTxts.push(txtTemp.pop());
	            }
	              
	            countTemp.push(temp);
	            txtTemp.push(temp2);
	        }
			while(!(countTemp.isEmpty())) {
				highestCounts.push( countTemp.peek());
				//System.out.print(S4.peek());
				countTemp.pop();			
			}
			while(!(txtTemp.isEmpty())) {
				highestTxts.push((String)txtTemp.peek());
				//System.out.print(S3.peek());
				txtTemp.pop();			
			}
		
			System.out.print("best relevent text: "+(String)highestTxts.pop());
				
				
			
	}
	    /* Function to print three largest elements */
	   public static void printFirst()
	    {
		   Stack<Integer> count = new Stack<>();
		   Stack<String> txt = new Stack<>();
		   Stack<Integer> countTemp = new Stack<>();
		   Stack<String> txtTemp = new Stack<>();
		   for(int i = 0; i < table1.length; i++) {
			   count.add(table1[i].getCount());
			   txt.add(table1[i].getText());
		   }
		   while(!(count.isEmpty())) {
				//System.out.print(S4.peek());		
			   countTemp.push(count.pop());
			}
			while(!(countTemp.isEmpty())) {
						
				count.push(countTemp.pop());
			}
			
			while(!(txt .isEmpty())) {
				//System.out.print(S3.peek());		
				txtTemp.push(txt .pop());
			}
			while(!(txtTemp.isEmpty())) {
						
				txt .push(txtTemp.pop());
			}
			while(!count.isEmpty())
	        {
				int temp=count.pop();
				String temp2=(String) txt.pop();   
				//sort
	            while(!countTemp.isEmpty() && (countTemp.peek() < temp))
	            {             
	            	count.push(countTemp.pop());
	            	txt.push(txtTemp.pop());
	            }
	              
	            countTemp.push(temp);
	            txtTemp.push(temp2);
	        }
			while(!(countTemp.isEmpty())) {
				count.push( countTemp.peek());
				//System.out.print(S4.peek());
				countTemp.pop();			
			}
			while(!(txtTemp.isEmpty())) {
				txt.push((String)txtTemp.peek());
				//System.out.print(S3.peek());
				txtTemp.pop();			
			}
			highestCounts.push(count.peek());
			highestTxts.push(txt.peek());
			
			System.out.println(table1[0].getKey());
			for(int i=0;i<3;i++) {
				System.out.print((String)txt.pop()+" "+String.valueOf(count.pop())+"\n");
				
				}
		
	    }
	   public static void printSecond()
	    {
		   Stack<Integer> count = new Stack<>();
		   Stack<String> txt = new Stack<>();
		   Stack<Integer> countTemp = new Stack<>();
		   Stack<String> txtTemp = new Stack<>();
		   for(int i = 0; i < table2.length; i++) {
			   count.add(table2[i].getCount());
			   txt.add(table2[i].getText());
		   }
		   while(!(count.isEmpty())) {
				//System.out.print(S4.peek());		
			   countTemp.push(count.pop());
			}
			while(!(countTemp.isEmpty())) {
						
				count.push(countTemp.pop());
			}
			
			while(!(txt .isEmpty())) {
				//System.out.print(S3.peek());		
				txtTemp.push(txt .pop());
			}
			while(!(txtTemp.isEmpty())) {
						
				txt .push(txtTemp.pop());
			}
			while(!count.isEmpty())
	        {
				int temp=count.pop();
				String temp2=(String) txt.pop();   
				//sort
	            while(!countTemp.isEmpty() && (countTemp.peek() < temp))
	            {             
	            	count.push(countTemp.pop());
	            	txt.push(txtTemp.pop());
	            }
	              
	            countTemp.push(temp);
	            txtTemp.push(temp2);
	        }
			while(!(countTemp.isEmpty())) {
				count.push( countTemp.peek());
				//System.out.print(S4.peek());
				countTemp.pop();			
			}
			while(!(txtTemp.isEmpty())) {
				txt.push((String)txtTemp.peek());
				//System.out.print(S3.peek());
				txtTemp.pop();			
			}
			highestCounts.push(count.peek());
			highestTxts.push(txt.peek());
			
			System.out.println(table2[0].getKey());
			for(int i=0;i<3;i++) {
				System.out.print((String)txt.pop()+" "+String.valueOf(count.pop())+"\n");
				
				}
		
	    }
	   public static void printThird()
	    {
		   Stack<Integer> count = new Stack<>();
		   Stack<String> txt = new Stack<>();
		   Stack<Integer> countTemp = new Stack<>();
		   Stack<String> txtTemp = new Stack<>();
		   for(int i = 0; i < table3.length; i++) {
			   count.add(table3[i].getCount());
			   txt.add(table3[i].getText());
		   }
		   while(!(count.isEmpty())) {
				//System.out.print(S4.peek());		
			   countTemp.push(count.pop());
			}
			while(!(countTemp.isEmpty())) {
						
				count.push(countTemp.pop());
			}
			
			while(!(txt .isEmpty())) {
				//System.out.print(S3.peek());		
				txtTemp.push(txt .pop());
			}
			while(!(txtTemp.isEmpty())) {
						
				txt .push(txtTemp.pop());
			}
			while(!count.isEmpty())
	        {
				int temp=count.pop();
				String temp2=(String) txt.pop();   
				//sort 
	            while(!countTemp.isEmpty() && (countTemp.peek() < temp))
	            {             
	            	count.push(countTemp.pop());
	            	txt.push(txtTemp.pop());
	            }
	              
	            countTemp.push(temp);
	            txtTemp.push(temp2);
	        }
			while(!(countTemp.isEmpty())) {
				count.push( countTemp.peek());
				//System.out.print(S4.peek());
				countTemp.pop();			
			}
			while(!(txtTemp.isEmpty())) {
				txt.push((String)txtTemp.peek());
				//System.out.print(S3.peek());
				txtTemp.pop();			
			}
			highestCounts.push(count.peek());
			highestTxts.push(txt.peek());
			
			System.out.println(table3[0].getKey());
			for(int i=0;i<3;i++) {
				System.out.print((String)txt.pop()+" "+String.valueOf(count.pop())+"\n");
				
				}
		
	    }
	//calculate time
public static void scanSearch() {
		 
		 long totalNano=0;
		 Scanner sc2 = null;
    	    try {
    	        sc2 = new Scanner(new File("search.txt"));
    	    } catch (FileNotFoundException e) {
    	        e.printStackTrace();  
    	    }
    	    while (sc2.hasNextLine()) {
       	        Scanner s2 = new Scanner(sc2.nextLine());
       	        while (s2.hasNext()) {
       	            String s = s2.next().toLowerCase(Locale.forLanguageTag("en-US")).replaceAll("[^A-Za-z0-9]","");	
       	         final long startTime = System.nanoTime();
       	             hash.find(s);
       	          final long duration = System.nanoTime() - startTime;
       	           nanosecs.add(duration);
       	           
       			  totalNano+=duration;
       			   System.out.println("Execution time is " + duration + " nanoseconds");
       	            } 
       	 
       	        }	
    	    System.out.println("Avarage: "+totalNano/1000 + " nanoseconds");
    	    System.out.println("max: " + Collections.max(nanosecs));
    	    System.out.println("min: " + Collections.min(nanosecs));
	}
	
	
	public static List<String> input() {
	        System.out.println("Enter three keys:");        
	       
	        Scanner scan = new Scanner(System.in);  
	       
	        String first = scan.next().toLowerCase(Locale.forLanguageTag("en-US"));  	        
	        String second = scan.next().toLowerCase(Locale.forLanguageTag("en-US"));  	        
	        String third = scan.next().toLowerCase(Locale.forLanguageTag("en-US"));  
	        list.add(first);
	        list.add(second);
	        list.add(third);
	        System.out.println("first: "+first);  
	        System.out.println("second: "+second);    
	        System.out.println("third: "+third);   
	        
	        scan.close();  
	  return list;
	}
	
	
}
