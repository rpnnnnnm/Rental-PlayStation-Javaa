package rentalps.controller;

public class AuthController {
    public static boolean login(String username, String password) {
        return "admin".equals(username) && "admin123".equals(password);
        
    }
}
