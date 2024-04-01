package com.a602.actors.domain.notification.repository;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.a602.actors.domain.notification.document.Notify;

public interface NotifyRepository extends MongoRepository<Notify, String> {
	List<Notify> findByReceiverIdAndIsRead(Long loginId, char isRead);
	Optional<Notify> findById(ObjectId id);
}
