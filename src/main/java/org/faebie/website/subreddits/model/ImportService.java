package org.faebie.website.subreddits.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.nio.file.*;

public abstract class ImportService {
    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    protected static final Logger log = LogManager.getLogger(ImportService.class);

    protected String importInputPath;
    protected String importDLQPath;
    protected String importGlob;
    protected Path inputPath;
    protected Path dlqPath;

    @Scheduled(fixedDelay = 10000)
    public void doWork() {
        try (final DirectoryStream<Path> dir = Files.newDirectoryStream(inputPath, this.importGlob)) {
            for (final Path entry : dir) {
                long start = System.currentTimeMillis();
                try {
                    importFile(entry);
                    Files.delete(entry);
                } catch (final IOException e) {
                    log.error("Error processing file {}", entry, e);
                    try {
                        Path dest = Paths.get(dlqPath.toString(), entry.getFileName().toString());
                        Files.move(entry, dest, StandardCopyOption.REPLACE_EXISTING);
                    } catch (final IOException dlqErr) {
                        log.error("Error moving {} to DLQ {}", entry, dlqPath, dlqErr);
                    }

                    throw e;
                }
                long finish = System.currentTimeMillis();
                log.info("Imported {} in {} ms", entry, finish-start);
            }
        } catch (final IOException e) {
            log.error("Failed to open directory stream at path {}", inputPath, e);
        }
    }

    protected void importFile(final Path entry) throws IOException {
        throw new RuntimeException("Not implemented, this class must be extended");
    }

    @PostConstruct
    public void createDirectories() {
        try {
            inputPath = Files.createDirectories(Paths.get(importInputPath));
            dlqPath = Files.createDirectories(Paths.get(importDLQPath));
            log.info("{} monitoring input path: {}", this.getClass().getSimpleName(), inputPath.toAbsolutePath());
            log.info("{} DLQ path: {}", this.getClass().getSimpleName(), dlqPath.toAbsolutePath());
        } catch (final IOException e) {
            log.error("Error creating directory", e);
        }
    }
}
