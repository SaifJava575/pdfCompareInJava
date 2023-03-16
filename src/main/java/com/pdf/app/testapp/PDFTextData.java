package com.pdf.app.testapp;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFTextData {

	public static void main(String[] args) throws Exception {
		System.out.println("Original pdf file data information...........");
		File pdf = new File("rev.pdf");

		PDDocument pdDocument = PDDocument.load(pdf);
		PDFTextStripper pdfTextStripper = new PDFTextStripper();
		String text = pdfTextStripper.getText(pdDocument);
		System.out.println(text);

		
	}

}
