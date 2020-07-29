package ecms.service;

import java.util.List;

import ecms.domain.BaseUser;

public interface IAdministrationManagerSer {
	
	public void addStoreManager(BaseUser sm);
	
	public List<BaseUser> listAllStoreManager();
	
	public BaseUser authStoreManager(String login, String passwd);
	
	public BaseUser updateStoreManager(BaseUser sm);
	
	public void deleteStoreManager(BaseUser sm);

	public Object findAdminByLogin(String login);

	public void addAdminManager(BaseUser mailUser);

	boolean findStoreManagerByLogin(String login);
	
}
