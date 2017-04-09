package command.cinema;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Order;
import command.ActionCommand;
import exeption.CustomFileNotFoundExeption;
import utils.ConfigurationManager;
import utils.MessageManager;
import utils.Util;

public class ShowOrdersCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		int count = 0;
		try {
		List<Order> list = Util.getInstance().showOrders();
			count = list.size();
		request.setAttribute("list", list);	
		HttpSession session = request.getSession(true);
		session.setAttribute("list", list);
			session.setAttribute("countOrder", count);
		page = ConfigurationManager.getProperty("path.page.showorder");
		} catch (CustomFileNotFoundExeption e) {
			request.setAttribute("exeptionMessage", MessageManager.getProperty("message.exeptionMessage"));
			page = ConfigurationManager.getProperty("path.page.index");
		}
		return page;
	}
}
