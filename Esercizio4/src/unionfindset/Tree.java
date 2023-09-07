package unionfindset;

// The data structure shall allow for the input of generic type

public class Tree<T> {
  private T parent;

// A rank tree is a data structure that stores a sequence of elements, indexed from 0 to n−1.
  private int rank;

// This function returns the Parent of the given file object.
  public T getparent() {
	    return parent;
  }
  
  public void setparent(T p) {
    this.parent = p;
  }

// This function returns the Rank of the given file object.
  public int getrank() {
	    return rank;
  }
  
  public void setrank(int r) {
    this.rank = r;
  }
 

}
