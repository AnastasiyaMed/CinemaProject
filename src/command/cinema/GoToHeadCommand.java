package command.cinema;

import javax.servlet.http.HttpServletRequest;

import command.ActionCommand;
import utils.ConfigurationManager;

public class GoToHeadCommand implements ActionCommand {
		
	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.index");
	
		return page;
		
	}

}
