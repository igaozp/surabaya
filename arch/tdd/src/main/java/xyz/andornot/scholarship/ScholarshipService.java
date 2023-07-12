package xyz.andornot.scholarship;

public class ScholarshipService {
    private final Calculator bachelorScholarshipCalculator = new BachelorScholarshipCalculator();
    private final Calculator masterScholarshipCalculator = new MasterScholarshipCalculator();
    private final Calculator phdScholarshipCalculator = new PhDScholarshipCalculator();

    public int calculate(Transcript transcript) throws UnknownProgramTypeException {

        var programType = transcript.getProgramType();

        var calculator = findCalculator(programType);
        return calculator.calculate(transcript);
    }

    private Calculator findCalculator(String programType) throws UnknownProgramTypeException {
        return switch (programType) {
            case "Bachelor" -> bachelorScholarshipCalculator;
            case "Master" -> masterScholarshipCalculator;
            case "PhD" -> phdScholarshipCalculator;
            default -> throw new UnknownProgramTypeException(programType);
        };
    }

    public static class UnknownProgramTypeException extends Throwable {
        public UnknownProgramTypeException(String programType) {
            super("Unknown program type: " + programType);
        }
    }
}
