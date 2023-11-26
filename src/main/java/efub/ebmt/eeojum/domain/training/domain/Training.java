package efub.ebmt.eeojum.domain.training.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/* "applyUrl" : "String",
"contentsName" : "String",
"courseName" : "String",
"courseSeq" : int,
"detailUrl" : "String",
"fileUrl" : "String",
"majorCategoryName" : "String",
"onlineTrainingTime" : "String",
"trainingGoal" : <html />,
"trainingSummary" : <html /> */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long trainingId;

    @Column(updatable = true)
    private String applyUrl;

    @Column(columnDefinition = "TEXT")
    private String contentsName;

    @Column(updatable = true)
    private String courseSeq;

    @Column(updatable = true)
    private String detailUrl;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String courseName;

    @Column(updatable = true)
    @NotNull
    private String fileUrl;

    @Column(updatable = true)
    @NotNull
    private String majorCategoryName;

    @Column(updatable = true)
    @NotNull
    private String middleCategoryName;

    @Column(columnDefinition = "TEXT", updatable = true)
    private String trainingGoal;

    @Column(columnDefinition = "TEXT", updatable = true)
    private String trainingSummary;

    @Column(columnDefinition = "TEXT", updatable = true)
    private String onlineTrainingTime;

    @Column(name = "type", insertable = true, updatable = true)
    @NotNull
    private String type;
}
