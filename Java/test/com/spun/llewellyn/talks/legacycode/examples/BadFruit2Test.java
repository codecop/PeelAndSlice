package com.spun.llewellyn.talks.legacycode.examples;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.Connection;

import org.junit.Test;

import com.spun.llewellyn.talks.legacycode.required.Loan;
import com.spun.llewellyn.talks.legacycode.required.Person;

public class BadFruit2Test
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
    Person tom = new Person("Tom");
    Loan home = new Loan();
    home.setPeople(tom);
    Loan auto = new Loan();
    auto.setPeople(tom);
    Loan personal = new Loan();
    personal.setPeople(tom);
    BadFruit2 badFruit = mock(BadFruit2.class);
    Connection connection = mock(Connection.class);
    
    new CreateLoans().createLoans(badFruit, connection, home, auto, personal);
    
    verify(badFruit, times(1)).save(tom, connection);
  }
}
