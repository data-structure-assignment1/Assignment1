package dsa.impl;

import dsa.iface.INode;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {
	public int height;
	public INode<T> find( INode<T> node, T value ) {
		   if(isExternal(node)) { 
			   //Return the node if it is external.
			   return node;
		   }else {
			   	   //Compare the element of the node with 'value'.
			   if (value.compareTo(node.element())<0) { 
				   //If the value is less than the node's element, recursively call this method to search the left sub-tree.
				   return find(left(node), value);
			   }else if (value.compareTo(node.element())>0) {
				   //If the value is greater than the node's element, recursively call this method to search the right sub-tree.				  
				   return find(right(node), value);
			   }else { 
				   //If the value is equal to the node's element, we have found it! Return this node.
				   return node;
			   }
		   }
	   }

   private BTNode rightRotation(INode<T> x) {
	   BTNode y = (BTNode) parent(x);
	   BTNode z = (BTNode) parent(y);
	   BTNode a = (BTNode) x;
	   BTNode b = (BTNode) y;
	   BTNode c = (BTNode) z;

	   BTNode T0 = (BTNode) left(x);
	   BTNode T1 = (BTNode) right(x);
	   BTNode T2 = (BTNode) right(y);
	   BTNode T3 = (BTNode) right(z);
	   a.left=T0;
	   if(T0!=root) {
		   T0.parent=a;
	   }
	   a.right=T1;
	   if(T1!=root) {
		   T1.parent=a;
	   }
	   updateHeight(a);
	   c.left=T2;
	   if(T2!=root) {
		   T2.parent=c;
	   }
	   c.right=T1;
	   if(T3!=root) {
		   T3.parent=c;
	   }
	   updateHeight(c);
	   b.left=a;
	   a.parent=b;
	   b.right=c;
	   c.parent=b;

	   updateHeight(b);
	   return b;
	
   }
   private BTNode leftRotation(INode<T> x) {
	   BTNode y = (BTNode) parent(x);
	   BTNode z = (BTNode) parent(y);
	   BTNode a = (BTNode) z;
	   BTNode b = (BTNode) y;
	   BTNode c = (BTNode) x;

	   BTNode T0 = (BTNode) left(z);
	   BTNode T1 = (BTNode) left(y);
	   BTNode T2 = (BTNode) left(x);
	   BTNode T3 = (BTNode) right(x);
	   a.left=T0;
	   if(T0!=root) {
		   T0.parent=a;
	   }
	   a.right=T1;
	   if(T1!=root) {
		   T1.parent=a;
	   }
	   updateHeight(a);
	   c.left=T2;
	   if(T2!=root) {
		   T2.parent=c;
	   }
	   c.right=T1;
	   if(T3!=root) {
		   T3.parent=c;
	   }
	   updateHeight(c);
	   b.left=a;
	   a.parent=b;
	   b.right=c;
	   c.parent=b;

	   updateHeight(b);
	   return b;
   }
 
   private BTNode doubleRotation1(INode<T> x) {//zig-zag
	   BTNode y = (BTNode) parent(x);
	   BTNode z = (BTNode) parent(y);
	   BTNode a = (BTNode) y;
	   BTNode b = (BTNode) x;
	   BTNode c = (BTNode) z;

	   BTNode T0 = (BTNode) left(y);
	   BTNode T1 = (BTNode) left(x);
	   BTNode T2 = (BTNode) right(x);
	   BTNode T3 = (BTNode) right(z);
	   a.left=T0;
	   if(T0!=root) {
		   T0.parent=a;
	   }
	   a.right=T1;
	   if(T1!=root) {
		   T1.parent=a;
	   }
	   updateHeight(a);
	   c.left=T2;
	   if(T2!=root) {
		   T2.parent=c;
	   }
	   c.right=T1;
	   if(T3!=root) {
		   T3.parent=c;
	   }
	   updateHeight(c);
	   b.left=a;
	   a.parent=b;
	   b.right=c;
	   c.parent=b;

	   updateHeight(b);
	   return b;		
	}
   private BTNode doubleRotation2(INode<T> x) {//zag-zig
	   BTNode y = (BTNode) parent(x);
	   BTNode z = (BTNode) parent(y);
	   BTNode a = (BTNode) z;
	   BTNode b = (BTNode) x;
	   BTNode c = (BTNode) y;

	   BTNode T0 = (BTNode) left(z);
	   BTNode T1 = (BTNode) left(x);
	   BTNode T2 = (BTNode) right(x);
	   BTNode T3 = (BTNode) right(y);
	   a.left=T0;
	   if(T0!=root) {
		   T0.parent=a;
	   }
	   a.right=T1;
	   if(T1!=root) {
		   T1.parent=a;
	   }
	   updateHeight(a);
	   c.left=T2;
	   if(T2!=root) {
		   T2.parent=c;
	   }
	   c.right=T1;
	   if(T3!=root) {
		   T3.parent=c;
	   }
	   updateHeight(c);
	   b.left=a;
	   a.parent=b;
	   b.right=c;
	   c.parent=b;

	   updateHeight(b);
	   return b;
		
	   }
   public int height(INode<T> node) {
	 return height;
   }
   public boolean isBalanced(INode<T> node) {
	   if (height(left(node))>height(right(node))) {
		   if ((height(left(node))-height(right(node)))<2) {
			   return true;
		   }else {
			   return false;
		   }
	   }else {
			if ((height(right(node))-height(left(node)))<2) {
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
   
   private void updateHeight(BTNode node) {
		if(node==null) {		
			height= 0;
		}else {
			height=1+Math.max(height(left(node)),height(right(node)));
		}
	}

   private INode<T> tallerChild(INode<T> x) {
		if(height(left(x))>height(right(x))) { 
			return left(x);
		
		}else if(height(right(x))>height(left(x))) {
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
			   for(BTNode p = xINode.parent;p!=root;p=p.parent) {
					if (!isBalanced(p)) {//Once the node is unbalanced, restructure it
						   restructure(tallerChild(tallerChild(p)));
						   break;
					}
					updateHeight(p);//update the height of the node 
					//note even if the tree is balance, the height of p is possible changed
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
	   				updateHeight(p);			
	   			} 
	   		}
	   
	   }
   }

   private void restructure( INode<T> x ) {
	   BTNode y=(BTNode) parent(x); 
	   BTNode z= (BTNode) parent(y);
	   if(y.equals(left(z))) {
		   if (x.equals(left(y))) {
			rightRotation(x);
		   }else {
			doubleRotation1(x);
		   }
	  }else {
		  if(x.equals(right(y))) {
			  leftRotation(x);
		  }else {
			  doubleRotation2(x);
		  }
	  }
		
	}
}




