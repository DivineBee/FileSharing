package com.pbl.filesharing.FileSharing.repository;

import com.pbl.filesharing.FileSharing.entity.SharingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareRepository extends JpaRepository<SharingInfo, Long> {
    @Query("SELECT i FROM SharingInfo i")
    List<SharingInfo> findPermissions();
}