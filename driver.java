/*import java.util.*;

public class driver {

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *


	Important: this Driver is contingent on your toString already working. If your toString is broken, you may recieve confusing results.


	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
/*
	private static String message(String input, String desired, String output) {
		return String.format("\n%s\n\n\tDesired output:   %s\n\tYour output:      %s", input, desired, output);
	}

	private static String message(String input, Integer desired, Integer output) {
		return message(input, Integer.toString(desired), Integer.toString(output));
	}

	private static boolean edge_check(Deque<Integer> a, MyDeque<Integer> b) {
		if (a.size() == b.size() && a.size() == 0) return true;
		return a.getFirst().equals(b.getFirst()) && a.getLast().equals(b.getLast());
	}

	private static String gist_string(Deque<Integer> a) {
		String full = a.toString();
		if (full.length() < 45) return full;
		return full.substring(0, 15).trim()+" ... "+full.substring(full.length()-15, full.length()).trim();
	}

	private static String gist_string(MyDeque<Integer> a) {
		String full;
		try {full = a.toString();}
		catch (Exception e) {return "toString threw: "+e;}

		if (full.length() < 30) return full;
		return full.substring(0, 10).trim()+" ... "+full.substring(full.length()-10, full.length()).trim();
	}

	public static void main(String[] args) {
		ArrayList<String> out = new ArrayList<>();
		MyDeque<Integer> deque = new MyDeque<>();

		// empty array

		if (!deque.toString().equals("{}"))
			out.add(message("{}.toString()", "\"{}\"", "\""+deque.toString()+"\""));

		if (deque.size() != 0)
			out.add(message("{}.size()", 0, deque.size()));

		// exception testing

		try {
			deque.getFirst();
			out.add(message("{}.getFirst()", "NoSuchElementException", Integer.toString(deque.getFirst())));
		} catch (NoSuchElementException e) {}
		catch (Exception e) {
			out.add(message("{}.getFirst()", "NoSuchElementException", e.getClass().getSimpleName()));
		}

		try {
			deque.getLast();
			out.add(message("{}.getLast()", "NoSuchElementException", Integer.toString(deque.getLast())));
		} catch (NoSuchElementException e) {}
		catch (Exception e) {
			out.add(message("{}.getLast()", "NoSuchElementException", e.getClass().getSimpleName()));
		}

		try {
			deque.removeFirst();
			out.add(message("{}.removeFirst()", "NoSuchElementException", Integer.toString(deque.removeFirst())));
		} catch (NoSuchElementException e) {}
		catch (Exception e) {
			out.add(message("{}.removeFirst()", "NoSuchElementException", e.getClass().getSimpleName()));
		}

		try {
			deque.removeLast();
			out.add(message("{}.removeLast()", "NoSuchElementException", Integer.toString(deque.removeLast())));
		} catch (NoSuchElementException e) {}
		catch (Exception e) {
			out.add(message("{}.removeLast()", "NoSuchElementException", e.getClass().getSimpleName()));
		}

		try {
			deque.addFirst(null);
			out.add(message("{}.addFirst(null)", "NullPointerException", "you didn't throw anything"));
		} catch (NullPointerException e) {}
		catch (Exception e) {
			out.add(message("{}.addFirst(null)", "NullPointerException", e.getClass().getSimpleName()));
		}

		try {
			deque.addLast(null);
			out.add(message("{}.addLast(null)", "NullPointerException", "you didn't throw anything"));
		} catch (NullPointerException e) {}
		catch (Exception e) {
			out.add(message("{}.addLast(null)", "NullPointerException", e.getClass().getSimpleName()));
		}

		// adding forward past capacity

		Deque<Integer> comp = new ArrayDeque<>();

		for (int i = 0; i < 1000; i++) {
			String old = gist_string(deque);
			try {
				deque.addLast(i);
				comp.addLast(i);
				if (!edge_check(comp, deque)) {
					out.add(message(old+".addLast("+i+")", gist_string(comp), gist_string(deque)));
					break;
				}
				if (comp.size() != deque.size()) {
					out.add(message(old+".addLast("+i+")\n"+gist_string(deque)+".size()", comp.size(), deque.size()));
					break;
				}
			} catch (Exception e) {
				out.add(message(old+".addLast("+i+")", gist_string(comp), e.toString()));
				break;
			}
		}

		// removing from end without wrap

		for (int i = 0; i < 1000; i++) {
			String old = gist_string(deque);
			try {
				deque.removeLast();
				comp.removeLast();
				if (!edge_check(comp, deque)) {
					out.add(message(old+".removeLast()", gist_string(comp), gist_string(deque)));
					break;
				}
				if (comp.size() != deque.size()) {
					out.add(message(old+".removeLast()\n"+gist_string(deque)+".size()", comp.size(), deque.size()));
					break;
				}
			} catch (Exception e) {
				out.add(message(old+".removeLast()", gist_string(comp), e.toString()));
				break;
			}
		}

		// adding backward past capacity

		deque = new MyDeque<>();
		comp = new ArrayDeque<>();

		for (int i = 0; i < 1000; i++) {
			String old = gist_string(deque);
			try {
				deque.addFirst(i);
				comp.addFirst(i);
				if (!edge_check(comp, deque)) {
					out.add(message(old+".addFirst("+i+")", gist_string(comp), gist_string(deque)));
					break;
				}
				if (comp.size() != deque.size()) {
					out.add(message(old+".addFirst("+i+")\n"+gist_string(deque)+".size()", comp.size(), deque.size()));
					break;
				}
			} catch (Exception e) {
				out.add(message(old+".addFirst("+i+")", gist_string(comp), e.toString()));
				break;
			}
		}

		// removing from front without wrap

		for (int i = 0; i < 1000; i++) {
			String old = gist_string(deque);
			try {
				deque.removeFirst();
				comp.removeFirst();
				if (!edge_check(comp, deque)) {
					out.add(message(old+".removeFirst()", gist_string(comp), gist_string(deque)));
					break;
				}
				if (comp.size() != deque.size()) {
					out.add(message(old+".removeFirst()\n"+gist_string(deque)+".size()", comp.size(), deque.size()));
					break;
				}
			} catch (Exception e) {
				out.add(message(old+".removeFirst()", gist_string(comp), e.toString()));
				break;
			}
		}

		// stepping forwards -- this should place us somewhere in the middle of the data array

		deque = new MyDeque<>();
		comp = new ArrayDeque<>();

		for (int i = 0; i < 500; i++) {
			String old = gist_string(deque);
			try {
				deque.addLast(i);
				comp.addLast(i);
				if (!edge_check(comp, deque)) {
					out.add(message(old+".addLast("+i+")", gist_string(comp), gist_string(deque)));
					break;
				}
				if (comp.size() != deque.size()) {
					out.add(message(old+".addLast("+i+")\n"+gist_string(deque)+".size()", comp.size(), deque.size()));
					break;
				}
			} catch (Exception e) {
				out.add(message(old+".addLast("+i+")", gist_string(comp), e.toString()));
				break;
			}
			old = gist_string(deque);
			try {
				deque.removeFirst();
				comp.removeFirst();
				if (!edge_check(comp, deque)) {
					out.add(message(old+".removeFirst()", gist_string(comp), gist_string(deque)));
					break;
				}
				if (comp.size() != deque.size()) {
					out.add(message(old+".removeFirst()\n"+gist_string(deque)+".size()", comp.size(), deque.size()));
					break;
				}
			} catch (Exception e) {
				out.add(message(old+".removeFirst()", gist_string(comp), e.toString()));
				break;
			}
		}

		// adding forward to meet at middle

		for (int i = 0; i < 1000; i++) {
			String old = gist_string(deque);
			try {
				deque.addLast(i);
				comp.addLast(i);
				if (!edge_check(comp, deque)) {
					out.add(message(old+".addLast("+i+")", gist_string(comp), gist_string(deque)));
					break;
				}
				if (comp.size() != deque.size()) {
					out.add(message(old+".addLast("+i+")\n"+gist_string(deque)+".size()", comp.size(), deque.size()));
					break;
				}
			} catch (Exception e) {
				out.add(message(old+".addLast("+i+")", gist_string(comp), e.toString()));
				break;
			}
		}

		// removing from end with wrap

		for (int i = 0; i < 1000; i++) {
			String old = gist_string(deque);
			try {
				deque.removeLast();
				comp.removeLast();
				if (!edge_check(comp, deque)) {
					out.add(message(old+".removeLast()", gist_string(comp), gist_string(deque)));
					break;
				}
				if (comp.size() != deque.size()) {
					out.add(message(old+".removeLast()\n"+gist_string(deque)+".size()", comp.size(), deque.size()));
					break;
				}
			} catch (Exception e) {
				out.add(message(old+".removeLast()", gist_string(comp), e.toString()));
				break;
			}
		}

		// stepping backwards -- this should place us somewhere in the middle of the data array

		deque = new MyDeque<>();
		comp = new ArrayDeque<>();

		for (int i = 0; i < 500; i++) {
			String old = gist_string(deque);
			try {
				deque.addFirst(i);
				comp.addFirst(i);
				if (!edge_check(comp, deque)) {
					out.add(message(old+".addFirst("+i+")", gist_string(comp), gist_string(deque)));
					break;
				}
				if (comp.size() != deque.size()) {
					out.add(message(old+".addFirst("+i+")\n"+gist_string(deque)+".size()", comp.size(), deque.size()));
					break;
				}
			} catch (Exception e) {
				out.add(message(old+".addFirst("+i+")", gist_string(comp), e.toString()));
				break;
			}
			old = gist_string(deque);
			try {
				deque.removeLast();
				comp.removeLast();
				if (!edge_check(comp, deque)) {
					out.add(message(old+".removeLast()", gist_string(comp), gist_string(deque)));
					break;
				}
				if (comp.size() != deque.size()) {
					out.add(message(old+".removeLast()\n"+gist_string(deque)+".size()", comp.size(), deque.size()));
					break;
				}
			} catch (Exception e) {
				out.add(message(old+".removeLast()", gist_string(comp), e.toString()));
				break;
			}
		}

		// adding backward to meet at middle

		for (int i = 0; i < 1000; i++) {
			String old = gist_string(deque);
			try {
				deque.addFirst(i);
				comp.addFirst(i);
				if (!edge_check(comp, deque)) {
					out.add(message(old+".addFirst("+i+")", gist_string(comp), gist_string(deque)));
					break;
				}
				if (comp.size() != deque.size()) {
					out.add(message(old+".addFirst("+i+")\n"+gist_string(deque)+".size()", comp.size(), deque.size()));
					break;
				}
			} catch (Exception e) {
				out.add(message(old+".addFirst("+i+")", gist_string(comp), e.toString()));
				break;
			}
		}

		// removing from front with wrap

		for (int i = 0; i < 1000; i++) {
			String old = gist_string(deque);
			try {
				deque.removeFirst();
				comp.removeFirst();
				if (!edge_check(comp, deque)) {
					out.add(message(old+".removeFirst()", gist_string(comp), gist_string(deque)));
					break;
				}
				if (comp.size() != deque.size()) {
					out.add(message(old+".removeFirst()\n"+gist_string(deque)+".size()", comp.size(), deque.size()));
					break;
				}
			} catch (Exception e) {
				out.add(message(old+".removeFirst()", gist_string(comp), e.toString()));
				break;
			}
		}

		// summary

		if (out.size() == 0) {
			System.out.println("You passed every test.");
		} else {
			System.out.printf("You had %d error(s). Summary:\n", out.size());
			for (String s : out) System.out.println(s);
		}
	}

}
*/


