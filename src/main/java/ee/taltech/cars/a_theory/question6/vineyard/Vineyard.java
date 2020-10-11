package ee.taltech.cars.a_theory.question6.vineyard;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Vineyard {

    //todo for question 6 there are 4 assignments in total
    // Each person has to do only 1. So 2 person team has to do 2 different ones, 3 person - 3, 4 person - 4.
    // Make sure to commit under your user otherwise points won't count.
    // I didn't number these so you can pick your favorite

    //todo create a working api for a wineyard
    // It compiles and runs. You need to use proper annotations, methods, etc, however to ease the process you can use empty methods (examples below).
    // Follow the story to have only the necessary methods in it

    //todo 1
    // Maxime retired to start a vineyard business.
    // As people are shopping more online Maxime has decided to follow the trend.
    // His cousin Garpard recently started a job with a major software firm in Paris.
    // Maxime writes:
    // Dear Gaspard!
    // I want to share my sweet vines with all the Parisians.
    // I want to create a web shop with the best selection!
    // Imagine! People can view a list of my magnificent wines:
    // they can filter by the region (optional), year (optional),
    // they can search by name (optional) and grape (optional).
    // After they search they can click on a wine and see a detailed view with the best description.
    // I do not need to add new wines because I have excel-macro done by Josephine.
    // However as she is only 5 years old I need to update the region, description, grape etc.
    // Create me the best back-end API!
    // Uncle Maxime


    List<Wine> wines = new ArrayList<>();

    @GetMapping("")
    public List<Wine> getAll() {
        return List.of();
    }

    @GetMapping("")
    public List<Wine> getByRegion(@RequestParam(required = false) String region) {
        return wines.stream().filter(wine -> wine.getRegion().equals(region)).collect(Collectors.toList());
    }

    @GetMapping("")
    public List<Wine> getByYear(@RequestParam(required = false) int year) {
        return wines.stream().filter(wine -> wine.getYear() == year).collect(Collectors.toList());
    }

    @GetMapping("")
    public List<Wine> getByName(@RequestParam(required = false) String name) {
        return wines.stream().filter(wine -> wine.getName().equals(name)).collect(Collectors.toList());
    }

    @GetMapping("")
    public List<Wine> getByGrape(@RequestParam(required = false) String grape) {
        return wines.stream().filter(wine -> wine.getGrape().equals(grape)).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public Optional<Wine> getGrapeById(@PathVariable Long id) {
        return wines.stream().filter(wine -> wine.getId().equals(id)).findAny();
    }

    public Optional<Wine> getWineById(Long id) {
        return wines.stream().filter(wine1 -> wine1.getId().equals(id)).findAny();
    }

    @PutMapping("{id}")
    public void updateWine(@RequestBody Wine wine, @PathVariable Long id) {
        Optional<Wine> wineOptional = getWineById(id);
        wineOptional.ifPresent(wine1 -> wines.set(wines.indexOf(wine1), wine));
    }

    // they can click on a wine and see a detailed view with the best description
    @PutMapping("{id}")
    public void updateWineRegion(@RequestParam String region, @PathVariable Long id) {
        getWineById(id).ifPresent(wine -> wine.setRegion(region));
    }
}
