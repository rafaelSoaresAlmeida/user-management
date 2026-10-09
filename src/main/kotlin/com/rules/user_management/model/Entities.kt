import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("USERS")
data class SystemUser(
    @Id val id: Long? = null,
    val login: String,
    val firstname: String,
    val lastname: String,
    val description: String? = null
)