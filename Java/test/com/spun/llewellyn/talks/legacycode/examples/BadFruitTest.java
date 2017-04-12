package com.spun.llewellyn.talks.legacycode.examples;

import org.junit.Ignore;
import org.junit.Test;

import com.spun.llewellyn.talks.legacycode.required.Loan;
import com.spun.llewellyn.talks.legacycode.required.Person;

public class BadFruitTest
{
  @Test @Ignore
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
    
    BadFruit badFruit = new BadFruit();
    
    badFruit.createLoans(home, auto, personal);
    
  }
}
