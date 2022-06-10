package com.example.versioning.data.migrations;

import com.mongodb.BasicDBObject;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeUnit(id = "RenamePublicationTextToContent", order = "002")
public class RenamePublicationTextToContent {

    @Execution
    public void initialize(MongoTemplate template) {
        BasicDBObject searchQuery = new BasicDBObject();

        // Update all objects: Rename "text" to "content" so that it is compliant with the "new" schema
        BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.append(
            "$rename",
            new BasicDBObject()
                .append("text", "content")
        );

        template.getCollection("publication").updateMany(searchQuery, updateQuery);
    }

    @RollbackExecution
    public void rollback() {

    }
}


