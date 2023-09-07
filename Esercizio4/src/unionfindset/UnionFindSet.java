package unionfindset;

import java.util.*;

public class UnionFindSet<T> {

// Logical tree representation
	
private HashMap <T,Tree<T>> tree = new HashMap<>();

/* MakeSet operation requires the generation of a tree with two nodes,
 * does not check that the input != null and that the elements of the array != null	*/

public void makeSet(ArrayList<T> array) {
	for(int i = 0; i < array.size(); i++) {
		 T x = array.get(i);
		 Tree<T> e = new Tree<>();
		 e.setparent(x);
		 e.setrank(0);
		 tree.put(x, e);
	}
}

/* It takes, as input, two elements. And finds the representatives of their sets using the find operation, 
 * and finally puts either one of the trees (representing the set) 
 * under the root node of the other tree, effectively merging the trees and the sets. */

public void union(T x,T y) {
	link(findSet(x),findSet(y));
}

/* Link method receives the roots from the union method and sets as parent
 * Generally we transform the highest rank root in the father of the lowest rank root
 * This allows faster root search */

private void link (T x,T y) {
	Tree<T> x1= tree.get(x);
	Tree<T> y1= tree.get(y);
	if (x1.getrank()>y1.getrank())
		y1.setparent (x);
	else
		{
		 x1.setparent (y);
		 if(x1.getrank()==y1.getrank())
			y1.setrank (y1.getrank()+1);
	}

}

/* Can be implemented by recursively traversing the parent 
 * array until we hit a node who is parent of itself */

public T findSet (T x) {
	Tree<T> x1= tree.get(x);
	if (!x.equals(x1.getparent()))
		x1.setparent(findSet(x1.getparent()));
	return x1.getparent();
}

}
