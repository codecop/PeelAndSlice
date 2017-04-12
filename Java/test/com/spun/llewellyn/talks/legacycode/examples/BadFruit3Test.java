package com.spun.llewellyn.talks.legacycode.examples;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.Connection;

import org.junit.Test;

import com.spun.llewellyn.talks.legacycode.required.Loan;
import com.spun.llewellyn.talks.legacycode.required.Person;

public class BadFruit3Test
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
    Connection connection = mock(Connection.class);

    BadFruit3 badFruit = mock(BadFruit3.class);
    doCallRealMethod().when(badFruit).createLoans(connection, home, auto, personal); // duplication with call
    
    badFruit.createLoans(connection, home, auto, personal);

    verify(badFruit, times(1)).save(tom, connection);
  }
}
