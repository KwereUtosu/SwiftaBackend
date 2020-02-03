package swiftaproject.swifta.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Kwerenachi Utosu
 * @date 2/2/2020
 */

@Setter
@Getter
@Entity
@Table(name = "swifta_user")
public class SwiftaUser {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    Long id;

    @Version
    private int version;
    private String delFlag = "N";
    private Date dateCreated = new Date();
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String phone;
    private String gender;
    private Date dob;
    private String nationality;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String status;

    @Override
    public String toString() {
        return "SwiftaUser{" +
                "id=" + id +
                ", version=" + version +
                ", delFlag='" + delFlag + '\'' +
                ", dateCreated=" + dateCreated +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", dob=" + dob +
                ", nationality='" + nationality + '\'' +
                ", role=" + role +
                ", status='" + status + '\'' +
                '}';
    }
}
