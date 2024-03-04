package src.service;

import src.model.Reader;
import src.repository.ReaderRepository;
import src.util.MyList;

public class ReaderService {

    private Reader activeReader;
    private final ReaderRepository readerRepository;

    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public MyList<Reader> getAllReaders() {
        return readerRepository.getAllReaders();
    }

    public Reader getActiveReader() {
        return activeReader;
    }

    public boolean isEmailValid(String email) {
        if (email == null || email.isEmpty()) return false;

        int indexAt = email.indexOf('@');
        if (indexAt == -1 || indexAt != email.lastIndexOf('@')) return false;

        int indexFirstDotAfterAt = email.indexOf('.', indexAt);

        if (indexFirstDotAfterAt == -1 || indexFirstDotAfterAt == indexAt + 1) return false;

        if (email.lastIndexOf('.') >= email.length() - 2) return false;

        boolean isCharAlphabetic = Character.isAlphabetic(email.charAt(0))
                && Character.isAlphabetic(email.charAt(email.length() - 1))
                && Character.isAlphabetic(email.charAt(email.length() - 2));
        if (!isCharAlphabetic) return false;

        for (int i = 0; i < email.length(); i++) {
            char c = email.charAt(i);

            boolean isCharValid = (Character.isAlphabetic(c)
                    || Character.isDigit(c)
                    || c == '-'
                    || c == '_'
                    || c == '@'
                    || c == '.');
            if (!isCharValid) return false;
        }
        return true;
    }

    public boolean isPasswordValid(String password) {

        if (password == null || password.isEmpty()) return false;

        if (password.length() < 8) return false;

        boolean isLowerCase = false;
        boolean isUpperCase = false;
        boolean isDigit = false;
        boolean isSpecialSymbol = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if (Character.isDigit(c)) {
                isDigit = true;
                //res[0] = true;
                continue;
            }
            if (Character.isLowerCase(c)) {
                isLowerCase = true;
                //res[1] = true;
                continue;
            }
            if (Character.isUpperCase(c)) {
                isUpperCase = true;
                continue;
            }
            if ("!@#$%&*()[]".indexOf(c) >= 0) {
                isSpecialSymbol = true;
                continue;
            }
        }
        return isLowerCase && isUpperCase && isDigit && isSpecialSymbol;
    }

    public Reader registerNewReader(String email, String password) {
        Reader reader = readerRepository.addNewReader(email, password);
        return reader;
    }

    public Reader authoriseReader(String email, String password) {
        Reader reader = activeReader;
        return reader;
    }

}
