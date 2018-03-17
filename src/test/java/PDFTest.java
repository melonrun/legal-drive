import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by guoyong on 2018/2/5.
 */
public class PDFTest {

    @Test
    public void test() throws IOException {
        PDDocument pdfDocument = PDDocument.load(new File("files/test/123.pdf"));
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(pdfDocument);
        System.out.println(text);
    }

    @Test
    public void testSuffix() {
        String fileName = "闲话PS和PDF.pdf";
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        if(".pdf".equals(suffix)){
            System.out.println("pdf");
        }
        if(".doc".equals(suffix) || ".docx".equals(suffix)){
            System.out.println("doc");
        }
    }

}
