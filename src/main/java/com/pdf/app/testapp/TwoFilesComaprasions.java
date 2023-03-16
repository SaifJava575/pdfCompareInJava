package com.pdf.app.testapp;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;

public class TwoFilesComaprasions {

	public static void main(String[] args) throws IOException {

		DiffRowGenerator generator = DiffRowGenerator.create().showInlineDiffs(true) // show the ~ ~ and ** ** symbols
				// on each difference
				.inlineDiffByWord(true) // show the ~ ~ and ** ** around each different word instead of each letter
// .reportLinesUnchanged(true) //experiment
				.oldTag(f -> "~").newTag(f -> "**").build();

		List<String> listOne = Files.lines(new File("templates/issue15_1.txt").toPath())
				.collect(toList());

		List<String> listTwo = Files.lines(new File("templates/issue15_2.txt").toPath())
				.collect(toList());

		List<DiffRow> rows = generator.generateDiffRows(listOne, listTwo);
		System.out.println(rows);

	}

}
