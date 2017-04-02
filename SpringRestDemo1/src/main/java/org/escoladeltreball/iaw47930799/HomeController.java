package org.escoladeltreball.iaw47930799;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Handles requests for the application home page.
 */
@Controller
@PropertySource("classpath:application.properties")
public class HomeController {
	
	@Autowired
	private ClientDAOImpl clientDAO;
	
	@Autowired
	private Environment env;
	
	public String getProperty(String propertyName) {
		return env.getProperty(propertyName);
	}
	
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//CREATE
	
	@RequestMapping(headers = "Accept=application/json;charset=utf-8",value = "/createClient", method = RequestMethod.GET)
	public String createClient(Model model) {
		model.addAttribute("Client", new Client());
		return "createClient";
	}
	
	@PostMapping(headers = "Accept=application/json;charset=utf-8",value = "/readFormClientInsert")
	public String insert( @ModelAttribute("Client") @Valid Client beanClient, final BindingResult bResult, final RedirectAttributes attr , Model model) {
	    if (bResult.hasErrors()){
			attr.addFlashAttribute("err",beanClient);
	    	attr.addFlashAttribute(beanClient);
	    	return "createClient";
	    } 	    
		clientDAO.insertOrUpdateClient(beanClient);
		return "index";
	}
	
	//UPDATE
	
	@RequestMapping(headers = "Accept=application/json;charset=utf-8",value = "/updateClient", method = RequestMethod.GET)
	public String updateClient(Model model) {
		model.addAttribute("Client", new Client());
		return "updateClient";
	}
	
	@PostMapping(headers = "Accept=application/json;charset=utf-8",value = "/readFormClientUpdate")
	public String update( @ModelAttribute("Client") @Valid Client beanClient, final BindingResult bResult, final RedirectAttributes attr , Model model) {
	    if (bResult.hasErrors()){
			attr.addFlashAttribute("err",beanClient);
	    	attr.addFlashAttribute(beanClient);
	    	return "updateClient";
	    } 	    
		clientDAO.insertOrUpdateClient(beanClient);
		return "index";
	}
	
	//DELETE
	
	@RequestMapping(headers = "Accept=application/json;charset=utf-8",value = "/deleteClient", method = RequestMethod.GET)
	public String deleteClient(Model model) {
		model.addAttribute("Client", new Client());
		return "deleteClient";
	}
	
	@PostMapping(headers = "Accept=application/json;charset=utf-8",value = "/readFormClientDelete")
	public String delete( @ModelAttribute("Client") @Valid Client beanClient, final BindingResult bResult, final RedirectAttributes attr , Model model) {  
		clientDAO.deleteClient((long)beanClient.getId());
		return "index";
	}
	
}
