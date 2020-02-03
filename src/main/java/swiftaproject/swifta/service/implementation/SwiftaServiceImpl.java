package swiftaproject.swifta.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swiftaproject.swifta.dto.SwiftaUserDto;
import swiftaproject.swifta.model.Response;
import swiftaproject.swifta.model.Role;
import swiftaproject.swifta.model.SwiftaUser;
import swiftaproject.swifta.repository.SwiftaUserRepository;
import swiftaproject.swifta.service.SwiftaService;
import swiftaproject.swifta.utility.ResponseUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Kwerenachi Utosu
 * @date 2/2/2020
 */

@Service
public class SwiftaServiceImpl implements SwiftaService {

    @Autowired
    private ResponseUtil responseUtil;

    @Autowired
    private SwiftaUserRepository swiftaUserRepository;

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public ResponseUtil.Response createSwitfaUser(SwiftaUser swiftaUser) {

        if ( swiftaUser.getEmail()==null || swiftaUser.getEmail().isEmpty() )
            return responseUtil.getResponse(responseUtil.FAILURE_STATUS, "Required 'email' missing from payload", null);

        //Check if the user already Exist
        if (Objects.nonNull(swiftaUserRepository.findFirstByEmail(swiftaUser.getEmail())))
            return responseUtil.getResponse(responseUtil.FAILURE_STATUS, "Existing email", null);

        swiftaUser.setEmail(swiftaUser.getEmail().toLowerCase());
        logger.info("Creating User with Email ->" + swiftaUser.getEmail());

        //Converting the string to an Enum
        if (swiftaUser.getRole().equals("ADMIN")){
            swiftaUser.setRole(Role.ADMIN);
        }else { swiftaUser.setRole(Role.CUSTOMER); }

        swiftaUser = swiftaUserRepository.save(swiftaUser);

        return responseUtil.getResponse(responseUtil.SUCCESS_STATUS, "Swifta User created successfully", swiftaUser);

    }

    @Override
    public ResponseUtil.Response updateSwitfaUser(SwiftaUserDto swiftaUserDto) {

        //Check if the user already Exist
        SwiftaUser swiftaUserSaved = swiftaUserRepository.findFirstByEmail(swiftaUserDto.getEmail());

        if (!Objects.nonNull(swiftaUserSaved))
            return responseUtil.getResponse(responseUtil.FAILURE_STATUS, "User Does not Exist", null);

        //Replacing the User's old Info with the new one
        swiftaUserSaved.setFirstName(swiftaUserDto.getFirstName());
        swiftaUserSaved.setLastName(swiftaUserDto.getLastName());
        swiftaUserSaved.setGender(swiftaUserDto.getGender());
        swiftaUserSaved.setNationality(swiftaUserDto.getNationality());
        swiftaUserSaved.setPhone(swiftaUserDto.getPhone());
        swiftaUserSaved.setDob(swiftaUserDto.getDob());

        swiftaUserSaved = swiftaUserRepository.save(swiftaUserSaved);

        return responseUtil.getResponse(responseUtil.SUCCESS_STATUS, "Swifta User Updated successfully", swiftaUserSaved);
    }

    @Override
    public ResponseUtil.Response findSwitfaUser(String email) {

        //Check if the user already Exist
        SwiftaUser swiftaUser = swiftaUserRepository.findFirstByEmail(email);

        if (!Objects.nonNull(swiftaUser))
            return responseUtil.getResponse(responseUtil.FAILURE_STATUS, "User Does not Exist", null);

        return responseUtil.getResponse(responseUtil.SUCCESS_STATUS, "Swifta User Found successfully", swiftaUser);
    }

    @Override
    public ResponseUtil.Response deleteSwitfaUser(SwiftaUserDto swiftaUserDto) {

        //Check if the user already Exist
        SwiftaUser swiftaUserSaved = swiftaUserRepository.findFirstByEmail(swiftaUserDto.getEmail());

        if (!Objects.nonNull(swiftaUserSaved))
            return responseUtil.getResponse(responseUtil.FAILURE_STATUS, "User Does not Exist", null);

        //This act of Deleting is Done for Audit purposes
        swiftaUserSaved.setDelFlag("Y");
        swiftaUserRepository.save(swiftaUserSaved);

        return responseUtil.getResponse(responseUtil.SUCCESS_STATUS, "Swifta User Deleted Successfully", null);

    }

    @Override
    public ResponseUtil.Response bulkDeleteSwitfaUser(List<SwiftaUser> swiftaUsers) {

        if (Objects.nonNull(swiftaUsers)) {

            swiftaUsers.forEach( swiftaUser -> {

                //Check if the user already Exist
                SwiftaUser swiftaUserSaved = swiftaUserRepository.findFirstByEmail(swiftaUser.getEmail());

                if (Objects.nonNull(swiftaUserSaved)) {
                    //This act of Deleting is Done for Audit purposes
                    swiftaUserSaved.setDelFlag("Y");
                    swiftaUserRepository.save(swiftaUserSaved);
                }

            });

            }

        return responseUtil.getResponse(responseUtil.SUCCESS_STATUS, "Bulk Swifta Users Deleted Successfully", null);

    }

    @Override
    public ResponseUtil.Response permanentlyDeleteSwitfaUser(SwiftaUserDto swiftaUserDto) {

        //Check if the user already Exist
        SwiftaUser swiftaUserSaved = swiftaUserRepository.findFirstByEmail(swiftaUserDto.getEmail());

        if (!Objects.nonNull(swiftaUserSaved))
            return responseUtil.getResponse(responseUtil.FAILURE_STATUS, "User Does not Exist", null);

        //This User has been Permanently Deleted
        swiftaUserRepository.delete(swiftaUserSaved);

        return responseUtil.getResponse(responseUtil.SUCCESS_STATUS, "Swifta User Permanently Deleted Successfully", null);


    }

    @Override
    public ResponseUtil.Response blockSwitfaUser(SwiftaUserDto swiftaUserDto) {

        //Check if the user already Exist In the DB
        SwiftaUser swiftaUserSaved = swiftaUserRepository.findFirstByEmail(swiftaUserDto.getEmail());

        if (!Objects.nonNull(swiftaUserSaved))
            return responseUtil.getResponse(responseUtil.FAILURE_STATUS, "User Does not Exist", null);

        //This Is my way of Blocking a user
        swiftaUserSaved.setDelFlag("BLOCK");
        swiftaUserRepository.save(swiftaUserSaved);

        return responseUtil.getResponse(responseUtil.SUCCESS_STATUS, "Swifta User Blocked Successfully", null);

    }

    @Override
    public ResponseUtil.Response unblockSwitfaUser(SwiftaUserDto swiftaUserDto) {

        //Check if the user already Exist In the DataBase
        SwiftaUser swiftaUserSaved = swiftaUserRepository.findFirstByEmail(swiftaUserDto.getEmail());

        if (!Objects.nonNull(swiftaUserSaved))
            return responseUtil.getResponse(responseUtil.FAILURE_STATUS, "User Does not Exist", null);

        //This Is my way of UNBlocking a user
        swiftaUserSaved.setDelFlag("UNBLOCK");
        swiftaUserRepository.save(swiftaUserSaved);

        return responseUtil.getResponse(responseUtil.SUCCESS_STATUS, "Swifta User Unblocked Successfully", null);

    }
}
