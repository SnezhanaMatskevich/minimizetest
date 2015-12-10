package by.bsuir.group172301.matskevich.tour.entity;

import by.bsuir.group172301.matskevich.tour.entity.Entity;

/**
 * User role
 */
public class Role extends Entity{

	public static final String ROLE_ADMIN = "admin";
	public static final String ROLE_CLIENT = "client";

	/**
	 * Role name
	 */
	private String rolename;


    /**
     * Get role name
     * @return
     */
	public String getRolename() {
		return rolename;
	}

    /**
     * Set role name
     * @param rolename
     */
	public void setRolename(String rolename) {
		this.rolename = ROLE_ADMIN;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Role role = (Role) o;

		if (!rolename.equals(role.rolename)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return rolename.hashCode();
	}
}
