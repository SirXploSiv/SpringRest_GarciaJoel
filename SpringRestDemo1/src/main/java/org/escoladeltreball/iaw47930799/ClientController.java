package org.escoladeltreball.iaw47930799;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ClientController {
	
	@Autowired
	private ClientDAOImpl clientDAO;
	
	@RequestMapping("/clients/{param}")
	public Client findByParams (@PathVariable String param) {
		try {
			
			return clientDAO.findById(Integer.parseInt(param.toString()));
			
		} catch (NumberFormatException ex) {
			
			return clientDAO.findByName(param.toString());
			
		}		
	}
	
	@RequestMapping("/clients")
	public List<Client> findAllClients() {
		return clientDAO.findAll();
	}
	
	
}
