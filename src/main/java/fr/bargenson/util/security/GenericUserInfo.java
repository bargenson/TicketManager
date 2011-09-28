package fr.bargenson.util.security;

import java.util.Arrays;
import java.util.List;

public class GenericUserInfo implements UserInfo {
	
	private String username;
	private char[] password;
	private List<String> roleNames;
	
	public GenericUserInfo(String username, char[] password, List<String> roleNames) {
		this.username = username;
		this.password = Arrays.copyOf(password, password.length);
		this.roleNames = roleNames;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public List<String> getRoleNames() {
		return roleNames;
	}

	@Override
	public boolean checkPassword(char[] suppliedPassword) {
		return Arrays.equals(password, suppliedPassword);
	}

	@Override
	public char[] getPassword() {
		return Arrays.copyOf(password, password.length);
	}

}
