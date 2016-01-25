package ErrorMsg;

public class Loc
{
  public Source src;
	public int left;    // offset of the left position
	public int right;   // offset of the right position
	
	public Loc()
	{
	   this(null, -1, -1);
	}
	
	public Loc(Source src, int left, int right)
	{
	  this.src = src;
		this.left = left;
		this.right = right;
	}

	public String toString()
	{
		return src.sayLoc(this);
	}
}
