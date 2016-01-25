package Translate;

public class Exp
{
	public StringBuilder code;

	public Exp(StringBuilder code)
	{
		this.code = code;
	}

	public Exp(String code)
	{
		this.code = new StringBuilder(code);
	}

  @Override
  public String toString()
  {
    return "Exp [code=" + code + "]";
  }
}
