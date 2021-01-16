package com.pbl.filesharing.FileSharing.repository;

import com.pbl.filesharing.FileSharing.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Beatrice V.
 * @created 22.11.2020 - 22:22
 * @project FileSharing
 */

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query("SELECT d FROM Document d WHERE OWNER = ?1 ORDER BY d.uploadTime DESC")
    List<Document> findbyLogin(String login);

    @Query("SELECT d FROM Document d inner join SharingInfo s on s.docID = d.id  WHERE s.recieverID = ?1 ORDER BY d.uploadTime DESC")
    List<Document> findbyReciever(String login);
}