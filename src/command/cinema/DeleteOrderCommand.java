package command.cinema;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import beans.Order;
import command.ActionCommand;
import utils.ConfigurationManager;
import utils.MessageManager;
import utils.Util;

public class DeleteOrderCommand implements ActionCommand {
	
	private final String ORDER = "order";
	
	@Override
	public String execute(HttpServletRequest request) {
		int orderNumber = 0;
		String page = null;
		if (request.getParameter(ORDER).matches(("\\d+")) == true) {
			orderNumber = Integer.parseInt(request.getParameter(ORDER));
		} else {
			request.setAttribute("errorOrderMessage", MessageManager.getProperty("message.errorOrderMessage"));
			page = ConfigurationManager.getProperty("path.page.deleteorder");
		}
		
		
		if ((Util.getInstance().checkNumberOfOrder(orderNumber)) == false) {
			request.setAttribute("errorOrderMessage", MessageManager.getProperty("message.errorOrderMessage"));
			page = ConfigurationManager.getProperty("path.page.deleteorder");
		} else {
		Util.getInstance().deleteOrder(orderNumber);
		List<Order> list;
		list = Util.getInstance().showOrders();
		request.setAttribute("list", list);		
		page = ConfigurationManager.getProperty("path.page.showorder");
		}
		return page;
	}
}
