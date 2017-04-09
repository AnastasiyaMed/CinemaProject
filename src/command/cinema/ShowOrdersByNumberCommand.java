package command.cinema;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Order;
import command.ActionCommand;
import utils.ConfigurationManager;
import utils.MessageManager;
import utils.Util;

public class ShowOrdersByNumberCommand implements ActionCommand {
	private final String ORDER = "order";
	@Override
	public String execute(HttpServletRequest request) {
		int orderNumber = 0;
		String page = null;
		String time;
		List<String> placesOrder;

		if (request.getParameter(ORDER).matches(("\\d+")) == true) {
			orderNumber = Integer.parseInt(request.getParameter(ORDER));
		} else {
			request.setAttribute("errorOrderMessage", MessageManager.getProperty("message.errorOrderMessage"));
			page = ConfigurationManager.getProperty("path.page.showorder");
		}
		if ((Util.getInstance().showFindOrder(orderNumber)) == null) {
			request.setAttribute("errorOrderMessage", MessageManager.getProperty("message.errorOrderMessage"));
			page = ConfigurationManager.getProperty("path.page.showorder");
		} else {
		Order order = Util.getInstance().showFindOrder(orderNumber);
		List<Order> orderList = new ArrayList<>();
		orderList.add(order);
		placesOrder = order.getPlacesOrder();
		time = order.getShowTime();
		request.setAttribute("order", orderList);
		request.setAttribute("placesOrder", placesOrder);
		request.setAttribute("time", time);
		request.setAttribute("numberOrder", orderNumber);
		HttpSession session = request.getSession(true);
		session.setAttribute("numberOrder", orderNumber);
		page = ConfigurationManager.getProperty("path.page.order");
		}
		return page;
	}
}
