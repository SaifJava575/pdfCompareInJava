package com.pdf.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.difflib.DiffUtils;
import com.github.difflib.patch.Patch;

public class DiffUtilsTest {

	public static void main(String[] args) {

		final Patch<String> patch = DiffUtils.diff(Arrays.asList("The", "dog", "is", "brown"),
				Arrays.asList("The", "fox", "is", "down"), true);
		System.out.println(patch);

		System.out.println("======================testDiff_Insert function==============================");
		final Patch<String> patch1 = DiffUtils.diff(Arrays.asList("hhh"), Arrays.asList("hhh", "jjj", "kkk"));
		System.out.println(patch1);

		System.out.println("======================testDiff_Delete()======================");
		final Patch<String> patch2 = DiffUtils.diff(Arrays.asList("ddd", "fff", "ggg"), Arrays.asList("ggg"));
		System.out.println(patch2);

		System.out.println("=====================testDiff_Change()==============================");
		final List<String> changeTest_from = Arrays.asList("aaa", "bbb", "ccc");
		final List<String> changeTest_to = Arrays.asList("aaa", "zzz", "ccc");
		final Patch<String> patch3 = DiffUtils.diff(changeTest_from, changeTest_to);
		System.out.println(patch3);

		System.out.println("===========================testDiff_EmptyList()==============");
		final Patch<String> patch4 = DiffUtils.diff(new ArrayList<>(), new ArrayList<>());
		System.out.println(patch4);

		System.out.println("======================testDiff_EmptyListWithNonEmpty()=========================");
		final Patch<String> patch5 = DiffUtils.diff(new ArrayList<>(), Arrays.asList("aaa"));
		System.out.println(patch5);

		System.out.println("====================testDiffMyersExample1()===================");
		final Patch<String> patch6 = DiffUtils.diff(Arrays.asList("A", "B", "C", "A", "B", "B", "A"),
				Arrays.asList("C", "B", "A", "B", "A", "C"));
		System.out.println(patch6);

		System.out.println("========================testDiff_Equal()===================");
		final Patch<String> patch7 = DiffUtils.diff(Arrays.asList("hhh", "jjj", "kkk"),
				Arrays.asList("hhh", "jjj", "kkk"), true);
		System.out.println(patch7);

		System.out.println("============testDiff_InsertWithEqual()==============");
		final Patch<String> patch8 = DiffUtils.diff(Arrays.asList("hhh"), Arrays.asList("hhh", "jjj", "kkk"), true);
		System.out.println(patch8);

	}

}
