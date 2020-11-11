package collectionproactise;

import java.util.HashSet;
import java.util.Set;


import otherbeans.OtherBean;

public class HashSetDuplication {
public static void main(String[] args) {
	Set<OtherBean> lastSet = new HashSet<>();
	OtherBean a = new OtherBean();
	OtherBean b = new OtherBean();
	a.setName("ram");
	b.setName("ram");
	lastSet.add(a);
	lastSet.add(b);
	System.out.println(lastSet);
	System.out.println(lastSet.contains(a));
	lastSet.remove(a);
	System.out.println(lastSet);
}
}
