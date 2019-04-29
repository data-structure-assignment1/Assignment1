package dsa.impl;

import javax.lang.model.element.Element;

import dsa.iface.INode;
import dsa.impl.AbstractBinaryTree.BTNode;

public class SplayTree<T extends Comparable<T>> extends BinarySearchTree<T> {

	public int height;
	private void AttachAsRChild(INode<T> node,INode<T> rChild) {//set rChild as the rightchild of node
		BTNode n=(BTNode) node;
		BTNode r=(BTNode) rChild;

		n.right=(BTNode) rChild;
		r.parent=n;
	}
	private void AttachAsLChild(INode<T> node,INode<T> lChild) {//set lChild as the leftchild of node
		BTNode n=(BTNode) node;
		BTNode l=(BTNode) lChild;

		n.right=(BTNode) lChild;
		l.parent=n;
	}
	private void updateHeight(INode<T> y) {
			if(y==null) {		
				height= 0;
			}else {
				height=1+Math.max(height(left(y)),height(right(y)));
			}
		}
	private int height(INode<T> left) {
		return height;
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
					   x.parent=null;
				   }else {
					   if(right(gg).equals(z)){
						   AttachAsRChild(gg, x);
					   }else {
						   AttachAsLChild(gg, x);
					   }
				   }
				   updateHeight(x);
				   updateHeight(y);
				   updateHeight(z);
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
		if(root==null) {
			replace(root, value);
			root.right=newNode(null, root);
			root.left=newNode(null, root);
			size=3;
		}else {
			BTNode xINode= (BTNode)find(root, value);
			if (!isExternal(xINode)) {//Verify that the destination node does not exist
				   return;
			}else {
				size+=2;
				BTNode t = root;
				if (root.element.compareTo(value)<0) {
					root=newNode(value, null);
					t.parent=root;
					AttachAsLChild(root, t);

					AttachAsRChild(root, right(t));
				}else {
					root=newNode(value, null);
					t.parent=root;
					AttachAsRChild(root, t);
					AttachAsLChild(root, left(t));

				}
			 }
			updateHeight(root);
		}
		
		
		
	}
	public INode<T> find( INode<T> node, T value ) {
		   if(isExternal(node)) { 
			   //Return the node if it is external.
			   splay(parent(node));

			   return parent(node);
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
				   splay(node);
				   return node;
			   }
		   }
	   }
    public boolean contains( T value ) {
    	   if (isInternal(find(root, value))) {
    		   return true;
    	   }else {
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
