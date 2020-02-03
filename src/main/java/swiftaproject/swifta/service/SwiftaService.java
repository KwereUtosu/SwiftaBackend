package swiftaproject.swifta.service;

import swiftaproject.swifta.dto.SwiftaUserDto;
import swiftaproject.swifta.model.Response;
import swiftaproject.swifta.model.SwiftaUser;
import swiftaproject.swifta.utility.ResponseUtil;

import java.util.List;

/**
 * @author Kwerenachi Utosu
 * @date 2/2/2020
 */
public interface SwiftaService {

    ResponseUtil.Response createSwitfaUser(SwiftaUser swiftaUser);

    ResponseUtil.Response updateSwitfaUser(SwiftaUserDto swiftaUserDto);

    ResponseUtil.Response findSwitfaUser(String email);

    ResponseUtil.Response deleteSwitfaUser(SwiftaUserDto swiftaUserDto);

    ResponseUtil.Response bulkDeleteSwitfaUser(List<SwiftaUser> swiftaUsers);

    ResponseUtil.Response permanentlyDeleteSwitfaUser(SwiftaUserDto swiftaUserDto);

    ResponseUtil.Response blockSwitfaUser(SwiftaUserDto swiftaUserDto);

    ResponseUtil.Response unblockSwitfaUser(SwiftaUserDto swiftaUserDto);


}
