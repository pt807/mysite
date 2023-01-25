package com.douzone.mysite.web.mvc.guestbook;

import com.douzone.mysite.web.mvc.main.MainAction;
import com.douzone.web2.mvc.Action;
import com.douzone.web2.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;

		if ("list".equals(actionName)) {
			action = new GuestbookListAction();
		} else if ("insert".equals(actionName)) {
			action = new GuestbookInsertAction();
		} else if ("deleteform".equals(actionName)) {
			action = new GuestbookDeleteFormAction();
		} else if ("delete".equals(actionName)) {
			action = new GuestbookDeleteAction();
		} else {
			action = new MainAction();
		}

		return action;
	}

}
