package lang.record;

sealed interface TwoDimensional {
}

public class RecordExample {

    boolean recordPattern(Object obj) {
        if (obj instanceof Person person) {
            return person.age() >= 18;
        }
        return false;
    }

    boolean anotherRecordPattern(Object obj) {
        if (obj instanceof Person(String name, int age)) {
            return age >= 20;
        }
        return false;
    }

    boolean checkFirstNameAndCountryCodeAgain(Object obj) {
        if (obj instanceof Passenger(
                Name(var name, var lastName),
                var phoneNumber,
                Country from,
                Country(var countryCode, var countryName)
        )) {
            if (name != null && countryCode != null) {
                return name.startsWith("Simo") && countryCode.equals("PRG");
            }
        }
        return false;
    }

    void unwrap(Gift<WristWatch> obj) {
        if (obj instanceof Gift<WristWatch>(var watch)) {
            System.out.println(watch);
        }
    }

    int sealedClassPattern(TwoDimensional twoDim) {
        return switch (twoDim) {
            case Point(int x, int y) -> x + y;
            case Line(var a, var b) -> a.x() + b.x();
            case Triangle(Point a, Point b, Point c) -> a.x() + b.x() + c.x();
            case Square(var a, var b, var c, var d) -> a.x() + b.x() + c.x() + d.x();
        };
    }
}

record Person(String name, int age) {
}

record Name(String name, String lastName) {
}

record PhoneNumber(String areaCode, String number) {
}

record Country(String countryCode, String countryName) {
}

record Passenger(Name name, PhoneNumber phoneNumber, Country from, Country destination) {
}

class WristWatch {
}

record Gift<T>(T t) {
}

record Point(int x, int y) implements TwoDimensional {
}

record Line(Point start, Point end) implements TwoDimensional {
}

record Triangle(Point pointA, Point pointB, Point pointC) implements TwoDimensional {
}

record Square(Point pointA, Point pointB, Point pointC, Point pointD) implements TwoDimensional {
}
