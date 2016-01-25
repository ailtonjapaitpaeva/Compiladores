package ErrorMsg;

public class Source
{
  private String filename;      // name of file being compiled
  private IntList lineOffsets;  // list containing the offset of each line
  private int lineNum;          // currrent line number

  public Source(String filename)
  {
    this.filename = filename;
    lineOffsets = new IntList(-1, null);
    lineNum = 1;
  }

  public void newline(int offset)
  {
    lineNum++;
    lineOffsets = new IntList(offset, lineOffsets);
  }

  private String sayPosition(int pos)
  {
    int n = lineNum;
    IntList p = lineOffsets;
    String sayPos = "0.0";
    while (p != null)
    {
      if (p.head < pos)
      {
        sayPos = n + "." + (pos - p.head);
        break;
      }
      p = p.tail;
      n--;
    }
    return sayPos;
  }

  public String sayLoc(Loc loc)
  {
    return String.format("%s:%s-%s",
        filename,
        sayPosition(loc.left),
        sayPosition(loc.right));
  }
}

class IntList
{
  int head;
  IntList tail;

  public IntList(int head, IntList tail)
  {
    this.head = head;
    this.tail = tail;
  }
}