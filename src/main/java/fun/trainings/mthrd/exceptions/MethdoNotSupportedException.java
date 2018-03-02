package fun.trainings.mthrd.exceptions;

public class MethdoNotSupportedException extends Exception {
    public MethdoNotSupportedException(String className, String method) {
        super(String.format("Method {%s} not supported in class {%s}", method, className));
    }
}
