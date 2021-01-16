package com.pbl.filesharing.FileSharing.entity;

import javax.persistence.*;

@Entity
@Table(name="sharing_info")
public class SharingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner")
    private String ownerID;

    @Column(name = "reciever")
    private String recieverID;

    @Column(name = "doc_id")
    private Long docID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getRecieverID() {
        return recieverID;
    }

    public void setRecieverID(String recieverID) {
        this.recieverID = recieverID;
    }

    public Long getDocID() {
        return docID;
    }

    public void setDocID(Long docID) {
        this.docID = docID;
    }
//    @Column(name = "mode")
//    private String mode;
}