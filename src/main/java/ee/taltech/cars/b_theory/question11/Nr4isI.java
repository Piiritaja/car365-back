package ee.taltech.cars.b_theory.question11;

public class Nr4isI {

    //todo this is a contribution based question so make sure to keep commits separate
    //todo A What does I stand for in SOLID? Explain the principle. I stands for interface segregation. This means that
    // your interfaces shouldn't have that methods other classes extending those interfaces do not use. Rather you should
    // separate those methods to other interfaces.

}


//todo B Give an example. Write actual or pseudo code.
// we know a friend called Mikk who is a refrigerator enthusiast. He owns many and has also developed a program to control all of them.
// He uses a refrigerator interface that his refrigerators implement.
interface Refrigerator{
    int countBeer();
    void setTemperature();
}

//todo every refrigerator Mikk owns use the methods defined in the interface.
class favoriteFridge implements Refrigerator {

    @Override
    public int countBeer() {
        return 0;
    }

    @Override
    public void setTemperature() {

    }
}

//todo One day Mikk buys a set of new refrigerators that can also play Skyrim.
// Mikk quickly modifies his program and adds a new method to his interface:
interface RefrigeratorWrong{
    int countBeer();
    void setTemperature();
    void playSkyrim();
}
//todo Now this interface has a unnecessary, because not every refrigerator can play Skyrim.

//todo this is what Mikk should have done.
// define a new interface for refrigerators that can play Skyrim:
interface RefrigeratorSkyrim extends Refrigerator{
    void playSkyrim();
}

//todo and keep the old refrigerator interface as it was:
interface RefrigeratorCorrect{
    int countBeer();
    void setTemperature();
}
