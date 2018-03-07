package com.soul.itext.demo;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author wangkun1
 * @version 2018/3/6
 */
public class ITextDemo {

    public static final String DEST = "./reflect/src/main/resources/pdf/demo.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ITextDemo().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Font f = new Font(Font.FontFamily.TIMES_ROMAN, 25.0f, Font.BOLD, BaseColor.WHITE);
        Chunk c = new Chunk("White text on red background,White text on red background,White text on red background," +
                "White text on red background,White text on red background,White text on red background,White text on red background," +
                "White text on red background,", f);
        c.setBackground(BaseColor.RED);
        Paragraph p = new Paragraph(c);
        document.add(p);
        document.close();
    }
}
