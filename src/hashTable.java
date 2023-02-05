import java.util.*;


public class hashTable<K,V> {
		
	//Implements a linear probing hash table with String keys
	//Associated with each key is additional data of any Object type
	//keys are unique (i.e. no duplicates)
	private enum Status {
		DELETED, EMPTY, FULL
	}
	private class Item<S,T> {
			public S getKey() {
			return key;
		}
		public void setKey(S key) {
			this.key = key;
		}
		public T getValue() {
			return value;
		}
		public void setValue(T value) {
			this.value = value;
		}
		public Status getStatus() {
			return status;
		}
		public void setStatus(Status status) {
			this.status = status;
		}
			private S key;
			private T value;
			Status status;
			public int Count = 1 ;
			List<String> textList = new ArrayList<String>();
			List<Integer> textCount = new ArrayList<Integer>();
			private Item(S key, T value, Status s, int Count) {
			this.key = key;
			this.value = value;
			status = s;
			this.Count = Count;
			
			}
		}
	private static final int DEFAULT_SIZE = 2500; 
	private static final double MAX_LOAD_FACTOR = 0.5;
	
	
	Item<K, V>[] table;
	private int tableSize = 2500;
	private final int primes[] = {23, 59, 131, 271, 563, 1171, 2083, 4441, 8839, 16319, 32467, 65701, 131413};
	private int primeIndex;
	private int filled;
	private int firstDeleted;
	private int totalCollision=0;
	
	
	private int nextPrime(int p) {
		while (primes[primeIndex] <= p)
		primeIndex++;
		return primes[primeIndex];
	}
	private void initTable() {
		for (int i = 0; i < tableSize; i++) {
		table[i] = new Item<K, V>(null, null, Status.EMPTY,0);
		}
			filled = 0;
	}
	
	@SuppressWarnings("unchecked")
	public hashTable(int s) {
			primeIndex = 0;
			tableSize = nextPrime(s);
			table = new Item[tableSize];
			initTable();
		}
	public hashTable() {
		this(DEFAULT_SIZE);
	}
	public int tableSize() {
		return tableSize;
	}
	
	private int simpleHash(K k) {
		int h = 0;
		String str = k.toString();
		for (int i = 0; i < str.length(); i++) {
			h = h*31+str.charAt(i);
		}
		h = h % tableSize;
		if (h < 0)
			h = h + tableSize;
			return h;
		}
	private int polyHash(K word) {
    	String str = word.toString();
    	int dataKey = 0;
    	 
    	for (int i = 0; i < str.length(); i++) {  			  
    			 dataKey += str.charAt(i) * Math.pow(31, str.length() - i - 1);
    			  }
    	 dataKey = dataKey % tableSize;
    	
    	 return dataKey;
    }
	
	
		@SuppressWarnings("unchecked")
		private void resize() {
			int oldTableSize = tableSize;
			Item<K, V>  oldTable[] = table;
			tableSize = nextPrime(oldTableSize);
			table = new Item[tableSize];
			initTable();
			for (int i = 0; i < oldTableSize; i++) {
				if (oldTable[i].status == Status.FULL)
					insert(oldTable[i].key, oldTable[i].value);
				}
		}
		
