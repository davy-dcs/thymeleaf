package com.insy2s.thymeleaf;

import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
@Slf4j
public class ThymleafTemplate {
    public static void genrate() throws IOException {
        generatePdfFromHtml(parseThymeleafTemplate());
    }
    private static String parseThymeleafTemplate() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("to", "Petite Peruche");

        return templateEngine.process("templates/demo_template.html", context);
    }

    public static void generatePdfFromHtml(String html) {

        File file = new File("files");
        if (!(file.exists() && file.isDirectory())) {
            file.mkdir();
        }
        
        String outputFolder = "./files/thymeleaf.pdf";
        try (OutputStream outputStream = new FileOutputStream(outputFolder)) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(outputStream);
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }
}
