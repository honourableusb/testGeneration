package testing;

public class LinkedList<AnyType> {
	private Node root;
	
	LinkedList()
	{
		this.root = new Node();
	}
	
	LinkedList(AnyType value)
	{
		this.root = new Node(value);
	}
	
	LinkedList(AnyType[] values)
	{
		this.root = new Node(values[0]);
		Node current = root;
		for (int i = 1; i < values.length; i++)
		{
			current = current.next;
			current = new Node(values[i]);
		}
	}
	
	public void insertToEnd(AnyType value)
	{
		if (root.getContent() == null)
		{
			root.setContent(value);
			return;
		}
		Node current = root;
		while(current.next != null)
			current = current.next;
		current.next = new Node(value);
	}
	
	public void print()
	{
		Node current = root;
		while(current.next != null)
		{
			System.out.print(current.getContent() + " -> ");
			current = current.next;
		}
		System.out.println(current.getContent() + "");
		
	}
	
	/**
	 * getIndex = gets index value given a specified index
	 * @param indexVal index to find
	 * @return value of content at specified index, null if out of bounds
	 */
	
	public AnyType getIndex(int indexVal)
	{
		Node current = root;
		for(int i = 0; i < this.getSize(); i++)
		{
			if (i == indexVal)
				return current.getContent();
			current = current.next;
		}
		return null;
	}
	
	
	/**
	 * search = searches for a value within the list
	 * @param value = the value that is being searched for
	 * @return the content if it exists within the list, null otherwise
	 */
	public AnyType search(AnyType value)
	{
		Node current = root;
		while (current.next != null);
		{
			if(value == current.getContent())
				return current.getContent();
		}
		return null;
	}
	
	/**
	 * getSize = gets the amount of nodes within a linkedList
	 * @return number of nodes within a linked list
	 */
	public int getSize()
	{
		Node current = root;
		int counter = 1;
		while(current.next != null)
		{
			counter++;
			current = current.next;
		}
		return counter;
	}
	
	private class Node{
		AnyType content;
		Node next;
		
		Node()
		{
			content = null;
			next = null;
		}
		
		Node(AnyType value)
		{
			this.content = value;
			next = null;
		}
		
		public void setContent(AnyType n) {this.content = n;}
		
		public AnyType getContent() { return this.content; }
		
		public String toString() {return (String)this.content;}
		
	}
}
