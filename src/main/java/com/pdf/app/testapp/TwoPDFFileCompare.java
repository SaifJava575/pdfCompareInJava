package com.pdf.app.testapp;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;

public class TwoPDFFileCompare {

	public static void main(String[] args) throws IOException {
		
		
		List<String> listOne = Files.lines(new File("org.pdf").toPath())
				.collect(toList());
		PDDocument pdDocument = PDDocument.load((File) listOne);
		PDFTextStripper pdfTextStripper = new PDFTextStripper();
		String text1 = pdfTextStripper.getText(pdDocument);
		

		List<String> listTwo = Files.lines(new File("rev.pdf").toPath())
				.collect(toList());
		PDDocument pdDocument1 = PDDocument.load((File) listTwo);
		PDFTextStripper pdfTextStripper1 = new PDFTextStripper();
		String text2 = pdfTextStripper1.getText(pdDocument1);
		
		
		DiffRowGenerator generator = DiffRowGenerator.create().showInlineDiffs(true).inlineDiffByWord(true)
				.oldTag(f -> "~").newTag(f -> "**").build();
		List<DiffRow> rows = generator.generateDiffRows(Arrays.asList(text1), Arrays.asList(text2));

		for (DiffRow row : rows) {
			System.out.println("|" + row.getOldLine() + "| " + row.getNewLine() + " |");

		}

	}

}
