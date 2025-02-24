package com.amvb.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class ChatTableService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public String createTableForFriends(String user1, String user2) {
        List<String> users = Arrays.asList(user1, user2);
        Collections.sort(users);
        String tableName = "CHAT_" + users.get(0).replaceAll("[^a-zA-Z0-9]", "") + "_AND_" + users.get(1).replaceAll("[^a-zA-Z0-9]", "");

//        // Create SEQUENCE
//        String sequenceSQL = "CREATE SEQUENCE " + tableName + "_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE";
//        entityManager.createNativeQuery(sequenceSQL).executeUpdate();

        // Create TABLE
        String tableSQL = "CREATE TABLE " + tableName + " (" +
                          "messageId NUMBER(10,0) PRIMARY KEY, " +
                          "message VARCHAR2(50), " +
                          "user1 VARCHAR2(30), " +
                          "user2 VARCHAR2(30), " +
                          "sentBy VARCHAR2(30), " +
                          "time TIMESTAMP(6))";
        entityManager.createNativeQuery(tableSQL).executeUpdate();

//        // Create TRIGGER (Fixed Version)
//        String triggerSQL = "CREATE OR REPLACE TRIGGER " + tableName + "_trigger " +
//                            "BEFORE INSERT ON " + tableName + " " +
//                            "FOR EACH ROW " +
//                            "BEGIN " +
//                            "SELECT " + tableName + "_seq.NEXTVAL INTO NEW.messageId FROM dual; " +
//                            "END;";
//        entityManager.createNativeQuery(triggerSQL).executeUpdate();

        return "Table Created: " + tableName;
    }
}
