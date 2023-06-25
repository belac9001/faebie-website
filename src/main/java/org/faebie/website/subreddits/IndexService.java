package org.faebie.website.subreddits;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class IndexService {
    private static final Logger log = LogManager.getLogger(IndexService.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<String> getIndexedSubreddits() throws IOException {
        log.info("DIR: {}", new File(".").getCanonicalPath());

        //final Map<String, String> data = objectMapper.readValue(new File(""));
        return List.of("sub1", "sub2");
    }
}
