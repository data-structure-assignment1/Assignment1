package dsa.impl;

import dsa.iface.INode;

public class SplayTree<T extends Comparable<T>> extends BinarySearchTree<T> {

	public int height = 0;

	public void insert(T value) {
		INode<T> n = find(root, value);
		if (isExternal(n)) {
			if (root.element() == null) {
				root.element = value;
				root.right = newNode(null, root);
				root.left = newNode(null, root);
				size = 3;
			} else {
				((BTNode) n).element = value;
				((BTNode) n).left = newNode(null, (BTNode) n);
				((BTNode) n).right = newNode(null, (BTNode) n); // Expand an external node create two external child
																// size increase by2
				size += 2;// it is possible that the height of parent changed the grandparent may be
							// unbalanced
				splay(n);
			}
		} else {
			splay(n);
		}
	}

	private void splay(INode<T> n) {
		if (n != root) {
			if (((BTNode) n).parent == root) {
				// using zig
				zig(n);
			} else {
				BTNode pp = ((BTNode) n).parent.parent;
				BTNode p = ((BTNode) n).parent;
				BTNode ppp = ((BTNode) pp).parent;
				if (((BTNode) n).parent.parent != root) {
					// using zig-zig or zig-zag
					if (pp.left.left == n || pp.right.right == n) {
						zigzig(ppp, pp, p, n);
					} else {
						if (pp.right.left == n) {
							zigzag(pp, p, n, ppp);
						} else if (pp.left.right == n) {
							zigzag(pp, p, n, ppp);
						}
					}
					splay(n);
				} else {
					if (pp.left.left == n || pp.right.right == n) {
						zigzig(ppp, root, p, n);
						root = (BTNode) n;
					} else {
						if (pp.right.left == n) {
							zigzag(p, pp, n, ppp);
							root = (BTNode) n;
						} else if (root.left.right == n) {
							zigzag(pp, p, n, ppp);
							root = (BTNode) n;
						}
					}
				}
			}
		}
	}

	private INode<T> zigzag(INode<T> max, INode<T> min, INode<T> n, INode<T> ppp) {
		BTNode T2 = ((BTNode) n).left;
		BTNode T3 = ((BTNode) n).right;
		((BTNode) n).parent = (BTNode) ppp;
		((BTNode) n).right = (BTNode) max;
		((BTNode) n).left = (BTNode) min;
		((BTNode) min).parent = (BTNode) n;
		((BTNode) max).parent = (BTNode) n;
		((BTNode) max).left = T3;
		T3.parent = (BTNode) max;
		((BTNode) min).right = T2;
		T2.parent = (BTNode) min;
		if (ppp != null) {
			if ((ppp.element()).compareTo(n.element()) > 0) {
				((BTNode) ppp).left = (BTNode) n;
			} else {
				((BTNode) ppp).right = (BTNode) n;
			}
		} else {
			root = (BTNode) n;
			((BTNode) n).parent = null;
			root.parent = null;
		}
		return n;
	}

	private INode<T> zig(INode<T> n) {
		BTNode parent = (BTNode) parent(n);
		if (left(parent) == n) {
			BTNode T2 = ((BTNode) n).right;
			((BTNode) n).right = parent;
			parent.parent = (BTNode) n;
			parent.left = T2;

		} else {
			BTNode T2 = ((BTNode) n).left;
			((BTNode) n).left = parent;
			parent.parent = (BTNode) n;
			parent.right = T2;
			T2.parent = parent;
		}
		((BTNode) n).parent = null;
		root = (BTNode) n;
		return n;
	}

	private INode<T> zigzig(BTNode ppp, BTNode pp, BTNode p, INode<T> n) {
		if (pp.right.right == n) {
			BTNode T2 = p.left;
			BTNode T3 = ((BTNode) n).left;
			((BTNode) n).left = p;
			p.parent = (BTNode) n;
			p.left = pp;
			pp.parent = p;
			p.right = T3;
			T3.parent = p;
			pp.right = T2;
			T2.parent = pp;
		} else {
			BTNode T2 = ((BTNode) n).right;
			((BTNode) n).right = p;
			p.parent = (BTNode) n;
			BTNode T3 = p.right;
			p.right = pp;
			pp.parent = p;
			p.left = T2;
			T2.parent = p;
			pp.left = T3;
			T3.parent = pp;
		}
		if (pp != root) {
			if ((ppp.element()).compareTo(n.element()) > 0) {
				((BTNode) ppp).left = (BTNode) n;
			} else {
				((BTNode) ppp).right = (BTNode) n;
			}
			((BTNode) n).parent = (BTNode) ppp;
		} else {
			((BTNode) n).parent = null;
			root = (BTNode) n;
		}
		return n;
	}

	public INode<T> find(INode<T> node, T value) {
		if (isExternal(node)) {

			return node;
		} else {
			// Compare the element of the node with 'value'.
			if (value.compareTo(node.element()) < 0) {
				// If the value is less than the node's element, recursively call this method to
				// search the left sub-tree.
				return find(left(node), value);
			} else if (value.compareTo(node.element()) > 0) {
				// If the value is greater than the node's element, recursively call this method
				// to search the right sub-tree.
				return find(right(node), value);
			} else {

				return node;
			}
		}
	}

	public boolean contains(T value) {
		if (isInternal(find(root, value))) {
			splay(find(root, value));
			return true;
		} else {
			splay(((BTNode) find(root, value)).parent);
			return false;
		}
	}

	public void remove(T value) {
		BTNode node = (BTNode) find(root, value);
		BTNode parent = node.parent;
		if (node != root) {
			if (isInternal(node)) {
				if (node.right.element == null || node.left.element == null) {
					remove(node);
				} else {
					INode<T> n = left(node);
					while (right(n) != null) {
						n = right(n);
					}
					node.element = n.element();
					remove(n);
				}
				splay(parent);
			} else {
				splay(parent);
			}
		} else {
			if (isInternal(node)) {
				if (root.right.element == null) {
					root = root.left;
				} else if (root.left.element == null) {
					root = root.right;
				} else {
					INode<T> n = left(node);
					while (right(n) != null) {
						n = right(n);
					}
					remove(n);
				}
			} else {
				root.element = value;
			}
		}
	}
}


