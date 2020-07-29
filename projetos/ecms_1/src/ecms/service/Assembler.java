package ecms.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ecms.domain.BaseUser;
import ecms.domain.Role;

@SuppressWarnings("deprecation")
@Service("assembler")
public class Assembler {

  @Transactional(readOnly = true)
  User buildUserFromUserEntity(BaseUser userEntity) {

    String login    = userEntity.getLogin();
    String password = userEntity.getPassword();
    String role;
    
    if(userEntity.getRole() == Role.ROLE_ADMIN) {
    	role = "ROLE_ADMIN";
    } else if(userEntity.getRole() == Role.ROLE_SM) {
    	role = "ROLE_SM";
    } else {
    	role = "ROLE_USER";
    }
    
    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    authorities.add(new GrantedAuthorityImpl(role));

    User user = new User(login, password, true, true, true, true, authorities);
    return user;
  }
}
