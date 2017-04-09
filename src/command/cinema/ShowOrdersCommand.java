package command.cinema;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Order;
import command.ActionCommand;
import utils.ConfigurationManager;
import utils.Util;

public class ShowOrdersCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		List<Order> list = Util.getInstance().showOrders();
		request.setAttribute("list", list);	
		HttpSession session = request.getSession(true);
		session.setAttribute("list", list);
		page = ConfigurationManager.getProperty("path.page.showorder");
		return page;
	}
}
