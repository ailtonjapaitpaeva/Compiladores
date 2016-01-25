package util;

public class Pair<A, B>
{
  public A fst;
  public B snd;

  public Pair(A fst, B snd)
  {
    this.fst = fst;
    this.snd = snd;
  }

  @Override
  public String toString()
  {
    return "Pair [fst=" + fst + ", snd=" + snd + "]";
  }
}
