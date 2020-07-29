package ecms.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import ecms.domain.BaseUser;
import ecms.domain.Role;
import ecms.repository.Dao;

@Service
public class AdministrationManagerSer implements IAdministrationManagerSer {

	private Dao ecmsDao;
	
	public void setEcmsDao(Dao ecmsDao) {
		this.ecmsDao = ecmsDao;
	}
	public Dao getEcmsDao() {
		return this.ecmsDao;
	}
	
	@PostConstruct
	public void init() { 
		ecmsDao = new Dao();
		ecmsDao.init();
		
	}
	
	@PreDestroy
	public void close() {
		ecmsDao.close();
		
	}

	@Override
	public void addStoreManager(BaseUser sm) {
		sm.setRole(Role.ROLE_SM);
		ecmsDao.addUser(sm);
	}
	@Override
	public List<BaseUser> listAllStoreManager() {
		return ecmsDao.findAllStoreManager();
	}
	@Override
	public BaseUser authStoreManager(String login, String passwd) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BaseUser updateStoreManager(BaseUser sm) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void deleteStoreManager(BaseUser sm) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean findStoreManagerByLogin(String login) {
		
		try {
			ecmsDao.findOneStoreManager(login);
			return true;
		
		} catch(NoResultException e) {
			return false;
		}
	
	}
	@Override
	public void addAdminManager(BaseUser mailUser) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object findAdminByLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}






}
