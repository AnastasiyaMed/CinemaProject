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

public class DeleteOrderCommand implements ActionCommand {
	
	private final String ORDER = "order";
	
	@Override
	public String execute(HttpServletRequest request) {
		int orderNumber = 0;
		int countOrder = 0;
		String page = null;
		if (request.getParameter(ORDER).matches(("\\d+")) == true) {
			orderNumber = Integer.parseInt(request.getParameter(ORDER));
		} else {
			request.setAttribute("errorOrderMessage", MessageManager.getProperty("message.errorOrderMessage"));
			page = ConfigurationManager.getProperty("path.page.deleteorder");
		}

		try {
			if ((Util.getInstance().checkNumberOfOrder(orderNumber)) == false) {
				request.setAttribute("errorOrderMessage", MessageManager.getProperty("message.errorOrderMessage"));
				page = ConfigurationManager.getProperty("path.page.deleteorder");
			} else {
				Util.getInstance().deleteOrder(orderNumber);
				List<Order> list;
				list = Util.getInstance().showOrders();
				countOrder = list.size();
				request.setAttribute("list", list);
				HttpSession session = request.getSession(true);
				session.setAttribute("list", list);
				session.setAttribute("countOrder", countOrder);
				page = ConfigurationManager.getProperty("path.page.showorder");
			}
		} catch (CustomFileNotFoundExeption e) {
			request.setAttribute("exeptionMessage", MessageManager.getProperty("message.exeptionMessage"));
			page = ConfigurationManager.getProperty("path.page.deleteorder");
		}
		return page;
	}
}
