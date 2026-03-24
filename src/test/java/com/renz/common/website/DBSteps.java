package com.renz.common.website;

import com.renz.utils.DBUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBSteps {
    @Given("I insert user {string} with email {string} into the DB")
    public void createUser(String name, String email) throws SQLException {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        // PreparedStatement prevents SQL Injection (Security Best Practice)
        PreparedStatement pstmt = DBUtils.getConnection().prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setString(2, email);
        pstmt.executeUpdate(); // executeUpdate is for Insert, Update, Delete
    }

    @Then("the DB should contain user {string}")
    public void verifyUser(String name) throws SQLException {
        String sql = "SELECT * FROM users WHERE name = ?";
        PreparedStatement pstmt = DBUtils.getConnection().prepareStatement(sql);
        pstmt.setString(1, name);
        ResultSet rs = pstmt.executeQuery(); // executeQuery is for Select

        // rs.next() returns true if a record was found
        Assert.assertTrue(rs.next(), "User not found in Database!");
    }

    @When("I update the email to {string} for user {string}")
    public void updateUser(String newEmail, String name) throws SQLException {
        String sql = "UPDATE users SET email = ? WHERE name = ?";
        PreparedStatement pstmt = DBUtils.getConnection().prepareStatement(sql);
        pstmt.setString(1, newEmail);
        pstmt.setString(2, name);
        pstmt.executeUpdate();
    }

    @And("I delete user {string} from the DB")
    public void deleteUser(String name) throws SQLException {
        String sql = "DELETE FROM users WHERE name = ?";
        PreparedStatement pstmt = DBUtils.getConnection().prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.executeUpdate();
    }
}
