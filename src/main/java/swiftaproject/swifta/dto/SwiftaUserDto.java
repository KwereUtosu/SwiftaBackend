package swiftaproject.swifta.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Kwerenachi Utosu
 * @date 2/3/2020
 */
@Setter
@Getter
public class SwiftaUserDto {

    private Long id;
    private int version;
    private String delFlag;
    private Date dateCreated;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String gender;
    private Date dob;
    private String nationality;
    private String role;
    private String status;

    @Override
    public String toString() {
        return "SwiftaUserDto{" +
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
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
