package by.bsuir.group172301.matskevich.tour.command;

import by.bsuir.group172301.matskevich.tour.entity.Role;
import by.bsuir.group172301.matskevich.tour.entity.User;

/**
 * Client command
 */
public abstract class ClientCommand extends ActionCommand{
	@Override
	public boolean checkAccess(User user) {
		if (user != null){
			String rolename = user.getRole().getRolename();
			return rolename.equals(Role.ROLE_ADMIN);
		}

		return false;
	}
}
