package ee.taltech.cars.c_theory.question14.phones;

import ee.taltech.cars.c_theory.question14.lessons.Lesson;
import ee.taltech.cars.c_theory.question14.lessons.LessonsController;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("phone")
@RestController
public class PhonesController {
    private enum OrderBy {
        EARLIEST, LATEST
    }

    //todo for question 14 there are 4 assignments in total
    // Each person has to do only 1. So 2 person team has to do 2 different ones, 3 person - 3, 4 person - 4.
    // Make sure to commit under your user otherwise points won't count.
    // I didn't number these so you can pick your favorite

    //todo
    // You are creating a rest controller for lessons. Think of a phone shop.
    // You need to add necessary annotations and methods to this class.
    // This class should compile.
    // It should run successfully when moved to your application package.
    // Method body is not important and will not be graded.
    // Modifying other classes is unnecessary and will not be graded.

    //todo A add necessary annotations on the class [x]

    //todo B create a method to query phones (plural) [x]

    //todo C create a method to query single phone [x]

    //todo D create a method to save a phone [x]

    //todo E create a method to update a phone [x]

    //todo F create a method to delete a phone [x]

    //todo G assuming each phone has apps installed (one-to-many relation) create a method to query phone's apps [x]

    //todo H create a method to update phone's price (and nothing else) [x]

    //todo I modify correct method to support searching by manufacturer while keeping original functionality [x]

    //todo J modify correct method to support searching by price range: priceFrom-priceTo while keeping original functionality [x]

    //todo K modify correct method to order/sort chairs []
    // * by latest released date first
    // * by earliest released date first
    // (you can assume that by default it searches most popular first)
    // !!!!! Description seems to be wrong, assuming 'latest' > 'most popular' !!!!!


    @GetMapping
    public List<Phone> getPhones(@RequestParam(required = false) String manufacturer,
                                 @RequestParam(required = false, defaultValue = "0") int priceFrom,
                                 @RequestParam(required = false) int priceTo,
                                 @RequestParam(required = false, defaultValue = "LATEST") OrderBy order) {
        return new ArrayList<>();
    }

    @GetMapping("{id}")
    public Phone getPhone(@PathVariable long id) {
        return new Phone();
    }

    @PostMapping
    public Phone postPhone(@RequestBody Phone phone) {
        return phone;
    }

    @PutMapping("{id}")
    public Phone updatePhone(@RequestBody Phone phone, @PathVariable long id) {
        return new Phone();
    }

    @DeleteMapping("{id}")
    public Phone deletePhone(@PathVariable long id) {
        return new Phone();
    }

    @GetMapping("{id}/apps")
    public List<App> getApps(@PathVariable long id) {
        return new ArrayList<>();
    }

    @PatchMapping("{id}")
    public Phone partialUpdatePrice(@PathVariable long id, @RequestBody PhoneOnlyPrice partialUpdate) {
        return new Phone();
    }


}
