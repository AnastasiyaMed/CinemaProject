package command.cinema;

import javax.servlet.http.HttpServletRequest;

import command.ActionCommand;
import utils.ConfigurationManager;

public class GoToHeadCommand implements ActionCommand {
		
	@Override
	public String execute(HttpServletRequest request) {
		@SuppressWarnings("unused")
		String page;
		return page = ConfigurationManager.getProperty("path.page.index");
		
	}

}