		private int findAux(K k) {
			int hVal = simpleHash(k);
			int loc = hVal;
			
			firstDeleted = -1;
			while ((table[loc].status != Status.EMPTY) &&
			!(table[loc].status == Status.FULL && table[loc].key.equals(k))) {
				if (firstDeleted == -1 && table[loc].status == Status.DELETED) {
					firstDeleted = loc;
					
				}
				
				loc = (loc+1) % tableSize;
				totalCollision++;
				if (loc == hVal) {
					
					return -1;
				}
			}
			 
			
						
			return loc;
			}
		private int findAuxDouble(K k) {
			
			
			int hashing1 = simpleHash(k);
		    int hashing2 = doubleHash(k);
			firstDeleted = -1;
			while ((table[hashing1].status != Status.EMPTY) &&
			!(table[hashing1].status == Status.FULL && table[hashing1].key.equals(k))) {
				if (firstDeleted == -1 && table[hashing1].status == Status.DELETED) {
					firstDeleted = hashing1;
					
				}
				
				hashing1 += hashing2;
	            hashing1 %= tableSize;
				totalCollision++;
				
				
			}
			 if (hashing1 < 0)
	    	 {
				 hashing1 += tableSize;
	    	 }
						
			return hashing1;
			}
		private int doubleHash(K k) {			
			int hVal1 = simpleHash(k);
			return Math.abs(31 -((hVal1) % 31));
		}
		
		
		public void Counter(K k) {
			int loc = findAux(k);
			if((table[loc].status == Status.FULL && table[loc].key.equals(k))) {
				table[loc].Count=(table[loc].Count)+1;
			}
		}
		public int getCount(K k) {
			int loc = findAux(k);
			int count = 0;
			
			if((table[loc].status == Status.FULL && table[loc].key.equals(k))) {
				count = table[loc].Count;
			}
				
				
			
			return count;
			
		}
		public void printLists(K k) {
			int loc = findAux(k);			
			
				if((table[loc].key.equals(k))) {
					System.out.println(table[loc].textList);
					//System.out.println(table[loc].textCount);
				}
				
			
			
			
		}
		public int setCount(K k, int c) {
			int loc = findAux(k);
			
			if((table[loc].status == Status.FULL && table[loc].key.equals(k))) {
				
				table[loc].Count = c;
			}
			return c;
			
		}
		public void setCountZero(K k) {
			int hVal = simpleHash(k);
			int loc = hVal;
			
			firstDeleted = -1;
			while ((table[loc].status != Status.EMPTY) &&
			!(table[loc].status == Status.FULL && table[loc].key.equals(k))) {
				if (firstDeleted == -1 && table[loc].status == Status.DELETED) {
					firstDeleted = loc;
					
				}
				
				loc = (loc+1) % tableSize;
				
				
			}
			if((table[loc].status == Status.FULL && table[loc].key.equals(k))) {
				table[loc].Count=0;
			}
			
		}
		public List<String> texts(K k, String txt, int loc) {
			
		
				if((table[loc].status == Status.FULL) &&!(table[loc].textList.contains(txt))) {
					table[loc].textList.add(txt);
					table[loc].textCount.add(table[loc].Count);
				}
				else if (((table[loc].status == Status.FULL && table[loc].key.equals(k)))&&(table[loc].textList.contains(txt))){
					table[loc].textCount.set(table[loc].textList.indexOf(txt), (table[loc].Count)++);
				}
				else if(!(table[loc].status == Status.FULL)) {
					table[loc].textList.add(txt);
					table[loc].textCount.add(table[loc].Count);
				}
				
			
			
			return table[loc].textList;
		}
		
		public Object find(K k) {
				int location = findAux(k);
				if (location == -1 || table[location].status == Status.EMPTY) return null;
			return table[location].textList;
			}
		public Object contains(K k) {
			int location = findAux(k);
			if (location == -1 || table[location].status == Status.EMPTY) return null;
		return table[location].textList;
		}
		
		public boolean insert(K k, V v) {
			boolean newKey = false;			
			int location = findAux(k);
			
			if (firstDeleted != -1) location = firstDeleted;
			if (table[location].status != Status.FULL) {
			table[location].key = k;
			table[location].value = v;
			table[location].status = Status.FULL;
			table[location].Count= 1;
			//table[location].textList.add(v.toString());
			//table[location].textCount.add(1);
			newKey = true;
			filled++;
			}
			else 
				table[location].Count += 1;
			
				
			table[location].textList.add(v.toString());
			
			//texts(k,v.toString(),location);
			//System.out.println("key: "+ table[location].key+" value: "+ table[location].value+" loc "+ location+" cnt: "+table[location].Count);
			if (filled > tableSize * MAX_LOAD_FACTOR) resize();
			return newKey;
			}
	
		public boolean remove(K k) {
			boolean keyFound = false;
			int location = findAux(k);
			if (location != -1 && table[location].status == Status.FULL) {
			table[location].status = Status.DELETED;
			keyFound = true;
			filled--;
			}
			return keyFound;
			}
		public void print() {
			for(int i = 0; i<tableSize;i++) {
				if(table[i].key!=null) {
					System.out.println("Key: "+table[i].key+ " Value: "+table[i].value+" Count: "+ table[i].Count);
				}
			}
		}
		public void printCollision() {
			System.out.println(totalCollision);
		}
		public V getValue(K key) {
			V result = null;
			int index = simpleHash(key);
			int location = findAux( key);
			if (index != -1)
				result = table[location].getValue(); 
			return result;
		}
		

}