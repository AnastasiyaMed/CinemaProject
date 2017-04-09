package command;

import javax.servlet.http.HttpServletRequest;

import utils.ConfigurationManager;

public class EmptyCommand implements ActionCommand {

	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.index");
		return page;
	}
}