package HeapsHW;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class HuffmanConverter
{
	// The # of chars in the ASCII table dictates
// the size of the count[] & code[] arrays.
	public static final int NUMBER_OF_CHARACTERS = 256;
	// the contents of our message...
	private String contents;
	// the tree created from the msg
	private HuffmanTree huffmanTree;
	// tracks how often each character occurs
	private int count[];
	// the huffman code for each character
	private String code[];
	// stores the # of unique chars in contents
	private int uniqueChars = 0; //(optional)
	/** Constructor taking input String to be converted */
	public HuffmanConverter(String input)
	{
		this.contents = input;
		this.count = new int[NUMBER_OF_CHARACTERS];
		this.code = new String[NUMBER_OF_CHARACTERS];
	}
	/**
	 * Records the frequencies that each character of our
	 * message occurs...
	 * I.e., we use 'contents' to fill up the count[] list...
	 */
	public void recordFrequencies() {
		for(int i = 0; i < contents.length(); i++) {
			char c = contents.charAt(i);
			count[c] ++;
		}
	}
	/**
	 * Converts our frequency list into a Huffman Tree. We do this by
	 * taking our count[] list of frequencies, and creating a binary
	 * heap in a manner similar to how a heap was made in HuffmanTree's
	 * fileToHeap method. Then, we print the heap, and make a call to
	 * HuffmanTree.heapToTree() method to get our much desired
	 * HuffmanTree object, which we store as huffmanTree.
	 */
	public void frequenciesToTree() {
		BinaryHeap<HuffmanNode> freq = new BinaryHeap();
		for(int i = 0; i < count.length; i++) {
			if(count[i] > 0) {
				HuffmanNode node = new HuffmanNode(String.valueOf((char)i) , (double)count[i]);
				freq.insert(node);
			}
		}
		freq.printHeap();
		this.huffmanTree = HuffmanTree.createFromHeap(freq);

	}
	/**
	 * Iterates over the huffmanTree to get the code for each letter.
	 * The code for letter i gets stored as code[i]... This method
	 * behaves similarly to HuffmanTree's printLegend() method...
	 * Warning: Don't forget to initialize each code[i] to ""
	 * BEFORE calling the recursive version of treeToCode...
	 */
	public void treeToCode() {
		for(int i=0; i < code.length; i++) {
			code[i] = "";
		}
		treeToCode(huffmanTree.root, "");
	}
	/*
	 * A private method to iterate over a HuffmanNode t using s, which
	 * contains what we know of the HuffmanCode up to node t. This is
	 * called by treeToCode(), and resembles the recursive printLegend
	 * method in the HuffmanTree class. Note that when t is a leaf node,
	 * t's letter tells us which index i to access in code[], and tells
	 * us what to set code[i] to...
	 */
	private void treeToCode(HuffmanNode t, String s) {
		if(t == null) {
			return;
		}
		if(t.left == null && t.right == null) {
			//create encoding
			char c = t.letter.charAt(0);
			code[c] = s;
			System.out.println("'" + t.letter + "'=" + s);
		}
		else {
			treeToCode(t.left, s + "0");
			treeToCode(t.right, s + "1");
		}
	}
	/**
	 * Using the message stored in contents, and the huffman conversions
	 * stored in code[], we create the Huffman encoding for our message
	 * (a String of 0's and 1's), and return it...
	 */
	public String encodeMessage() {
		StringBuilder encoding = new StringBuilder();
		for(int i = 0; i < contents.length(); i++) {
			char c = contents.charAt(i);
			encoding.append(code[c]);
		}
		return encoding.toString();

	}
	/**
	 * Reads in the contents of the file named filename and returns
	 * it as a String. The main method calls this method on args[0]...
	 * @throws IOException 
	 */
	public static String readContents(String filename) throws IOException {
		return Files.readString(Path.of(filename), Charset.forName("US-ASCII"));
	}
	/**
	 * Using the encoded String argument, and the huffman codings,
	 * re-create the original message from our
	 * huffman encoding and return it...
	 */
	public String decodeMessage(String encodedStr) {
		StringBuilder decoding = new StringBuilder();
		HuffmanNode iterate = huffmanTree.root;
		for(int i = 0; i < encodedStr.length(); i++) {
			char c = encodedStr.charAt(i);
			if(c=='0') {
				iterate = iterate.left;
			}
			else {
				iterate = iterate.right;
			}
			
			if(iterate.left == null && iterate.right == null) {
				decoding.append(iterate.letter);
				iterate = huffmanTree.root;
			}
		}
		return decoding.toString();
	}
	/**
	 * Uses args[0] as the filename, and reads in its contents. Then
	 * instantiates a HuffmanConverter object, using its methods to
	 * obtain our results and print the necessary output. Finally,
	 * decode the message and compare it to the input file.<p>
	 * NOTE: Example method provided below...
	 * @throws IOException 
	 **/
	public static void main(String args[]) throws IOException
	{
		String filename = args[0];
		String contents = readContents(filename);
		HuffmanConverter convert = new HuffmanConverter(contents);
		System.out.println("Frequencies:");
		convert.recordFrequencies();
		convert.frequenciesToTree();
		System.out.println();
		System.out.println("Encoding:");
		convert.treeToCode();
		System.out.println();
		String encode = convert.encodeMessage();
		System.out.println("Huffman Encoding:");
		System.out.print(encode);
		System.out.println();
		System.out.println("Decoded Message:");
		System.out.println(convert.decodeMessage(encode));
		
	}
}