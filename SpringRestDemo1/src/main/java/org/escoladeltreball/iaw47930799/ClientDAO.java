package org.escoladeltreball.iaw47930799;

import java.util.List;

public interface ClientDAO {
	
	public Client findById (long clientId);
	public Client findByName (String clientName);
	public List<Client> findAll();
	public void insertOrUpdateClient ( Client client );
	public void deleteClient ( long clientId );
	
}
