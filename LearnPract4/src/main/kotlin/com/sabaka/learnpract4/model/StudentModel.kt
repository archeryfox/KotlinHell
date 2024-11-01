import org.springframework.data.annotation.Id

open class StudentModel(
    @Id
    var id: Int,
    val name: String,
    val lastName: String,
    val firstName: String,
    val middleName: String
)
