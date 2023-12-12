package Dictionary;

import utils.Entry;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.UnexpectedException;
import java.rmi.server.ExportException;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

public class TDProject {
	
	private SortedArrayDictionary <BusinessName, String> phoneBook;

	private SortedArrayReverseDictionary <BusinessName, String> reversePhoneBook;

	private TreeDictionary<BusinessName, String> treeDictionaryPhoneBook;




	public TDProject() {
		phoneBook = new SortedArrayDictionary <>();
		reversePhoneBook = new SortedArrayReverseDictionary<>();
		treeDictionaryPhoneBook = new TreeDictionary<>();
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
				reversePhoneBook.add(fullName, phoneNumber);
				treeDictionaryPhoneBook.add(fullName, phoneNumber);
				System.out.println(treeDictionaryPhoneBook.getSize());

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

	public boolean addPhoneTreeNumber(String fullName, String phone){
		try{
			treeDictionaryPhoneBook.add(new BusinessName(fullName), phone);
			return true;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}


	public boolean addPhoneNormalNumber( String fullName, String phone) {
		try{
			phoneBook.add(new BusinessName(fullName), phone);
			return true;
		}  catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean removePhoneTreeNumber(String fullName){
		try{
			treeDictionaryPhoneBook.remove(new BusinessName(fullName));
			return true;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean removePhoneNormalNumber (String fullName) {

		try{
			phoneBook.remove(new BusinessName(fullName));
			return true;
		}  catch (Exception e){
			e.printStackTrace();
			return false;
		}

	}

	public boolean addPhoneReverseNumber( String fullName, String phone) {
		try{
			reversePhoneBook.add(new BusinessName(fullName), phone);
			return true;
		}  catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean removePhoneReverseNumber (String fullName) {

		try{
			reversePhoneBook.remove(new BusinessName(fullName));
			return true;
		}  catch (Exception e){
			e.printStackTrace();
			return false;
		}

	}

	public boolean addPhoneNumber(String fullName, String phone){
		return addPhoneNormalNumber(fullName, phone) && addPhoneReverseNumber(fullName, phone) && addPhoneTreeNumber(fullName, phone);
	}

	public boolean removePhoneNumber(String fullName){

		return removePhoneNormalNumber(fullName) && removePhoneReverseNumber(fullName) && removePhoneTreeNumber(fullName);

	}

	public void printTree(){
		System.out.println("Tree");

		Iterator<BusinessName> nameIterator = treeDictionaryPhoneBook.getKeyIterator();

		Iterator <String> phoneIterator = treeDictionaryPhoneBook.getValueIterator();

		while (nameIterator.hasNext()) {

			BusinessName businessName = nameIterator.next();
			System.out.println(businessName.getName() + ": " +
					phoneIterator.next() + ": Location: " + businessName.getMunicipality());
		}
	}

	public void printInorder(){
		Iterator< Entry<BusinessName, String>> iterator = treeDictionaryPhoneBook.getIterator();
		while(iterator.hasNext()){
			Entry<BusinessName, String> entry = iterator.next();
			BusinessName businessName = entry.getKey();

			System.out.println(businessName.getName() + ": " +
					entry.getValue() + ": Location: " + businessName.getMunicipality());
		}
	}

	
	public void printNormal() {
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

	public void printReverse(){

		System.out.println("Printing");
//		System.out.println(phoneBook.getSize());
		Iterator <BusinessName> nameIterator = reversePhoneBook.getKeyIterator();
		Iterator <String> phoneIterator = reversePhoneBook.getValueIterator();

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
