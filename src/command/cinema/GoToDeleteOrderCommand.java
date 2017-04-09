package command.cinema;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import beans.Order;
import command.ActionCommand;
import utils.ConfigurationManager;
import utils.Util;

public class GoToDeleteOrderCommand implements ActionCommand {
	
	@Override
	public String execute(HttpServletRequest request) {
		@SuppressWarnings("unused")
		String page;
		List<Order> list = Util.getInstance().showOrders();
		request.setAttribute("list", list);	
		return page = ConfigurationManager.getProperty("path.page.deleteorder");
		
	}

}
