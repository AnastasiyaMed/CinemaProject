package command.cinema;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import beans.Schedule;
import command.ActionCommand;
import utils.ConfigurationManager;
import utils.Util;


public class ShowScheduleCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		List<Schedule> list;
		list = Util.getInstance().showSchedule();
		request.setAttribute("list", list);
		HttpSession session = request.getSession(true);
		session.setAttribute("list", list);
		page = ConfigurationManager.getProperty("path.page.showschedule");
		return page;
		
	}

}
