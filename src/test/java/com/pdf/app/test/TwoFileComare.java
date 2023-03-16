package com.pdf.app.test;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;

public class TwoFileComare {

	@Test
	public void twoFileComapare() throws IOException {
		
		
		DiffRowGenerator generator = DiffRowGenerator.create().showInlineDiffs(true).inlineDiffByWord(true)
				.oldTag(f -> "~").newTag(f -> "**").build();

		List<String> listOne = Files.lines(new File("target/test-classes/mocks/issue15_1.txt").toPath())
				.collect(toList());

		List<String> listTwo = Files.lines(new File("target/test-classes/mocks/issue15_2.txt").toPath())
				.collect(toList());

		List<DiffRow> rows = generator.generateDiffRows(listOne, listTwo);

		for (DiffRow row : rows) {
			System.out.println("|" + row.getOldLine() + "| " + row.getNewLine() + " |");

		}
	}

}
