package com.pdf.app.testapp;

import java.util.Arrays;
import java.util.List;

import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;

public class ComaprasionFunction {

	public static void main(String[] args) {

		DiffRowGenerator generator = DiffRowGenerator.create().showInlineDiffs(true).mergeOriginalRevised(true)
				.inlineDiffByWord(true).oldTag(f -> "~").newTag(f -> "**").build();
		List<DiffRow> rows = generator.generateDiffRows(Arrays.asList("This is a test senctence."),
				Arrays.asList("This is a test for diffutils."));

		System.out.println(rows.get(0).getOldLine());

		System.out.println("************************************************");
		DiffRowGenerator generator1 = DiffRowGenerator.create().showInlineDiffs(true).inlineDiffByWord(true)
				.oldTag(f -> "~").newTag(f -> "**").build();
		List<DiffRow> rows1 = generator1.generateDiffRows(
				Arrays.asList("This is a test senctence.", "This is the second line.", "And here is the finish."),
				Arrays.asList("This is a test for diffutils.", "This is the second line."));

		System.out.println("|original|new|");
		System.out.println("|--------|---|");
		for (DiffRow row : rows1) {
			System.out.println("|" + row.getOldLine() + "|" + row.getNewLine() + "|");
		}

	}

}
