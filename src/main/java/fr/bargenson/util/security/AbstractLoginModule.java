package fr.bargenson.util.security;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public abstract class AbstractLoginModule implements LoginModule {

	private CallbackHandler callbackHandler;
	private Subject subject;
	private boolean authenticated;
	private JAASUserInfo currentUser;
	private boolean commited;

	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {

		this.subject = subject;
		this.callbackHandler = callbackHandler;
	}

	@Override
	public boolean login() throws LoginException {
		try {  
			if (callbackHandler == null)
				throw new LoginException ("No callback handler");

			Callback[] callbacks = buildCallbacks();
			callbackHandler.handle(callbacks);

			String webUsername = ((NameCallback)callbacks[0]).getName();
			char[] webPassword = ((PasswordCallback)callbacks[1]).getPassword();

			if ((webUsername == null) || (webPassword == null)) {
				setAuthenticated(false);
				return isAuthenticated();
			}

			UserInfo userInfo = getUserInfo(webUsername);

			if (userInfo == null) {
				setAuthenticated(false);
				return isAuthenticated();
			}

			currentUser = new JAASUserInfo(userInfo);
			
			setAuthenticated(currentUser.checkPassword(webPassword));
			return isAuthenticated();
		} catch (IOException e) {
			throw new LoginException (e.toString());
		} catch (UnsupportedCallbackException e) {
			throw new LoginException (e.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoginException (e.toString());
		}
	}

	protected abstract UserInfo getUserInfo (String username) throws Exception;

	private Callback[] buildCallbacks () {
		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("Username");
		callbacks[1] = new PasswordCallback("Password", false);
		return callbacks;
	}

	private void setAuthenticated (boolean authState) {
		this.authenticated = authState;
	}

	private boolean isAuthenticated () {
		return authenticated;
	}

	@Override
	public boolean commit() throws LoginException {
		if (!isAuthenticated()) {
            currentUser = null;
            setCommitted(false);
            return false;
        }
        
        setCommitted(true);
		currentUser.setJAASInfo(subject);
		return true;
	}
	
	private void setCommitted(boolean b) {
		this.commited = b;
	}

	@Override
	public boolean abort() throws LoginException {
		this.currentUser = null;
        return (isAuthenticated() && isCommitted());
	}

	private boolean isCommitted() {
		return commited;
	}

	@Override
	public boolean logout() throws LoginException {
		currentUser.unsetJAASInfo(subject);
		return true;
	}
	
	private static class JAASUserInfo implements UserInfo {
		
        private UserInfo user;
        private Principal principal;
        private List<JAASRole> roles;
              
        public JAASUserInfo(UserInfo u) {
            setUserInfo(u);
        }
        
        public String getUsername() {
            return user.getUsername();
        }
        
        public void setUserInfo (UserInfo u) {
            this.user = u;
            this.principal = new JAASPrincipal(u.getUsername());
            this.roles = new ArrayList<JAASRole>();
            if (u.getRoleNames() != null)
            {
                Iterator<String> itor = u.getRoleNames().iterator();
                while (itor.hasNext())
                    this.roles.add(new JAASRole(itor.next()));
            }
        }
               
        public void setJAASInfo (Subject subject) {
            subject.getPrincipals().add(this.principal);
            subject.getPrivateCredentials().add(this.user.getPassword());
            subject.getPrincipals().addAll(roles);
        }
        
        public void unsetJAASInfo (Subject subject) {
            subject.getPrincipals().remove(this.principal);
            subject.getPrivateCredentials().remove(this.user.getPassword());
            subject.getPrincipals().removeAll(roles);
        }
        
        public boolean checkPassword (char[] suppliedCredential) {
            return this.user.checkPassword(suppliedCredential);
        }

		@Override
		public char[] getPassword() {
			return user.getPassword();
		}

		@Override
		public List<String> getRoleNames() {
			return user.getRoleNames();
		}
    }

}
