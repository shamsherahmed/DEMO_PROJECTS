package com.shamsid.databaseaudit.user;

import com.shamsid.databaseaudit.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "user")
@Audited @AuditOverride(forClass = Auditable.class)
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;
    String mobile;
    String email;
}
