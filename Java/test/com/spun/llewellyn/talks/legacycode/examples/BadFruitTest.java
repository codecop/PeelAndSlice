package com.spun.llewellyn.talks.legacycode.examples;

import static org.mockito.Mockito.mock;

import java.sql.Connection;

import org.junit.Test;

import com.spun.llewellyn.talks.legacycode.required.Loan;
import com.spun.llewellyn.talks.legacycode.required.Person;

public class BadFruitTest
{
  @Test
  public void shouldCreateUserOnceForThreeLoads()
  {
    /*
     * Bug #54
     * If a home , auto and personal loan
     * are taken out by Tom,
     * 3 versions of Tom appear in the database.
     */
    
    Loan home = new Loan();
    home.setPeople(new Person("Tom"));
    Loan auto = new Loan();
    auto.setPeople(new Person("Tom"));
    Loan personal = new Loan();
    personal.setPeople(new Person("Tom"));

    BadFruit badFruit = mock(BadFruit.class);
    Connection connection = mock(Connection.class);
    BadFruit.createLoans(badFruit, connection, home, auto, personal);
    
    
  }
}
