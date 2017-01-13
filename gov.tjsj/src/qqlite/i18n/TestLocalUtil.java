package qqlite.i18n;

import java.util.List;

public class TestLocalUtil {
	public static void main(String[] args) {
		LocalUtil lu = LocalUtil.getInstance();
		List<String> list = lu.getCities("中国", "江苏");
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
	}
}