/*
import java.util.*;

public class driver {

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *


	Important: this Driver is contingent on your toString already working. If your toString is broken, you may recieve confusing results.


	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
/*
	private static String message(String input, String desired, String output) {
		return String.format("\n%s\n\n\tDesired output:   %s\n\tYour output:      %s", input, desired, output);
	}

	private static String message(String input, Integer desired, Integer output) {
		return message(input, Integer.toString(desired), Integer.toString(output));
	}

	private static boolean edge_check(Deque<Integer> a, MyDeque<Integer> b) {
		if (a.size() == b.size() && a.size() == 0) return true;
		return a.getFirst().equals(b.getFirst()) && a.getLast().equals(b.getLast());
	}

	private static String gist_string(Deque<Integer> a) {
		String full = a.toString();
		if (full.length() < 45) return full;
		return full.substring(0, 15).trim()+" ... "+full.substring(full.length()-15, full.length()).trim();
	}

	private static String gist_string(MyDeque<Integer> a) {
		String full;
		try {full = a.toString();}
		catch (Exception e) {return "toString threw: "+e;}

		if (full.length() < 30) return full;
		return full.substring(0, 10).trim()+" ... "+full.substring(full.length()-10, full.length()).trim();
	}

	public static void main(String[] args) {
    MyDeque<Integer> test = new MyDeque<>(5);
    test.addFirst(2);
    System.out.println("test is = " + test);
    test.addFirst(3);
    System.out.println("test is = " + test);
    test.addFirst(4);
    System.out.println("test is = " + test);
    test.addFirst(5);
    System.out.println("test is = " + test);
    test.addFirst(6);
    System.out.println("test is = " + test);
    test.addFirst(7);
    System.out.println("test is = " + test);
		ArrayList<String> out = new ArrayList<>();
		MyDeque<Integer> deque = new MyDeque<>();

		System.out.println("empty array");

		if (!deque.toString().equals("{}"))
    System.out.println(message("{}.toString()", "\"{}\"", "\""+deque.toString()+"\""));

		if (deque.size() != 0)
			System.out.println(message("{}.size()", 0, deque.size()));

		System.out.println("exception testing");

		try {
			deque.getFirst();
			System.out.println(message("{}.getFirst()", "NoSuchElementException", Integer.toString(deque.getFirst())));
		} catch (NoSuchElementException e) {}
		catch (Exception e) {
			System.out.println(message("{}.getFirst()", "NoSuchElementException", e.getClass().getSimpleName()));
		}

		try {
			deque.getLast();
			System.out.println(message("{}.getLast()", "NoSuchElementException", Integer.toString(deque.getLast())));
		} catch (NoSuchElementException e) {}
		catch (Exception e) {
			System.out.println(message("{}.getLast()", "NoSuchElementException", e.getClass().getSimpleName()));
		}

		try {
			deque.removeFirst();
			System.out.println(message("{}.removeFirst()", "NoSuchElementException", Integer.toString(deque.removeFirst())));
		} catch (NoSuchElementException e) {}
		catch (Exception e) {
			System.out.println(message("{}.removeFirst()", "NoSuchElementException", e.getClass().getSimpleName()));
		}

		try {
			deque.removeLast();
			System.out.println(message("{}.removeLast()", "NoSuchElementException", Integer.toString(deque.removeLast())));
		} catch (NoSuchElementException e) {}
		catch (Exception e) {
			System.out.println(message("{}.removeLast()", "NoSuchElementException", e.getClass().getSimpleName()));
		}

		try {
			deque.addFirst(null);
			System.out.println(message("{}.addFirst(null)", "NullPointerException", "you didn't throw anything"));
		} catch (NullPointerException e) {}
		catch (Exception e) {
			System.out.println(message("{}.addFirst(null)", "NullPointerException", e.getClass().getSimpleName()));
		}

		try {
			deque.addLast(null);
			System.out.println(message("{}.addLast(null)", "NullPointerException", "you didn't throw anything"));
		} catch (NullPointerException e) {}
		catch (Exception e) {
			System.out.println(message("{}.addLast(null)", "NullPointerException", e.getClass().getSimpleName()));
		}

		System.out.println("adding forward past capacity");

		Deque<Integer> comp = new ArrayDeque<>();

		for (int i = 0; i < 1000; i++) {
			String old = gist_string(deque);
			try {
				deque.addLast(i);
				comp.addLast(i);
				if (!edge_check(comp, deque)) {
					System.out.println(message(old+".addLast("+i+")", gist_string(comp), gist_string(deque)));
					break;
				}
				if (comp.size() != deque.size()) {
					System.out.println(message(old+".addLast("+i+")\n"+gist_string(deque)+".size()", comp.size(), deque.size()));
					break;
				}
			} catch (Exception e) {
				System.out.println(message(old+".addLast("+i+")", gist_string(comp), e.toString()));
				break;
			}
		}

		System.out.println("removing from end without wrap");

		for (int i = 0; i < 1000; i++) {
			String old = gist_string(deque);
			try {
				deque.removeLast();
				comp.removeLast();
				if (!edge_check(comp, deque)) {
					System.out.println(message(old+".removeLast()", gist_string(comp), gist_string(deque)));
					break;
				}
				if (comp.size() != deque.size()) {
					System.out.println(message(old+".removeLast()\n"+gist_string(deque)+".size()", comp.size(), deque.size()));
					break;
				}
			} catch (Exception e) {
				System.out.println(message(old+".removeLast()", gist_string(comp), e.toString()));
				break;
			}
		}

		System.out.println("adding backward past capacity");

		deque = new MyDeque<>();
		comp = new ArrayDeque<>();

		for (int i = 0; i < 1000; i++) {
			String old = gist_string(deque);
			try {
				deque.addFirst(i);
				comp.addFirst(i);
				if (!edge_check(comp, deque)) {
					System.out.println(message(old+".addFirst("+i+")", gist_string(comp), gist_string(deque)));
					break;
				}
				if (comp.size() != deque.size()) {
					System.out.println(message(old+".addFirst("+i+")\n"+gist_string(deque)+".size()", comp.size(), deque.size()));
					break;
				}
			} catch (Exception e) {
				System.out.println(message(old+".addFirst("+i+")", gist_string(comp), e.toString()));
				break;
			}
		}

		System.out.println("removing from front without wrap");

		for (int i = 0; i < 1000; i++) {
			String old = gist_string(deque);
			try {
				deque.removeFirst();
				comp.removeFirst();
				if (!edge_check(comp, deque)) {
					System.out.println(message(old+".removeFirst()", gist_string(comp), gist_string(deque)));
					break;
				}
				if (comp.size() != deque.size()) {
					System.out.println(message(old+".removeFirst()\n"+gist_string(deque)+".size()", comp.size(), deque.size()));
					break;
				}
			} catch (Exception e) {
				System.out.println(message(old+".removeFirst()", gist_string(comp), e.toString()));
				break;
			}
		}

		System.out.println("stepping forwards -- this should place us somewhere in the middle of the data array");

		deque = new MyDeque<>();
		comp = new ArrayDeque<>();

		for (int i = 0; i < 500; i++) {
			String old = gist_string(deque);
			try {
				deque.addLast(i);
				comp.addLast(i);
				if (!edge_check(comp, deque)) {
					System.out.println(message(old+".addLast("+i+")", gist_string(comp), gist_string(deque)));
					break;
				}
				if (comp.size() != deque.size()) {
					System.out.println(message(old+".addLast("+i+")\n"+gist_string(deque)+".size()", comp.size(), deque.size()));
					break;
				}
			} catch (Exception e) {
				System.out.println(message(old+".addLast("+i+")", gist_string(comp), e.toString()));
				break;
			}
			old = gist_string(deque);
			try {
				deque.removeFirst();
				comp.removeFirst();
				if (!edge_check(comp, deque)) {
					System.out.println(message(old+".removeFirst()", gist_string(comp), gist_string(deque)));
					break;
				}
				if (comp.size() != deque.size()) {
					System.out.println(message(old+".removeFirst()\n"+gist_string(deque)+".size()", comp.size(), deque.size()));
					break;
				}
			} catch (Exception e) {
				System.out.println(message(old+".removeFirst()", gist_string(comp), e.toString()));
				break;
			}
		}

		System.out.println("adding forward to meet at middle");

		for (int i = 0; i < 1000; i++) {
			String old = gist_string(deque);
			try {
				deque.addLast(i);
				comp.addLast(i);
				if (!edge_check(comp, deque)) {
					System.out.println(message(old+".addLast("+i+")", gist_string(comp), gist_string(deque)));
					break;
				}
				if (comp.size() != deque.size()) {
					System.out.println(message(old+".addLast("+i+")\n"+gist_string(deque)+".size()", comp.size(), deque.size()));
					break;
				}
			} catch (Exception e) {
				System.out.println(message(old+".addLast("+i+")", gist_string(comp), e.toString()));
				break;
			}
		}

		System.out.println("removing from end with wrap");

		for (int i = 0; i < 1000; i++) {
			String old = gist_string(deque);
			try {
				deque.removeLast();
				comp.removeLast();
				if (!edge_check(comp, deque)) {
					System.out.println(message(old+".removeLast()", gist_string(comp), gist_string(deque)));
					break;
				}
				if (comp.size() != deque.size()) {
					System.out.println(message(old+".removeLast()\n"+gist_string(deque)+".size()", comp.size(), deque.size()));
					break;
				}
			} catch (Exception e) {
				System.out.println(message(old+".removeLast()", gist_string(comp), e.toString()));
				break;
			}
		}

		System.out.println("stepping backwards -- this should place us somewhere in the middle of the data array");

		deque = new MyDeque<>();
		comp = new ArrayDeque<>();

		for (int i = 0; i < 500; i++) {
			String old = gist_string(deque);
			try {
				deque.addFirst(i);
				comp.addFirst(i);
				if (!edge_check(comp, deque)) {
					System.out.println(message(old+".addFirst("+i+")", gist_string(comp), gist_string(deque)));
					break;
				}
				if (comp.size() != deque.size()) {
					System.out.println(message(old+".addFirst("+i+")\n"+gist_string(deque)+".size()", comp.size(), deque.size()));
					break;
				}
			} catch (Exception e) {
				System.out.println(message(old+".addFirst("+i+")", gist_string(comp), e.toString()));
				break;
			}
			old = gist_string(deque);
			try {
				deque.removeLast();
				comp.removeLast();
				if (!edge_check(comp, deque)) {
					System.out.println(message(old+".removeLast()", gist_string(comp), gist_string(deque)));
					break;
				}
				if (comp.size() != deque.size()) {
					System.out.println(message(old+".removeLast()\n"+gist_string(deque)+".size()", comp.size(), deque.size()));
					break;
				}
			} catch (Exception e) {
				System.out.println(message(old+".removeLast()", gist_string(comp), e.toString()));
				break;
			}
		}

		System.out.println("adding backward to meet at middle");

		for (int i = 0; i < 1000; i++) {
			String old = gist_string(deque);
			try {
				deque.addFirst(i);
				comp.addFirst(i);
				if (!edge_check(comp, deque)) {
					System.out.println(message(old+".addFirst("+i+")", gist_string(comp), gist_string(deque)));
					break;
				}
				if (comp.size() != deque.size()) {
					System.out.println(message(old+".addFirst("+i+")\n"+gist_string(deque)+".size()", comp.size(), deque.size()));
					break;
				}
			} catch (Exception e) {
				System.out.println(message(old+".addFirst("+i+")", gist_string(comp), e.toString()));
				break;
			}
		}

		System.out.println("removing from front with wrap");

		for (int i = 0; i < 1000; i++) {
			String old = gist_string(deque);
			try {
				deque.removeFirst();
				comp.removeFirst();
				if (!edge_check(comp, deque)) {
					System.out.println(message(old+".removeFirst()", gist_string(comp), gist_string(deque)));
					break;
				}
				if (comp.size() != deque.size()) {
					System.out.println(message(old+".removeFirst()\n"+gist_string(deque)+".size()", comp.size(), deque.size()));
					break;
				}
			} catch (Exception e) {
				System.out.println(message(old+".removeFirst()", gist_string(comp), e.toString()));
				break;
			}
		}
    System.out.println("call me AWESOME!!!!");
	}
}
*/


