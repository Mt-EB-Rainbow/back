package efub.ebmt.eeojum.domain.train.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long educationId;

    @Column(columnDefinition = "TEXT")
    private String courseName;

    @Column
    private String majorCategoryName;

    @Column
    private String middleCategoryName;

    @Column
    private String fileUrl;

    @Column(columnDefinition = "TEXT")
    private String contentsName;

    @Column
    private String applyUrl;
}
