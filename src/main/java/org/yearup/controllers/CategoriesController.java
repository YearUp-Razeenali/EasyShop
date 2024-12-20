package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.List;

// add the annotations to make this a REST controller
// add the annotation to make this controller the endpoint for the following url
@RestController
@RequestMapping("categories")
    // http://localhost:8080/categories
// add annotation to allow cross site origin requests
@CrossOrigin
public class CategoriesController
{
    private CategoryDao categoryDao;
    private ProductDao productDao;


    // create an Autowired controller to inject the categoryDao and ProductDao
    @Autowired
    public CategoriesController(CategoryDao categoryDao, ProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    // add the appropriate annotation for a get action
    @GetMapping("")
    @PreAuthorize("permitAll()")
    public List<Category> getAll()
    {
        // find and return all categories
        try {
            // Fetch and return all categories from the database
            return categoryDao.getAllCategories();
        } catch (Exception ex) {
            // Handle errors and return a 500 INTERNAL_SERVER_ERROR response
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving categories.");
        }
    }

    // add the appropriate annotation for a get action
    @GetMapping("{id}")
    @PreAuthorize("permitAll()")
    public Category getById(@PathVariable int id) {
        Category category = categoryDao.getById(id);

        if (category == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found.");
        }

        return category;
    }


    // the url to return all products in category 1 would look like this
    // https://localhost:8080/categories/1/products
    @GetMapping("{categoryId}/products")
    @PreAuthorize("permitAll()")
    public List<Product> getProductsById(@PathVariable int categoryId)
    {
        // get a list of product by categoryId
        try {
            // Fetch and return products belonging to the specified category
            return productDao.listByCategoryId(categoryId);
        } catch (Exception ex) {
            // Handle errors and return a 500 INTERNAL_SERVER_ERROR response
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving products.");
        }
    }

    // add annotation to call this method for a POST action
    // add annotation to ensure that only an ADMIN can call this function
    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Category addCategory(@RequestBody Category category)
    {
        // insert the category
        try {
            // Insert a new category into the database and return it
            return categoryDao.create(category);
        } catch (Exception ex) {
            // Handle errors and return a 500 INTERNAL_SERVER_ERROR response
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error adding category.");
        }
    }

    // add annotation to call this method for a PUT (update) action - the url path must include the categoryId
    // add annotation to ensure that only an ADMIN can call this function
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateCategory(@PathVariable int id, @RequestBody Category category)
    {
        // update the category by id
        try {
            // Fetch the existing category by ID
            Category existing = categoryDao.getById(id);
            if (existing == null) {
                // Return a 404 NOT_FOUND response if the category does not exist
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found.");
            }
            // Update the category in the database
            categoryDao.update(id, category);
        } catch (Exception ex) {
            // Handle errors and return a 500 INTERNAL_SERVER_ERROR response
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating category.");
        }
    }


    // add annotation to call this method for a DELETE action - the url path must include the categoryId
    // add annotation to ensure that only an ADMIN can call this function
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable int id)
    {
        // delete the category by id
        try {
            // Fetch the existing category by ID
            Category existing = categoryDao.getById(id);
            if (existing == null) {
                // Return a 404 NOT_FOUND response if the category does not exist
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found.");
            }
            // Delete the category from the database
            categoryDao.delete(id);
        } catch (Exception ex) {
            // Handle errors and return a 500 INTERNAL_SERVER_ERROR response
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting category.");
        }
    }
}
