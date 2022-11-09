import java.util.Scanner;

class MyHashMap<K, V> implements MyMap<K, V> {
  // Define the default hash table size. Must be a power of 2
  private static int DEFAULT_INITIAL_CAPACITY = 4;

  // Define the maximum hash table size. 1 << 30 is same as 2^30
  private static int MAXIMUM_CAPACITY = 1 << 30;

  // Current hash table capacity. Capacity is a power of 2
  private int capacity;

  // Define default load factor
  private static float DEFAULT_MAX_LOAD_FACTOR = 0.5f;

  // Specify a load factor used in the hash table
  private float loadFactorThreshold;

  // The number of entries in the map
  private int size = 0;

  // Hash table is an array with each cell that is a linked list
  MyMap.Entry<K, V>[] table;

  /** Construct a map with the default capacity and load factor */
  public MyHashMap() {
   this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
  }

  /**
   * Construct a map with the specified initial capacity and default load
   * factor
   */
  public MyHashMap(int initialCapacity) {
   this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
  }

  /**
   * Construct a map with the specified initial capacity and load factor
   */
  @SuppressWarnings("unchecked")
  public MyHashMap(int initialCapacity, float loadFactorThreshold) {
   if (initialCapacity > MAXIMUM_CAPACITY)
    this.capacity = MAXIMUM_CAPACITY;
   else
    this.capacity = trimToPowerOf2(initialCapacity);

   this.loadFactorThreshold = loadFactorThreshold;
   table = new MyMap.Entry[capacity];
  }

  @Override
  /** Remove all of the entries from this map */
  public void clear() {
   size = 0;
   removeEntries();
  }

  @Override
  /** Return true if the specified key is in the map */
  public boolean containsKey(K key) {
   int i = hash(key.hashCode());
   while (table[i] != null) {
    if (table[i].getKey().equals(key))
     return true;
    i = Math.abs(i+1) % capacity;
   }

   return false;
  }

  @Override
  /** Return true if this map contains the value */
  public boolean containsValue(V value) {
   for (int i = 0; i < capacity; i++) {
    if (table[i] != null) {
     if (table[i].getValue().equals(value))
      return true;
    }
   }

   return false;
  }

  @Override
  /** Return a set of entries in the map */
  public java.util.Set<MyMap.Entry<K, V>> entrySet() {
   java.util.Set<MyMap.Entry<K, V>> set = new java.util.HashSet<MyMap.Entry<K, V>>();

   for (int i = 0; i < capacity; i++) {
    if (table[i] != null) {
     set.add(table[i]);
    }
   }

   return set;
  }

  @Override
  /** Return the value that matches the specified key */
  public V get(K key) {
   int i = hash(key.hashCode());
   while (table[i] != null) {
    if (table[i].getKey().equals(key))
     return table[i].getValue();
    i = (i + 1) % capacity;
   }

   return null;
  }

  @Override
  /** Return true if this map contains no entries */
  public boolean isEmpty() {
   return size == 0;
  }

  @Override
  /** Return a set consisting of the keys in this map */
  public java.util.Set<K> keySet() {
   java.util.Set<K> set = new java.util.HashSet<K>();

   for (int i = 0; i < capacity; i++) {
    if (table[i] != null) {
     set.add(table[i].getKey());
    }
   }

   return set;
  }

  @Override
  /** Add an entry (key, value) into the map */
  public V put(K key, V value) {
   if (get(key) != null) { // The key is already in the map
    int i = hash(key.hashCode());
    while (table[i] != null) {
     if (table[i].getKey().equals(key)) {
      V oldValue = table[i].getValue();
      table[i].value = value;
      return oldValue;
     }
     i = (i + 1) % capacity;
    }
   }
   
   
   // Check load factor
   if (size >= capacity * loadFactorThreshold) {
    if (capacity == MAXIMUM_CAPACITY)
     throw new RuntimeException("Exceeding maximum capacity");

    rehash();
   }

   int i = hash(key.hashCode());

   while (table[i] != null) {
    i = (i + 1) % capacity;
   }
   table[i] = new MyMap.Entry<K, V>(key, value);
   size++;
   return value;
  }

  @Override
  public void remove(K key) {
	    int i = hash(key.hashCode());

	    while (table[i] != null && (table[i].key == null || !table[i].key.equals(key)))
	      i = (i + 1) % table.length;

	    if (table[i] != null && table[i].key.equals(key)) {
	      table[i] = new MyMap.Entry<K, V>(null, null);
	      size--;
	    }
	  }

  @Override
  /** Return the number of entries in this map */
  public int size() {
   return size;
  }

  @Override
  /** Return a set consisting of the values in this map */
  public java.util.Set<V> values() {
   java.util.Set<V> set = new java.util.HashSet<V>();

   for (int i = 0; i < capacity; i++) {
    if (table[i] != null) {
     set.add(table[i].getValue());
    }
   }

   return set;
  }

  /** Hash function */
  private int hash(int hashCode) {
   return supplementalHash(hashCode) & (capacity - 1);
  }

  /** Ensure the hashing is evenly distributed */
  private static int supplementalHash(int h) {
   h ^= (h >>> 20) ^ (h >>> 12);
   return h ^ (h >>> 7) ^ (h >>> 4);
  }

  /** Return a power of 2 for initialCapacity */
  private int trimToPowerOf2(int initialCapacity) {
   int capacity = 1;
   while (capacity < initialCapacity) {
    capacity <<= 1;
   }

   return capacity;
  }

  /** Remove all entries from each bucket */
  private void removeEntries() {
   for (int i = 0; i < capacity; i++) {
    if (table[i] != null) {
     table[i] = null;
    }
   }
  }

  /** Rehash the map */
  @SuppressWarnings("unchecked")
  private void rehash() {
   java.util.Set<Entry<K, V>> set = entrySet(); // Get entries
   capacity <<= 1; // Double capacity
   table = new MyMap.Entry[capacity];
   size = 0; // Reset size to 0

   for (Entry<K, V> entry : set) {
    put(entry.getKey(), entry.getValue()); // Store to new table
   }
  }

  @Override
  public String toString() {
   StringBuilder builder = new StringBuilder("[");

   for (int i = 0; i < capacity; i++) {
    if (table[i] != null)
     builder.append(table[i]);
   }

   builder.append("]");
   return builder.toString();
  }
  public static void main(String[] args) {
	  MyMap<String, Integer> hashMap = new MyHashMap<String, Integer>();
	  Scanner scanner = new Scanner(System.in);	
	  int answer = 10;
	  while(answer!=0) {
	  System.out.println("choose an option: 1- Add User, 2-Delete User, 3-Show the map , 4- Clear the map,  0-exit");
	  answer = scanner.nextInt();
	  switch (answer) {
	    case 1:
	  	  System.out.println("What is the name? ");
		  String name = scanner.next();  
		  System.out.println("What is the age? ");
		  int age = scanner.nextInt();
		  hashMap.put(name,age);  
	      break;
	    case 2:
	  	  System.out.println("what is the name?");
		  String deleted = scanner.next();
		  hashMap.remove(deleted);
	      break;
	    case 3:
	  	  System.out.println("Map contains: " + hashMap);
	      break;
	    case 4:
	      hashMap.clear();
	      
	  }
	  }

  }
 }