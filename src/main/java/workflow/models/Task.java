package workflow.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "task")
public class Task extends BaseEntity {

    @Column(name = "task_name", nullable = false, unique = true)
    private String taskName;

    @Column(name = "task_description")
    private String taskDescription;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_date", updatable = false)
    private LocalDate createdDate;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "is_expired")
    private boolean isExpired;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "folder_id")
    private Folder folder;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDate.now();
    }


}
