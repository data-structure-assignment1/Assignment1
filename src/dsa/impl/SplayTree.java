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
		if (n.equals(root)) {
			return;
		}else {
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
					AttachAsLChild(t, right(t));
				}
			 }
		}
		
		
		
	}

   public boolean contains( T value ) {
      // TODO: Implement the contains(...) method.
      return false;
   }

   public void remove( T value ) {
      // TODO: Implement the remove(...) method.
   }


}
