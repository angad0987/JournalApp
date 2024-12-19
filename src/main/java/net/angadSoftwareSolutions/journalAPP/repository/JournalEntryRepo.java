package net.angadSoftwareSolutions.journalAPP.repository;

import net.angadSoftwareSolutions.journalAPP.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepo extends MongoRepository<JournalEntry, ObjectId> {

}
