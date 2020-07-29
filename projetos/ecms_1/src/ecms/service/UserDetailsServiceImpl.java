package ecms.service;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ecms.domain.BaseUser;
import ecms.repository.Dao;

@Service("userDetailsService") 
public class UserDetailsServiceImpl implements UserDetailsService {

  private Dao dao;
  private Assembler assembler;

  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String login)
      throws UsernameNotFoundException, DataAccessException {

    // UserDetails userDetails = null;
    BaseUser userEntity = dao.findUserByLogin(login);
    if (userEntity == null) {
    	throw new UsernameNotFoundException("user not found");
    }

    return assembler.buildUserFromUserEntity(userEntity);
  }
}
