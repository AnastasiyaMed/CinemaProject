package command.cinema;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Order;
import command.ActionCommand;
import exeption.CustomFileNotFoundExeption;
import utils.ConfigurationManager;
import utils.MessageManager;
import utils.Util;

public class ReservationCommand implements ActionCommand {

	private final String TIME = "time";
	private final String PLACE = "place";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		String goTime = request.getParameter(TIME);
		String goPlace = request.getParameter(PLACE);
		try {
		if ((Util.getInstance().checkFindSchedule(goTime)) == false) {
			request.setAttribute("errorTimeMessage", MessageManager.getProperty("message.errorTimeMessage"));
			page = ConfigurationManager.getProperty("path.page.showschedule");
		} else if ((Util.getInstance().checkFreePlace(goPlace, goTime)) == false) {
				request.setAttribute("errorPlaceMessage", MessageManager.getProperty("message.errorPlaceMessage"));
				return page = ConfigurationManager.getProperty("path.page.showschedule");
		} else if (((Util.getInstance().checkFindSchedule(goTime)) == true) && ((Util.getInstance().checkFreePlace(goPlace, goTime)) == true)) {
				Order order = new Order();
				order = Util.getInstance().createNewOrder(goTime, goPlace);
				List<Order> orderList = new ArrayList<>();
				orderList.add(order);
				request.setAttribute("order", orderList);
				request.setAttribute("time", goTime);
				HttpSession session = request.getSession(true);
				int numberOrder = order.getNumberOfOrder();
				session.setAttribute("numberOrder", numberOrder);
				page = ConfigurationManager.getProperty("path.page.order");
		}
		} catch (CustomFileNotFoundExeption e) {
			request.setAttribute("exeptionMessage", MessageManager.getProperty("message.exeptionMessage"));
			page = ConfigurationManager.getProperty("path.page.showschedule");
		}
		return page;
	}
}
