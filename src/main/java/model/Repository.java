package model;

import lombok.Data;
import java.util.Date;
import java.util.UUID;

@Data
public class Repository {
    UUID repository_uuid;
    UUID user_uuid;
    UUID organization_uuid;
    String repostory_name;
    String repository_description;
    String repository_visibility;
    String repository_git_url;
    String repository_github_type;
    String repository_type;
    String repository_status;
    Date repository_submission_date;
    Date created_at;
    Date updated_at;
}
