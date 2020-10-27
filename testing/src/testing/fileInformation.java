package testing;

public class fileInformation {
		private int totalLines;
		LinkedList<Pair<Integer,String>> statements;
		int[] decisions;
		int[] conditions;
		
		fileInformation()
		{
		totalLines = -1;
		statements = new LinkedList<Pair<Integer,String>>();
		}
		
		public void setTotalLines(int val)
		{
			this.totalLines = val;
		}
		
		public int getTotalLines()
		{
			return totalLines;
		}
}
