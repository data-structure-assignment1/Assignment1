package dsa.impl;

import dsa.iface.INode;
import dsa.impl.AbstractBinaryTree.BTNode;

public class SplayTree<T extends Comparable<T>> extends BinarySearchTree<T> {

	
	
	
	public int height=0;
	private void AttachAsRChild(INode<T> node,INode<T> rChild) {//set rChild as the rightchild of node
		
		((BTNode) node).right=(BTNode) rChild;
		((BTNode) rChild).parent=(BTNode) node;
	}
	private void AttachAsLChild(INode<T> node,INode<T> lChild) {//set lChild as the leftchild of node
		
		((BTNode) node).right=(BTNode) lChild;
		((BTNode) lChild).parent=(BTNode) node;
	}


	private void splay( INode<T> n ) {
		BTNode x=(BTNode) n;
		INode<T> y = null;
		INode<T> z;
		if (n!=root) {
			
			while(parent(n) != null&&parent(parent(n))!= null) {
				 y=parent(x);
				 z=parent(y);
				INode<T> gg=parent(z);
				   if(y.equals(left(z))) {
					   if (x.equals(left(y))) {
						AttachAsLChild(z, right(y));
						AttachAsLChild(y, right(x));
						AttachAsRChild(y, z);
						AttachAsRChild(x, y);
						

					   }else {
						AttachAsLChild(y, right(x));
						AttachAsRChild(z, left(x));
						AttachAsLChild(x, z);
						AttachAsRChild(x, y);

					   }
				  }else {
					  if(x.equals(right(y))) {
						AttachAsRChild(z, left(y));
						AttachAsRChild(y, left(x));
						AttachAsLChild(y, z);
						AttachAsLChild(x, y);


					  }else {
						AttachAsRChild(y, left(x));
						AttachAsLChild(z, right(x));
						AttachAsRChild(x, z);
						AttachAsLChild(x, y);

					  }
				  }
				   if(gg==null) {
					   root=x;
				   }else {
					   if(right(gg).equals(z)){
						   AttachAsRChild(gg, x);
					   }else {
						   AttachAsLChild(gg, x);
					   }
				   }
			}
			if(x.parent!=null) {
				if (x.equals(right(x.parent))) {
					AttachAsRChild(y, left(x));
					AttachAsLChild(x, y);
				}else {
					AttachAsLChild(y, right(x));
					AttachAsRChild(x, y);
				}
			}
		}
		x.parent=null;
	}
	public void insert( T value ) {
		   BTNode xINode= (BTNode)find(root, value);
		   if (isExternal(xINode)) {//Verify that the destination node does not exist
			   if(xINode==root) {
				   root.element=value;
				   root.right=newNode(null, root);
				   root.left=newNode(null, root);
				   size=3;
			   }else {
				   replace(xINode, value);
				   xINode.left=newNode(null, xINode);
				   xINode.right=newNode(null, xINode); //Expand an external node create two external child size increase by2				
				   size+=2;//it is possible that the height of parent changed the grandparent may be unbalanced			  
				}
			   splay(find(root, value));
		}
		
		
		
	}
	public INode<T> find( INode<T> node, T value ) {
		   if(isExternal(node)) { 
			   
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
				  
				   return node;
			   }
		   }
	   }
    public boolean contains( T value ) {
    	   if (isInternal(find(root, value))) {
    		   splay(find(root, value));
    		   return true;
    	   }else {
    		   splay(find(root, value));
    		   return false;
    	   }
   }

   public void remove( T value ) {
	   BTNode node= (BTNode)find(root, value);

	   if (root==null||isExternal(node)) {
		   
	   }else {
			BTNode t = root;
			if (root.right.element==null) {
				root=root.right;
				root.parent=null;
			}else if (root.left.element==null) {
				root=root.left;
				root.parent=null;
			}else {
				BTNode leftSubTree=root.left;
				leftSubTree.parent=null;
				root.left=null;
				root=root.right;
				root.parent=null;
				find(root, value);
				root.left=leftSubTree;
				leftSubTree.parent=root;
				
			}
			t.element=null;
			t=null;
			size--;
	   }
	}
}
