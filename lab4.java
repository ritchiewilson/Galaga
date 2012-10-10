import javalib.worldimages.*;
import javalib.worldcanvas.*;
import javalib.colors.*;
import tester.*;

interface IBook{
  //consumes the number that represents 
  //today in the library date-recording system 
  //and produces the number of days this book is overdue
  int daysOverdue(int today);
  /*
   * produces a boolean value that informs us 
   * whether the book is overdue on the given day
   */
  boolean isOverdue(int today);
  /*
   * computes the fine for this book,
   * if the book is returned on the given day
   */
  int computeFine(int today);
}

abstract class ABook implements IBook{
  String title;
  int dayTaken;
  
  ABook(String title, int dayTaken){
    this.title = title;
    this.dayTaken = dayTaken;
  }
  public int daysOverdue(int today){
    return (today - this.dayTaken) - 14;
  }
  public boolean isOverdue(int today){
    return this.daysOverdue(today) > 0;
  }
  public int computeFine(int today){
    if (this.isOverdue(today))
      return this.daysOverdue(today) * 10;
    else return 0;
          
      
    
  }
}

class Book extends ABook{
  String author; 
  
  Book(String title, String author, int dayTaken){
    super(title, dayTaken);
    this.author = author;
    
  }
}

class RefBook extends ABook{
  
  RefBook(String title, int dayTaken){
    super(title, dayTaken);
  }
  //RefBook can only be borrowed for 2 days
  public int daysOverdue(int today){
    return (today-this.dayTaken) - 2;
  }
}

class AudioBook extends ABook{
  String author;
  
  AudioBook(String title, String author, int dayTaken){
    super(title, dayTaken);
    this.author = author;
  }  
  public int computeFine(int today){
    if (this.isOverdue(today))
      return this.daysOverdue(today) * 20;
    else return 0;
  }
}

class ExampleBook{
  IBook b1 = new Book("Redwall", "Brian Jacques", 3);
  IBook b2 = new RefBook("Grey's Anatomy", 5);
  IBook b3 = new AudioBook("Secret Life of Bees", "Sue Monk Kidd", 10);
  boolean testBooks(Tester t){
    return t.checkExpect(b1.daysOverdue(19), 2) &&
           t.checkExpect(b2.daysOverdue(6), -1) &&
           t.checkExpect(b3.daysOverdue(24), 0) &&
           t.checkExpect(b1.isOverdue(19), true) &&
           t.checkExpect(b2.isOverdue(6), false) &&
           t.checkExpect(b3.isOverdue(24), false) &&
           t.checkExpect(b1.computeFine(19), 20) &&
           t.checkExpect(b2.computeFine(6), 0) &&
           t.checkExpect(b3.computeFine(26), 40);
  }          
}















