package com.springAI.SpringDocumentation;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

@Component
public class ReferenceDocsLoader {
    private final Logger logger = LoggerFactory.getLogger(ReferenceDocsLoader.class);
    private final VectorStore vectorStore;
    private final JdbcClient jdbcClient;

    @Value("classpath:/docs/spring-boot-reference.pdf")
    private Resource pdfResource;
    public ReferenceDocsLoader(VectorStore vectorStore, JdbcClient jdbcClient) {
        this.vectorStore = vectorStore;
        this.jdbcClient = jdbcClient;
    }

    @PostConstruct
    public void init(){
        int count = jdbcClient.sql("select count(*) from vector_store")
                .query(Integer.class)
                .single();

        logger.info("Current count of vector_store: {}", count);
        if(count==0){
            logger.info("Loading pdf document into the vector_store...");
            var config = PdfDocumentReaderConfig.builder()
                    .withPageExtractedTextFormatter(new ExtractedTextFormatter.Builder().withNumberOfBottomTextLinesToDelete(0)
                            .withNumberOfTopPagesToSkipBeforeDelete(0).build())
                    .withPagesPerDocument(1)
                    .build();
            var pdfReader = new PagePdfDocumentReader(pdfResource, config);
            var textSplitter = new TokenTextSplitter();
            vectorStore.accept(textSplitter.apply(pdfReader.get()));

            logger.info("Application is ready...");
        }
    }
}
