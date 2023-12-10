package Dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

public class TDProject {
	
	private SortedArrayDictionary <BusinessName, String> phoneBook;




	public TDProject() {
		phoneBook = new SortedArrayDictionary <>();
	}

	public boolean readFile(String fileName){
        File datafile = new File (fileName);
        String line;

        String phoneNumber;
        try {
        	Scanner read = new Scanner (datafile);
        	while (read.hasNext()) {
        		line = read.nextLine().trim();
//				System.out.println(line);
        		int lastBlank = line.lastIndexOf(' ');
				BusinessName fullName = new BusinessName(line.substring(0, lastBlank).trim());


//				System.out.println(fullName.getName());

//				System.out.println(line.substring(0, lastBlank).trim());
        		phoneNumber = line.substring(lastBlank+1);

//				System.out.println(phoneNumber);
        		phoneBook.add(fullName, phoneNumber);

//				System.out.println(fullName);

//				System.out.println(phoneBook.getSize());

//				System.out.println(phoneBook.getSize());
        		if (line.isEmpty()) continue;
        	}

        } catch (FileNotFoundException ex) {
        	System.out.println ("The file " + fileName + " is not found");
        	return false;
        }
        return true;
        
	}



	public String addPhoneNumber( String fullName, String phone) {
		return phoneBook.add(new BusinessName(fullName), phone);
	}
	
	public String removePhoneNumber (String fullName) {
		return phoneBook.remove(new BusinessName(fullName));
	}
	
	public void print() {
		System.out.println("Printing");
//		System.out.println(phoneBook.getSize());
		Iterator <BusinessName> nameIterator = phoneBook.getKeyIterator();
		Iterator <String> phoneIterator = phoneBook.getValueIterator();
		
		while (nameIterator.hasNext()) {
			BusinessName businessName = nameIterator.next();
			System.out.println(businessName.getName() + ": " +
					phoneIterator.next() + ": Location: " + businessName.getMunicipality());
		}
		
		
	}

	public String getFullNameAndPhone(String s){
		Iterator <BusinessName> nameIterator = phoneBook.getKeyIterator();
		Iterator <String> phoneIterator = phoneBook.getValueIterator();
		while(nameIterator.hasNext()){
			String name = nameIterator.next().getName();
			if(s.equals(name)){
				return name + ":" + phoneIterator.next();
			}
			phoneIterator.next();
		}



		return null;
	}

	public void add(String name, String phoneNumber){
		BusinessName businessName = new BusinessName(name);

		phoneBook.add(businessName, phoneNumber);
	}

	public String getPhoneNumber(String name){
		Iterator <BusinessName> nameIterator = phoneBook.getKeyIterator();
		Iterator <String> phoneIterator = phoneBook.getValueIterator();
		while(nameIterator.hasNext()){
			if(name.equals(nameIterator.next().getName())){
				return phoneIterator.next();
			}
			phoneIterator.next();
		}

		return null;
	}



	
	private static int firstDigitIndex(String s) {
		int minIndex = s.length();
		int curIndex;
		for (int i = 0; i <= 9; i ++) {
			curIndex = s.indexOf('0' + i);
			if (curIndex < 0)
				continue;
			if (curIndex < minIndex )
				minIndex = curIndex;		
		}
		return minIndex;
	}



}
