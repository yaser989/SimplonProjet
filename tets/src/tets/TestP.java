package tets;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<String> names = new ArrayList<>();
		List<Integer> ages = new ArrayList<>();
		
		System.out.println("how many ticket you want");
		Scanner sc = new Scanner(System.in); 
	
		int person = sc.nextInt();
		
		for(int i=0; i<person; i++) {
			System.out.println("enter the name of the "+(i+1)+":");
			names.add(sc.next());
			System.out.println("enter the age of"+" "+names.get(i)+" : ");
			ages.add(sc.nextInt());	
		}
		sc.close();
		
		for(int i=0; i<person; i++) {
			if(ages.get(i)<18) {
				System.out.println("sory for"+" "+names.get(i)+" "+"is under 18");
			}
			else {
				System.out.println("welcome "+" "+names.get(i)+" "+"your ticket it's done");
			}
		}
	
 
	}

}
