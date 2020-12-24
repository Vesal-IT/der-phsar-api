package com.kshrd.derphsar_api.rest.restcontroller;

import com.kshrd.derphsar_api.configuration.jwtconfiguration.JwtTokenUtil;
import com.kshrd.derphsar_api.repository.dto.JwtResponse;
import com.kshrd.derphsar_api.repository.dto.UserDto;
import com.kshrd.derphsar_api.repository.dto.UserLoginDto;
import com.kshrd.derphsar_api.rest.message.MessageProperties;
import com.kshrd.derphsar_api.service.implement.UserServiceImp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
//@CrossOrigin
@RequestMapping("api/v1")
public class UserLoginRestController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserServiceImp userServiceImp;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private MessageProperties message;


    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }


    /**
     * Post a login
     *
     * @param user - Use
     * @return - Return response message
     * @throws Exception - Exception
     */
    @PostMapping("login")
    @ApiOperation(value = "user login to system")
    public ResponseEntity<Map<String, Object>> createAuthenticationToken(@Validated @RequestBody UserLoginDto user)throws Exception {
        Map<String, Object> result = new HashMap<>();
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        }
        catch (BadCredentialsException e){
            throw new Exception("Incorrect email or password",e);

        }
        final UserDetails userDetails = userServiceImp.loadUserByUsername(user.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        if(userDetails!=null){

            UserDto user1 = userServiceImp.findByEmail(user.getEmail());

            result.put("message", message.loginSuccess("User") );
           // result.put("token",new JwtResponse(token));
            result.put("token",jwtTokenUtil.generateToken(userDetails));
            result.put("role", userDetails.getAuthorities());
//            result.put("userId", user1.getUserId());
            result.put("userId", user1.getUserId());
            result.put("username", user1.getName());
            result.put("status", HttpStatus.OK);
        }else {
            result.put("Message", HttpStatus.UNAUTHORIZED);
            result.put("status", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(result);
    }


}


