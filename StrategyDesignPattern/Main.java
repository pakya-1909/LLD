package StrategyDesignPattern;

interface AuthenticateStrategy{
    boolean authenticate(String user, String password);
}

class GitHubAuth implements AuthenticateStrategy{

    @Override
    public boolean authenticate(String user, String password) {
        return false;
    }
}

class Passcode implements AuthenticateStrategy{

    @Override
    public boolean authenticate(String user, String password) {
        return false;
    }
}

class GoogleAuth implements AuthenticateStrategy{

    @Override
    public boolean authenticate(String user, String password) {
        return false;
    }
}

class Authenticator{
    private final AuthenticateStrategy authenticateStrategy;

    public Authenticator(AuthenticateStrategy authenticateStrategy){
        this.authenticateStrategy = authenticateStrategy;
    }


    public boolean login(String user, String password){
        return authenticateStrategy.authenticate(user, password);
    }
}


public class Main {
    public static void main(String[] args) {

        Authenticator authenticator = new Authenticator(new GitHubAuth());
        boolean flag = authenticator.login("pankaj", "1234");
    }
}
