package swiftaproject.swifta.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swiftaproject.swifta.dto.SwiftaUserDto;
import swiftaproject.swifta.model.SwiftaUser;
import swiftaproject.swifta.service.SwiftaService;
import swiftaproject.swifta.utility.ResponseUtil;

import java.util.List;

/**
 * @author Kwerenachi Utosu
 * @date 2/3/2020
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = { "/v1/swifta/user", "/api/v1/swifta/user" })
public class SwiftaController {

    @Autowired
    private SwiftaService swiftaService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/create")
    public ResponseEntity createSwiftaUser(@RequestBody SwiftaUser swiftaUser) {

        logger.info("Trying to Create a User");
        ResponseUtil.Response response = swiftaService.createSwitfaUser(swiftaUser);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity updateSwiftaUser(@RequestBody SwiftaUserDto swiftaUserDto) {

        logger.info("Trying to Update a User");
        ResponseUtil.Response response = swiftaService.updateSwitfaUser(swiftaUserDto);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/find")
    public ResponseEntity findSwiftaUser(@RequestBody String email) {

        logger.info("Trying to Find a User");
        ResponseUtil.Response response = swiftaService.findSwitfaUser(email);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity deleteSwiftaUser(@RequestBody SwiftaUserDto swiftaUserDto) {

        logger.info("Trying to Delete a User");
        ResponseUtil.Response response = swiftaService.deleteSwitfaUser(swiftaUserDto);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/delete/bulk")
    public ResponseEntity bulkDeleteSwiftaUser(@RequestBody List<SwiftaUser> swiftaUsers) {

        logger.info("Trying to Delete Bulk a Users");
        ResponseUtil.Response response = swiftaService.bulkDeleteSwitfaUser(swiftaUsers);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/delete/permanently")
    public ResponseEntity permanentlyDeleteSwiftaUser(@RequestBody SwiftaUserDto swiftaUserDto) {

        logger.info("Trying to Permanently Delete a User");
        ResponseUtil.Response response = swiftaService.permanentlyDeleteSwitfaUser(swiftaUserDto);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/block")
    public ResponseEntity blockSwiftaUser(@RequestBody SwiftaUserDto swiftaUserDto) {

        logger.info("Trying to Block a User");
        ResponseUtil.Response response = swiftaService.blockSwitfaUser(swiftaUserDto);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/unblock")
    public ResponseEntity unblockSwiftaUser(@RequestBody SwiftaUserDto swiftaUserDto) {

        logger.info("Trying to Unblock a User");
        ResponseUtil.Response response = swiftaService.unblockSwitfaUser(swiftaUserDto);
        return new ResponseEntity(response, HttpStatus.OK);
    }


}
