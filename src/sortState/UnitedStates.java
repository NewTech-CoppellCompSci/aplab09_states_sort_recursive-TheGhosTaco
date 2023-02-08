package sortState;

import java.util.*;
import java.io.*; 

public class UnitedStates
{
	// instance variables
	private ArrayList <State> states;
	
	public UnitedStates()
	{
	   states = new ArrayList <State> ();
	   
	   readFile();
	   printStates();
	   
	   System.out.println();
	   System.out.println("=========================");
	   System.out.println("  Sorted by State");
	   System.out.println("=========================");
	   printStates();	
	   
	   menu();
	}
	
	


	/*
	 * Merge Sort
	 * 
	 * Use a merge sort to order the ArrayList
	 * by the state's name using a bubble sort
	 */
	public void sortStates(int front, int back) 
	{
		
		if(front < back)
		{
			int mid = (front + back) / 2;
			sortStates(front, mid);
			sortStates(mid + 1, back);

			merge(front, mid, back);

		}
		
	}
	
	

	/*
	 * Quick Sort
	 * 
	 * Use a selection sort to order the ArrayList
	 * by the state's capital using a bubble sort
	 */
	public void sortCapitals(int low, int high) 
	{
		if(low < high)
		{
			int pivot = partition(low, high);
			sortCapitals(low, pivot - 1);
			sortCapitals(pivot + 1, high);
		}
	}

/*
	 * Insertion Sort
	 * 
	 * Use an insertion sort to order the ArrayList
	 * by the state's population using a bubble sort
	 */
	public void sortPopulation() 
	{
		for(int i = 1; i < states.size(); i++)
		{
			State temp = states.get(i);
			int j = i - 1;
			
			while(j >= 0 && states.get(j).getPopulation() > temp.getPopulation())
			{
				states.set(j + 1, states.get(j));
				j--;
			}
			states.set(j + 1, temp);
		}
	}
	


	public void merge(int front, int mid, int back)
	{
		int n1 = mid - front + 1;
		int n2 = back - mid;
		
		State[] left = new State[n1];
		State[] right = new State[n2];
		
		for(int i = 0; i < n1; i++)
		{
			left[i] = states.get(front + i);
		}
		
		for(int j = 0; j < n2; j++)
		{
			right[j] = states.get(mid + 1 + j);
		}
		
		int i = 0;
		int j = 0;
		int k = front;
		
		while(i < n1 && j < n2)
		{
			if(left[i].getName().compareTo(right[j].getName()) <= 0)
			{
				states.set(k, left[i]);
				i++;
			}
			else
			{
				states.set(k, right[j]);
				j++;
			}
			k++;
		}
		
		while(i < n1)
		{
			states.set(k, left[i]);
			i++;
			k++;
		}
		
		while(j < n2)
		{
			states.set(k, right[j]);
			j++;
			k++;
		}
	}
	public int partition(int low, int high)
	{
		String pivot = states.get(high).getCapital();
		int i = low - 1;
		
		for(int j = low; j < high; j++)
		{
			if(states.get(j).getCapital().compareTo(pivot) <= 0)
			{
				i++;
				swap(i, j);
			}
		}
		swap(i + 1, high);
		return i + 1;
	}
	public void swap(int i, int j)
	{
		State temp = states.get(i);
		states.set(i, states.get(j));
		states.set(j, temp);
	}


	
	

    public void menu()
	{
		
		boolean quitSelected = false;
		
		while (!quitSelected) {
			Scanner inKey = new Scanner(System.in);
			
			System.out.println("\n");
			System.out.println("=========================");
			System.out.println("          Menu");
			System.out.println("=========================");
			System.out.println("  1 - Sort by State");
			System.out.println("  2 - Sort by Capital");
			System.out.println("  3 - Exit");
			System.out.print("\n   Enter Selection: ");
			
			
			int choice = inKey.nextInt();
			
			
			switch (choice) {
			case (1):
	
				System.out.println("\n");
				System.out.println("=========================");
				System.out.println("  Sorted by State");
				System.out.println("=========================");
				sortStates(0, states.size() - 1);
				printStates();
				
				break;
			case (2):
	
				System.out.println("\n");
				System.out.println("=========================");
				System.out.println("  Sorted by Capital");
				System.out.println("=========================");
				sortCapitals(0, states.size() - 1);
				printStates();	
				
				break;
			case (3):
				System.out.println("\n\nGoodbye!");
				quitSelected = true;
				break;
			default:
				System.out.println("\nNot a valid Choice:");
				
	
			}
		
		}
		

	}
	
	
	
	
	
	public void printStates()
	{
		for(State s : states)
		{
			System.out.printf("%-15s", s.getName());
			System.out.printf("%-15s", s.getCapital());
			System.out.printf("%-25s", s.getNickname());
			System.out.printf("%10s\n", s.getPopulation());	
		}
	}
	
	public void readFile()
	{
		Scanner scan = null;
		try
		{
			scan = new Scanner(new File("states.txt"));
		}
		catch(FileNotFoundException ex)
		{
			System.out.println("File not Found!");
		}
		
		String name;
		String capital;
		String nickname;
		int population;
		while(scan.hasNextLine())
		{
			name = scan.nextLine();
			capital = scan.nextLine();
			nickname = scan.nextLine();
			population = scan.nextInt();
			if(scan.hasNextLine())
			{
			   String dummy = scan.nextLine();	
			}
			  
			
			State state = new State(name, capital, nickname, population);
			states.add(state);
		}
		
		
		
	}
}