package com.pdf.app.testapp;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;

public class TwoPDFCompare {

	public static void main(String[] args) throws IOException {
		File orgPdf = new File("org.pdf");

		PDDocument pdDocument = PDDocument.load(orgPdf);
		PDFTextStripper pdfTextStripper = new PDFTextStripper();
		String text1 = pdfTextStripper.getText(pdDocument);

		File orgPdf1 = new File("org.pdf");

		PDDocument pdDocument1 = PDDocument.load(orgPdf1);
		PDFTextStripper pdfTextStripper1 = new PDFTextStripper();
		String text2 = pdfTextStripper1.getText(pdDocument1);

		DiffRowGenerator generator = DiffRowGenerator.create().showInlineDiffs(true).mergeOriginalRevised(true)
				.inlineDiffByWord(true).oldTag(f -> "~").newTag(f -> "**").build();
		List<DiffRow> rows = generator.generateDiffRows(Arrays.asList(text1.split("\n")),
				Arrays.asList(text2.split("\n")));

		rows.stream().filter(item -> item.getTag() != DiffRow.Tag.EQUAL).forEach(System.out::println);

	}

}
