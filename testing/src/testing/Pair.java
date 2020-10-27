package testing;

public class Pair<val1 extends Comparable<? super val1>, val2 extends Comparable<? super val2>> {
	private val1 first;
	private val2 second;
	
	Pair()
	{
		first = null;
		second = null;
	}
	
	Pair(val1 value, val2 second)
	{
		this.first = value;
		this.second = second;
	}
	
	public void setFirst(val1 value) {this.first = value;}
	public void setSecond(val2 value) {this.second = value;}
	public val1 getFirst() { return this.first;}
	public val2 getSecond() { return this.second;}
}
