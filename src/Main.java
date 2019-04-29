import dsa.iface.IBinarySearchTree;
import dsa.impl.AVLTree;
import dsa.impl.SplayTree;
import dsa.util.TreePrinter;

public class Main {
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
			System.out.println("Splay TREE------------------------------------------------------");

      // Replace this with your code to test your implementations.
      // This just an example of one simple test for a Splay Tree.
      IBinarySearchTree<Integer> st2 = new SplayTree<Integer>();
      st2.insert( 10 );
      st2.insert( 20 );
      st2.insert( 40 );
      st2.remove( 20 );
      st2.contains( 10 );
      st2.contains( 30 );
      st2.insert( 30 );
      st2.insert( 80 );
      st2.insert( 20 );
      
      TreePrinter.printTree( st2);
   }
}
