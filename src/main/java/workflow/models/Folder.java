package workflow.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "folder")
public class Folder extends BaseEntity {

    @Column(name = "folder_name", nullable = false, unique = true)
    private String folderName;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL)
    private List <Task> taskList;

    public Integer getPriorityWeight() {
        return priority.getWeight();
    }

    public String getFolderShortName() {
        return folderName.length() > 24 ? folderName.substring(0, 24) + "..." : folderName;
    }
}
























