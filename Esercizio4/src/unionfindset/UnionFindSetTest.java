package unionfindset;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;

public class UnionFindSetTest {


	@Test
	public void testMakeSet() {
		ArrayList<Integer> array = new ArrayList<>();
		for(int i = 0; i < 10; i++)
			array.add(i);
  		UnionFindSet<Integer> test = new UnionFindSet<>();
  		test.makeSet(array);
  		
	}

	@Test
	public void testUnion() {
		ArrayList<String> test = new ArrayList<>();
		for(int i = 0; i < 10; i++)
			  test.add("" + i);
		UnionFindSet<String> union = new UnionFindSet<>();
		union.makeSet(test);
		
		try {
		assertEquals("0", union.findSet("0"));
		assertEquals("1", union.findSet("1"));
		assertEquals("2", union.findSet("2"));
		assertEquals("3", union.findSet("3"));
		assertEquals("4", union.findSet("4"));
    	} 
		catch (Exception e) {
			System.err.println(e.toString());
		}
	}
	
	@Test
	public void testUnion1() {
		ArrayList<String> test = new ArrayList<>();
		for(int i = 0; i < 10; i++)
		  test.add("" + i);
		UnionFindSet<String> union = new UnionFindSet<>();
		union.makeSet(test);
		union.union("3", "2");
		
		try {
		assertEquals("2", union.findSet("2"));
		assertEquals("2", union.findSet("3"));
    	} 
		catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	@Test
	public void testUnion2() {
		ArrayList<String> test = new ArrayList<>();
		for(int i = 0; i < 10; i++)
		  test.add("" + i);
		UnionFindSet<String> union = new UnionFindSet<>();
		union.makeSet(test);	
		union.union("1", "2");
		union.union("1", "3");

		try {
		assertEquals("2", union.findSet("3"));
    	} 
		catch (Exception e) {
			System.err.println(e.toString());
		}
	}
	
	
}