package HeapsHW;

public class HuffmanTree {

	HuffmanNode root;

	public HuffmanTree(HuffmanNode huff){
		this.root = huff;
	}
	public void printLegend(){
		printLegend(root, "");
	}

	private void printLegend(HuffmanNode t, String s){
		if(t.letter.length() > 1){
			printLegend(t.left, s + "0");
			printLegend(t.right, s + "1");
		}
		else{
			System.out.println(t.letter + "=" + s);
		}
	}

	public static BinaryHeap legendToHeap(String legend){
		String[] arr = legend.split(" ");
		HuffmanNode[] node = new HuffmanNode[arr.length/2];
		for(int i = 0; i < arr.length; i+=2){
			String letter = arr[i];
			String freq = arr[i+1];
			HuffmanNode curr = new HuffmanNode(letter, Double.parseDouble(freq));
			node[i/2] = curr;
		}
		BinaryHeap tree = new BinaryHeap(node);
		return tree;
	}
	public static HuffmanTree createFromHeap(BinaryHeap<HuffmanNode> b){
		while(!(b.isEmpty())){
			HuffmanNode node1 = b.deleteMin();
			if(!(b.isEmpty())){
			HuffmanNode node2 =b.deleteMin();
			HuffmanNode curr = new HuffmanNode(node1, node2);
			b.insert(curr);
			}
			else{
				HuffmanTree htree = new HuffmanTree(node1);
				return htree;
			}
		}
		return null;
	}

	public static void main(String[]args){
		String legend = "A 20 E 24 G 3 H 4 I 17 L 6 N 5 O 10 S 8 V 1 W 2";
		BinaryHeap bheap = legendToHeap(legend);
		bheap.printHeap();
		HuffmanTree htree = createFromHeap(bheap);
		htree.printLegend();
	}
}