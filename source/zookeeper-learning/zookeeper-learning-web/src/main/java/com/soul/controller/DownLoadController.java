package com.soul.controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @author wangkun1
 * @version 2018/3/7
 */
@Controller
@RequestMapping("/erp/download")
public class DownLoadController {

    @RequestMapping(value = "/pdf.do")
    public void downloadTest(HttpServletRequest request, HttpServletResponse response) {

        try {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition",
                    "attachment; filename="+ UUID.randomUUID()+".pdf");
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            Font f = new Font(Font.FontFamily.TIMES_ROMAN, 25.0f, Font.BOLD, BaseColor.WHITE);
            Chunk c = new Chunk("White text on red background,White text on red background,White text on red background," +
                    "White text on red background,White text on red background,White text on red background,White text on red background," +
                    "White text on red background,", f);
            c.setBackground(BaseColor.RED);
            Paragraph p = new Paragraph(c);
            document.add(p);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
