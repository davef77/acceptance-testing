package feature;

import com.cd.acceptance.dsl.CucumberDsl;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Copyright (c) Continuous Delivery Ltd. 2016
 */
public class AddBookToBasketSteps
{
    private CucumberDsl dsl = new CucumberDsl();

    @Given("^I search for books about \"([^\"]*)\"$")
    public void i_search_for_books_about(String subject) throws Throwable {
        dsl.shopping.searchForBook(subject);
    }

    @Given("^I select a book by \"([^\"]*)\"$")
    public void i_select_a_book_by(String author) throws Throwable {
        dsl.shopping.selectBook(author);
    }

    @When("^I add my selected book to my shopping-basket$")
    public void i_add_my_selected_book_to_my_shopping_basket() throws Throwable {
        dsl.shopping.addSelectedItemToShoppingBasket();
    }

    @Then("^I can see the book \"([^\"]*)\" listed in my shopping-basket$")
    public void i_can_see_the_book_listed_in_my_shopping_basket(String title) throws Throwable {
        dsl.shopping.assertItemListedInShoppingBasket(title);
    }
}
