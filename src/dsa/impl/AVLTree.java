package dsa.impl;

import dsa.iface.INode;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {
	public AVLTree() {

		this.root = newNode(null, null);
		this.size=1;
		
	}


   @Override
   public void insert( T element ) {
	   if(tree==null)
	   INode<T> node = find(root, element);
	   expandExternal(node, element);
	   restructure(node);

	   
   }

   
   public boolean contains( T element ) {
	   if (isInternal(find(root, element))) {
		   return true;
	   }else {
		   return false;
	   }
   }

   @Override
   public void remove( T element ) {
	   INode<T> node=find(root, element);
	   if (isInternal(left(node))&&isInternal(right(node))) {
		   INode<T> n=right(node);
		   while(isInternal(left(n))) {
			   n=left(n);
		   }
		 replace(node, n.element());
/*		 if(hasLeft(n)) {
			 remove(left(n));
		 }*/
		 remove(n);
	   }else {
		   remove(node);
	   }
	   restructure(x);
   }

   private void restructure( INode<T> x ) {
	   
	   if (unbalanceNode(x)==null) {
		   			
		}else {
			   INode<T> z = unbalanceNode(x);
			   INode<T> y = tallerChild(z);
			   x=tallerChild(x);
			   if (x.element().compareTo(y.element())>0&&y.element().compareTo(z.element())>0) {
				leftRotation(x);//Viola
			   }else if (x.element().compareTo(y.element())<0&&y.element().compareTo(z.element())<0) {
				rightRotation(x);//Sunny
			   }else if (x.element().compareTo(y.element())>0&&y.element().compareTo(z.element())<0) {
				doubleRotation1(x);//Viola
			   }else if (x.element().compareTo(y.element())<0&&y.element().compareTo(z.element())>0) {
				doubleRotation2(x);//Sunny
			   }
			
		}
	   
		
	}

   private void rightRotation(INode<T> x) {
	  
	
   }
   private void leftRotation(INode<T> x) {
	   INode<T> y = parent(x);
	   INode<T> z = parent(y);
	   INode<T> T0 = left(z);
	   INode<T> T1 = left(y);
	   INode<T> T2 = left(x);
	   INode<T> T3 = right(x);
	   replace(right(z), T1.element());
	   replace(left(z), T0.element());
	   replace(right(y), x.element());
	   replace(left(y), y.element());

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


private void doubleRotation1(INode<T> x) {
	   INode<T> y = parent(x);
	   INode<T> z = parent(y);
	   INode<T> T0 = left(z);
	   INode<T> T1 = left(y);
	   INode<T> T2 = left(x);
	   INode<T> T3 = right(x);
	   replace(right(z), x.element());
	   replace(right(x), y.element());
	   replace(right(y), T2.element());
	   leftRotation(y);		
	}
   private void doubleRotation2(INode<T> x) {
		// TODO Auto-generated method stub
		
	   }


public int height(INode<T> node) {
	if(node==null) {
		return 0;
	}else {
		return 1+Math.max(height(left(node)),height(right(node)));
	}
   }
   public boolean isBalanced(INode<T> node) {
		if(Math.abs(height(left(node))-height(right(node)))<=1) {
			return true;
		}else {
			return false;
		}
	}
   public INode<T> find( INode<T> node, T value ) {
	   if(isExternal(node)) {
		   return node;
	   }else {
		   if (value.compareTo(node.element())<0) {
			   return find(left(node), value);
		   }else if (value.compareTo(node.element())>0) {
			   return find(right(node), value);
		   }else {
			   return node;
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
}




