package HeapsHW;
public class HuffmanNode implements Comparable {
	public String letter;
	public Double frequency; 
	public HuffmanNode left, right; 

	public HuffmanNode(String letter, Double frequency){
		this.letter = letter;
		this.frequency = frequency;
	}
	public HuffmanNode(HuffmanNode left, HuffmanNode right){
		this.left = left;
		this.right = right;
		this.letter = left.letter + right.letter;
		this.frequency = left.frequency + right.frequency;
	}
	public int compareTo(Object o){
		HuffmanNode huff = (HuffmanNode)o;
		return this.frequency.compareTo(huff.frequency);
	}

	//a way of printing the nodes
	public String toString(){
		return "<" + letter + ", "+frequency+">";
	}
	
}