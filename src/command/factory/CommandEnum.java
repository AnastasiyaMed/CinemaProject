package command.factory;

import command.ActionCommand;
import command.cinema.DeleteOrderCommand;
import command.cinema.GoToDeleteOrderCommand;
import command.cinema.GoToHeadCommand;
import command.cinema.ReservationCommand;
import command.cinema.ShowOrdersByNumberCommand;
import command.cinema.ShowOrdersCommand;
import command.cinema.ShowScheduleCommand;

public enum CommandEnum {
	SHOW_SCHEDULE {
		{
			this.command = new ShowScheduleCommand();
		}
	},
	SHOW_ORDER {
		{
			this.command = new ShowOrdersCommand();
		}
	},
	DELETE_ORDER {
		{
			this.command = new DeleteOrderCommand();
		}
	},
	GO_TO_HEAD {
		{
			this.command = new GoToHeadCommand();
		}
	},
	GO_TO_DELETE_ORDER {
		{
			this.command = new GoToDeleteOrderCommand();
		}
	},
	RESERVATION {
		{
			this.command = new ReservationCommand();
		}
	},
	SHOWORDERBYNUMBER {
		{
			this.command = new ShowOrdersByNumberCommand();
		}
	};

	ActionCommand command;

	public ActionCommand getCurrentCommand() {
		return command;
	}
}
