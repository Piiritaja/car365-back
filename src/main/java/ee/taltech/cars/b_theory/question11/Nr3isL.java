package ee.taltech.cars.b_theory.question11;

import lombok.Getter;
import lombok.Setter;

public class Nr3isL {

    //todo this is a contribution based question so make sure to keep commits separate
    //todo A What does L stand for in SOLID? Explain the principle.
    // Answer: L stands for Liskov Substitution principle. The principle defines that objects of a superclass shall be
    // replaceable with objects of its subclasses without breaking the application.
    // That requires the objects of subclasses to behave in the same way as the objects of superclass.
    //todo B Give an example. Write actual or pseudo code.
}
//todo Let's say we have a class Vehicle, that has name and engine.
@Getter
@Setter
class Vehicle {

    String name;
    String engine;

    void startEngine(String engine) { }
}

//todo And we have class Car that extends Vehicle
class Car extends Vehicle {

    @Override
    void startEngine(String engine) { }
}

//todo But if we want to add bicycle, we couldn't because bicycles do not have engine
// so we can't implement startEngine method.
// So we add new more specified classes that extend vehicle.

@Getter
@Setter
class VehicleAfter {
    String name;
}

@Getter
@Setter
class VehiclesWithEngine extends VehicleAfter {

    String engine;

    void startEngine(String engine) { }
}

class CarAfter extends VehiclesWithEngine {

    @Override
    void startEngine(String engine) { }
}

class VehiclesWithoutEngine extends VehicleAfter {

    void startMoving() { }
}

class Bicycle extends VehiclesWithoutEngine {

    @Override
    void startMoving() { }
}

//todo And now it enables us to replace objects of a parent
// class with objects of a subclass without braking anything.
