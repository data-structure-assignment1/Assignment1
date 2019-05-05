package dsa.impl;

import dsa.iface.INode;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {
	public int height=0;
	public int size=0;
	public INode<T> find( INode<T> node, T value ) {
		   if(isExternal(node)) {  //Return the node if it is external.
			   return node;
		   }else {//Compare the element of the node with 'value'.
			   if (value.compareTo(node.element())<0) { //If the value is less than the node's element, recursively call this method to search the left sub-tree.
				   return find(left(node), value);
			   }else if (value.compareTo(node.element())>0) {//If the value is greater than the node's element, recursively call this method to search the right sub-tree.				  
				   return find(right(node), value);
			   }else {//If the value is equal to the node's element, we have found it! Return this node.
				   return node;
			   }
		   }
	   }
   public void rotation(INode<T> a,INode<T> b,INode<T> c,INode<T> T0,INode<T> T1,INode<T> T2,INode<T> T3) {
	   ((BTNode)a).left=(BTNode) T0;
	   if(T0!=root) {
		   ((BTNode)T0).parent=(BTNode) a;
	   }
	   ((BTNode)a).right=(BTNode) T1;
	   if(T1!=root) {
		   ((BTNode)T1).parent=(BTNode) a;
	   }
	   ((BTNode)c).left=(BTNode) T2;
	   if(T2!=root) {
		   ((BTNode)T2).parent=(BTNode) c;
	   }
	   ((BTNode)c).right=(BTNode) T1;
	   if(T3!=root) {
		   ((BTNode)T3).parent=(BTNode) c;
	   }
	   ((BTNode)b).left=(BTNode) a;
	   ((BTNode)a).parent=(BTNode) b;
	   ((BTNode)b).right=(BTNode)c;
	   ((BTNode)c).parent=(BTNode)b;
   }
   public int updateHeight(INode<T> node) {
	 
	 if (isExternal(node)) {
		return height;
	}else {
		return height=1+Math.max(updateHeight(right(node)), updateHeight(left(node)));
	}
	 
   }
   public boolean isBalanced(INode<T> node) {
	   if (updateHeight(left(node))>updateHeight(right(node))) {
		   if ((updateHeight(left(node))-updateHeight(right(node)))<2) {
			   return true;
		   }else {
			   return false;
		   }
	   }else {
			if ((updateHeight(right(node))-updateHeight(left(node)))<2) {
				return true;
			}else {
				return false;
			}
		}
   }

   public INode<T> unbalanceNode(INode<T> node){
	   if(node==root&&isBalanced(node)) {
		   return null;
	   }else if (isBalanced(node)) {
		   return unbalanceNode(parent(node));
	   }else {
		   return node;
	}
	   
   }
   private INode<T> tallerChild(INode<T> x) {
		if(updateHeight(left(x))>updateHeight(right(x))) { 
			return left(x);
		
		}else if(updateHeight(right(x))>updateHeight(left(x))) {
			return right(x);
		}else  {
			if (isRoot(x)) {
				return left(x);
			}else if (x==left(parent(x))) {
				return left(x);
			}else {
				return right(x);
			}
		}
	}
   public boolean contains( T element ) {
	   if (isInternal(find(root, element))) {
		   return true;
	   }else {
		   return false;
	   }
   }
   public void insert( T element ) {//insert the element to the AVLtree
	   BTNode xINode= (BTNode)find(root, element);
	   if (isExternal(xINode)) {//Verify that the destination node does not exist
		   
		   if(xINode==root) {//if root is null set the root element directly
			   root.element=element;
			   root.right=newNode(null, root);
			   root.left=newNode(null, root);
			   size=3;
		   }else {
			   replace(xINode, element);
			   xINode.left=newNode(null, xINode);
			   xINode.right=newNode(null, xINode); //Expand an external node create two external child size increase by2				
			   size+=2;//it is possible that the height of parent changed the grandparent may be unbalanced			  
			   ///
					   for(BTNode p = xINode.parent;p!=root;p=p.parent) {
					if (!isBalanced(p)) {//Once the node is unbalanced, restructure it
						   restructure(tallerChild(tallerChild(p)));
						   break;
					}//note even if the tree is balance, the height of p is possible changed
				}
			}
		}	   	   
   }

   public void remove( T element ) {
	   BTNode node= (BTNode)find(root, element);
	   if (!isExternal(node)) {//Verify that the destination node does not exist
		  
		   if (isInternal(left(node))&&isInternal(right(node))) {
			   INode<T> n=right(node);
			   while(isInternal(left(n))) {
				   n=left(n);
			   }
			 replace(node, n.element());
			 remove(n);
		   }else {
			 remove(node);
		   }	  
	   size--;
	   		if(node!=root) {
	   			for(BTNode p = node.parent;p!=root;p=p.parent) {
	   				if (!isBalanced(p)) {//Once the node is unbalanced, restructure it
	   					restructure(tallerChild(tallerChild(p)));
	   				}
	   			} 
	   		}
	   
	   }
   }
   private void restructure( INode<T> x ) {
	   BTNode y=(BTNode) parent(x); 
	   BTNode z= (BTNode) parent(y);
	   if(y.equals(left(z))) {
		   if (x.equals(left(y))) {
			rotation(x, y, z, left(x), right(x), right(y), right(z));
		   }else {
				rotation(y, x, z, left(y), left(x), right(x), right(z));
		   }
	  }else {
		  if(x.equals(right(y))) {
				rotation(z, y, x, left(z), left(y), left(x), right(x));
		  }else {
				rotation(z, x, y, left(z), left(x), right(x), right(y));
		  }
	  }		
	}
}




