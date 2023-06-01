package org.example.first;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class GetQuestion {
    public static void main(String[] args) throws IOException {
        int c = 1;
        try {
            FileInputStream file = new FileInputStream("C:\\Users\\Admins\\OneDrive - ptit.edu.vn\\ATBM\\mix2-102den130.docx");
            XWPFDocument doc = new XWPFDocument(file);

            for (XWPFParagraph paragraph : doc.getParagraphs()) {
                String text = paragraph.getText();

                text = text.replaceAll("(?<![A-Za-z])\\b(\\d+)\\b", "Câu " + String.valueOf(c));

                // Clear the existing paragraph text
                for (int i = paragraph.getRuns().size() - 1; i >= 0; i--) {
                    paragraph.removeRun(i);
                }

                XWPFRun run = paragraph.createRun();
                run.setText(text);
                c++;
            }

            FileOutputStream out = new FileOutputStream("questions.docx");
            doc.write(out);
            out.close();

            file.close();

            System.out.println("DOCX manipulation complete.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}