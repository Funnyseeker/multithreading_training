package fun.trainings.mthrd.exceptions;

public class InvalidNumberOfParams extends Exception {
    public InvalidNumberOfParams(String className, String method) {
        super(String.format("Invalid number of imput params! Method {%s} in class {%s}"));
    }
}
