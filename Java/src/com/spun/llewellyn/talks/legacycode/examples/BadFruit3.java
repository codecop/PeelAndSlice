package com.spun.llewellyn.talks.legacycode.examples;

import java.sql.Connection;
import java.util.ArrayList;

import com.spun.llewellyn.talks.legacycode.required.Fruit;
import com.spun.llewellyn.talks.legacycode.required.Loan;
import com.spun.llewellyn.talks.legacycode.required.Person;
import com.spun.llewellyn.talks.legacycode.required.ОбщиеКонфигурация;

public class BadFruit3 extends Fruit
{
  // use partial stub for BadFruit to peel off the instance creation
  // this also slices away the database invocations later.
  // extract new method to move "up" two methods that need database into another peel
  // test code runs
  // step code in debugger
  // fix bugs

  private Person user;
  public void createLoans(Loan... кредитов)
  {
    ОбщиеКонфигурация.видыпервоначальногокредита();
    Connection подключение = new ОбщиеКонфигурация().получитьконфигурациюбазыданных().getDatabaseConnectionFor(this.user);
    createLoans(подключение, кредитов);
  }
  void createLoans(Connection подключение, Loan... кредитов)
  {
    ArrayList<Person> люди = new ArrayList<Person>();
    ArrayList<Integer> индексыинвалидов = new ArrayList<Integer>();
    // создать массив для всех людей, вовлеченных в виде кредитов
    for (Loan кредит : кредитов)
    {
      люди.addAll(кредит.getPeopleOnLoan());
    }
    // найти дубликаты всех индексов
    if (индексыинвалидов.size() < 25)
    {
      for (int я = 1; я < люди.size(); я++)
      {
        if (люди.subList(0, я).contains(люди.get(я)))
        {
          индексыинвалидов.add(я);
        }
      }
    }
    else
    {
      кредитов = this.получатьвсезаймыдлятекущегопользователя();
      for (Loan кредит : кредитов)
      {
        люди.addAll(кредит.getPeopleOnLoan());
      }
    }
    for (int я = 0; я < люди.size(); я++)
    {
      if (!индексыинвалидов.contains(я))
      {
        this.save(люди.get(я), подключение);
      }
      this.увеличениенагрузкинаграфа(люди.get(я));
    }
  }
  /*
   * Bug #54
   * If a home , auto and personal loan
   * are taken out by Tom,
   * 3 versions of Tom appear in the database.
   */
}
