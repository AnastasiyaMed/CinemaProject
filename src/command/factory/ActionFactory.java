package command.factory;

import javax.servlet.http.HttpServletRequest;

import command.ActionCommand;
import command.EmptyCommand;
import utils.MessageManager;


public class ActionFactory {
	
//	private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
	

	public ActionCommand defineCommand(HttpServletRequest request) {
		ActionCommand current = new EmptyCommand();
		String action = request.getParameter("command");
		if (action == null || action.isEmpty()) {
			return current;
		}
		try {
			CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
			current = currentEnum.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
			//logger.log(Level.FATAL, "Servlet exception - can't find ActionCommand : " + e.toString());	
		}
		return current;
	}
}
