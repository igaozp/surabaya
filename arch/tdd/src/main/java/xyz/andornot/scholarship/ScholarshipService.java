package xyz.andornot.scholarship;

public class ScholarshipService {
    private final Calculator bachelorScholarshipCalculator = new BachelorScholarshipCalculator();
    private final Calculator masterScholarshipCalculator = new MasterScholarshipCalculator();
    private final Calculator phdScholarshipCalculator = new PhDScholarshipCalculator();

    public int calculate(Transcript transcript) throws UnknownProgramTypeException {

        var programType = transcript.getProgramType();

        if (programType.equals("Bachelor")) {
            return bachelorScholarshipCalculator.calculate(transcript);
        }

        if (programType.equals("Master")) {
            return masterScholarshipCalculator.calculate(transcript);
        }

        if (programType.equals("PhD")) {
            return phdScholarshipCalculator.calculate(transcript);
        }

        throw new UnknownProgramTypeException(programType);
    }

    public static class UnknownProgramTypeException extends Throwable {
        public UnknownProgramTypeException(String programType) {
            super("Unknown program type: " + programType);
        }
    }
}
