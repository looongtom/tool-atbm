package org.example.second;

import org.apache.poi.xwpf.usermodel.*;

import java.io.*;

public class ModifyAnswer {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\Admins\\OneDrive - ptit.edu.vn\\ATBM\\mix2-102den130.docx");
            XWPFDocument inputDocument = new XWPFDocument(fis);
            XWPFDocument outputDocument = new XWPFDocument();

            int paragraphIndex = 0;
            int numParagraphs = inputDocument.getParagraphs().size();

            while (paragraphIndex < numParagraphs) {
                XWPFParagraph questionParagraph = inputDocument.getParagraphArray(paragraphIndex);
                String question = getParagraphText(questionParagraph);

                if (question != null ) {
                    String[] answers = new String[4];
                    for (int i = 0; i < 4; i++) {
                        paragraphIndex++;
                        XWPFParagraph answerParagraph = inputDocument.getParagraphArray(paragraphIndex);
                        String answer = getParagraphText(answerParagraph);
                        answers[i] = answer;
                    }

                    XWPFParagraph outputQuestionParagraph = outputDocument.createParagraph();
                    outputQuestionParagraph.setStyle(questionParagraph.getStyle());
                    XWPFRun questionRun = outputQuestionParagraph.createRun();
                    questionRun.setText(question);

                    for (int i = 0; i < 4; i++) {
                        String answer = answers[i];
                        char answerLetter = (char) ('A' + i);
                        XWPFParagraph outputAnswerParagraph = outputDocument.createParagraph();
                        outputAnswerParagraph.setStyle(questionParagraph.getStyle());
                        XWPFRun answerRun = outputAnswerParagraph.createRun();
                        answerRun.setText(answerLetter + ". " + answer);
                    }
                }

                paragraphIndex++;
            }

            FileOutputStream fos = new FileOutputStream("questions.docx");
            outputDocument.write(fos);

            inputDocument.close();
            outputDocument.close();
            fis.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getParagraphText(XWPFParagraph paragraph) {
        StringBuilder builder = new StringBuilder();
        for (XWPFRun run : paragraph.getRuns()) {
            String text = run.getText(0);
            if (text != null && !text.isEmpty()) {
                builder.append(text.trim());
            }
        }
        String paragraphText = builder.toString();
        return paragraphText.isEmpty() ? null : paragraphText;
    }
}
