package com.filehandling.crud.project;
import java.util.*;
import java.io.*;
  //used some reference from Internet but did practice myself too
  class lockedme implements Serializable{
	int prodno;
    String prodname;
	int price;
	String aboutit;
     lockedme(int prodno, String prodname, int price, String aboutit) {
		this.prodno = prodno;
		this.prodname = prodname;
		this.price = price;
		this.aboutit = aboutit;
	}
     public String toString() {
		return prodno+" "+prodname+" "+price+" "+aboutit;
	}
   static class lockedmedemo{
	public static void main(String[] args) throws Exception{
		int choice=-1;
		Scanner s = new Scanner(System.in);
		Scanner s1= new Scanner(System.in);
		File file=new File("product.txt");
		ArrayList<lockedme> a1=new ArrayList<lockedme>();
		ObjectOutputStream oos = null;
		ObjectInputStream ois= null;
		ListIterator li=null;
		if(file.isFile()) {
			ois= new ObjectInputStream(new FileInputStream(file));
			a1=(ArrayList<lockedme>)ois.readObject();
			ois.close();
		}
		do {
			System.out.println("1.INSERT");
			System.out.println("2.DISPLAY");
			System.out.println("3.DELETE");
			System.out.println("4.UPDATE");
			System.out.println("5.SEARCH");
			System.out.println("6.SORT by product no");
			System.out.println("7.SORT by product name");
			System.out.println("8.SORT by price");
			System.out.println("9.SORT by product name in reverse order");
			System.out.println("10.SORT by product no in reverse order");
			System.out.println("11.SORT by price in reverse order");
			System.out.println("0.EXIT");
			System.out.println("Enter your choice");
			choice =s.nextInt();
			
			switch(choice) {
			case 1:
				System.out.println("How many products u want..............");
				int n=s.nextInt();
				for(int i=0;i<n;i++) {
					System.out.println("Enter the product no............");
					int prodno=s.nextInt();
					System.out.println("Enter the product name............");
					String prodname=s1.nextLine();
					System.out.println("Enter the price............");
					int price=s.nextInt();
					System.out.println("Enter the about it............");
					String aboutit=s1.nextLine();
					a1.add(new lockedme(prodno,prodname,price,aboutit));
					}
				oos=new ObjectOutputStream(new FileOutputStream(file));	
				oos.writeObject(a1);
				oos.close();
				break;
				
			case 2:
				ois=new ObjectInputStream(new FileInputStream(file));
				a1=(ArrayList<lockedme>)ois.readObject();
				ois.close();
				System.out.println("------------------------------------------");
				li=a1.listIterator();
				while(li.hasNext()) {
					System.out.println(li.next());
					System.out.println("---------------------------------------------");
				}
				break;
				
			case 3:
				ois=new ObjectInputStream(new FileInputStream(file));
				a1=(ArrayList<lockedme>)ois.readObject();
				ois.close();
				boolean found=false;
				System.out.println("Enter the product no to delete..................");
				int prodno=s.nextInt();
				System.out.println("--------------------------------");
				li=a1.listIterator();
				while(li.hasNext()) {
					lockedme lm=(lockedme)li.next();
					if(lm.prodno==prodno) {
						li.remove();
						found=true;
					}
					if(found) {
						oos=new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(a1);
						oos.close();
						System.out.println("Product deleted successfully....................");
					}else {
						System.out.println("Record not found..................");
						System.out.println("---------------------------");
					}
				}
				break;
			case 4:
				ois=new ObjectInputStream(new FileInputStream(file));
				a1=(ArrayList<lockedme>)ois.readObject();
				ois.close();
				boolean found1=false;
				System.out.println("Enter the product no to update...............");
				int prodno1=s.nextInt();
				System.out.println("-----------------------------");
				li=a1.listIterator();
				while(li.hasNext()) {
				lockedme lm=(lockedme)li.next();
				if(lm.prodno==prodno1) {
					System.out.println("Enter the new product name............");
					String prodname=s1.nextLine();
					System.out.println("Enter the new price............");
					int price=s.nextInt();
					System.out.println("Enter the new about it...............");
					String aboutit=s1.nextLine();
					li.set(new lockedme(prodno1,prodname,price,aboutit));
					if(found1) 
					{
						oos=new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(a1);
						oos.close();
						System.out.println("the record was found successfully..............");
						
					}else {
						System.out.println("the record was not found");
					}
				}
				}
				break;
			case 5:
				ois=new ObjectInputStream(new FileInputStream(file));
				a1=(ArrayList<lockedme>)ois.readObject();
				ois.close();
				boolean found2 = false;
				System.out.println("Enter the product no to search............");
				int prodno2=s.nextInt();
				System.out.println("--------------------------");
				li=a1.listIterator();
				while(li.hasNext()) {
					lockedme lm=(lockedme)li.next();
					if(lm.prodno==prodno2) {
						System.out.println(lm);
						found=true;
					}
					if(!found2) {
						System.out.println("Record not found..........");
						System.out.println("-----------------------------");
					}
				}
				break;
			case 6:
				ois=new ObjectInputStream(new FileInputStream(file));
				a1=(ArrayList<lockedme>)ois.readObject();
				ois.close();
				Collections.sort(a1,new Comparator<lockedme>() {
					public int compare(lockedme lm1,lockedme lm2) {
						return lm1.prodno - lm2.prodno;
					}
				});
				System.out.println("--------------------------------");
				li=a1.listIterator();
				while(li.hasNext()) {
					System.out.println(li.next());
				}
				System.out.println("--------------------------------");
				break;
			case 7:
				ois=new ObjectInputStream(new FileInputStream(file));
				a1=(ArrayList<lockedme>)ois.readObject();
				ois.close();
				Collections.sort(a1,new Comparator<lockedme>() {
					public int compare(lockedme lm1,lockedme lm2) {
						return lm1.prodname.compareTo(lm2.prodname);
					}
				});
				System.out.println("--------------------------------");
				li=a1.listIterator();
				while(li.hasNext()) {
					System.out.println(li.next());
				}
				System.out.println("--------------------------------");
				break;
			case 8:
				ois=new ObjectInputStream(new FileInputStream(file));
				a1=(ArrayList<lockedme>)ois.readObject();
				ois.close();
				Collections.sort(a1,new Comparator<lockedme>() {
					public int compare(lockedme lm1,lockedme lm2) {
						return lm1.price - lm2.price;
					}
				});
				System.out.println("--------------------------------");
				li=a1.listIterator();
				while(li.hasNext()) {
					System.out.println(li.next());
				}
				System.out.println("--------------------------------");
				break;
				
			case 9:
				ois=new ObjectInputStream(new FileInputStream(file));
				a1=(ArrayList<lockedme>)ois.readObject();
				ois.close();
				Collections.sort(a1,new Comparator<lockedme>() {
					public int compare(lockedme lm1,lockedme lm2) {
						return lm2.prodno - lm1.prodno;
					}
				});
				System.out.println("--------------------------------");
				li=a1.listIterator();
				while(li.hasNext()) {
					System.out.println(li.next());
				}
				System.out.println("--------------------------------");
				break;
			case 10:
				ois=new ObjectInputStream(new FileInputStream(file));
				a1=(ArrayList<lockedme>)ois.readObject();
				ois.close();
				Collections.sort(a1,new Comparator<lockedme>() {
					public int compare(lockedme lm1,lockedme lm2) {
						return lm2.prodname.compareTo(lm1.prodname);
					}
				});
				System.out.println("--------------------------------");
				li=a1.listIterator();
				while(li.hasNext()) {
					System.out.println(li.next());
				}
				System.out.println("--------------------------------");
				break;
			case 11:
				ois=new ObjectInputStream(new FileInputStream(file));
				a1=(ArrayList<lockedme>)ois.readObject();
				ois.close();
				Collections.sort(a1,new Comparator<lockedme>() {
					public int compare(lockedme lm2,lockedme lm1) {
						return lm1.price - lm2.price;
					}
				});
				System.out.println("--------------------------------");
				li=a1.listIterator();
				while(li.hasNext()) {
					System.out.println(li.next());
				}
				System.out.println("--------------------------------");
				break;
			}
		}while (choice!=0);
		
	}
   }
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  