package ee.taltech.cars.c_theory.question14.blogs;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("blog")
@RestController
public class BlogsController {

    //todo for question 14 there are 4 assignments in total
    // Each person has to do only 1. So 2 person team has to do 2 different ones, 3 person - 3, 4 person - 4.
    // Make sure to commit under your user otherwise points won't count.
    // I didn't number these so you can pick your favorite

    //todo
    // You are creating a rest controller for blogs. Think blog aggregator or blog collection.
    // You need to add necessary annotations and methods to this class.
    // This class should compile.
    // It should run successfully when moved to your application package.
    // Method body is not important and will not be graded.
    // Modifying other classes is unnecessary and will not be graded.

    //todo A add necessary annotations on the class

    //todo B create a method to query blogs (plural)

    //todo C create a method to query single blog

    //todo D create a method to save a new blog

    //todo E create a method to update a bog

    //todo F create a method to delete a blog

    //todo G assuming each blog has only 1 author (one-to-one relation) create a method to query blog's author

    //todo H create a method to update blog url (and nothing else)

    //todo I-J modify correct method to support pagination, pagination is done by page and size
    //todo I add page (pagination starts at page 1)
    //todo J add size (default page size is 20)

    //todo K modify correct method to order blogs
    // * by most recent first
    // * by least recent first
    // (you can assume that by default it searches by most popular first)

    private enum OrderBy {
        ASC, DESC, POPULAR
    }

    @GetMapping
    public List<Blog> getBlogs(@RequestParam(required = false, defaultValue = "1") int page,
                              @RequestParam(required = false, defaultValue = "20") int size,
                               @RequestParam(required = false, defaultValue = "POPULAR") OrderBy orderBy) {
        return getBlogsMethod(page, size, orderBy);
    }

    @GetMapping("{url}")
    public Blog getBlog(@PathVariable String url) {
        // return service.getBlogByUrl(url);
        return new Blog();
    }

    @PostMapping
    public Blog saveBlog(@RequestBody Blog blog) {
        // return service.saveBlog(blog)
        return blog;
    }

    @PutMapping
    public Blog updateBlog(@RequestBody Blog blog) {
        // return service.updateBlog(blog);
        return blog;
    }

    @DeleteMapping("{url}")
    public void deleteBlog(@PathVariable String url) {
        // service.deleteBlog(url)
    }

    @GetMapping("{url}/author")
    public Author getAuthor(@PathVariable String url) {
        // service.getAuthor(url);
        return new Author();
    }

    @PutMapping("{url}")
    public Blog updateUrl(@PathVariable String url, @RequestBody String newUrl) {
        // service.updateUrl(url, newUrl);
        return new Blog();
    }

    public List<Blog> getBlogsMethod(int page, int size, OrderBy orderBy) {
        if (orderBy.equals(OrderBy.ASC)) {
            // order by least recent first (from db)
            return new ArrayList<>();
        } else if (orderBy.equals(OrderBy.DESC)) {
            // order by recent first (from db)
            return new ArrayList<>();
        } else {
            // order by popularity (from db)
            return new ArrayList<>();
        }
    }
}
