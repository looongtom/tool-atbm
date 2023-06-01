package org.example.last;

import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileInputStream;

public class GetKey {
    public static Integer Convert(String x){
        if(x.equals("A.")) return 1;
        if(x.equals("B.")) return 2;
        if(x.equals("C.")) return 3;
        if(x.equals("D.")) return 4;
return 0;
    }
    public static void main(String[] args) {
        try {
            // Load the DOCX file
            FileInputStream file = new FileInputStream("C:\\Users\\Admins\\OneDrive - ptit.edu.vn\\ATBM\\200den250.docx");
            XWPFDocument doc = new XWPFDocument(file);

            // Iterate over paragraphs
            for (XWPFParagraph paragraph : doc.getParagraphs()) {
                String line = paragraph.getText().trim();
                if (!line.isEmpty()) {
                    String[] words = line.split("\\s+");
                    String firstWord = words[0];

                    boolean isUnderlined = isRunUnderlined(paragraph.getRuns().get(0));
                    if (isUnderlined) {
                        System.out.println(Convert(String.valueOf(firstWord.charAt(0))));
                    }
                }
            }

            // Close the input file
            file.close();

            System.out.println("DOCX manipulation complete.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isRunUnderlined(XWPFRun run) {
        UnderlinePatterns underline = run.getUnderline();
        return underline != UnderlinePatterns.NONE;
    }
}

