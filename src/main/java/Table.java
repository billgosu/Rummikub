import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

public class Table{
	private ArrayList<ArrayList<Tile>> table;
	private Boolean[][] isTableSet;
	private int timesAdded = -1;
	private int totalAdded = 0;
	private int nextLine = 0;
	private HandleJoker check = new HandleJoker();
	
	public Table() {
		table = new ArrayList<ArrayList<Tile>>();
		isTableSet = new Boolean[20][7];
		
		clearBool();
	}
	
	private int checking_index = 0;
	public void initTable()
	{
		for(int x=0;x<20;x++)
		{
			table.add(new ArrayList<Tile>());
			for(int y=0;y<7;y++)
			{
				table.get(x).add(new Tile());
			}
		}
		
	}
	
	public int getNumberOfTile() {
		// TODO Auto-generated method stub
		int u = 0;
		for(int i =0; i < table.size();i++) {
			u += table.get(i).size();
		}
		return u;
	}

	// Add set or sequence on table, return true if success
	// if it is not a set or sequence, it will return false
	public boolean addTiles(ArrayList<Tile> tiles2) 
	{
		
		if(timesAdded != -1)
		{
			//System.out.println("Table size: "+table.get(timesAdded).size());
		}
		
		if(isSequence(tiles2) || isSet(tiles2))
		{
			if(totalAdded+tiles2.size()>19)
			{
				totalAdded=0;
				nextLine++;
			}
			try {
			for(int x=0;x<tiles2.size()+1;x++)
			{
				isTableSet[totalAdded+x][nextLine] = true;
			}}
			catch(ArrayIndexOutOfBoundsException exception) {}
			timesAdded++;
			
			totalAdded+=tiles2.size()+1;
			if(totalAdded>20)
			{
				totalAdded=0;
				nextLine++;
			}
			
			return table.add(tiles2);
		}
		
		return false;
	}
	
