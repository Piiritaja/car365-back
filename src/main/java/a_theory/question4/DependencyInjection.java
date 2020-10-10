package a_theory.question4;

public class DependencyInjection {

    //todo
    // One of the reasons we use Spring is that it gives us good support for
    // Dependency Injection (DI) and Inversion of Control (IoC)

    //todo p1
    // In your words (do not use wiki definitions)
    // What is Dependency Injection?
    // Answer: Instead of an object creating a different object that it needs itself, the different object is instead given to the object
    // from other sources.

    //todo p2
    // Bring example from your code of Dependency Injection.
    // Paste your code here, but comment it out

    // From frontend listing-view.component.ts:
    /*
    constructor(
    private listingItemService: ListingItemService) {}

    getListing(): ListingItem {
    const id = this.route.snapshot.paramMap.get('id');
    this.listingItemService.getListing(id).subscribe(listing => {
      this.listing = listing;})
    */
    // Inside listing-view.component.ts info is requested from listingItemService that is given in constructor.

    //todo p3
    // Name 2 benefits of Dependency Injection
    // 1  Objects are less bound together.
    // 2  Objects with different attributes can be injected. Makes it easier to use same code for different purposes.

    //todo p4
    // Name 1 disadvantage of Dependency Injection
    // 1 Need separate methods in separate objects. Makes the code longer.
}
