package ee.taltech.cars.b_theory.question11;

import lombok.Getter;
import lombok.Setter;

public class Nr2isO {

    //todo this is a contribution based question so make sure to keep commits separate
    //todo A What does O stand for in SOLID? Explain the principle.
    // Answer: O stands for Open/closed principle. This means that software classes (entities) should be open for
    // extension, but closed for changes. This is to stop causing any problems to old (working code) by making
    // changes to it. Instead the existing code can be used as a base and be extended (by using it as a parent).


    //todo B Give an example. Write actual or pseudo code.
}

//todo For example we have a class Dog.
@Getter
@Setter
abstract
class Dog {

    private String gender;
    private String name;
    private double energy;

    public Dog(String gender, String name) {
        this.gender = gender;
        this.name = name;
        this.energy = 1;
    }

    public void feed(int food) {
        this.setEnergy(this.getEnergy() + food);
    }
}

//todo
// As we develop our application we discover that larger dogs need more food than smaller dogs to gain energy.
// We add 'size' attribute and an if statement to our 'feed' method, but this might damage our code;
@Getter
@Setter
class DogWrong {

    private String size;
    private String gender;
    private String name;
    private double energy;

    public DogWrong(String size, String gender, String name) {
        this.size = size;
        this.gender = gender;
        this.name = name;
        this.energy = 1;
    }

    public void feed(double food) {
        if (size.equals("large")) {
            this.setEnergy(this.getEnergy() + (food / 2));
        } else {
            this.setEnergy(this.getEnergy() + food);
        }
    }

}

//todo
// According to the Open/Closed principle, the correct way is to extend our original Dog class, to avoid
// causing bugs or malfunctions in our codes work.
@Getter
@Setter
class LargeDog extends Dog {

    public LargeDog(String gender, String name) {
        super(gender, name);
    }

    public void feed(double food) {
        this.setEnergy(this.getEnergy() + (food / 2));
    }
}