	// a is the number of row holding tile.
	// b is the index of Tile in a
	// c is the number of row we want to push tile in
	// d is the number of index we want to put tile in c.
	public boolean moveTile(int a, int b, int c, int d) {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<Tile> getSetOrSequences(int i) {
		// TODO Auto-generated method stub
		return table.get(i);		
	}
	
	public ArrayList<ArrayList<Tile>> getTable(){
		return table;
	}
	
	public Tile getTile(int x, int y)
	{
		System.out.println("x: "+x+", y: "+y+"\nx.length: "+table.size()+", y.length: "+table.get(x).size());
		if(table.size() == 0) return null;
		
		if(table.size()<x)
		{
			return null;
		}
		if(table.get(x).size()<y)
		{
			return null;
		}
		
		return table.get(x).get(y);		
	}
	
	public void setTile(int x, int y, Tile tile)
	{
		isTableSet[table.size()][y] = true;
		table.get(x).set(y, tile);
	}
	
	//check the array is set or not
	public boolean isSet(ArrayList<Tile> t) {
		if (t == null) return false;
		if(t.size() > 4 || t.size() < 3) return false;
		Collections.sort(t, new SortbyValue());
		//ArrayList<Tile> y = new ArrayList<Tile>();
		//t = jokerSort(t);
		int NumberOfJoker = 0;
		if (t.get(t.size()-1).getNumber()== 14) NumberOfJoker++;
		if (t.get(t.size()-2).getNumber()== 14) NumberOfJoker++;
		int value = t.get(0).getNumber();
		HashSet<String> set = new HashSet<String>();
		if(t.size()-1-NumberOfJoker == 0) return true;
		
		for(int  u =0; u < t.size()-1-NumberOfJoker;u++) {
			int current, next;
			String current_color = t.get(u).getColor();
			current = t.get(u).getNumber();
			next = t.get(u+1).getNumber();
			if(current != next)
				return false;
			
			if(set.add(current_color) == false) {
				return false;		
			}
		}
		return true;
	}
	//sort tiles by number
	class SortbyValue implements Comparator<Tile> 
	{ 
	    public int compare(Tile a, Tile b) 
	    { 
	    	if (a.getNumber()==14) {
	    		
	    		return a.getJokerPoint()-b.getNumber();
	    		
	    	}/*
	   	else if (b.getNumber()==14) {
	    		
	    		return b.getNumber()-b.getJokerPoint();
	    		
	    	}	
	    	*///System.out.println(a.getJokerPoint()); System.out.println(a.getNumber());
	        return a.getNumber() - b.getNumber(); 
	    } 
	} 
	//check the array is sequence or not
	public boolean isSequence(ArrayList<Tile> t) {
		if (t == null) return false;
		Collections.sort(t, new SortbyValue());
		
		if(t.size() < 3) return false;
		int NumberOfJoker = 0;
		if (t.get(t.size()-1).getNumber()== 14) NumberOfJoker++;
		if (t.get(t.size()-2).getNumber()== 14) NumberOfJoker++;
		if(t.size()-1-NumberOfJoker == 0) return true;
		if (NumberOfJoker!=0) {
		//	t = jokerSort(t);
		}
		String color = t.get(0).getColor();
		if (t.get(0).getColor()=="J") {
			 color = t.get(0).getJokerColor(); 
		}
		
		for(int u =0; u < t.size()-1-NumberOfJoker;u++) {
			int current, next;
			String current_color = t.get(u).getColor();
			if (t.get(u).getColor()=="J") {
				current_color= t.get(0).getJokerColor(); 
			}
			current = t.get(u).getNumber();
			if (t.get(u).getNumber()==14) {
				current= t.get(0).getJokerPoint(); 
			}
			next = t.get(u+1).getNumber();
			if (t.get(u+1).getNumber()==14) {
				next= t.get(0).getJokerPoint(); 
			}
			
			if(next == 14) {
				return true;}
			
			if(!current_color.equals(color)) {
					return false;}
			else {
				if(current+1 != next) {
					if(next - current > 2) return false;
					else if((NumberOfJoker > 0) && (next-2 == current))	NumberOfJoker--;
					else return false;
				}
			}
		}
		return true;
	}
	
	public ArrayList<Tile> jokerSort(ArrayList<Tile> x) {
		ArrayList<Tile> y = new ArrayList<Tile>();
		//System.out.println(x);
		int z=0;
		int u=0;
		for (int j=0;j<x.size();j++) {
			if (x.get(j).isJoker()) {
				z=j;
			}
		}
		for (int i=0;i<x.size();i++) {
			if (x.get(z).getJokerPoint()<x.get(i).getNumber()) {
				u=x.size()-i-1; //System.out.println(u);
				
			}
		}//System.out.println(u);
			for (int j=0;j<x.size()-1;j++) {
				if (j==u) {
				y.add(x.get(z)); y.add(x.get(j)); //System.out.println(x.get(z));
				}
			
			else {
				y.add(x.get(j)); //System.out.println((j));
			}
		}
			System.out.println(y);
		
		return y;
	}
	
	public ArrayList<Tile> get(int i ){
		return table.get(i);
	}
	
	public boolean equals(Table t) {
		for(int i =0; i < table.size();i++) {
			for(int u = 0; u < table.get(i).size();u++) {
				if(!table.get(i).get(u).getColor().equals(t.get(i).get(u).getColor()))
					return false;
				if(table.get(i).get(u).getNumber() != t.get(i).get(u).getNumber())
					return false;
				
			}
		}
		return true;
	}
	public String toString() {
		String output = "";
		for(int i =0; i < table.size();i++) {
			for(int u =0; u < table.get(i).size();u++) {
				output += "Colour " + table.get(i).get(u).getColor() + " Number " + table.get(i).get(u).getNumber() + "\n"; 
			}
		}
		
		
		
		return output;
	}
	
	public void clean() {
		table.clear();
	}
	public void setTable(ArrayList<ArrayList<Tile>> t) {
		table = t;
	}

	/*
	public void addTile(Tile temp) {
		// TODO Auto-generated method stub
		ArrayList<Tile> t  = new ArrayList<Tile>();
		t.add(temp);
		table.add(t);
	}
	*/

	public void clearBool()
	{
		for(int x=0;x<20;x++)
		{
			for(int y=0;y<7;y++)
			{
				isTableSet[x][y] = false;
			}
		}
	}
	
	public Boolean[][] getBool()
	{
		return isTableSet;
	}
	
	public void addTableCounter()
	{
		try {
		isTableSet[totalAdded][nextLine] = true;
		}
		catch (Exception e) {}
		totalAdded++;
		if(totalAdded>20)
		{
			totalAdded=0;
			nextLine++;
		}
	}
	// Add set or sequence on table, return true if success
		// if it is not a set or sequence, it will return false
		public boolean AiAddTiles(ArrayList<Tile> tiles2) 
		{
			
			if(timesAdded != -1)
			{
				//System.out.println("Table size: "+table.get(timesAdded).size());
			}
			
			if(check.isRun(tiles2) || check.isSet(tiles2))
			{
				if(totalAdded+tiles2.size()>19)
				{
					totalAdded=0;
					nextLine++;
				}
				try {
				for(int x=0;x<tiles2.size()+1;x++)
				{
					isTableSet[totalAdded+x][nextLine] = true;
				}
				timesAdded++;
				}
				catch(ArrayIndexOutOfBoundsException exception) {}
				totalAdded+=tiles2.size()+1;
				if(totalAdded>20)
				{
					totalAdded=0;
					nextLine++;
				}
				
				return table.add(tiles2);
			}
			
			return false;
		}
	
	
}