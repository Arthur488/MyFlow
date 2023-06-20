package workflow.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Column(name = "first_name", nullable = false, length = 45)
    protected String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    protected String lastName;

    @Column(name = "email", nullable = false, length = 45, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List <Folder> folderList;

    @Transient
    public String getFullName() {
        return firstName + " " + lastName;
    }

}
