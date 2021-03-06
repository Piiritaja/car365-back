package ee.taltech.cars.c_theory.question14.chairs;


import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("chair")
@RestController
public class ChairsController {

    //todo for question 14 there are 4 assignments in total
    // Each person has to do only 1. So 2 person team has to do 2 different ones, 3 person - 3, 4 person - 4.
    // Make sure to commit under your user otherwise points won't count.
    // I didn't number these so you can pick your favorite

    //todo
    // You are creating a rest controller for chairs.
    // Think a backoffice system for furniture shop like Aatrium or some Kalamaja chair boutique.
    // You need to add necessary annotations and methods to this class.
    // This class should compile.
    // It should run successfully when moved to your application package.
    // Method body is not important and will not be graded.
    // Modifying other classes is unnecessary and will not be graded.

    //todo A add necessary annotations on the class

    //todo B create a method to query chairs (plural)

    //todo C create a method to query single chair

    //todo D create a method to save a chair

    //todo E create a method to update a chair

    //todo F create a method to delete a chair

    //todo G assuming each chair has a designer (one-to-one relation) create a method to query chair's designer

    //todo H create a method to update chair's name (and nothing else)

    //todo I modify correct method to support searching chairs by type while keeping original functionality

    //todo J modify correct method to support searching chairs by whether chair is in stock while keeping original functionality

    //todo K modify correct method to order/sort chairs
    // * by lowest priced first
    // * by highest priced first
    // (you can assume that by default it searches most popular first)

    @GetMapping
    public List<Chair> getChairs(@RequestParam(required = false) String chairType,
                                 @RequestParam(required = false) boolean inStock,
                                 @RequestParam(required = false, defaultValue = "MOST_POPULAR") Order order) {
        return getSortedChairs(order);
    }

    @GetMapping("{id}")
    public Chair getChair(@PathVariable String id) {
        return new Chair();
    }

    @PostMapping
    public Chair postChair(@RequestBody Chair chair) {
        return new Chair();
    }

    @PutMapping
    public Chair updateChair(@RequestBody Chair chair) {
        // service.updateChair(id, chair)
        return chair;
    }

    @DeleteMapping("{id}")
    public void deleteChair(@PathVariable String id) {
        // service.deleteChair(id);
    }

    @GetMapping("{id}/designer")
    public Designer getDesigner(@PathVariable String id) {
        // service.getDesigner(id);
        return new Designer();
    }

    @PutMapping("{id}")
    public Chair updateChairName(@PathVariable String id, @RequestBody String newName) {
        // service.updateChairName(id, newName);
        return new Chair();
    }

    public List<Chair> getSortedChairs(Order order) {
        if (order.equals(Order.MOST_POPULAR)) {
            // sort by most popular first
            return new ArrayList<>();
        } else if (order.equals(Order.CHEAPER_FIRST)) {
            // sort cheaper chairs first
            return new ArrayList<>();
        } else {
            // sort more expensive first
            return new ArrayList<>();
        }
    }

    private enum Order {
        MOST_POPULAR,
        CHEAPER_FIRST,
        EXPENSIVE_FIRST
    }
}
