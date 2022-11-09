
public class asdasd {

}


hashMap.put("Tom", 30);
hashMap.put("James", 31);
hashMap.put("Sarah", 29);
hashMap.put("Sepehr", 29);
hashMap.put("Saba", 65);
System.out.println("Map contains: " + hashMap);
System.out.println("Does map contain a the name 'Tom'? " + hashMap.containsKey("Tom"));
System.out.println("What is Sarah's age? " + hashMap.get("Sarah"));
System.out.println("Is age 20 in the map? " + hashMap.containsValue(20));
hashMap.remove("Tom");
System.out.println("Map contains: " + hashMap);
hashMap.clear();
System.out.println("Map contains: " + hashMap);



for(int i= 0; i<=j;i++) {
	  System.out.println("What is the name? ");
	  String name = scanner.next();
	  System.out.println("What is the age? ");
	  int age = scanner.nextInt();
	  hashMap.put(name,age);
}
System.out.println("Do you want to remove any user?(Y/N) ");
String answer = scanner.next();
if(answer == "Y") {
	  System.out.println("what is the user's name?");
	  String deleted = scanner.next();
	  hashMap.remove(deleted);
}
else if(answer == "N") {
	  return;
}
else {System.out.println("wrong answer!!"); }
System.out.println("Do you want to see the map?(Y/N)");
if(answer == "Y") {
	  System.out.println("Map contains: " + hashMap);
}
else if(answer == "N") {
	  return;
}
else {System.out.println("wrong answer!!");}
System.out.println("Do you want to clear the map? (Y/N)");
if(answer == "Y") {
	  hashMap.clear();	  }
else if(answer == "N") {
	  System.out.println("Map contains: " + hashMap);
	  return;
}
else {System.out.println("wrong answer!!");}

System.out.println("how many users do you want to add? :");
int j = scanner.nextInt();


char answer = 0;
while(answer != 'e') {
	  System.out.println("write 'done' when you are finished");
	  
	  if(name == "exit") {
		  return;
	  }
	  
}
System.out.println("Do you want to remove any user?(Y/N) ");
String response = scanner.next();
if(response == "Y") {
	  System.out.println("what is the user's name?");
	  String deleted = scanner.next();
	  hashMap.remove(deleted);
}
else if(response == "N") {
	  return;

}
System.out.println("Map contains: " + hashMap);