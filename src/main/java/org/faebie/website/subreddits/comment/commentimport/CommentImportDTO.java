package org.faebie.website.subreddits.comment.commentimport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.faebie.website.subreddits.model.Subreddit;
import org.immutables.value.Value;
import org.springframework.stereotype.Component;

@Component
@Value.Immutable
@JsonDeserialize(as = ImmutableCommentImportDTO.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface CommentImportDTO {

    @JsonProperty("id") String id();
    @JsonProperty("subreddit")
    Subreddit subreddit();
    @JsonProperty("author") String author();
    @JsonProperty("body") String body();
    @JsonProperty("link_id") String submissionId();
    @JsonProperty("parent_id") String parentId();
    @JsonProperty("score") int score();
    @JsonProperty("ups") @Value.Default default int ups() {
        return 0;
    }
    @JsonProperty("controversiality") int controversiality();
    @JsonProperty("created_utc") long created(); // Time created in UTC
}
