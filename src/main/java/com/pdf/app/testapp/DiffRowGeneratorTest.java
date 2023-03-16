package com.pdf.app.testapp;

import java.util.Arrays;
import java.util.List;

import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;

public class DiffRowGeneratorTest {

	private static void print(List<DiffRow> diffRows) {
		for (DiffRow row : diffRows) {
			System.out.println(row);
		}
	}

	private static List<String> split(String content) {
		return Arrays.asList(content.split("\n"));
	}

	public static void main(String[] args) {

		System.out.println("************testGenerator_IgnoreWhitespaces()********************");
		String first = "anything  \n \nother\nmore lines";
		String second = "anything\n\nother\nsome more lines";
		DiffRowGenerator generator = DiffRowGenerator.create().ignoreWhiteSpaces(true).columnWidth(Integer.MAX_VALUE)
				.build();
		List<DiffRow> rows = generator.generateDiffRows(split(first), split(second));
		print(rows);

		System.out.println("***************testGenerator_InlineDiff()************");
		String first1 = "anything \n \nother";
		String second1 = "anything\n\nother";
		DiffRowGenerator generator1 = DiffRowGenerator.create().showInlineDiffs(true).columnWidth(Integer.MAX_VALUE)
				.build();
		List<DiffRow> rows1 = generator1.generateDiffRows(split(first1), split(second1));
		print(rows1);

		System.out.println("+++++++++++++++testGeneratorWithWordWrap()+++++++++++++++++++");
		String first2 = "anything \n \nother";
		String second2 = "anything\n \nother";
		DiffRowGenerator generator2 = DiffRowGenerator.create().columnWidth(5).build();
		List<DiffRow> rows2 = generator2.generateDiffRows(split(first2), split(second2));
		print(rows2);

		System.out.println("*****************testGeneratorWithMerge()*****************");
		String first3 = "test\nanything \n \nother";
		String second3 = "anything\n\nother\ntest\ntest2";

		DiffRowGenerator generator3 = DiffRowGenerator.create().showInlineDiffs(true).mergeOriginalRevised(true)
				.build();
		List<DiffRow> rows3 = generator3.generateDiffRows(split(first3), split(second3));
		print(rows3);

		System.out.println("********************testGeneratorWithMergeByWord4()********************");
		DiffRowGenerator generator4 = DiffRowGenerator.create().showInlineDiffs(true).mergeOriginalRevised(true)
				.inlineDiffByWord(true).build();
		List<DiffRow> rows4 = generator4.generateDiffRows(Arrays.asList("Test"), Arrays.asList("ester"));
		print(rows4);

		System.out.println("*********************testGeneratorIssue22_3()******************");
		DiffRowGenerator generator5 = DiffRowGenerator.create().showInlineDiffs(true).inlineDiffByWord(true)
				.oldTag(f -> "~").newTag(f -> "**").build();
		String aa = "This is a test senctence.";
		String bb = "This is a test for diffutils.\nThis is the second line.\nAnd one more.";
		List<DiffRow> rows5 = generator5.generateDiffRows(Arrays.asList(aa.split("\n")), Arrays.asList(bb.split("\n")));
		System.out.println(rows5);

	}

}
