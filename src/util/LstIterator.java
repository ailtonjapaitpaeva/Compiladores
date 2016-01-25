package util;

import java.util.Iterator;

public class LstIterator<E> implements Iterator<E>
{
  private LstNode<E> cursor;

  public LstIterator(LstNode<E> cursor)
  {
    this.cursor = cursor;
  }

  @Override
  public boolean hasNext()
  {
    return cursor != null;
  }

  @Override
  public E next()
  {
    final E x = cursor.head;
    cursor = cursor.tail;
    return x;
  }

  @Override
  public void remove()
  {
    throw new UnsupportedOperationException(
        "LstIterator does not support the remove operation");
  }
}
