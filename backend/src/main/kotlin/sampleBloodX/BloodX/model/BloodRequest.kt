import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BloodRequest")
data class BloodRequest(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blood_request_seq")
    @SequenceGenerator(name = "blood_request_seq", sequenceName = "blood_request_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long = 0,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "bloodgroup", nullable = false)
    val bloodgroup: String,

    @Column(name = "age", nullable = false)
    val age: Integer,

    @Column(name = "units", nullable = false)
    val units: String,
    
    @Column(name = "reason", nullable = false)
    val reason: String,
)
