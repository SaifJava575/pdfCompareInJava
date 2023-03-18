package com.pdf.app.testapp;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;

public class TwoPDFFileCompare {

	// https://github.com/java-diff-utils/java-diff-utils this library used and
	// PDFBox maven dependency

	public static void main(String[] args) throws IOException {

		File pdf = new File("org.pdf");
		PDDocument pdDocument = PDDocument.load(pdf);
		PDFTextStripper pdfTextStripper = new PDFTextStripper();
		String text1 = pdfTextStripper.getText(pdDocument);

		File pdf1 = new File("rev.pdf");
		PDDocument pdDocument1 = PDDocument.load(pdf1);
		PDFTextStripper pdfTextStripper1 = new PDFTextStripper();
		String text2 = pdfTextStripper1.getText(pdDocument1);

		DiffRowGenerator generator = DiffRowGenerator.create().showInlineDiffs(true).inlineDiffByWord(true)
				.oldTag(f -> "~").newTag(f -> "**").build();
		List<DiffRow> rows = generator.generateDiffRows(Arrays.asList(text1), Arrays.asList(text2));
		System.out.println(rows);

		/*
		 * for (DiffRow row : rows) { System.out.println("|" + row.getOldLine() + "| " +
		 * row.getNewLine() + " |");
		 * 
		 * }
		 */

	}

}
