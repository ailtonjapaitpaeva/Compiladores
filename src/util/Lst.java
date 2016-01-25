package util;

import java.util.Iterator;

class LstNode<E>
{
  final E head;
  final LstNode<E> tail;

  LstNode(E head, LstNode<E> tail)
  {
    this.head = head;
    this.tail = tail;
  }

  @Override
  public String toString()
  {
    return "LstNode [head=" + head + ", tail=" + tail + "]";
  }
}

public class Lst<E> implements Iterable<E>
{
  private LstNode<E> list;

  public Lst()
  {
    list = null;
  }

  private Lst(LstNode<E> list)
  {
    this.list = list;
  }

  public Lst(E head)
  {
    list = new LstNode<E>(head, null);
  }

  public Lst(E head, Lst<E> tail)
  {
    list = new LstNode<E>(head, tail.list);
  }

  @SafeVarargs
  public Lst(E... args)
  {
    list = null;
    for (int i = args.length - 1; i >= 0; i--)
      list = new LstNode<E>(args[i], list);
  }

  public boolean isEmpty()
  {
    return list == null;
  }

  public E head()
  {
    return list.head;
  }

  public Lst<E> tail()
  {
    return new Lst<E>(list.tail);
  }

  @Override
  public String toString()
  {
    StringBuilder builder = new StringBuilder("Lst[");
    if (list != null)
    {
      builder.append(list.head);
      for (LstNode<E> p = list.tail; p != null; p = p.tail)
        builder.append(", ").append(p.head.toString());
    }
    builder.append("]");
    return builder.toString();
  }

  @Override
  public Iterator<E> iterator()
  {
    return new LstIterator<E>(list);
  }

  public Lst<E> reverse()
  {
    LstNode<E> rev = null;
    for (LstNode<E> p = list; p != null; p = p.tail)
      rev = new LstNode<E>(p.head, rev);
    return new Lst<E>(rev);
  }

  // Tests

  private static int sum(Lst<Integer> lst)
  {
    int sum = 0;
    for (int x : lst.reverse())
      sum += x;
    return sum;
  }

  public static void main(String[] args)
  {
    Lst<Integer> li = new Lst<>(11, 12, 13, 14);

    System.out.println("The list: " + li);

    if (li.isEmpty())
      System.out.println("The list is empty");
    else
      System.out.printf("The list is not empty.\n head: %s\n tail: %s\n",
          li.head(), li.tail());

    for (Integer x : li)
      System.out.println(x);

    for (Integer x : li)
      System.out.print(x + " ");

    System.out.println("===========");

    System.out.println(sum(new Lst<Integer>()));
    Lst<Integer> list = new Lst<>(10, new Lst<>(20, new Lst<>(30,
        new Lst<Integer>())));
    System.out.println(list);
    System.out.println(sum(list));
  }
}
