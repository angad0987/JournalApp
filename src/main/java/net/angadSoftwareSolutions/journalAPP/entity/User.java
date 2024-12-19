package net.angadSoftwareSolutions.journalAPP.entity;


import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@Builder
public class User {
    public User(ObjectId id, @NonNull String username, @NonNull String password, List<JournalEntry> entryList, List<String> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.entryList = entryList;
        this.roles = roles;
    }

    public User(){

    }
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String username;
    @NonNull
    private String password;

    @DBRef
    private List<JournalEntry> entryList=new ArrayList<>();

    private List<String> roles;
}