/*import java.util.*;

public class driver {
  public static void main(String[]args){
    /*MyDeque test1 = new MyDeque();
    System.out.println("trying toString of no initialCapacity");
    System.out.println(test1);
    System.out.println();
    System.out.println("trying to add a value 1 using addFirst:");
    test1.addFirst(1);
    System.out.println(test1);

    System.out.println();
    System.out.println("trying to add a value 5 using addLast:");
    test1.addLast(5);
    System.out.println(test1);

    System.out.println();
    System.out.println("trying to add a value 3 using addLast:");
    test1.addLast(3);
    System.out.println(test1);

    System.out.println();
    System.out.println("trying to add a value 4 using addFirst:");
    test1.addFirst(4);
    System.out.println(test1);

    System.out.println();
    System.out.println("trying to getFirst which now should be 4");
    System.out.println(test1.getFirst());

    System.out.println();
    System.out.println("trying to getLast which now should be 3");
    System.out.println(test1.getLast());

    System.out.println();
    System.out.println("trying to remove a value using removeFirst, should remove 4:");
    System.out.println(test1.removeFirst());
    System.out.println(test1);

    System.out.println();
    System.out.println("trying to getFirst which now should be 1");
    System.out.println(test1.getFirst());
System.out.println();
System.out.println();
System.out.println("TEST 2:");
System.out.println();
    MyDeque test2 = new MyDeque(0);
    System.out.println("trying toString of empty deque");
    System.out.println(test2);
    System.out.println();

    int[] info = new int[]{1,3,5,6,2};
    System.out.println("add array {1,3,5,6,2} into test2, start is 1, end is 2");
    for (int i = 0; i < info.length; i++){
      test2.addLast(info[i]);
    }
    System.out.println("see test2 after adding: " + test2);
    System.out.println("start of test2 is : " + test2.getFirst() + "       end is : " + test2.getLast());
    System.out.println("removing all last value");
    for (int i = 0; i < info.length; i++){
      test2.removeLast();
    }
    System.out.println(test2);
    System.out.println("start of test2 is : " + test2.getFirst() + "       end is : " + test2.getLast());

    MyDeque deque = new MyDeque<>();
    for (int i = 0; i < 100; i++){
      deque.addFirst(i);
      System.out.println(deque);
    }
  }
}
*/

import java.util.*;

public class driver {
  public static void main(String[]args){
      Calculator test = new Calculator();
      System.out.println("testing (10 2.0 +), should be == to 12.0");
      System.out.println(test.eval("10 2.0 +"));
      System.out.println();

      System.out.println("testing eval(11 3 - 4 + 2.5 *) is 30.0");
      System.out.println(test.eval("11 3 - 4 + 2.5 *"));
      System.out.println();

      System.out.println("testing eval(8 2 + 99 9 - * 2 + 9 -) is 893.0");
      System.out.println(test.eval("8 2 + 99 9 - * 2 + 9 -"));
      System.out.println();

      System.out.println("testing eval(1 2 3 4 5 + * - -) is 26.0");
      System.out.println(test.eval("1 2 3 4 5 + * - -"));
      System.out.println();
  }
}
