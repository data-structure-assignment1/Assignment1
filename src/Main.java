import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;
import dsa.iface.IBinarySearchTree;
import dsa.iface.IIterator;
import dsa.impl.AVLTree;
import dsa.impl.BinarySearchTree;
import dsa.impl.SplayTree;
import dsa.util.TreePrinter;

public class Main {
	public void test2(IIterator<Integer> com1, IIterator<Integer> com2) {
		Stack<Integer> stack2 = new Stack<Integer>();
		Stack<Integer> stack1 = new Stack<Integer>();
		while (com1.hasNext()) {
			Integer node1 = com1.next();

			if (node1 != null) {
				stack1.push(node1);
			}
		}
		while (com2.hasNext()) {
			Integer node2 = com2.next();
			stack2.push(node2);
		}
		if (stack1.size() == stack2.size()) {
			while (!stack1.isEmpty() && stack1.peek().equals(stack2.peek())) {
				stack1.pop();
				stack2.pop();
			}
			if (stack1.empty()) {
				System.out.println("yes,they are the same");
			} else {
				System.out.println("No, their contents are different.");

			}
		} else {
			System.out.println("No, their structures are different.");

		}

	}

	public void readFrom(File file, IBinarySearchTree<Integer> Tree) {
		try {
			FileReader fr = new FileReader(file);
			BufferedReader bufr = new BufferedReader(fr);
			String s = bufr.readLine();

			while (s != null) {
				String[] spl = s.split(" ");
				int num = Integer.parseInt(spl[1]);
				if (spl[0].equals("I")) {
					Tree.insert(num);
					// TreePrinter.printTree(st3);
				} else if (spl[0].equals("R")) {
					Tree.remove(num);
				}
				s = bufr.readLine();
			}
			bufr.close();
			fr.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeIn(File file, String content[]) {
		try {

			FileWriter fw = new FileWriter(file);
			System.out.println("enter");
			BufferedWriter bufw = new BufferedWriter(fw);
			for (int k = 0; k < content.length; k++) {
				bufw.write(content[k]);
				bufw.newLine();
			}
			bufw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println(" test1------------------------------------------------------");

		System.out.println("AVL TREE------------------------------------------------------");

		IBinarySearchTree<Integer> st1 = new AVLTree<Integer>();
		st1.insert(15);
		st1.insert(25);
		st1.insert(5);
		st1.remove(15);
		st1.contains(10);
		st1.contains(30);
		st1.insert(30);
		st1.insert(80);
		st1.insert(20);
		TreePrinter.printTree(st1);

		System.out.println("Splay TREE------------------------------------------------------");

		// Replace this with your code to test your implementations.
		// This just an example of one simple test for a Splay Tree.
		IBinarySearchTree<Integer> st2 = new SplayTree<Integer>();
		st2.insert(10);
		st2.insert(20);
		st2.insert(40);
		st2.remove(20);
		st2.contains(10);
		st2.contains(30);
		st2.insert(30);
		st2.insert(80);
		st2.insert(20);
		TreePrinter.printTree(st2);
		System.out.println(" the BinaryResearchTree------------------------------------------------------");
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();

		bst.insert(15);
		bst.insert(25);
		bst.insert(5);
		bst.remove(15);
		bst.contains(10);
		bst.contains(30);
		bst.insert(30);
		bst.insert(80);
		bst.insert(20);
		TreePrinter.printTree(bst);
		IIterator<Integer> iBSTree = bst.iterator();
		IIterator<Integer> iAVLTree = st1.iterator();
		IIterator<Integer> iSplayTree = st2.iterator();
		System.out.println(" test2------------------------------------------------------");

		Main main = new Main();
		System.out.println("Is this Binary Search Tree the same as the AVLTree?");
		main.test2(iBSTree, iAVLTree);
		System.out.println("Is this Binary Search Tree the same as the SplyTree?");
		main.test2(iBSTree, iSplayTree);

		System.out.println(" test3------------------------------------------------------");

		String content[] = { "I 15", "I 25", "I 30", "R 25" };
		File file = new File("test3.txt");
		IBinarySearchTree<Integer> st3 = new AVLTree<Integer>();
		IBinarySearchTree<Integer> st4 = new SplayTree<Integer>();
		main.writeIn(file, content);
		main.readFrom(file, st3);
		TreePrinter.printTree(st3);
		main.readFrom(file, st4);
		TreePrinter.printTree(st4);
	}
}
