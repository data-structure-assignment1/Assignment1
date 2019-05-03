
import java.awt.RenderingHints;
import java.util.Stack;

import dsa.iface.IBinarySearchTree;
import dsa.iface.INode;
import dsa.impl.AVLTree;
import dsa.impl.BinarySearchTree;
import dsa.impl.SplayTree;
import dsa.util.TreePrinter;

public class Main {
	BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
	public class test1<T extends Comparable<T>> extends BinarySearchTree<T>{

		public  void goAlongLeftBranch(INode<T> node, Stack<INode<T>> stack) {
			BTNode n=(BTNode)node;
		   while(n!=null) {
	 		  stack.push((INode<T>) n);
	 		  n=n.right;
	 	  }
		}
	   public void InorderTraversal (INode<T> node) {
		   Stack<INode<T>> stack;
		   BTNode n=(BTNode)node;
		   while(node==null){
			   goAlongLeftBranch(node, stack);
			   if (stack.empty()) {
				break;
			   }
			   node = stack.pop();
			   visit(node.element());
			   n=n.right;
		   }
	   }
	}

private void store(T element) {
	// TODO Auto-generated method stub
	
}
public static void main(String[] args ) {
		System.out.println("AVL TREE------------------------------------------------------");

	      IBinarySearchTree<Integer> st1 = new AVLTree<Integer>();
	      st1.insert( 15 );
	      TreePrinter.printTree( st1);
	      st1.insert( 25 );
	      TreePrinter.printTree( st1);
	      st1.insert( 5 );
	      TreePrinter.printTree( st1);
	     st1.remove( 15 );
	      TreePrinter.printTree( st1);

	      st1.contains( 10 );

	      TreePrinter.printTree( st1);
	      st1.contains( 30 );

	      TreePrinter.printTree( st1);
	      st1.insert( 30 );
	      TreePrinter.printTree( st1);
	      st1.insert( 80 );
	      TreePrinter.printTree( st1);
	      st1.insert( 20 );
	      TreePrinter.printTree( st1);
			System.out.println("Compare with the BinaryResearchTree------------------------------------------------------");

	      
	      
	      
			System.out.println("Splay TREE------------------------------------------------------");

      // Replace this with your code to test your implementations.
      // This just an example of one simple test for a Splay Tree.
      IBinarySearchTree<Integer> st2 = new SplayTree<Integer>();
      st2.insert( 10 );
      TreePrinter.printTree( st2);

      st2.insert( 20 );
      TreePrinter.printTree( st2);

      st2.insert( 40 );
      TreePrinter.printTree( st2);

      st2.remove( 20 );
      TreePrinter.printTree( st2);

      st2.contains( 10 );
      TreePrinter.printTree( st2);

      st2.contains( 30 );
      TreePrinter.printTree( st2);

      st2.insert( 30 );
      TreePrinter.printTree( st2);

      st2.insert( 80 );
      TreePrinter.printTree( st2);

      st2.insert( 20 );
      
      TreePrinter.printTree( st2);
   }
}
