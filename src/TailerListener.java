import org.apache.commons.io.input.TailerListenerAdapter;

public class TailerListener extends TailerListenerAdapter 
{
	public TailerListener()
	{
		
	}

	@Override
    public void handle(String line) 
    {
		System.out.println(line);
    }
}
