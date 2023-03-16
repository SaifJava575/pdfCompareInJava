package com.pdf.app.test;

import static java.util.stream.Collectors.joining;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;

public class TwoFileComparesion {

	@Test
	public void TwoFileComapre() throws IOException {
		String original = Files.lines(Paths.get("target/test-classes/mocks/issue_119_original.txt"))
				.collect(joining("\n"));
		String revised = Files.lines(Paths.get("target/test-classes/mocks/issue_119_revised.txt"))
				.collect(joining("\n"));

		DiffRowGenerator generator = DiffRowGenerator.create().showInlineDiffs(true).mergeOriginalRevised(true)
				.inlineDiffByWord(true).oldTag(f -> "~").newTag(f -> "**").build();
		List<DiffRow> rows = generator.generateDiffRows(Arrays.asList(original.split("\n")),
				Arrays.asList(revised.split("\n")));

		rows.stream().filter(item -> item.getTag() != DiffRow.Tag.EQUAL).forEach(System.out::println);
	}
		

}
