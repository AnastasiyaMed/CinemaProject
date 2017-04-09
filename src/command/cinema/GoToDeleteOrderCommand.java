package command.cinema;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import beans.Order;
import command.ActionCommand;
import exeption.CustomFileNotFoundExeption;
import utils.ConfigurationManager;
import utils.MessageManager;
import utils.Util;

public class GoToDeleteOrderCommand implements ActionCommand {
	
	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		List<Order> list = new ArrayList<>();
		try {
			list = Util.getInstance().showOrders();
			request.setAttribute("list", list);
			page = ConfigurationManager.getProperty("path.page.deleteorder");
		} catch (CustomFileNotFoundExeption e) {
			request.setAttribute("exeptionMessage", MessageManager.getProperty("message.exeptionMessage"));
			page = ConfigurationManager.getProperty("path.page.deleteorder");
		}
		
		return page;
	}

}
