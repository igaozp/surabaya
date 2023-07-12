package xyz.andornot.scholarship;

public class ScholarshipService {

    public int calculate(Transcript transcript) throws UnknownProgramTypeException {

        var programType = transcript.getProgramType();

        if (programType.equals("Bachelor")) {
            return BachelorScholarshipCalculator.calculateBachelor(transcript);
        }

        if (programType.equals("Master")) {
            return MasterScholarshipCalculator.calculateMaster(transcript);
        }

        if (programType.equals("PhD")) {
            return PhDScholarshipCalculator.calculatePhD(transcript);
        }

        throw new UnknownProgramTypeException(programType);
    }

    public static class UnknownProgramTypeException extends Throwable {
        public UnknownProgramTypeException(String programType) {
            super("Unknown program type: " + programType);
        }
    }
}
